package java01.basic.algorithm.basic.sort;

import java.security.InvalidParameterException;
import java.util.Arrays;

/**
 * 快速排序
 * @author yudazhi
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] data1 = IntUtils.buildIntArray(25);
        System.out.println(queryNthNumber(data1, 9));
        System.out.println(queryNthNumber(data1, 10));
        quickSort(data1, 0, data1.length - 1);
        System.out.println(Arrays.toString(data1));
    }

    /**
     * 寻找第k大元素
     * @param data 原始数据
     * @param k 第k个大小的元素
     * @return 第k大元素的值
     */
    private static int queryNthNumber(int[] data, int k) {
        if (k < 1) {
            throw new InvalidParameterException("error param of nth");
        }
        //  k-1 aka：first index is zero
        return queryNthNumber(data, 0, data.length - 1, k - 1);
    }

    private static int queryNthNumber(int[] data, int left, int right, int k) {
        if (left >= right) {
            return data[left];
        }
        int q = pivot(data, left, right);
        if (q == k) {
            return data[q];
        } else if (q > k) {
            return queryNthNumber(data, left, q - 1, k);
        } else {
            return queryNthNumber(data, q + 1, right, k);
        }
    }

    private static void quickSort() {
        int[] data = IntUtils.buildIntArray(25);
        System.out.println(Arrays.toString(data));
        quickSort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));
    }

    private static void quickSort(int[] data, int p, int r) {
        if (p >= r) {
            return;
        }
        int pivot = pivot(data, p, r);
        quickSort(data, p, pivot - 1);
        quickSort(data, pivot + 1, r);
    }

    /**
     * import
     * @param data 原数组
     * @param p 左起点
     * @param r 右终点
     * @return 最后一个起点在目标中的位置，并将数组进行分类：位置前的值都小于当前值，位置后的值都大于当前值
     */
    private static int pivot(int[] data, int p, int r) {
        //  待比较的值
        int compareVal = data[r];
        //  首次定位值
        int i = p;
        for (int j = p; j < r; j++) {
            if (data[j] < compareVal) {
                IntUtils.swap(data, i, j);
                //  每次交换后需要后移一个位置
                i = i + 1;
            }
        }
        //  将比较值放置在确定的位置，位置前的值都小于当前值，位置后的值都大于当前值
        IntUtils.swap(data, i, r);
        return i;
    }
}
