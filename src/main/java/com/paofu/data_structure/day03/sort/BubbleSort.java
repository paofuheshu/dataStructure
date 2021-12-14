package com.paofu.data_structure.day03.sort;

import java.util.Arrays;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/14 20:06
 * 冒泡排序示例  时间复杂度 O(n^2)
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {3, 9, -1, 10, -2};
        bubbleSort(array);
        bubbleSort1(array);
    }

    public static void bubbleSort(int[] array) {
        int temp = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "次排序后的数组为：" + Arrays.toString(array));
        }
    }

    /**
     * 优化冒泡排序，减少不必要的比较
     * @param array  数组
     */
    public static void bubbleSort1(int[] array) {
        int temp = 0;
        // 表示是否进行过交换
        boolean flag = false;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    flag = true;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "次排序后的数组为：" + Arrays.toString(array));
            if (!flag) {
                // 表示在一趟排序中一次交换都没发生
                break;
            } else {
                flag = false;
            }
        }
    }
}
