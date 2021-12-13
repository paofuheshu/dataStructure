package com.paofu.data_structure.day02.recursion;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/13 19:25
 * 测试递归
 */
public class RecursionTest {

    public static void main(String[] args) {
        // 此处注意三种方法打印出来的结果不同，由于调用执行的顺序不一样
        test1(10);
        System.out.println("----------------------");
        test2(10);
        System.out.println("----------------------");
        test3(10);
        System.out.println("10的阶乘是：" + test4(10));
    }

    public static void test1(int num) {
        if (num >= 0) {
            test1(num - 1);
            System.out.println("当前打印数字是：" + num);
        }
    }

    public static void test2(int num) {
        if (num >= 0) {
            System.out.println("当前打印数字是：" + num);
            test2(num - 1);
        }
    }

    public static void test3(int num) {
        if (num >= 0) {
            test3(num - 1);
        }
        System.out.println("当前打印数字是：" + num);
    }

    public static int test4(int num) {
        if (num == 1) {
            return 1;
        } else {
            return test4(num - 1) * num;
        }
    }
}
