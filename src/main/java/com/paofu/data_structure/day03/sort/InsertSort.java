package com.paofu.data_structure.day03.sort;

import java.util.Arrays;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/14 21:06
 * 插入排序  时间复杂度也是O(n^2)
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] array = {10, 34, 19, 1, 46};
        int[] testArray = new int[80000];
        for (int i = 0; i < 80000; i++) {
            testArray[i] = (int) (Math.random() * 80000);
        }
        insertSort(array);
    }

    public static void insertSort(int[] array) {
        int insertValue = 0;
        int insertIndex = 0;
        long start = System.currentTimeMillis();
        for (int i = 1; i < array.length; i++) {
            insertValue = array[i];
            insertIndex = i - 1;
            // 给insertValue找到插入的位置
            // 1: insertIndex >= 0,保证在找位置时，数组角标不越界
            // 2: insertValue < array[insertIndex] 说明待插入的数还没有找到插入的位置
            // 3: 需要将array[insertIndex]后移
            while (insertIndex >= 0 && insertValue < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                array[insertIndex + 1] = insertValue;
            }
            System.out.println("第" + i + "次排序后数组为：" + Arrays.toString(array));
        }
        long end = System.currentTimeMillis();
        System.out.println("排序共花费了：" + (end - start) + "毫秒");
//        System.out.println(Arrays.toString(array));
    }
}
