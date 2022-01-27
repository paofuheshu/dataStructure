package com.paofu.data_structure.demo.day01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/17 16:09
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * 示例 1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 * 输入：digits = [0]
 * 输出：[1]
 * 示例 4：
 * 输入：digits = [9,8,7,6,5,4,3,2,1,0];
 * 输出：[9,8,7,6,5,4,3,2,1,1]
 */
public class Demo14 {

    public static void main(String[] args) {
        int[] digits = {9};
        System.out.println(Arrays.toString(plusOne(digits)));
    }

    public static int[] plusOne(int[] digits) {
        int[] resList = new int[digits.length];
        int count = digits.length - 1;
        boolean flag = false;
        int pop = digits[digits.length - 1];
        if (pop == 9) {
            pop = 0;
            flag = true;
        } else {
            pop = pop + 1;
        }
        resList[count] = pop;
        for (int i = digits.length - 2; i >= 0; i--) {
            int integer = digits[i];
            if (flag) {
                if (integer == 9) {
                    integer = 0;
                } else {
                    integer = integer + 1;
                    flag = false;
                }
            }
            resList[--count] = integer;
        }
        if (!flag) {
            return resList;
        } else {
            int[] resArray = new int[digits.length + 1];
            resArray[0] = 1;
            System.arraycopy(resList, 0, resArray, 1, resList.length);
            return resArray;
        }
    }
}
