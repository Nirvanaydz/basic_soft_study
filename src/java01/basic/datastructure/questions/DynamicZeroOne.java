package java01.basic.datastructure.questions;

/**
 * @author Administrator
 */
public class DynamicZeroOne {
    /**
     * 存储背包中物品总重量的最大值
     */
    public int maxW = Integer.MIN_VALUE;

    /**
     * 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，那可以这样调用函数：f(0, 0, a, 10, 100)
     *
     * @param i     i表示考察到哪个物品了
     * @param cw    cw表示当前已经装进去的物品的重量和
     * @param items items表示每个物品的重量
     * @param n     n表示物品个数
     * @param w     w背包重量
     */
    public void func(int i, int cw, int[] items, int n, int w) {
        if (i == n || cw == w) {
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        func(i + 1, cw, items, n, w);
        if (cw + items[i] <= w) {
            func(i + 1, cw + items[i], items, n, w);
        }
    }
}
