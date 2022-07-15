package com.paofu.data_structure.demo.DynamicProgramming;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/3/2 15:58
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 */
public class Demo01 {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        // 物品的重量数组
        int[] weight = {1, 4, 3};
        // 物品的价值数组
        int[] value = {1500, 3000, 2000};
        // 背包的容量
        int packWeight = 4;
        // 物品的总数
        int count = value.length;
        // 二维数组 定义前i个物品在j容量的最大价值
        int[][] result = new int[count + 1][packWeight + 1];

        int[][] path = new int[count + 1][packWeight + 1];

        // 第一列和第一行赋值为0
        for (int i = 0; i <= count; i++) {
            result[i][0] = 0;
        }
        Arrays.fill(result[0], 0);

    }
}
