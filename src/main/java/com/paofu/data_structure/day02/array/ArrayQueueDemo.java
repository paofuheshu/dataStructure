package com.paofu.data_structure.day02.array;

import java.util.Scanner;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/2 20:57
 * 用数组实现模拟队列
 * 此例存在的问题  使用一次就不能使用了，取数据并没有真正的从数组中删除，没有达到复用的效果，后续优化成一个环形队列(取模的方式)
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        // 创建队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出队列");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showArr();
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int queue = arrayQueue.getQueue();
                        System.out.println("取出的数据是：" + queue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int queue = arrayQueue.headQueue();
                        System.out.println("队列的头数据是：" + queue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }

}

/**
 * 使用数组模拟编写一个ArrayDemo类
 */
class ArrayQueue {
    /**
     * 表示数组的最大容量
     */
    private int maxSize;
    /**
     * 队列头指针
     */
    private int front;
    /**
     * 队列尾指针
     */
    private int rear;
    /**
     * 存放数组的数组
     */
    private int[] arr;

    /**
     * 创建队列的构造器 rear 是队列最后[含] front 是队列最前元素[不含]
     * @param arrayMaxSize  数组最大存放数据量
     */
    public ArrayQueue(int arrayMaxSize) {
        maxSize = arrayMaxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;

    }

    /**
     * 判断队列是否满了
     * @return  boolean
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     * @return  boolean
     */
    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        // 判断队列是否满了
        if (isFull()) {
            System.out.println("队列已经满了，无法加入数据");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    /**
     * 获取队列的数据 数据出队列
     * @return  int
     */
    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("队列是空的，无法取出数据");
        }
        front++;
        return arr[front];
    }

    /**
     * 显示队列的所有数据
     */
    public void showArr() {
        if (isEmpty()) {
            System.out.println("队列是空的，没有数据");
            return;
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    /**
     * 显示队列的头数据和尾数据
     * @return  int
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的，没有头数据");
        }
        return arr[front+1];
    }
}
