package com.paofu.data_structure.day03.sort;

import java.util.Arrays;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/15 20:09
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = {-9, 78, 0, 23, -567, 70};
        int[] testArray = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            testArray[i] = (int) (Math.random() * 80000000);
        }
        long start = System.currentTimeMillis();
        quickSort(testArray, 0, array.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("排序共花费了：" + (end - start) + "毫秒");
    }

    public static void quickSort(int[] array, int left, int right) {
        // 左下标
        int l = left;
        // 右下标
        int r = right;
        // pivot代表中轴值
        int pivot = array[(left + right) / 2];
        int temp = 0;
        // while的目的是让比pivot值小的放到左边，比pivot值大的放到右边
        while (l < r) {
            // 在pivot的左边一直找，找到大于等于pivot的值，才退出
            while (array[l] < pivot) {
                l += 1;
            }
            // 在pivot的右边一直找，找到小于等于pivot的值，才退出
            while (array[r] > pivot) {
                r -= 1;
            }
            // 如果l >= r说明pivot的左边全部是小于等于pivot的值，右边全是大于等于pivot的值
            if (l >= r) {
                break;
            }
            // 交换
            temp = array[l];
            array[l] = array[r];
            array[r] = temp;

            // 如果交换完后，发现arr[l] == pivot，前移
            if (array[l] == pivot) {
                r -= 1;
            }
            // 如果交换完后，发现arr[r] == pivot，前移
            if (array[r] == pivot) {
                l += 1;
            }
        }
        // 如若l == r,必须l++, r--，否则出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        // 向左递归
        if (left < r) {
            quickSort(array, left, r);
        }
        // 向右递归
        if (right > l) {
            quickSort(array, l, right);
        }


    }
}
