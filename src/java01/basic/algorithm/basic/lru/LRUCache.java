package java01.basic.algorithm.basic.lru;

import java.util.HashMap;


public class LRUCache<K, V> {

    private int currentCacheSize;
    private int CacheCapcity;
    private HashMap<K, CacheNode> caches;
    private CacheNode first;
    private CacheNode last;

    public LRUCache(int size) {
        currentCacheSize = 0;
        this.CacheCapcity = size;
        caches = new HashMap<K, CacheNode>(size);
    }

    public void put(K k, V v) {
        CacheNode node = caches.get(k);
        if (node == null) {
            if (caches.size() >= CacheCapcity) {

                caches.remove(last.key);
                removeLast();
            }
            node = new CacheNode();
            node.key = k;
        }
        node.value = v;
        moveToFirst(node);
        caches.put(k, node);
    }

    public Object get(K k) {
        CacheNode node = caches.get(k);
        if (node == null) {
            return null;
        }
        moveToFirst(node);
        return node.value;
    }

    public Object remove(K k) {
        CacheNode node = caches.get(k);
        if (node != null) {
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            if (node == first) {
                first = node.next;
            }
            if (node == last) {
                last = node.prev;
            }
        }

        return caches.remove(k);
    }

    public void clear() {
        first = null;
        last = null;
        caches.clear();
    }


    private void moveToFirst(CacheNode node) {
        if (first == node) {
            return;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node == last) {
            last = last.prev;
        }
        if (first == null || last == null) {
            first = last = node;
            return;
        }

        node.next = first;
        first.prev = node;
        first = node;
        first.prev = null;

    }

    private void removeLast() {
        if (last != null) {
            last = last.prev;
            if (last == null) {
                first = null;
            } else {
                last.next = null;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        CacheNode node = first;
        while (node != null) {
            sb.append(String.format("%s:%s ", node.key, node.value));
            node = node.next;
        }

        return sb.toString();
    }

    class CacheNode {
        CacheNode prev;
        CacheNode next;
        Object key;
        Object value;

        public CacheNode() {
        }
    }

    public static void main(String[] args) {

        LRUCache<Integer, String> lru = new LRUCache<Integer, String>(3);

        lru.put(1, "a");    // 1:a
        System.out.println(lru.toString());
        lru.put(2, "b");    // 2:b 1:a
        System.out.println(lru.toString());
        lru.put(3, "c");    // 3:c 2:b 1:a
        System.out.println(lru.toString());
        lru.put(4, "d");    // 4:d 3:c 2:b
        System.out.println(lru.toString());
        lru.put(1, "aa");   // 1:aa 4:d 3:c
        System.out.println(lru.toString());
        lru.put(2, "bb");   // 2:bb 1:aa 4:d
        System.out.println(lru.toString());
        lru.put(5, "e");    // 5:e 2:bb 1:aa
        System.out.println(lru.toString());
        lru.get(1);         // 1:aa 5:e 2:bb
        System.out.println(lru.toString());
        lru.remove(11);     // 1:aa 5:e 2:bb
        System.out.println(lru.toString());
        lru.remove(1);      //5:e 2:bb
        System.out.println(lru.toString());
        lru.put(1, "aaa");  //1:aaa 5:e 2:bb
        System.out.println(lru.toString());
    }

}

