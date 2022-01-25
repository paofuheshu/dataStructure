package com.paofu.data_structure.demo.day01;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/14 15:36
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 * 分治算法
 */
public class Demo12 {

    public static void main(String[] args) {
        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(array));
    }

    public static int maxSubArray(int[] nums) {
        int res = 0;
        return res;
    }

    public static int divideAndConquer(int begin, int end, int[] nums) {
        return 0;
    }
}
