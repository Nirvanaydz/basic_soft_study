package basic.algorithm;

import java.util.*;

/**
 * 双指针解题思路
 *      使用相反方向的双指针：一般用来求解排序数组中的两个数字之和
 *      使用相同方向的双指针：求救子数组的和和乘积，p1，p2，首先移动p2增加元素，乘积或者和增加，若大于指定值，减少元素，向右移动p1
 * @author yudazhi
 */
public class Chapter2Array {

    /**
     * 获取排序数组中的第一个不等于当前值的索引
     *
     * @param nums
     * @return
     */
    private void twoSum(int[] nums, int idx, List<List<Integer>> result) {
        int i = idx + 1, j = nums.length - 1;
        while (i < j) {
            int tempI = nums[i];
            int tempJ = nums[j];
            if (nums[idx] + nums[i] + nums[j] < 0) {
                while (i < j && tempI == nums[i]) {
                    ++ i;
                }
            } else if (nums[idx] + nums[i] + nums[j] > 0) {
                while (i < j && tempJ == nums[j]) {
                    -- j;
                }
            } else {
                result.add(new ArrayList<>(Arrays.asList(nums[idx], nums[i], nums[j])));
                while (i < j && tempI == nums[i]) {
                    ++ i;
                }
                while (i < j && tempJ == nums[j]) {
                    -- j;
                }
            }
        }
    }

    /**
     * 查询给定数组中三数之和为0的数组，元素不重复
     * new int[]{-1, 0, 1, 2, -1, -4, 0, 2, -4, -2, 0, 4, 0, 3, -5, 4, 4}
     * [-5, 1, 4], [-5, 2, 3], [-4, 0, 4], [-4, 1, 3], [-4, 2, 2], [-2, -1, 3], [-2, 0, 2], [-1, -1, 2], [-1, 0, 1], [0, 0, 0]
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length >= 3) {
            Arrays.sort(nums);
            System.out.println(Arrays.toString(nums));
            int i = 0;
            while (i < nums.length - 2) {
                twoSum(nums, i, result);
                int temp = nums[i];
                while (i < nums.length && nums[i] == temp) {
                    ++ i;
                }
            }
        }
        return result;
    }

    /**
     * 寻找大于或者等于k的最短子数组的长度
     * @param target
     * @param nums
     * @return
     */
    public int minSumSubArrayLen(int target, int[] nums) {
        int left = 0;
        int minLength = Integer.MAX_VALUE;
        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (left <= right && sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left ++];
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /**
     * 统计乘积小于给定值的子数组的个数，并打印出子数组
     * 判断连续子数组的不重复性是关键
     * @param target
     * @param nums
     * @param result
     * @return
     */
    public int countTimesSubArray(int target, int[] nums, List<List<Integer>> result) {
        long product = 1;
        int count = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            while (left <= right && product >= target) {
                product /= nums[left ++];
            }
            if (right >= left) {
                //  以最右侧为边界，组成多个子数组 e.g: [1,2,5,6]与6组成的子数组个数为4，0-3，1-3，2-3，3-3
                count += right - left + 1;
                builder(result, right, left, nums);
            }
        }
        return count;
    }

    private void builder(List<List<Integer>> result, int right, int left, int[] nums) {
        List<Integer> list = new ArrayList<>();
        while (left <= right) {
            list.add(nums[right]);
            List<Integer> temp = new ArrayList<>(list);
            result.add(temp);
            right --;
        }
    }

    /**
     * 和为k的子数组的个数
     * @param nums
     * @param target
     * @return
     */
    public int subArrayCount(int[] nums, int target) {
        int count = 0, sum = 0;
        Map<Integer, Integer> sumToCount = new HashMap<>();
        sumToCount.put(0, 1);
        for (int num : nums) {
            sum += num;
            //  存在先前已经存入的sum-target的统计值，则在sum-target后的索引为值当前索引位组成的连续子数组就是
            count += sumToCount.getOrDefault(sum - target, 0);
            sumToCount.put(sum, sumToCount.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public int findMaxLength(int[] nums) {
        //  key = 首次出现的和为m的值， value = 首次出现的索引位置
        //  在[0,value]这个区间中的和为m，下一次在索引j出现和为m的值时，代表在区间[value+1, j]之间的连续子数组的累加和为0
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        //  初始值，当前[0, value]之间的累加和正好为0时，代表数组的长度为value-0+1==value-(-1)
        sumToIndex.put(0, -1);
        int sum = 0, maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            if (sumToIndex.containsKey(sum)) {
                //  移出现的和首次出现的比较获取长度，注意0，-1的初始化
                maxLength = Math.max(maxLength, i - sumToIndex.get(sum));
            } else {
                //  首次出现放置在数组中
                sumToIndex.put(sum, i);
            }
        }
        return maxLength;
    }

    /**
     * 存在一个数组，找到一个下标索引，在次位置左侧的和等于右侧的和，不包含此索引
     * @param nums
     * @return
     */
    public int pivot(int[] nums) {
        int total = 0;
        for (int i : nums) {
            total += i;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            //  前i项之和
            sum += nums[i];
            //  前i-1项和i+1之后的项和相等，返回索引值
            if (sum - nums[i] == total - sum) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Chapter2Array c = new Chapter2Array();
        int[] nums = IntUtils.buildIntArray(20);
        System.out.println(Arrays.toString(nums));
        System.out.println(c.subArrayCount(nums, 32));
    }
}
