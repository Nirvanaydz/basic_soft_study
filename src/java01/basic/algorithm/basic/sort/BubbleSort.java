package java01.basic.algorithm.basic.sort;

import java.util.Arrays;

/**
 * @author yudazhi
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] r = IntUtils.buildIntArray(10);
        IntUtils.bubbleSort(r, true);
        System.out.println("desc:" + Arrays.toString(r));
        IntUtils.bubbleSort(r, false);
        System.out.println("asc:" + Arrays.toString(r));

    }
}
