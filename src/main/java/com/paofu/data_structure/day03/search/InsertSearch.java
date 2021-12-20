package com.paofu.data_structure.day03.search;

import java.util.Arrays;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/20 19:01
 * 差值查找（数组有序）
 * 对于数据量较大，关键字分布比较均匀的查找表来说 速度更快
 */
public class InsertSearch {

    public static void main(String[] args) {
//        int[] array = new int[100];
//        for (int i = 0; i < 100; i++) {
//            array[i] = i + 1;
//        }
        int[] array = {1, 8, 10, 89, 100, 100, 123};
        System.out.println(insertSearch(array, 0, array.length - 1, 8));
    }

    /**
     * 插值查找
     * @param array 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findValue 查找值
     * @return int
     */
    public static int insertSearch(int[] array, int left, int right, int findValue) {
        System.out.println("方法调用了");
        if (left > right || findValue < array[0] || findValue > array[array.length - 1]) {
            return -1;
        }

        int mid = left + (right - left) * ((findValue - array[left]) / (array[right] - array[left]));
        int midValue = array[mid];

        if (findValue > midValue) {
            // 向右递归
            return insertSearch(array, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            // 向左递归
            return insertSearch(array, left, mid - 1, findValue);
        } else {
            return mid;
        }
    }
}
