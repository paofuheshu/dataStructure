package com.paofu.data_structure.day06;

import java.util.Arrays;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2022/1/6 18:52
 * 迪杰斯特拉算法示例
 */
public class DijkstraDemo {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        int[][] matrix = new int[][] {
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N},
        };
        DijkstraGraph dijkstraGraph = new DijkstraGraph(vertex, matrix);
        dijkstraGraph.show();
        dijkstraGraph.dijkstra(6);
        dijkstraGraph.showDijkstra();
    }
}

class DijkstraGraph {

    /**
     * 顶点数组
     */
    private char[] vertex;

    /**
     * 邻接矩阵
     */
    private int[][] matrix;

    /**
     * 已经访问顶点的集合
     */
    private VisitedVertex visitedVertex;

    public DijkstraGraph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showDijkstra() {
        visitedVertex.show();
    }

    public void show() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 迪杰斯特拉算法实现
     * @param index  出发顶点的下标
     */
    public void dijkstra(int index) {
        visitedVertex = new VisitedVertex(vertex.length, index);
        // 更新index顶点到周围顶点的距离和前驱节点
        update(index);

        for (int i = 1; i < vertex.length; i++) {
            // 选择并返回新的访问顶点
            index = visitedVertex.updateArray();
            update(index);
        }
    }

    /**
     * 更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
     */
    public void  update(int index) {
        int len = 0;
        // 遍历邻接矩阵的index行
        for (int i = 0; i < matrix[index].length; i++) {
            // len = 出发顶点到index顶点的距离 + 从index顶点到i顶点的距离
            len = visitedVertex.getDis(index) + matrix[index][i];
            // 如果i顶点没有被访问过，并且len小于出发顶点到i顶点的距离，就更新
            if (!visitedVertex.judgeVisited(i) && len < visitedVertex.getDis(i)) {
                visitedVertex.updatePreVisited(i, index);
                visitedVertex.updateDis(i, len);
            }
        }
    }
}

class VisitedVertex {
    /**
     * 记录各个顶点是否访问过 1表示访问过，0未访问，会动态更新
     */
    public int[] alreadyArr;

    /**
     * 每个下标对应的值为前一个顶点下标，会动态更新
     */
    public int[] preVisited;

    /**
     * 记录出发点到其他所有点的距离，会动态更新，求出的最短距离会放到dis中
     */
    public int[] dis;

    /**
     * 构造器
     * @param length  顶点的个数
     * @param index   出发顶点对应的下标
     */
    public VisitedVertex(int length, int index) {
        this.alreadyArr = new int[length];
        this.preVisited = new int[length];
        this.dis = new int[length];

        // 初始化dis
        Arrays.fill(dis, 65535);
        this.dis[index] = 0;
        // 设置出发顶点被访问过
        this.alreadyArr[index] = 1;
    }

    /**
     * 判断index下标对应的顶点是否被访问过
     * @param index  下标
     * @return  boolean
     */
    public boolean judgeVisited(int index) {
        return alreadyArr[index] == 1;
    }

    /**
     * 更新出发顶点到index下标顶点的距离
     * @param index  下标
     * @param len  距离
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新顶点的前驱为Index节点
     * @param pre 顶点下标
     * @param index 下标
     */
    public void updatePreVisited(int pre, int index) {
        preVisited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     * @param index  顶点下标
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点，比如G顶点结束后，就以A顶点作为新的访问顶点(注意不是出发顶点)
     */
    public int updateArray() {
        int min = 65535;
        int index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            if (alreadyArr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        alreadyArr[index] = 1;
        return index;
    }

    /**
     * 显示最后的结果,即输出三个数组的数据
     */
    public void show() {
        System.out.println(Arrays.toString(alreadyArr));
        System.out.println(Arrays.toString(preVisited));
        System.out.println(Arrays.toString(dis));
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int di : dis) {
            if (di != 65535) {
                System.out.print(vertex[count] + "(" + di + ")");
            } else {
                System.out.println("N");
            }
            count++;
        }
        System.out.println();
    }
}
