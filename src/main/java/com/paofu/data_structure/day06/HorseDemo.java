package com.paofu.data_structure.day06;

import org.springframework.util.CollectionUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2022/1/9 13:09
 * 马踏棋盘算法(骑士周游问题)示例
 */
public class HorseDemo {

    /**
     * 棋盘的列数
     */
    private static int X;

    /**
     * 棋盘的行
     */
    private static int Y;

    /**
     * 标记棋盘的各个位置是否被访问过
     */
    private static boolean[] visited;

    /**
     * 标记是否棋盘的所有位置都被访问过了
     */
    private static boolean finished;

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        // 初始位置的行  从1开始
        int row = 1;
        // 初始位置的列  从1开始
        int column = 1;
        int[][] chess = new int[X][Y];
        visited = new boolean[X * Y];
        long start = System.currentTimeMillis();
        horse(chess, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("方法耗时：" + (end - start) + "毫秒");

        for (int[] rows : chess) {
            for (int columns : rows) {
                System.out.print(columns + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 算法
     * @param chess  棋盘
     * @param row  当前位置行 从0开始
     * @param column 当前位置列 从0开始
     * @param step 第几步 从1开始
     */
    public static void horse(int[][] chess, int row, int column, int step) {
        chess[row][column] = step;
        // 标记该位置已经访问
        visited[row * X + column] = true;
        // 获取当前位置下一步可以走的集合
        List<Point> nextList = next(new Point(column, row));
        sort(nextList);
        // 遍历
        while (!CollectionUtils.isEmpty(nextList)) {
            Point point = nextList.remove(0);
            if (!visited[point.y * X + point.x]) {
                horse(chess, point.y, point.x, step + 1);
            }
        }
        // 判断是否完成
        if (step < X * Y && !finished) {
            chess[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }

    }

    /**
     * 根据当前位置(Point对象) 计算下一步还能走那些位置 并放入一个集合中，根据分析，最多只有8个位置
     * @param curPoint  当前点
     * @return 下一个点的集合
     */
    public static List<Point> next(Point curPoint) {
        // 创建一个集合
        List<Point> list = new ArrayList<>();
        // 创建Point
        Point point = new Point();
        // 判断下一步的8种位置是否符合
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y - 1) >= 0) {
            list.add(new Point(point));
        }
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y - 2) >= 0) {
            list.add(new Point(point));
        }
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y - 2) >= 0) {
            list.add(new Point(point));
        }
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y - 1) >= 0) {
            list.add(new Point(point));
        }
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y + 1) < Y) {
            list.add(new Point(point));
        }
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y + 2) < Y) {
            list.add(new Point(point));
        }
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y + 2) < Y) {
            list.add(new Point(point));
        }
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y + 1) < Y) {
            list.add(new Point(point));
        }

        return list;

    }


    /**
     * 根据当前这一步的所有的下一步的选择位置进行非递减排序,可以大幅度提高算法效率
     */
    public static void sort(List<Point> list) {
        list.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                // 先获取o1的下一步的所有位置个数
                int size1 = next(o1).size();
                int size2 = next(o2).size();
                if (size1 < size2) {
                    return -1;
                } else if (size1 == size2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}
