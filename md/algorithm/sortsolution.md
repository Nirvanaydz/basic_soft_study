### 1.1 bubble sort
- bubble cur index to next
- 比较当前位置和后一个节点的位置
    + 由小到大排序时，将较大的值后移
    + 由大到小排序时，将较小的值后移
```java
public class BubbleSortDemo{
    //  冒泡排序的优化
    public void bubbleSort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            boolean notExchangeFlag = true;
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    swap(j, j + 1, data);
                    notExchangeFlag = false;
                }
            }
            if (notExchangeFlag) {
                break;
            }
        }
    } 
}
```
### 1.2 select sort
- 选择未排序空间的最小值，放入已排序空间
- 初始状态：
    + 未排序空间大小为0
    + 已排序空间大小为n
- 选择未排序空间的最小值和未排序空间的第一个位置交换
```java
public class SelectSortDemo {
    public void selectSort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int minVal = data[i];
            int minIdx = i;
            for (int j = 1; j < data.length; j++) {
                if (data[j] < minVal) {
                    minVal = data[j];
                    minIdx = j;
                }
            }
            swap(i, minIdx, data);
        }
    }
}
```
### 1.3 insert sort
- 已排序空间：初始大小就是数组的第一个值
- 未排序空间
- 找到未排序空间的第一个值，与已排序空间的值一次比较，由后向前比较，当已排序空间的值大于当前要插入的值时，将已排序空间的值向后移动一位
```java
public class InsertSortDemo {
    public void insertSort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            int readyInsertVal = data[i];
            int haveSortedLength = i - 1;
            for (; haveSortedLength >= 0;haveSortedLength --) {
                if (data[haveSortedLength] > readyInsertVal) {
                    data[haveSortedLength + 1] = data[haveSortedLength];
                }
            }
            data[haveSortedLength + 1] = readyInsertVal;
        }
    }
}
```
### 2.1 merge sort
- 合并两个有序数组
```java
public class MergeSortDemo {
    public void mergeSort(int[] data, int p, int q, int r) {
        int[] tmpSort = new int[r-p+1];
        int k = 0,i = p,j = q + 1;
        while (i <= p && j <= r) {
            if (data[i] < data[j]) {
                tmpSort[k++] = data[i++];
            } else {
                tmpSort[k++] = data[j++];
            }
        }
        //  handle remain
        int remainStart = i;
        int remainEnd = q;
        if (j <= r) {
            remainStart = j;
            remainEnd = r;
        }
        while (remainStart <= remainEnd) {
            tmpSort[k++] = data[remainStart++];
        }
        //  copy
        for (int l = 0; l < tmpSort.length; l++) {
            data[p+l] = tmpSort[l];
        }
    }
}
```
### 2.2 quick sort
- 找到某一个值在数组应该出现的位置
```java
public class QuickSortDemo {
    public int pivot(int[] data, int p, int r) {
        int key = data[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (data[j] < key) {
                swap(i, j, data);
                i = i + 1;
            }
        }
        swap(i, r, data);
        return i;
    }
}
```
### 3.1 bucket sort
### 3.2 counting sort
- 计数排序的抽象思想，code都需要掌握
```java
public class CountingSortDemo{
    public void countingSort(int[] data) {
        //  find range
        int maxVal = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > maxVal) {
                maxVal = data[i];
            }
        }
        //  init countArr
        int[] countArr = new int[maxVal+1];
        for (int i = 0; i < data.length; i++) {
            countArr[data[i]]++;
        }
        //  add val
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] = countArr[i] + countArr[i - 1];
        }
        //  get idx and set val
        int[] tmp = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            int idx = countArr[data[i]] - 1;
            tmp[idx] = data[i];
            countArr[data[i]]--;
        }
        //  copy array
        for (int i = 0; i < tmp.length; i++) {
            data[i] = tmp[i];
        }
    }
}
```