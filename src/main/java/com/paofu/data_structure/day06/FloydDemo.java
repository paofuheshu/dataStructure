package com.paofu.data_structure.day06;

import java.util.Arrays;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2022/1/6 20:38
 * 弗洛伊德算法示例
 */
public class FloydDemo {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        int[][] matrix = new int[][] {
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0},
        };
        FloydGraph floydGraph = new FloydGraph(vertex.length, matrix, vertex);
        floydGraph.floyd();
        floydGraph.show();
    }
}

class FloydGraph {
    /**
     * 顶点数组
     */
    private char[] vertex;

    /**
     * 保存从各个顶点出发到其他顶点的距离
     */
    private int[][] dis;

    /**
     * 保存到达目标顶点的前驱顶点
     */
    private int[][] pre;

    /**
     * @param length  大小
     * @param matrix  邻接矩阵
     * @param vertex  顶点数组
     */
    public FloydGraph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];

        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    /**
     * 显示per数组和dis数组
     */
    public void show() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();
            for (int k = 0; k < dis.length; k++) {
                System.out.print("(" + vertex[i] + "到" + vertex[k] + "的距离是：" + dis[i][k] + ") ");
            }
            System.out.println();
            System.out.println();
        }
    }

    public void floyd() {
        // 保存距离
        int len = 0;
        // 对中间顶点的遍历
        for (int k = 0; k < dis.length; k++) {
            // 从i顶点开始出发
            for (int i = 0; i < dis.length; i++) {
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}
