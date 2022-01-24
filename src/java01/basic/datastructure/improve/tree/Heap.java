package java01.basic.datastructure.improve.tree;

import java01.basic.algorithm.basic.sort.IntUtils;

/**
 * @author yudazhi
 */
public class Heap {
    /** 从下标1开始的数组 */
    private int[] a;
    /** 数组的容量 */
    private int n;
    /** 数组中已存储的数量 */
    private int count;

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    public void insert(int data) {
        //  ensureCapacity
        if (count >= n) {
            return;
        }
        count ++;
        a[count] = data;
        int i = count;
        //  实现大顶堆
        while (i / 2 > 0 && a[i] > a[i / 2]) {
            IntUtils.swap(a, i, i / 2);
            i = i / 2;
        }
    }

    public void removeMax() {
        if (count == 0) {
            return;
        }
        a[1] = a[count];
        count--;
        heapify(a, count, 1);
    }

    private void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            //  当前节点有左节点且左节点值大于当前节点，认为最大值为左节点
            if (i * 2 <= n && a[i] < a[i * 2]) {
                maxPos = i * 2;
            }
            //  左节点和当前节点的较大值和存在的右节点比较，存在的右节点比较大，认为最大值为右节点
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            //  未发生变化，不需要继续向下堆化
            if (maxPos == i) {
                break;
            }
            //  堆化当前节点
            IntUtils.swap(a, i, maxPos);
            //  继续堆化后续节点
            i = maxPos;
        }
    }

    /**
     * 全堆化
     * @param a
     * @param n
     */
    public void buildHeap(int[] a, int n) {
        //  i = n/2,堆化所有的有子节点的数据
        for (int i = n/2; i >= 1; i++) {
            heapify(a, n, i);
        }
    }
}
