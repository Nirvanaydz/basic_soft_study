package java01.basic.datastructure.questions;

/**
 * 经典八皇后问题
 *
 * @author Administrator
 */
public class CallQueens {

    /**
     * 全局或成员变量,下标表示行,值表示queen存储在哪一列
     */
    int[] result = new int[8];

    /**
     * 调用方式：cal8queens(0)
     *
     * @param row
     */
    public void cal8queens(int row) {
        // 8个棋子都放置好了，打印结果
        if (8 == row) {
            printQueens(result);
            // 8行棋子都放好了，已经没法再往下递归了，所以就return
            return;
        }
        // 每一行都有8中放法
        for (int column = 0; column < 8; ++column) {
            // 有些放法不满足要求
            if (isOk(row, column)) {
                // 第row行的棋子放到了column列
                result[row] = column;
                // 考察下一行
                cal8queens(row + 1);
            }
        }
    }

    /**
     * 判断row行column列放置是否合适
     *
     * @param row    第row行
     * @param column 第column列
     * @return 是否可以放置
     */
    private boolean isOk(int row, int column) {
        int leftUp = column - 1, rightUp = column + 1;
        // 逐行往上考察每一行
        for (int i = row - 1; i >= 0; --i) {
            if (result[i] == column) {
                // 第i行的column列有棋子吗？
                return false;
            }
            // 考察左上对角线：第i行leftup列有棋子吗？
            if (leftUp >= 0) {
                if (result[i] == leftUp) {
                    return false;
                }
            }
            // 考察右上对角线：第i行rightup列有棋子吗？
            if (rightUp < 8) {
                if (result[i] == rightUp) {
                    return false;
                }
            }
            --leftUp;
            ++rightUp;
        }
        return true;
    }

    /**
     * 打印出一个二维矩阵
     *
     * @param result 排列阵
     */
    private void printQueens(int[] result) {
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
