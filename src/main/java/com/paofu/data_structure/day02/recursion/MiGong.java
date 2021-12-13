package com.paofu.data_structure.day02.recursion;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/13 19:53
 * 迷宫问题
 */
public class MiGong {

    public static void main(String[] args) {
        // 先创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7];
        // 使用1，表示墙，不能经过
        // 上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;
        // 遍历
        System.out.println("地图的展示-------->");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(findWay1(map, 1, 1));

        // 遍历
        System.out.println("小球走过并标识过的地图-------->");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回调给小球找路
     * i j 表示map[i][j]开始找
     * 如果小球能到map[6][5](终点)即找到
     * 约定 map[i][j] = 0时代表没有走过,1表示墙，2表示可以走 3表示该点已经走过，但走不通
     * 在开始前需要确定一个策略(方法) 下->右->上->左,如果该点走不通在回溯
     * @param map  地图
     * @param i  从哪开始找
     * @param j  从哪开始找
     * @return boolean
     */
    public static boolean findWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            // 当前点没有走过
            if (map[i][j] == 0) {
                // 按照策略走  下->右->上->左
                // 假设该点可以走
                map[i][j] = 2;
                // 向下走
                if (findWay(map, i + 1, j)) {
                    return true;
                } else if (findWay(map, i, j + 1)){
                    return true;
                } else if ( findWay(map, i - 1, j)){
                   return true;
                }  else if (findWay(map, i, j - 1)) {
                    return true;
                } else {
                    // 上下左右都走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * 使用递归回调给小球找路
     * i j 表示map[i][j]开始找
     * 如果小球能到map[6][5](终点)即找到
     * 约定 map[i][j] = 0时代表没有走过,1表示墙，2表示可以走 3表示该点已经走过，但走不通
     * 修改策略(方法) 上->右->下->左,如果该点走不通在回溯
     * @param map  地图
     * @param i  从哪开始找
     * @param j  从哪开始找
     * @return boolean
     */
    public static boolean findWay1(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            // 当前点没有走过
            if (map[i][j] == 0) {
                // 按照策略走  下->右->上->左
                // 假设该点可以走
                map[i][j] = 2;
                // 向下走
                if (findWay1(map, i - 1, j)) {
                    return true;
                } else if (findWay1(map, i, j + 1)){
                    return true;
                } else if ( findWay1(map, i + 1, j)){
                    return true;
                }  else if (findWay1(map, i, j - 1)) {
                    return true;
                } else {
                    // 上下左右都走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
