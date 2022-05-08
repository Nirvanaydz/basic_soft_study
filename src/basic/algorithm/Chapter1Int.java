package basic.algorithm;

/**
 * @author yudazhi
 */
public class Chapter1Int {
    /**
     * 加减法完成除法核心[使用负数的除法]：绝对值较大时，负数越小
     * @param dividend  被除数
     * @param divisor   除数/因子
     * @return 整除结果 -9 / -2 = 4
     */
    private int divideCore(int dividend, int divisor) {
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
     * @param dividend  被除数
     * @param divisor   除数
     * @return  整除的结果   9 / 2 = 4
     */
    private int dividePositiveCore(int dividend, int divisor) {
        int result = 0;
        while (divisor <= dividend) {
            //  除数始终不变
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

    public static void main(String[] args) {
        System.out.println(new Chapter1Int().divideCore(-2, -1));
        System.out.println(new Chapter1Int().divideCore(-9, -2));
        System.out.println(new Chapter1Int().dividePositiveCore(99, 2));
        System.out.println(new Chapter1Int().dividePositiveCore(101, 3));
        System.out.println(0xc0000000);
        System.out.println(0x80000000);
        System.out.println(0x800f081f);
        System.out.println(0xFE);

    }
}
