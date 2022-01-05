package com.paofu.data_structure.day06;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2022/1/5 21:04
 * 克鲁斯卡尔算法示例
 */
public class KruskalDemo {

    /**
     * 边的个数
     */
    private int edgeNum;
    /**
     * 顶点数组
     */
    private char[] vertexs;
    /**
     * 邻接矩阵
     */
    private int[][] matrix;

    /**
     * 表示两个顶点不能连通
     */
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, 14},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0},
        };
        KruskalDemo kruskalDemo = new KruskalDemo(vertexs, matrix);
        kruskalDemo.kruskal();

    }

    public KruskalDemo(char[] vertexs, int[][] matrix) {
        int vlen = vertexs.length;

        // 初始化顶点
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        // 初始化边
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    public void kruskal() {
        // 表示最后结果数组的索引
        int index = 0;
        // 用于保存已有最小生成树的每个顶点在最小生成树的终点
        int[] ends = new int[edgeNum];
        // 创建结果数组
        EdgeData[] res = new EdgeData[edgeNum];

        // 获取图中所有的边集合
        EdgeData[] edges = getEdges();

        // 按照边的权值排序
        sortEdges(edges);

        // 遍历edges数组，将边添加到最小生成树中，判断准备加入的边是否形成了回路，如果没有，加入res
        for (int i = 0; i < edgeNum; i++) {
            // 获取到第i条边的第一个顶点
            int start = getPosition(edges[i].start);
            // 获取到第i条边的第二个顶点
            int end = getPosition(edges[i].end);

            // 获取第一个顶点在已有最小生成树的终点
            int m = getEnd(ends, start);
            // 获取第二个顶点在已有最小生成树的终点
            int n = getEnd(ends, end);
            //判断是否构成回路
            if (m != n) {
                ends[m] = n;
                res[index++] = edges[i];
            }
        }

        for (int i = 0; i < index; i++) {
            System.out.println(res[i]);
        }


    }

    public void show() {
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%10d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 对边进行排序
     */
    public void sortEdges(EdgeData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EdgeData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 返回顶点对应的下标
     */
    public int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中的边，放入EdgeData[]数组中
     * @return
     */
    public EdgeData[] getEdges() {
        int index = 0;
        EdgeData[] edgeData = new EdgeData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edgeData[index++] = new EdgeData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edgeData;
    }

    /**
     * 获取下标为i的顶点的终点下标，用于判断两个顶点的终点是否相同
     * @param ends 记录了各个顶点对应的终点是哪个，在遍历过程中逐步形成
     * @param i 传入顶点对应的下标
     * @return 下标为i的这个顶点对应终点的下标
     */
    public int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}

/**
 * 边对象
 */
class EdgeData {
    char start;

    char end;

    int weight;

    public EdgeData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EdgeData{" +
                "<" + start +
                "," + end +
                "> = " + weight +
                '}';
    }
}


