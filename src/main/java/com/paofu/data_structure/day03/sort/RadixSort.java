package com.paofu.data_structure.day03.sort;

import java.util.Arrays;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/16 19:04
 * 基数排序(桶排序)
 * 因为基数排序会耗费内存空间，如果array的数量太大，比如80000000，会内存不足
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] array = {53, 3, 542, 748, 14, 214};
//        radixSortStep(array);
        radixSort(array);
    }

    public static void radixSort(int[] array) {
        // 定义一个二维数据代表一个二维数组，每个桶就是一个一维数组

        // 每个一维数组的大小不固定 为了防止数据溢出(角标越界) 大小定位array.length
        // 基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][array.length];

        // 为了记录每个桶中实际存放了多少个数据，定义一个一维数组来记录各个桶每次放入的数据个数
        int[] bucketElementsCounts = new int[10];
        // 先得到数组中最大数的位数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        // 得到最大数的位数
        int maxLength = (max + "").length();

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 第i轮排序 对每个元素的相应位数进行排序
            for (int j = 0; j < array.length; j++) {
                // 取出每个元素的对应位数值
                int digitOfElement = array[j] / n % 10;
                // 放入到对应的桶中
                bucket[digitOfElement][bucketElementsCounts[digitOfElement]] = array[j];
                bucketElementsCounts[digitOfElement]++;
            }

            // 按照这个桶的顺序(一位数组的下标依次取出数据，放入原来的数组)
            int index = 0;
            // 遍历每一个桶，并将桶中的数据放进原数组
            for (int k = 0; k < bucketElementsCounts.length; k++) {
                // 如果桶中有数据才放入原数组
                if (bucketElementsCounts[k] != 0) {
                    for (int l = 0; l < bucketElementsCounts[k]; l++) {
                        array[index] = bucket[k][l];
                        index++;
                    }
                }
                // 每轮处理后，需要将每个bucketElementsCounts[k] = 0
                bucketElementsCounts[k] = 0;
            }


            System.out.println("第" + (i + 1) + "轮对个数的排序处理为：" + Arrays.toString(array));
        }
    }


    /**
     * 第一轮对个数的排序处理为：[542, 53, 3, 14, 214, 748]
     * 第二轮对十位的排序处理为：[3, 14, 214, 542, 748, 53]
     * 第三轮对百位的排序处理为：[3, 14, 53, 214, 542, 748]
     * @param array  数组
     */
    public static void radixSortStep(int[] array) {
        // 第一轮排序 对每个元素的个位进行排序
        // 定义一个二维数据代表一个二维数组，每个桶就是一个一维数组
        // 每个一维数组的大小不固定 为了防止数据溢出(角标越界) 大小定位array.length
        // 基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][array.length];

        // 为了记录每个桶中实际存放了多少个数据，定义一个一维数组来记录各个桶每次放入的数据个数
        int[] bucketElementsCounts = new int[10];

        // 第一轮(针对每个元素的个位进行排序处理
        for (int j = 0; j < array.length; j++) {
            // 取出每个元素的个位数
            int digitOfElement = array[j] % 10;
            // 放入到对应的桶中
            bucket[digitOfElement][bucketElementsCounts[digitOfElement]] = array[j];
            bucketElementsCounts[digitOfElement]++;
        }

        // 按照这个桶的顺序(一位数组的下标依次取出数据，放入原来的数组)
        int index = 0;
        // 遍历每一个桶，并将桶中的数据放进原数组
        for (int k = 0; k < bucketElementsCounts.length; k++) {
            // 如果桶中有数据才放入原数组
            if (bucketElementsCounts[k] != 0) {
                for (int l = 0; l < bucketElementsCounts[k]; l++) {
                    array[index] = bucket[k][l];
                    index++;
                }
            }
            // 第一轮处理后，需要将每个bucketElementsCounts[k] = 0
            bucketElementsCounts[k] = 0;
        }


        System.out.println("第一轮对个数的排序处理为：" + Arrays.toString(array));

        // 第二轮(针对每个元素的十位进行排序处理
        for (int j = 0; j < array.length; j++) {
            // 取出每个元素的个位数
            int digitOfElement = array[j] / 10 % 10;
            // 放入到对应的桶中
            bucket[digitOfElement][bucketElementsCounts[digitOfElement]] = array[j];
            bucketElementsCounts[digitOfElement]++;
        }

        // 按照这个桶的顺序(一位数组的下标依次取出数据，放入原来的数组)
        index = 0;
        // 遍历每一个桶，并将桶中的数据放进原数组
        for (int k = 0; k < bucketElementsCounts.length; k++) {
            // 如果桶中有数据才放入原数组
            if (bucketElementsCounts[k] != 0) {
                for (int l = 0; l < bucketElementsCounts[k]; l++) {
                    array[index] = bucket[k][l];
                    index++;
                }
            }
            // 第二轮处理后，需要将每个bucketElementsCounts[k] = 0
            bucketElementsCounts[k] = 0;
        }

        System.out.println("第二轮对十位的排序处理为：" + Arrays.toString(array));

        // 第三轮(针对每个元素的百位进行排序处理
        for (int j = 0; j < array.length; j++) {
            // 取出每个元素的个位数
            int digitOfElement = array[j] / 10 / 10 % 10;
            // 放入到对应的桶中
            bucket[digitOfElement][bucketElementsCounts[digitOfElement]] = array[j];
            bucketElementsCounts[digitOfElement]++;
        }

        // 按照这个桶的顺序(一位数组的下标依次取出数据，放入原来的数组)
        index = 0;
        // 遍历每一个桶，并将桶中的数据放进原数组
        for (int k = 0; k < bucketElementsCounts.length; k++) {
            // 如果桶中有数据才放入原数组
            if (bucketElementsCounts[k] != 0) {
                for (int l = 0; l < bucketElementsCounts[k]; l++) {
                    array[index] = bucket[k][l];
                    index++;
                }
            }
            // 第三轮处理后，需要将每个bucketElementsCounts[k] = 0
            bucketElementsCounts[k] = 0;
        }

        System.out.println("第三轮对百位的排序处理为：" + Arrays.toString(array));
    }
}
