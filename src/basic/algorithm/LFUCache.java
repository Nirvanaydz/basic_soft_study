package basic.algorithm;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @author Administrator
 */
public class LFUCache {
    private HashMap<Integer, Integer> key2Value;
    private HashMap<Integer, Integer> key2Freq;
    private HashMap<Integer, LinkedHashSet<Integer>> freq2Keys;
    private int minFreq;
    private int capacity;

    public LFUCache(int capacity) {
        key2Value = new HashMap<>();
        key2Freq = new HashMap<>();
        freq2Keys = new HashMap<>();
        this.capacity = capacity;
        this.minFreq = 0;
    }

    public int get(int key) {
        if (!key2Value.containsKey(key)) {
            return -1;
        }
        // 增加 key 对应的 freq
        increaseFreq(key);
        return key2Value.get(key);
    }

    public void put(int key, int val) {
        if (this.capacity <= 0) {
            return;
        }

        /* 若 key 已存在，修改对应的 val 即可 */
        if (key2Value.containsKey(key)) {
            key2Value.put(key, val);
            // key 对应的 freq 加一
            increaseFreq(key);
            return;
        }

        /* key 不存在，需要插入 */
        /* 容量已满的话需要淘汰一个 freq 最小的 key */
        if (this.capacity <= key2Value.size()) {
            removeMinFreqKey();
        }

        /* 插入 key 和 val，对应的 freq 为 1 */
        // 插入 KV 表
        key2Value.put(key, val);
        // 插入 KF 表
        key2Freq.put(key, 1);
        // 插入 FK 表
        freq2Keys.putIfAbsent(1, new LinkedHashSet<>());
        freq2Keys.get(1).add(key);
        // 插入新 key 后最小的 freq 肯定是 1
        this.minFreq = 1;
    }

    private void removeMinFreqKey() {
        // freq 最小的 key 列表
        LinkedHashSet<Integer> keyList = freq2Keys.get(this.minFreq);
        // 其中最先被插入的那个 key 就是该被淘汰的 key
        int deletedKey = keyList.iterator().next();
        /* 更新 FK 表 */
        keyList.remove(deletedKey);
        if (keyList.isEmpty()) {
            freq2Keys.remove(this.minFreq);
            // 问：这里需要更新 minFreq 的值吗？
        }
        /* 更新 KV 表 */
        key2Value.remove(deletedKey);
        /* 更新 KF 表 */
        key2Freq.remove(deletedKey);
    }

    private void increaseFreq(int key) {
        int freq = key2Freq.get(key);
        /* 更新 KF 表 */
        key2Freq.put(key, freq + 1);
        /* 更新 FK 表 */
        // 将 key 从 freq 对应的列表中删除
        freq2Keys.get(freq).remove(key);
        // 将 key 加入 freq + 1 对应的列表中
        freq2Keys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freq2Keys.get(freq + 1).add(key);
        // 如果 freq 对应的列表空了，移除这个 freq
        if (freq2Keys.get(freq).isEmpty()) {
            freq2Keys.remove(freq);
            // 如果这个 freq 恰好是 minFreq，更新 minFreq
            if (freq == this.minFreq) {
                this.minFreq++;
            }
        }
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(4);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        lfuCache.put(3, 3);
        lfuCache.put(4, 4);
        for (Map.Entry<Integer, Integer> val : lfuCache.key2Value.entrySet()) {
            System.out.println(val.getKey() + "---" + val.getValue());
        }
        System.out.println("after val");
        lfuCache.get(4);
        lfuCache.put(5, 5);
        for (Map.Entry<Integer, Integer> val : lfuCache.key2Value.entrySet()) {
            System.out.println(val.getKey() + "---" + val.getValue());
        }
    }

}
