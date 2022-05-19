/**
 * 给定一个一维整数数组，找出差值绝对值最小的一组或者多组数，例如输入[2,1,4,7,5,9]，输出[2,1],[4,5]
 *
 * @author yudazhi
 */
public class Solution {

    private int[][] m;

    public Solution(int[][] matrix) {
        m = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int cnt = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                cnt += matrix[i][j];
                m[i][j] = cnt;
            }
        }
        for (int i = 1; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] += m[i - 1][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return m[row2][col2] - m[row2][col1] - m[row1][col2] + m[row1][col1];
    }

    public static void main(String[] args) {
        Solution s = new Solution(new int[][]{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}});
    }
}
