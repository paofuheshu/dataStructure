package com.paofu.data_structure.day06;

import java.util.Arrays;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2022/1/4 19:30
 * 动态规划算法之背包问题
 */
public class DynamicProgramming {
    public static void main(String[] args) {
        // 物品的重量
        int[] weight = {1, 4, 3};
        // 物品的价值
        int[] value = {1500, 3000, 2000};
        // 背包的容量
        int m = 4;
        // 表示物品的个数
        int n = value.length;
        // 二维数组，v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        int[][] path = new int[n + 1][m + 1];

        // 初始化第一行，第一列为0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        Arrays.fill(v[0], 0);
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (weight[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    if (v[i - 1][j] > value[i - 1] + v[i - 1][j - weight[i - 1]]) {
                        v[i][j] = v[i - 1][j];
                    } else {
                        v[i][j] = value[i - 1] + v[i - 1][j - weight[i - 1]];
                        path[i][j] = 1;
                    }
                }
            }
        }
        for (int[] ints : v) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= weight[i - 1];
            }
            i--;
        }
    }

}
