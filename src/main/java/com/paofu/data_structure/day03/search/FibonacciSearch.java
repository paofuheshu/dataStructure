package com.paofu.data_structure.day03.search;

import java.util.Arrays;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/20 19:23
 * 斐波那契查找(数据需要是有序的)
 */
public class FibonacciSearch {

    /**
     * 定义我们需要的斐波那契数组的长度
     */
    private static final int MAX_SIZE = 20;

    public static void main(String[] args) {
        int[] array = {1, 8, 10, 89, 100, 100, 123};
        System.out.println(fibonacciSearch(array, 10));
    }

    /**
     * 斐波那契查找法
     * @param array 数组
     * @param key 查找值
     * @return int 对应下标
     */
    public static int fibonacciSearch(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;
        // 表示斐波那契分割数值对应的下标
        int k = 0;
        int mid = 0;
        int[] fibArray = fib();
        // 获取到k
        while (high > fibArray[k] - 1) {
            k++;
        }
        // 因为f[k]的值可能大于a的长度 因此我们需要构造一个新的数组，并指向array  不足的部分会使用0填充
        int[] temp = Arrays.copyOf(array, fibArray[k]);
        // 实际上不能使用0来进行填充，需要用array的最后一个数填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = array[high];
        }

        while (low <= high) {
            mid = low + fibArray[k - 1] - 1;
            if (key < temp[mid]) {
                // 向左边查找
                high = mid - 1;
                // 全部元素 = 前面元素 + 后面元素
                // fibArray[k] = fib[k - 1] + fib[k - 2]
                // 因为前面有fib[k - 1]个元素 所以可以继续拆分成fib[k- 1] = fib[k - 2] + fib[k - 3]
                // 即在f[k-1]的前面继续查找 k--
                // 即下次循环 mid = f[k - 1 - 1] - 1
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                // 全部元素 = 前面元素 + 后面元素
                // fibArray[k] = fib[k - 1] + fib[k - 2]
                // 因为后面有fib[k - 2]个元素 所以可以继续拆分成fib[k- 2] = fib[k - 3] + fib[k - 4]
                // 即在f[k-2]的前面继续查找 k -= 2
                // 即下次循环 mid = f[k - 1 - 2] - 1
                k -= 2;
            } else {
                return Math.min(mid, high);
            }
        }
        return -1;
    }

    /**
     * 获取斐波那契数列
     * @return array
     */
    public static int[] fib() {
        int[] fibArray = new int[MAX_SIZE];
        fibArray[0] = 1;
        fibArray[1] = 1;
        for (int i = 2; i < MAX_SIZE; i++) {
            fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
        }
        return fibArray;
    }
}
