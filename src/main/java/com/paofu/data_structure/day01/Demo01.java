package com.paofu.data_structure.day01;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/1 19:51
 * 传入一个正整数N 打印出1-N的所有正整数
 */
public class Demo01 {

    /**
     * 此处数据量足够大时，比如1000000时，递归循环会发生堆栈溢出
     * @param args
     */
    public static void main(String[] args) {
        test1(1000000);
        System.out.println("------------------");
        test2(1000000);
    }

    /**
     * 直接循环
     * @param num
     */
    public static void test1(int num) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            System.out.println(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("花费了" + (end - start) + "毫秒");
    }

    /**
     * 递归循环
     * @param num
     */
    public static void test2(int num) {
        long start = System.currentTimeMillis();
        if (num > 0) {
            System.out.println(num);
            test2(num - 1);
        }
        long end = System.currentTimeMillis();
        System.out.println("花费了" + (end - start) + "毫秒");
    }

}
