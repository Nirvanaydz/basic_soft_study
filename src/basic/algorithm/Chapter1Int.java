package basic.algorithm;

/**
 * 详见Integer的源码！
 *
 * @author yudazhi
 */
public class Chapter1Int {
    /**
     * 01
     * 加减法完成除法核心[使用负数的除法]：绝对值较大时，负数越小
     *
     * @param dividend 被除数
     * @param divisor  除数/因子
     * @return 整除结果 -9 / -2 = 4
     */
    private int negativeDivideCore(int dividend, int divisor) {
        int result = 0;
        //  负数的比较，较大的值的绝对值较小
        while (dividend <= divisor) {
            //  每次循环都从分子开始
            int value = divisor;
            //  初始化每次的结果
            int quotient = 1;
            //  负数的比较，较大的值的绝对值较小，保证value不越界
            while (dividend <= value + value) {
                //  若分子
                quotient += quotient;
                //  value增加为原来的两倍
                value += value;
            }
            result += quotient;
            //  减去value，相当于减小被除数的绝对值
            dividend -= value;
        }
        return result;
    }

    /**
     * 写出两个正整数的除法
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return 整除的结果   9 / 2 = 4
     */
    private int positiveDivideCore(int dividend, int divisor) {
        int result = 0;
        while (divisor <= dividend) {
            //  除数始终从最初开始累加
            int value = divisor;
            //  若被除数大于除数，则结果必然初始化为1
            int quotient = 1;
            //  当被除数大于除数的两倍时，结果累加一次，除数也累加一次
            while (dividend >= value + value) {
                quotient += quotient;
                value += value;
            }
            //  累加结果
            result += quotient;
            //  将被除数减去已经累加的值
            dividend -= value;
        }
        return result;
    }

    /**
     * 负数用十六进制表示，首先应该将其表示成二进制形式，然后变反码，再变补码
     * 2 ——>  原码 0000 0010 ——> 反码 1111 1101  （原码按位反转） ——>  补码 1111 1110（反码+1）
     * -2^31  次方转化为16进制 2^31次方取补码
     * 1000 0000 0000 0000 0000 0000 0000 0000 = 2^31
     * 0111 1111 1111 1111 1111 1111 1111 1111 = 2^31 的 反码
     * 1000 0000 0000 0000 0000 0000 0000 0000 = 2^31 的 补码(反码+1)
     * 8    0    0    0    0    0    0    0    = 转换成16进制 0x80000000
     * -2^30  次方转化为16进制 2^30次方取补码
     * 0100 0000 0000 0000 0000 0000 0000 0000 = 2^31
     * 1011 1111 1111 1111 1111 1111 1111 1111 = 2^31 的 反码
     * 1100 0000 0000 0000 0000 0000 0000 0000 = 2^31 的 补码(反码+1)
     * c(12)0    0    0    0    0    0    0    = 转换成16进制 0xc0000000
     * 2^31-1
     * 0111 1111 1111 1111 1111 1111 1111 1111 = 2^31-1
     * 7    f    f    f    f    f    f    f    = 转换为16进制 0x7fffffff
     * <p>
     * 二进制算法
     * 与 或 非
     * 异或 左移右移
     * 对i进行多次i&(i-1)操作，直至i==0时结束，可以计算出i有多少位的1
     */
    private void printBinaryCount() {
        //  8进制表示法
        System.out.println(0010);
        //  16进制表示法
        System.out.println(0x0010);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }

    /**
     * 02
     * binary add
     * think-->get numeric-char val:
     * compare to '0'
     * Character.getNumericValue(char c)
     *
     * @param a 101101
     * @param b 101
     * @return 110010
     */
    private String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
//            int l = i >= 0 ? Character.getNumericValue(a.charAt(i)) : 0;
            int l = i >= 0 ? a.charAt(i) - '0' : 0;
//            int r = j >= 0 ? Character.getNumericValue(b.charAt(j)) : 0;
            int r = j >= 0 ? b.charAt(j) - '0' : 0;
            carry = carry + l + r;
            result.append(carry % 2);
            carry = carry / 2;
            i--;
            j--;
        }
        if (carry == 1) {
            result.append('1');
        }
        return result.reverse().toString();
    }

    /**
     * 计算一个数的1的个数
     * i = i & （i - 1) 当 (i != 0) 时继续循环
     */
    private int[] countBits(int nums) {
        int[] result = new int[nums + 1];
        // FIXME: 2022/5/9 注意起始的位置
        for (int i = 0; i <= nums; i++) {
            int j = i;
            while (j != 0) {
                result[i]++;
                j = j & (j - 1);
            }
        }
        return result;
    }

    /**
     * 通过上述while循环发现 j比 j&j-1 多一个1，优化代码
     *
     * @param nums 8
     * @return [1, 2, 2, 3, 2, 3, 3, 4, 2]
     */
    private int[] countBits2(int nums) {
        int[] result = new int[nums + 1];
        // FIXME: 2022/5/9 注意起始的位置
        for (int i = 1; i <= nums; i++) {
            result[i] = result[i & (i - 1)] + 1;
        }
        return result;
    }

    /**
     * 观察发现i是偶数时，最后一位数字为0，右移一位1的个数不变
     * 观察发现i是奇数时，最后一位数字为1，右移一位1的个数减少一个
     * f(x) = f(x >> 1) + (x & 1)
     *
     * @param nums 8
     * @return [1, 2, 2, 3, 2, 3, 3, 4, 2]
     */
    private int[] countBits3(int nums) {
        int[] result = new int[nums + 1];
        // FIXME: 2022/5/9 注意起始的位置
        for (int i = 1; i <= nums; i++) {
            result[i] = result[i >> 1] + (i & 1);
        }
        return result;
    }

    /**
     * 判断奇偶性
     * i & 1 == 0 偶数
     *
     * @param i 9       8
     * @return false   true
     */
    private boolean isEven(int i) {
        return (i & 1) == 0;
    }

    /**
     * 只出现一次的数字
     * thinking --> 统计一个数字的二进制位的方式：
     * step1：开一个长度位32的数组
     * step2：一次向右无符号位移31至0次，每次和&1取值就是取当前位的二进制值
     * 循环32次后结束，bit[0]代表第一位，bit[1]代表第二位，依次类推
     * 多个数的累加后的和统计值：将每一个bit位相加但不[进1]
     * <p>
     * 若出现此种场景：多个数字出现n次，仅有一个数字出现m次，统计这个数字
     * 思路：将每一个数字的bit位相加，得到统计数组
     * 再将统计数组中的每一个值和n取余数，进行统计，若出现了m次，将数组统计加之后整除m就是需要寻找的数字
     *
     * @param nums new int[]{1, 2, 1, 2, 1, 2, 100}
     * @return 100
     */
    private int singleNumber(int[] nums, int n, int m) {
        int[] bitNums = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                bitNums[i] += (num >> (31 - i)) & 1;
            }
        }
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) + bitNums[i] % n;
        }
        return result / m;
    }


    public static void main(String[] args) {
        System.out.println(new Chapter1Int().singleNumber(new int[]{1, 2, 1, 2, 1, 2, 100, 100}, 3, 2));

    }
}
