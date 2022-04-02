package com.paofu.data_structure.day06.test;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 3/15/2022 11:05 AM
 */
public class DynamicProgrammingTest {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        // 3个物品的重量数组
        int[] weight = {1, 4, 3};
        // 3个物品的价值数组
        int[] value = {1500, 3000, 2000};
        // 背包容量
        int packWeight = 4;
        // 物品个数
        int count = value.length;
        // 二维数组,result[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] result = new int[count + 1][packWeight + 1];
        int[][] path = new int[count + 1][packWeight + 1];

        for (int i = 1; i < result.length; i++) {
            for (int j = 1; j < result[0].length; j++) {
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


    public static void test2() {

        // 物品重量数组
        int[] weight = {1, 4, 3};
        // 物品价值数组
        int[] value = {1500, 3000, 2000};
        // 背包总容量
        int packWeight = 4;
        // 物品的数量
        int count = value.length;
        // 二维数组,result[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] result = new int[count + 1][weight.length + 1];
        int[][] path = new int[count + 1][weight.length + 1];


    }
}
