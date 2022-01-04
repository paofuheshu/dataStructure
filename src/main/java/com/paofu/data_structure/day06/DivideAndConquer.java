package com.paofu.data_structure.day06;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2022/1/4 19:16
 * 分治算法示例之汉诺塔
 */
public class DivideAndConquer {

    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
    }

    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            // 如果n>=2，我们总是可以看做为两个盘 1：最下边的一个盘 2：上面的所有盘
            // 1:先把最上面的盘从A->B
            hanoiTower(num - 1, a, c, b);
            // 2:把最下面的盘从A->C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            // 3:把B塔的所有盘从B->C
            hanoiTower(num - 1, b, a, c);
        }
    }
}
