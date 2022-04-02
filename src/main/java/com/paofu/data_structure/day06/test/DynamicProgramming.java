package com.paofu.data_structure.day06.test;

import java.util.Arrays;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/3/1 15:39
 */
public class DynamicProgramming {

    public static void main(String[] args) {
        test2();
//        test1();
    }

    public static void test1() {
        // 物品的重量数组
        int[] weight = {1, 4, 3};
        // 物品的价值
        int[] value = {1500, 3000, 2000};
        // 背包的容量
        int packWeight = 4;
        // 表示物品的个数
        int count = value.length;
        // 二维数组,v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[count + 1][packWeight + 1];
        int[][] path = new int[count + 1][packWeight + 1];

        // 初始化第一行，第一列为0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        Arrays.fill(v[0], 0);
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                // v[i][j]                      表示在前i个物品中能够装入容量为j的背包中的最大价值
                // v[i - 1][j]                  表示在前i - 1个物品中能够装入容量为j的背包中的最大价值
                // value[i - 1]                 表示当前商品的价值
                // v[i - 1][j - weight[i - 1]]  装入i-1商品，到剩余空间j-w[i]的最大值
                if (weight[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    if (v[i - 1][j] > value[i - 1] + v[i - 1][j - weight[i - 1]]) {
                        v[i][j] = v[i - 1][j];
                    } else {
                        v[i][j] = value[i - 1] + v[i - 1][j - weight[i - 1]];
                        path[i][j] = 1;
//                        System.out.println("添加物品");
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

        for (int[] ints : path) {
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
        System.out.println();
    }

    public static void test2() {
        // 物品的重量数组
        int[] weight = {1, 4, 3};
        // 物品的价值
        int[] value = {1500, 3000, 2000};
        // 背包的容量
        int packWeight = 4;
        // 表示物品的个数
        int count = value.length;
        // 二维数组,v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] result = new int[count + 1][packWeight + 1];
        int[][] path = new int[count + 1][packWeight + 1];
        for (int i = 1; i < result.length; i++) {
            for (int j = 1; j < result[0].length; j++) {
                // 如果当前物品重量大于背包容量，则在前i个物品中能够装入容量为j的背包中的最大价值继承前i - 1个物品的最大价值
                if (weight[i - 1] > j) {
                    result[i][j] = result[i - 1][j];
                } else {
                    if (result[i - 1][j] > value[i - 1] + result[i - 1][j - weight[i - 1]]) {
                        result[i][j] = result[i - 1][j];
                    } else {
                        result[i][j] = value[i - 1] + result[i - 1][j - weight[i - 1]];
                        path[i][j] = 1;
                    }
                }
            }
        }
        for (int[] ints : result) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        for (int[] ints : path) {
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
