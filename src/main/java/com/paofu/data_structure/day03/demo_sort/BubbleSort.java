package com.paofu.data_structure.day03.demo_sort;

import java.util.Arrays;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/2/17 15:42
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {3, 9, -1, 10, -2};
        bubbleSort(array);
        /**
         * [3, -1, 9, -2, 10]
         * [-1, 3, -2, 9, 10]
         * [-1, -2, 3, 9, 10]
         * [-2, -1, 3, 9, 10]
         */
    }

    public static void bubbleSortStep(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                int temp;
                temp = array[i + 1];
                array[i + 1] = array[i];
                array[i] = temp;
            }
        }
        System.out.println(Arrays.toString(array));
        // 第二步
        for (int i = 0; i < array.length - 2; i++) {
            if (array[i] > array[i + 1]) {
                int temp;
                temp = array[i + 1];
                array[i + 1] = array[i];
                array[i] = temp;
            }
        }
        System.out.println(Arrays.toString(array));
        // 第三步
        for (int i = 0; i < array.length - 3; i++) {
            if (array[i] > array[i + 1]) {
                int temp;
                temp = array[i + 1];
                array[i + 1] = array[i];
                array[i] = temp;
            }
        }
        System.out.println(Arrays.toString(array));
        // 第四步
        for (int i = 0; i < array.length - 4; i++) {
            if (array[i] > array[i + 1]) {
                int temp;
                temp = array[i + 1];
                array[i + 1] = array[i];
                array[i] = temp;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    public static void bubbleSort(int[] array) {
        int temp = 0;
        for (int j = 1; j < array.length; j++) {
            for (int i = 0; i < array.length - j; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = temp;
                }
            }
            System.out.println("第" + j + "次排序后的数组为：" + Arrays.toString(array));
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
        for (int j = 1; j < array.length - 1; j++) {
            for (int i = 0; i < array.length - j; i++) {
                if (array[i] > array[i + 1]) {
                    flag = true;
                    temp = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = temp;
                }
            }
            System.out.println("第" + j + "次排序后的数组为：" + Arrays.toString(array));
            if (!flag) {
                // 表示在一趟排序中一次交换都没发生
                break;
            } else {
                flag = false;
            }
        }
    }

}
