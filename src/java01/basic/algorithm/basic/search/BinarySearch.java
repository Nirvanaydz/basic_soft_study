package java01.basic.algorithm.basic.search;

/**
 * @author yudazhi
 */
public class BinarySearch {
    public int bSearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public int bSearchByRecursion(int[] a, int n, int value) {
        return bSearchInternally(a, 0, n - 1, value);
    }

    private int bSearchInternally(int[] a, int low, int high, int value) {
        if (low > high) {
            return -1;
        }
        int mid = (high + low) / 2;
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            return bSearchInternally(a, mid + 1, high, value);
        } else {
            return bSearchInternally(a, low, mid - 1, value);
        }
    }

    /**
     * 查询第一个等于，前面的数据要么没有，要么前面的数据小于它
     */
    public int bSearchFirstEqual(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == 0) || a[mid - 1] < value) {
                    return mid;
                } else {
                    //  不存在表明要查询的值在当前点的前面，高节点前移
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查询最后一个等于，前面的数据要么没有，要么前面的数据小于它
     */
    public int bSearchLastEqual(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == n - 1) || a[mid + 1] > value) {
                    return mid;
                } else {
                    //  不存在表明要查询的值在当前点的后面，低节点后移
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查询第一个大于等于给定值，前面的数据要么没有，要么前面的数据小于它
     */
    public int bSearchFirstEqualOrGather(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] >= value) {
                if (mid == 0 || a[mid - 1] < value) {
                    return  mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查询最后一个小于等于给定值，后面的数据要么没有，要么后面的数据大于它
     */
    public int bSearchLastEqualOrLess(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] <= value) {
                if (mid == n - 1 || a[mid + 1] > value) {
                    return  mid;
                } else {
                    low = mid + 1;
                }
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

}
