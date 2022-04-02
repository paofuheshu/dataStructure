package com.paofu.data_structure.day03.demo_sort;

import java.util.Arrays;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/3/8 11:21
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] array = {10, 34, 19, 1, 46};
        insertSort(array);
    }

    // 10, 34, 19, 1, 46
    public static void insertSort(int[] array) {
        int insertIndex;
        int insertValue;
        for (int i = 1; i < array.length; i++) {
            insertValue = array[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertValue < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                array[insertIndex + 1] = insertValue;
            }
            System.out.println("第" + i + "次排序后数组为：" + Arrays.toString(array));
        }
    }
}
