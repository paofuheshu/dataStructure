package com.paofu.data_structure.day03.demo_sort;

import java.util.Arrays;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/2/17 16:11
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] array = {10, 34, 19, 1, 46};
//        selectSortStep(array);
        selectSort(array);
    }

    /**
     * [1, 34, 19, 10, 46]
     * [1, 10, 19, 34, 46]
     * [1, 10, 19, 34, 46]
     * [1, 10, 19, 34, 46]
     * @param array
     */
    public static void selectSortStep(int[] array) {
        // 第一步
        // 首先假设第一个数据是最小值
        int min = array[0];
        int minIndex = 0;
        int temp;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                minIndex = i;
            }
        }
        temp = array[0];
        array[0] = array[minIndex];
        array[minIndex] = temp;
        System.out.println(Arrays.toString(array));
        // 第二步
        min = array[1];
        minIndex = 1;
        for (int i = 2; i < array.length; i++) {
            if (array[i] < min) {
                minIndex = i;
            }
        }
        temp = array[1];
        array[1] = array[minIndex];
        array[minIndex] = temp;
        System.out.println(Arrays.toString(array));
        // 第三步
        min = array[2];
        minIndex = 2;
        for (int i = 3; i < array.length; i++) {
            if (array[i] < min) {
                minIndex = i;
            }
        }
        temp = array[2];
        array[2] = array[minIndex];
        array[minIndex] = temp;
        System.out.println(Arrays.toString(array));
        // 第四步
        min = array[3];
        minIndex = 3;
        for (int i = 4; i < array.length; i++) {
            if (array[i] < min) {
                minIndex = i;
            }
        }
        temp = array[3];
        array[3] = array[minIndex];
        array[minIndex] = temp;
        System.out.println(Arrays.toString(array));
    }

    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = array[i];
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = min;
            }
            System.out.println("第" + (i + 1) + "次排序后的结果为：" + Arrays.toString(array));
        }

    }
}
