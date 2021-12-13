package com.paofu.data_structure.day02.recursion;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/13 21:04
 * 八皇后问题
 */
public class EightQueue {

    /**
     * 定义一个max表示共有几个皇后
     */
    public static int MAX = 8;

    /**
     * 定义数组，保存皇后放置位置的结果
     */
    public static int[] ARRAY = new int[MAX];

    public static int count = 0;

    public static void main(String[] args) {
        putQueue(0);
        System.out.println("八皇后一共有" + count + "种解法");
    }

    /**
     * 将皇后摆放的位置输出
     */
    public static void print() {
        for (int i = 0; i < ARRAY.length; i++) {
            System.out.print(ARRAY[i] + " ");
        }
        System.out.println();
        count++;
    }

    /**
     * 判断当我们放置第n个皇后时，是否和前面n-1个皇后冲突
     * @param num 代表第n个皇后
     * @return boolean
     */
    public static boolean judgeConflict(int num) {
        for (int i = 0; i < num; i++) {
            // ARRAY[i] == ARRAY[num] 判断第n个皇后是否和前面的n-1个皇后在同一列
            // Math.abs(num - i)  == Math.abs(ARRAY[num] - ARRAY[i]) 第num个皇后是否和第i个皇后在同一斜线
            if (ARRAY[i] == ARRAY[num] || Math.abs(num - i)  == Math.abs(ARRAY[num] - ARRAY[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 放置第n个皇后
     */
    public static void putQueue(int num) {
        if (num == MAX) {
            print();
            return;
        }
        // 依次放入皇后并判断是否冲突
        for (int i = 0; i < MAX; i++) {
            // 先把当前皇后(n)放到该行的第一列上
            ARRAY[num] = i;
            // 判断是否冲突
            if (judgeConflict(num)) {
                // 接着放置n+1个皇后，开始递归
                putQueue(num + 1);
            }
            // 如果冲突，继续执行array[num] = i,即接着进入for循环
        }
    }


}
