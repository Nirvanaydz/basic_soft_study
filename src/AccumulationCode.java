import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 可参考的代码思想
 * @author yudazhi
 */
public class AccumulationCode {

    /**
     * 快速排序核心方法
     * @param p 起始位置
     * @param q 结束位置
     * @param s 无序数组
     * @return 无序数组变成在节点前都小于其值，在节点后都大于等于其值，并返回参考节点的索引下标
     */
    private static int pivotInQuickSort(int p, int q, String[] s) {
        int i = p, j = p;
        while (j < q) {
            /* if (compareToIgnoreCase(s[j], s[q]) < 0) { */
            if (s[j].compareToIgnoreCase(s[q]) < 0) {
                swap(i, j, s);
                i++;
            }
            j++;
        }
        swap(i, q, s);
        return i;
    }

    /**
     * 比较两个string的字典序大小，不论大小写
     * @param s1 左
     * @param s2 右
     * @return s1 == s2 ? 0 : (s1 < s2 : ? s1 : s2)
     *
     */
    private static int compareToIgnoreCase(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        int min = Math.min(n1, n2);
        for (int i = 0; i < min; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2) {
                c1 = Character.toUpperCase(c1);
                c2 = Character.toUpperCase(c2);
                if (c1 != c2) {
                    c1 = Character.toLowerCase(c1);
                    c2 = Character.toLowerCase(c2);
                    if (c1 != c2) {
                        // No overflow because of numeric promotion
                        return c1 - c2;
                    }
                }
            }
        }
        return n1 - n2;
    }

    /**
     * 比较两个string的字典序大小，需要参考大小写
     * @param thisVal 左
     * @param anotherString 右
     * @return s1 == s2 ? 0 : (s1 < s2 : ? s1 : s2)
     *
     */
    private static int compareTo(String thisVal, String anotherString) {
        int len1 = thisVal.length();
        int len2 = anotherString.length();
        int lim = Math.min(len1, len2);
        char[] v1 = thisVal.toCharArray();
        char[] v2 = anotherString.toCharArray();

        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }

    /**
     * 交换数组中的值
     * @param i 待交换下标i
     * @param j 待交换下标j
     * @param s 数组
     */
    private static void swap(int i, int j, String[] s) {
        if (i != j) {
            String temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }

    /**
     * 如何通过流读取输入参数
     * @throws IOException 流异常
     */
    private void howToGetInputByBufferReader() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());
            String[] ss = new String[n];
            for (int i = 0; i < n; i++) {
                ss[i] = br.readLine();
            }
            System.out.println(Arrays.toString(ss));
        }
    }

    private static void characterFunctions() {
        System.out.println(Character.isDigit('a'));
        System.out.println(Character.isLetter('a'));
        System.out.println(Character.getNumericValue('a'));
        System.out.println(Character.getNumericValue(' '));
        System.out.println(Integer.parseInt("-123"));
    }

    /**
     * 判断是否可以转化为为一个数字
     * @param s 字符串
     * @return 是否可以转化为整数
     */
    private static boolean isNumber(String s) {
        if (null == s || s.length() == 0) {
            return false;
        }
        int start = 0;
        //  存在负数和正数的判断
        char[] cs = {'-', '+'};
        if (cs[0] == s.charAt(0) || cs[1] == s.charAt(0)) {
            start ++;
        }
        //
        if (start == 1 && s.length() == 1) {
            return false;
        }
        for (; start < s.length(); start ++) {
            int val = Character.getNumericValue(s.charAt(start));
            if (val < 0 || val > 9) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        characterFunctions();
        System.out.println(0x123);
        System.out.println(0x023);
    }
}
