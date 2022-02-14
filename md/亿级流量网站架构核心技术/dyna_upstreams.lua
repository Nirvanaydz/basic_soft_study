local http = require("socket.http")
local ltnl2 = require("ltnl2")
local cjson = require "cjson"
local function update_upstreams()
    local resp = {}
    http.request{
        url = "http://192.168.61.129:8500/v1/catalog/service/item_jd_tomcat",
        sink = ltnl2.sink.table(resp)
    }
    resp = table.concat(resp)
    resp = cjson.decode(resp)

    local upstreams = {{ip="127.0.0.1", port=1111}}
    for i, v in ipairs(resp) do
        upstreams[i+1] = {ip=v.Address, port=v.ServicePort}
    end
    ngx.shared.upstream_list:set("item_jd_tomcat", cjson.encode (upstreams))
end

local function get_upstreams()
    local upstreams_str = ngx.shared.upstream_list:get("item_jd_tomcat")
end

local _M = {
    update_upstreams = update_upstreams,
    get_upstreams = get_upstreams
}