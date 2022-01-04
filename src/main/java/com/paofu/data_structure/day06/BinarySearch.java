package com.paofu.data_structure.day06;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2022/1/4 18:58
 * 二分查找(非递归)
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] array = {1, 3, 8, 10, 11, 67, 100};
        System.out.println(binarySearch(array, 8));
    }

    /**
     * 二分查找(非递归)
     * @param array  数组
     * @param target  查找数
     * @return  下标
     */
    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid]  == target) {
                return mid;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
