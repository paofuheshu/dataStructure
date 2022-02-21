package com.paofu.data_structure.day03.sort;

import java.util.Arrays;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/15 19:19
 * 希尔排序(缩小增量排序)
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] testArray = new int[80000];
        for (int i = 0; i < 80000; i++) {
            testArray[i] = (int) (Math.random() * 80000);
        }
        int[] testArray1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            testArray1[i] = (int) (Math.random() * 80000);
        }
//        shellSortExchange(testArray);
        shellSortMove(array);
//        shellSortByStep(array);
    }

    /**
     * 希尔排序(交换法)
     * @param array  数组
     */
    public static void shellSortExchange(int[] array) {
        int temp = 0;
        int count = 0;
        long start = System.currentTimeMillis();
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            // 希尔排序的第gap轮排序
            // 将10个数据分为了gap组
            for (int i = gap; i < array.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，则交换
                    if (array[j] > array[j + gap]) {
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
                }
            }
//            System.out.println("希尔排序第" + (++count) + "轮排序后为：" + Arrays.toString(array));
        }
        long end = System.currentTimeMillis();
        System.out.println("排序共花费了：" + (end - start) + "毫秒");
    }

    /**
     * 因为传入的数组长度为10，根据10/2=5 5/2=2 2/2=1 1/2 = 0 !> 0 所以此次共分为三步
     * @param array  数组
     */
    public static void shellSortByStep(int[] array) {
        int temp = 0;
        // 希尔排序的第一轮排序
        // 第一轮排序，将10个数据分为了10 / 2 = 5组
        for (int i = 5; i < array.length; i++) {
            for (int j = i - 5; j >= 0; j -= 5) {
                // 如果当前元素大于加上步长后的那个元素，则交换
                if (array[j] > array[j + 5]) {
                    temp = array[j];
                    array[j] = array[j + 5];
                    array[j + 5] = temp;
                }
            }
        }
        System.out.println("希尔排序第一轮排序后为：" + Arrays.toString(array));

        // 希尔排序的第二轮排序
        // 第二轮排序，将10个数据分为了10 / 2 / 2 = 2组
        for (int i = 2; i < array.length; i++) {
            for (int j = i - 2; j >= 0; j -= 2) {
                // 如果当前元素大于加上步长后的那个元素，则交换
                if (array[j] > array[j + 2]) {
                    temp = array[j];
                    array[j] = array[j + 2];
                    array[j + 2] = temp;
                }
            }
        }
        System.out.println("希尔排序第二轮排序后为：" + Arrays.toString(array));

        // 希尔排序的第三轮排序
        // 第三轮排序，将10个数据分为了10 / 2 / 2 / 2 = 1组
        for (int i = 1; i < array.length; i++) {
            for (int j = i - 1; j >= 0; j -= 1) {
                // 如果当前元素大于加上步长后的那个元素，则交换
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println("希尔排序第三轮排序后为：" + Arrays.toString(array));
    }

    /**
     * 希尔排序(移位法)
     * @param array  数组
     */
    public static void shellSortMove(int[] array) {
        int temp = 0;
        int count = 0;
        long start = System.currentTimeMillis();
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            // 希尔排序的第gap轮排序
            // 将10个数据分为了gap组
            for (int i = gap; i < array.length; i++) {
                int j = i;
                temp = array[j];
                if (array[j] < array[j - gap]) {
                    while (j - gap >= 0 && temp < array[j - gap]) {
                        // 移动
                        array[j] = array[j - gap];
                        j -= gap;
                    }
                    array[j] = temp;
                }
            }
            System.out.println("希尔排序第" + (++count) + "轮排序后为：" + Arrays.toString(array));
        }
        long end = System.currentTimeMillis();
        System.out.println("排序共花费了：" + (end - start) + "毫秒");
    }

    public static void test(int[] array) {
        int temp = 0;
        int count = 0;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            // 希尔排序的第gap轮排序
            // 将10个数据分为了gap组
            for (int i = gap; i < array.length; i++) {
                int j = i;
                temp = array[j];
                if (array[j] < array[j - gap]) {
                    while (j - gap >= 0 && temp < array[j - gap]) {
                        // 移动
                        array[j] = array[j - gap];
                        j -= gap;
                    }
                    array[j] = temp;
                }
            }
            System.out.println("希尔排序第" + (++count) + "轮排序后为：" + Arrays.toString(array));
        }
    }
}
