package com.paofu.data_structure.day05.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/30 19:29
 * 图示例
 * 深度优先遍历算法步骤：
 *
 * 1：访问初始结点v，并标记结点v为已访问
 *
 * 2：查找结点v的第一个邻接结点w
 *
 * 3：若w存在，则继续执行4，如果w不存在，则回到第1步，将从v的下一个结点继续
 *
 * 4：若w未被访问，对w进行深度优先遍历递归（即把w当做另一个v，然后进行步骤123）
 *
 * 5：查找结点v的w邻接结点的下一个邻接结点，转到步骤3
 *
 * 广度优先遍历算法步骤
 *
 * 1：访问初始结点v并标记结点v为已访问
 *
 * 2：结点v入队列
 *
 * 3：当队列非空时，继续执行，否则算法结束
 *
 * 4：出队列，取得队头结点u
 *
 * 5：查找结点u的第一个邻接结点w
 *
 * 6：若结点u的邻接结点w不存在，则转到步骤3；否则循环执行以下三个步骤
 *
 * 6.1：若结点w尚未被访问，则访问结点w并标记为已访问
 *
 * 6.2：结点w入队列
 *
 * 6.3：查找结点u的继w邻接结点后的下一个邻接结点w，转到步骤6
 */
public class Graph {

    /**
     * 存储顶点集合
     */
    private List<String> vertexList;

    /**
     * 存储图对应的邻接矩阵
     */
    private int[][] edges;

    /**
     * 表示边的数目
     */
    private int numOfEdges;

    /**
     * 记录某个顶点是否被访问过
     */
    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 5;
        String[] vertexValue = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String value : vertexValue) {
            graph.insertVertx(value);
        }
        // 添加边  A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.showGraph();

//        graph.dfs();
        graph.bfs();
    }

    public Graph(int n) {
        // 初始化矩阵和存储顶点集合
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    /**
     * 获取第一个邻接节点得到下标
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接节点的下标获取下一个邻接节点
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历
     */
    public void dfs(boolean[] isVisited, int i) {
        System.out.println(getValue(i) + "-->");
        isVisited[i] = true;
        int firstNeighbor = getFirstNeighbor(i);
        while (firstNeighbor != -1) {
            if (!isVisited[firstNeighbor]) {
                dfs(isVisited, firstNeighbor);
            }
            firstNeighbor = getNextNeighbor(i, firstNeighbor);
        }
    }

    /**
     * 对dfs进行重载,遍历所有节点，进行dfs
     */
    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 对一个节点进行广度优先遍历
     */
    public void bfs(boolean[] isVisited, int i) {
        // 队列头节点的下标
        int u;
        int w;
        // 队列  记录节点访问的顺序
        LinkedList queue = new LinkedList<>();
        System.out.println(getValue(i) + "-->");
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()) {
            u = (Integer) queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.println(getValue(w) + "-->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    /**
     * 返回节点的个数
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 获取边的数目
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回节点i对应的数据
     */
    public String getValue(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回v1和v2的权值
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 显示图对应的矩阵
     */
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 插入
     * @param vertx  顶点
     */
    public void insertVertx(String vertx) {
        vertexList.add(vertx);
    }

    /**
     * 添加边
     * @param v1 表示点的下标 即第几个顶点
     * @param v2 表示点的下标 即第几个顶点
     * @param weight 权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
