package com.paofu.data_structure.day03.demo_sort;

import java.util.Arrays;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/2/17 16:06
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] array = {10, 34, 19, 1, 46};
        insertSort(array);
    }

    /**
     * 10, 34, 19, 1, 46
     * 第一步：
     * 将数据分为有序列表和无序列表
     * 有序列表：10
     * 无序列表：34，19，1，46
     * 将34取出，和10作比较，因为34 > 10，此时有序列表变为 10，34
     * 有序列表：10，34
     * 无序列表：19，1，46
     * 将19取出，寻找插入的位置
     * 因为10 < 19 < 34  此时将19插入到有序列表中，
     * 有序列表：10， 19， 34， 1， 46
     * .....
     * 第1次排序后数组为：[10, 34, 19, 1, 46]
     * 第2次排序后数组为：[10, 19, 34, 1, 46]
     * 第3次排序后数组为：[1, 10, 19, 34, 46]
     * 第4次排序后数组为：[1, 10, 19, 34, 46]
     */
    public static void insertSort(int[] array) {
        int insertIndex = 0;
        int insertValue = 0;
        for (int i = 1; i < array.length; i++) {
            insertValue = array[i];
            insertIndex = i;
            while (insertIndex - 1 >= 0 && insertValue < array[insertIndex - 1]) {
                array[insertIndex] = array[insertIndex - 1];
                insertIndex--;
            }
            if (insertIndex != i) {
                array[insertIndex] = insertValue;
            }
            System.out.println("第" + i + "次排序后数组为：" + Arrays.toString(array));
        }

    }
}
