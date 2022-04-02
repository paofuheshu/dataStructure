package com.paofu.data_structure.day03.demo_sort;

import java.util.Arrays;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/3/8 11:15
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {3, 9, -1, 10, -2};
        bubbleSort(array);
    }

    public static void bubbleSort(int[] array) {
        int temp;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
