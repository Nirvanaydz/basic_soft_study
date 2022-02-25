package java01.basic.algorithm.basic.sort;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author yudazhi
 */
public class IntUtils {

    /**
     * 随机创建指定长度大小的数组
     *
     * @param length 指定的长度
     * @return 随机数字数组
     */
    public static int[] buildIntArray(int length) {
        if (length <= 0) {
            throw new RuntimeException("error length:length of array must more than zero!");
        }
        int[] array = new int[length];
        Random r = new Random();
        IntStream.range(0, length).forEach(i -> array[i] = r.nextInt(25));
        return array;
    }

    /**
     * 交换数组中指定下标的数据位置
     *
     * @param nums 指定的数组
     * @param j    需要交换的下标j
     * @param i    需要交换的下标i
     */
    public static void swap(int[] nums, int j, int i) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    /**
     * 插入排序
     *
     * @param nums 原数组
     * @param desc 是否正序排序，true代表由小到大排序，false代表由大到小排序
     */
    public static void insertSort(int[] nums, boolean desc) {
        //  i=1:默认第一个数就是已经排好序的
        for (int i = 1; i < nums.length; i++) {
            int readyToInsertVal = nums[i];
            int sortedLength = i - 1;
            for (; sortedLength >= 0; sortedLength--) {
                if (desc) {
                    if (nums[sortedLength] > readyToInsertVal) {
                        nums[sortedLength + 1] = nums[sortedLength];
                    } else {
                        break;
                    }
                } else {
                    if (nums[sortedLength] < readyToInsertVal) {
                        nums[sortedLength + 1] = nums[sortedLength];
                    } else {
                        break;
                    }
                }
            }
            nums[sortedLength + 1] = readyToInsertVal;
        }
    }

    /**
     * 选择排序
     *
     * @param nums 原数组
     * @param desc 是否正序排序，true代表由小到大排序，false代表由大到小排序
     */
    public static void selectSort(int[] nums, boolean desc) {
        for (int i = 0; i < nums.length - 1; i++) {
            int minIdx = i;
            int minVal = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (desc) {
                    if (minVal > nums[j]) {
                        minIdx = j;
                        minVal = nums[j];
                    }
                } else {
                    if (minVal < nums[j]) {
                        minIdx = j;
                        minVal = nums[j];
                    }
                }

            }
            swap(nums, i, minIdx);
        }
    }

    /**
     * 冒泡排序
     *
     * @param nums 原数组
     * @param desc 是否正序排序，true代表由小到大排序，false代表由大到小排序
     */
    public static void bubbleSort(int[] nums, boolean desc) {
        //  比较当前值和后面值的大小，符合就交换
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (desc) {
                    //  由小到大：当前值较大时交换
                    if (nums[j] > nums[j + 1]) {
                        swap(nums, j, j + 1);
                    }
                } else {
                    //  由大到小：当前值较小时交换
                    if (nums[j] < nums[j + 1]) {
                        swap(nums, j, j + 1);
                    }
                }
            }
        }
    }


    /**
     * 归并排序
     *
     * @param data 原数组
     */
    public static void mergeSort(int[] data) {
        mergeSort(data, 0, data.length - 1);
    }

    /**
     * 归并排序递归表达式
     *
     * @param data  原数组
     * @param left  左边界
     * @param right 右边界
     */
    private static void mergeSort(int[] data, int left, int right) {
        if (left >= right) {
            return;
        }
        //  找到中间点
        int q = (left + right) / 2;
        //  左边分治
        mergeSort(data, left, q);
        //  右边分治
        mergeSort(data, q + 1, right);
        merge(data, left, q, right);
    }

    /**
     * 合并两个有序数组
     *
     * @param data  原数组
     * @param left  指定左边界
     * @param q     中间点
     * @param right 指定右边界
     */
    private static void merge(int[] data, int left, int q, int right) {
        int[] temp = new int[right - left + 1];
        int k = 0;
        int i = left;
        int j = q + 1;
        //  此处是由小到大的正序排序
        while (i <= q && j <= right) {
            if (data[i] < data[j]) {
                temp[k++] = data[i++];
            } else {
                temp[k++] = data[j++];
            }
        }
        //  判断剩余的数量，默认左边剩余
        int remainStart = i;
        int remainEnd = q;
        //  判断右边是否剩余
        if (j <= right) {
            remainStart = j;
            remainEnd = right;
        }
        //  将剩余的数据插入到临时数组中
        while (remainStart <= remainEnd) {
            temp[k++] = data[remainStart++];
        }
//        for (int l = 0; l < temp.length; l++) {
//            data[left + l] = temp[l];
//        }
        if (temp.length >= 0) {
            System.arraycopy(temp, 0, data, left, temp.length);
        }
    }

    /**
     * 计数排序
     *
     * @param temp 临时数组
     */
    public static void countSort(int[] temp) {
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
