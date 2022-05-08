### 一、string的实现
- 1.6之前
    + char[]，16位，两个字节
    + offset
    + count
    + hash
- 1.7~1.8
    + char[]
    + hash
- 1.9
    + byte[]，8位，一个字节
    + order
        - 在计算字符串长度或者使用 indexOf（）函数时，我们需要根据这个字段，判断如何计算字符串长度
        - 默认两个值
            + 0：单字节编码，Latin-1
            + 1：UTF-16
    + hash
### 二、string对象的不可变性
- String 类被 final 关键字修饰了，而且变量 char 数组也被 final 修饰了
- String str=“abc”做了什么
    + 当代码中使用第一种方式创建字符串对象时，JVM 首先会检查该对象是否在字符串常量池中，
      如果在，就返回该对象引用，否则新的字符串将在常量池中被创建。这种方式可以减少同一个值的字符串对象的重复创建，节约内存
- String str = new String(“abc”)做了什么
    + 首先在编译类文件时，"abc"常量字符串将会放入到常量结构中
    + 在类加载时，“abc"将会在常量池中创建
    + 在调用 new 时，JVM 命令将会调用 String 的构造函数，
      同时引用常量池中的"abc” 字符串，在堆内存中创建一个 String 对象
    + 最后，str 将引用 String 对象
### 三、jvm对string的优化
- 代码中使用+号拼接字符串时，jvm会优化成创建一个string对象，而不是多个
- 当涉及到多个字符串拼接时，jvm倾向于使用stringBuilder实现拼接，最终返回string对象
  所以在实际编码中建议使用stringBuilder，多线程环境中使用stringBuffer
- intern方法的使用，在调用intern方法之后，会去常量池【1.7之后已经由方法区迁移到堆中】中查找是否有等于该字符串对象的引用，有就返回引用。
### todo、split方法引出的正则表达式的使用
### ab工具使用
- Linux：yum-y install httpd-tools
- post接口测试
    + ab -n 100  -c 10 -p 'post.txt' -T 'application/x-www-form-urlencoded' 'http://test.api.com/test/register'
        - -n：总请求次数（最小默认为 1）；
        - -c：并发次数（最小默认为 1 且不能大于总请求次数，例如：10 个请求，10 个并发，实际就是 1 人请求 1 次）；
        - -p：post 参数文档路径（-p 和 -T 参数要配合使用）；
            + post.txt 为存放 post 参数的文档，存储格式如下:usernanme=test&password=test&sex=1
        - -T：header 头内容类型（此处切记是大写英文字母 T）
- get接口测试：可以直接在链接的后面带上请求的参数
    + ab -c 10 -n 100 http://www.test.api.com/test/login?userName=test&password=test
- 输出指标解释
    + Requests per second：吞吐率，指某个并发用户数下单位时间内处理的请求数；
    + Time per request：上面的是用户平均请求等待时间，指处理完成所有请求数所花费的时间 /（总请求数 / 并发用户数）；
    + Time per request：下面的是服务器平均请求处理时间，指处理完成所有请求数所花费的时间 / 总请求数；
    + Percentage of the requests served within a certain time：每秒请求时间分布情况，指在整个请求中，每个请求的时间长度的分布情况
        例如有 50% 的请求响应在 8ms 内，66% 的请求响应在 10ms 内，说明有 16% 的请求在 8ms~10ms 之间
    