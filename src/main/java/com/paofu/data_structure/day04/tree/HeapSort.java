package com.paofu.data_structure.day04.tree;

import java.util.Arrays;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/23 21:16
 * 堆排序(根据升序还是降序要求，调整至大顶堆或小顶堆来进行排序交换)
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = {4, 6, 8, 5, 9};
        heapSort(array);
    }

    /**
     * 堆排序方法
     */
    public static void heapSort(int[] array) {
        int temp = 0;
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        for (int j = array.length - 1; j > 0; j--) {
            temp = array[j];
            array[j] = array[0];
            array[0] = temp;
            adjustHeap(array, 0, j);
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * 将一个数组(二叉树)调整为一个大顶堆
     * 将i对应的非叶子节点的数调整成大顶堆
     * @param array 数组
     * @param i 表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整，(逐渐减少的)
     */
    public static void adjustHeap(int[] array, int i, int length) {
        // 临时变量保存当前元素的值
        int temp = array[i];
        // 开始调整
        // k代表i节点的左子节点  k = i * 2 + 1
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1< length && array[k] < array[k + 1]) {
                // 说明左子节点的值小于右子节点的值, k指向右子节点
                k++;
            }
            if (array[k] > temp) {
                // 说明子节点大于父节点 则把较大的值付给当前节点  再将i指向k，继续循环比较
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp;
    }
}
