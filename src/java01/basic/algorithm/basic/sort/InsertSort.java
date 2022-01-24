package java01.basic.algorithm.basic.sort;

import java.util.Arrays;

/**
 * 用待插入的值和当前值比较
 * 找到当前值在要插入数组中的索引位置，将此索引位置处的其他值都向后移动一位，然后插入数据
 * 未发生移动时a[j+1]=data[ready_to_insert]
 * @author yudazhi
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] r1 = IntUtils.buildIntArray(10);
        IntUtils.insertSort(r1, true);
        System.out.println("desc:" + Arrays.toString(r1));
        int[] r2 = IntUtils.buildIntArray(10);
        IntUtils.insertSort(r2, false);
        System.out.println("asc:" + Arrays.toString(r2));
    }
}
