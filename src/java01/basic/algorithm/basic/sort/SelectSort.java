package java01.basic.algorithm.basic.sort;

import java.util.Arrays;

/**
 * 选择未排序空间的最小值和未排序空间的首位交换
 * 交换后未排序空间减少长度1，排序空间增加长度1
 * @author yudazhi
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] r = IntUtils.buildIntArray(10);
        IntUtils.selectSort(r, true);
        System.out.println("desc:" + Arrays.toString(r));
        IntUtils.selectSort(r, false);
        System.out.println("asc:" + Arrays.toString(r));
    }
}
