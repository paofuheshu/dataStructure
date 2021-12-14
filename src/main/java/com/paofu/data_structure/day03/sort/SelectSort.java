package com.paofu.data_structure.day03.sort;

import java.util.Arrays;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/14 20:47
 * 选择排序 时间复杂度也是O(n^2)
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] array = {101, 34, 119, 1};
        selectSort(array);
    }

    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            int min = array[i];
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
            System.out.println("第" + (i + 1) + "次排序后的数组为：" + Arrays.toString(array));
        }

    }
}
