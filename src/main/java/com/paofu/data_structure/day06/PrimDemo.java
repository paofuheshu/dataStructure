package com.paofu.data_structure.day06;

import lombok.Data;

import java.util.Arrays;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2022/1/5 19:48
 * 普利姆算法
 */
public class PrimDemo {

    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        // 10000表示二者不连通
        int[][] weight = new int[][] {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},
        };
        Graph graph = new Graph(7);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);
        minTree.showGraph(graph);
        minTree.prim(graph, 0);
    }
}

class MinTree {
    /**
     * 创建图的邻接矩阵
     * @param graph 图对象
     * @param verxs 对应顶点个数
     * @param data  顶点值
     * @param weight 邻接矩阵
     */
    public void createGraph(Graph graph, int verxs, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    /**
     * 显示图的方法
     */
    public void showGraph(Graph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 编写普利姆算法得到最小生成树
     * @param graph 图
     * @param v 从图的第几个顶点开始生成
     */
    public void prim(Graph graph, int v) {
        // 标记节点是否被访问过
        int[] visited = new int[graph.verxs];
        // 把当前节点标记为以访问
        visited[v] = 1;
        // 记录h1和h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        // 有k个顶点  则结束后应该有k-1条边
        for (int k = 1; k < graph.verxs; k++) {

            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    // i节点被访问过，j节点没被访问过
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值：" + minWeight);
            visited[h2] = 1;
            minWeight = 10000;
        }
    }
}

@Data
class Graph {
    /**
     * 表示图的节点个数
     */
    int verxs;

    /**
     * 存放节点数据
     */
    char[] data;

    /**
     * 存放边，就是邻接矩阵
     */
    int[][] weight;

    public Graph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
