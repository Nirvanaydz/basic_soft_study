package java01.basic.algorithm.basic.sort;

/**
 * 桶排序简化版，在知道固定数值时进行排序，在一定范围（非负整数）存在大量的重复数据
 * @author yudazhi
 */
public class CountSort {
    public static void main(String[] args) {
        int[] temp = IntUtils.buildIntArray(25);
        countSort(temp);
    }

    private static void countSort(int[] temp) {
        if (temp.length <= 1) {
            return;
        }
        //  find max
        int max = temp[0];
        for (int i = 1; i < temp.length; i++) {
            if (max < temp[i]) {
                max = temp[i];
            }
        }
        //  build count array
        int[] count = new int[max + 1];
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }
        //  count array
        for (int i = 0; i < temp.length; i++) {
            count[temp[i]]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }
        //  set result
        int[] result = new int[temp.length];
        for (int i = 0; i < result.length; i++) {
            //  find idx
            int idx = count[temp[i]] - 1;
            //  set value
            result[idx] = temp[i];
            //  remove exist value
            count[temp[i]]--;
        }
        //  copy
        for (int i = 0; i < result.length; i++) {
            temp[i] = result[i];
        }
    }
}
