package com.paofu.data_structure.demo.day01;

import java.util.StringJoiner;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/13 17:09
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * 示例 4:
 * 输入: nums = [1,3,5,6], target = 0
 * 输出: 0
 * 示例 5:
 * 输入: nums = [1], target = 0
 * 输出: 0
 */
public class Demo11 {

    public static void main(String[] args) {
        // 2 2 < 3   5  5 <= 5
        int[] array = {1, 3, 5, 6};
        int target = 5;
        System.out.println(searchInsert(array, target));

    }

    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
