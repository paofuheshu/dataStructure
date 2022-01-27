package com.paofu.data_structure.demo.day02;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/26 11:40
 * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false
 * 输入：nums = [1,2,3,1]
 * 输出：true
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 输入：nums = [1,1,1,3,3,4,3,2,4,2]
 * 输出：true
 */
public class Demo04 {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(containsDuplicate(nums));
    }

    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            boolean add = hashSet.add(num);
            if (!add) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsDuplicate1(int[] nums) {
        Set<Integer> collect = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        return collect.size() != nums.length;
    }

    public static boolean containsDuplicate2(int[] nums) {
        for (int j = 0; j < nums.length; j++) {
            int temp = nums[j];
            for (int i = j + 1; i < nums.length; i++) {
                if (nums[i] == temp) {
                    return true;
                }
            }
        }
        return false;
    }
}
