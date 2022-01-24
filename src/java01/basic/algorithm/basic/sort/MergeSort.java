package java01.basic.algorithm.basic.sort;

import java.util.Arrays;

/**
 * @author yudazhi
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] data = IntUtils.buildIntArray(25);
        System.out.println(Arrays.toString(data));
        IntUtils.mergeSort(data);
        System.out.println(Arrays.toString(data));
    }
}
