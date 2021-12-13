package com.paofu.data_structure.day02.stack;

import java.util.Scanner;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/8 20:43
 * 使用数组模拟栈
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        // 测试
        ArrayStack arrayStack = new ArrayStack(4);
        String key = "";
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while (flag) {
            System.out.println("show：显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：添加数据");
            System.out.println("pop：取出数据");
            System.out.println("请输入指令");
            key = scanner.next();
            switch (key) {
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int pop = arrayStack.pop();
                        System.out.println("出栈数据为：" + pop);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    flag = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出了");
    }
}

/**
 * 定义一个栈结构
 */
class ArrayStack {

    /**
     * 栈的大小
     */
    private int maxSize;

    /**
     * 数组模拟栈，数据存放在数组中
     */
    private int[] stack;

    /**
     * 栈顶  初始化为-1
     */
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 判断栈满
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     * @param value 入栈值
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已经满了，无法入栈");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已经空了，无法出栈");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历栈
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.println("栈的第" + i + "个元素值为：" + stack[i]);
        }
    }
}
