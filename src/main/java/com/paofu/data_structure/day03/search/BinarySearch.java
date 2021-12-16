package com.paofu.data_structure.day03.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/16 20:43
 * 二分查找(要求数组必须是有序的)
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] array = {1, 8, 10, 89, 100, 100, 123};
        System.out.println(binarySearch1(array, 0, array.length - 1, 100));
        System.out.println(binarySearch2(array, 0, array.length - 1, 100));
    }

    /**
     * 只查询出一个数返回，即使存在多个相同的查找值
     * @param array  数组
     * @param left  左索引
     * @param right  右索引
     * @param value  查找值
     * @return  int
     */
    public static int binarySearch1(int[] array, int left, int right, int value) {
        // 当left > right时，说明递归了整个数组，但是没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midValue = array[mid];

        if (value > midValue) {
            // 向右递归
            return binarySearch1(array, mid + 1, right, value);
        } else if (value < midValue) {
            // 向左递归
            return binarySearch1(array, left, mid - 1, value);
        } else {
            return mid;
        }
    }

    /**
     * 将存在的多个相同的查找值都找出
     * @param array  数组
     * @param left  左索引
     * @param right  右索引
     * @param value  查找值
     * @return  List<Integer>
     */
    public static List<Integer> binarySearch2(int[] array, int left, int right, int value) {

        // 当left > right时，说明递归了整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midValue = array[mid];

        if (value > midValue) {
            // 向右递归
            return binarySearch2(array, mid + 1, right, value);
        } else if (value < midValue) {
            // 向左递归
            return binarySearch2(array, left, mid - 1, value);
        } else {
            /**
             * 在找到mid索引时，不要马上返回
             * 向mid索引是的左边扫描 将所有满足查找值的元素的下标，加到集合
             * 向mid索引是的右边扫描 将所有满足查找值的元素的下标，加到集合
             */
            List<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (temp >= 0 && array[temp] == value) {
                // 否则将temp放入集合
                list.add(temp);
                temp--;
            }
            list.add(mid);

            temp = mid + 1;
            while (temp <= array.length - 1 && array[temp] == value) {
                // 否则将temp放入集合
                list.add(temp);
                temp++;
            }
            return list;
        }

    }
}
