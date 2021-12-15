package com.paofu.data_structure.day03.sort;

import java.util.Arrays;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/15 21:03
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[array.length];
        mergeSort(array, 0, array.length - 1, temp);
        System.out.println(Arrays.toString(array));
    }

    public static void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 向左递归进行分解
            mergeSort(array, left, mid, temp);
            // 向右递归分解
            mergeSort(array, mid + 1, right, temp);
            // 合并
            merge(array, left, mid, right, temp);
        }
    }

    /**
     * 归并排序的合并方法
     * @param array 待排序数组
     * @param left  左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边有序序列的初始索引
     * @param temp 中转数组
     */
    public static void merge(int[] array, int left, int mid, int right, int[] temp) {
        // 初始化i，左边有序序列的初始索引
        int i = left;
        // 初始化j，右边有序序列的初始索引
        int j = mid + 1;
        // 中转数组的存放位置当前索引
        int t = 0;
        // 1:先把左右两边的数据按规则填充到中转数组temp。知道左右两边有序序列有一方处理完毕
        while (i <= mid && j <= right) {
            // 如果左边有序序列的当前元素小于等于右边有序序列的当前元素，把左边序列当前元素拷贝到temp中转数组
            if (array[i] <= array[j]) {
                temp[t] = array[i];
                t++;
                i++;
            } else {
                temp[t] = array[j];
                t++;
                j++;
            }
        }

        // 2:把有剩余数据的一边依次填充到temp中
        // 说明左边的序列有剩余元素
        while (i <= mid) {
            temp[t] = array[i];
            t++;
            i++;
        }
        // 说明右边的序列有剩余元素
        while (j <= right) {
            temp[t] = array[j];
            t++;
            j++;
        }

        // 3:将temp数组元素拷贝到array
        // 并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            array[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }

    }
}
