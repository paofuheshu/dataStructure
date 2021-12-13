package com.paofu.data_structure.day02.array;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/2 19:34
 * 稀疏数组
 */
public class SparseArray {

    public static void main(String[] args) {
        // 定义一个原始的二维数组(11 * 11的棋盘  1代表黑色棋子  2代表蓝色棋子 0代表无子)
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[4][3] = 2;
        // 遍历输出
        System.out.println("原始的二维数组为:");
        for (int[] row : chessArray) {
            for (int data : row) {
                System.out.printf("%d\t" ,data);
            }
            System.out.println();
        }

        // 将二维数据转为稀疏数组
        // 1: 先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for (int[] row : chessArray) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }
        // 2: 创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        // 3: 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        // 4: 遍历二维数据，将非0的数组放入稀疏数组
        // 定义count 用于记录是稀疏数组的第几行
        int count = 1;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray[i][j] != 0) {
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArray[i][j];
                    count++;
                }
            }
        }

        // 遍历输出稀疏数组
        System.out.println("转换后的稀疏数组为:");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        // 将转换后的稀疏数组还原成二维数据
        int row = sparseArr[0][0];
        int column = sparseArr[0][1];
        int[][] chessArray1 = new int[row][column];

        for (int i = 1; i < sparseArr.length; i++) {
            chessArray1[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        // 遍历还原后的二维数组
        System.out.println("还原后的二维数组为:");
        for (int[] row1 : chessArray1) {
            for (int data : row1) {
                System.out.printf("%d\t" ,data);
            }
            System.out.println();
        }
    }
}
