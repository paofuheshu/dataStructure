package com.paofu.data_structure.day02.array;

import java.util.Scanner;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/2 20:57
 * 用数组实现模拟队列
 * 此例存在的问题  使用一次就不能使用了，取数据并没有真正的从数组中删除，没有达到复用的效果，后续优化成一个环形队列(取模的方式)
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        // 创建队列 此时最大有效数据为3  因为留出了一个空间做约定

        CircleArrayQueue arrayQueue = new CircleArrayQueue(4);
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

class CircleArrayQueue {

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

    public CircleArrayQueue(int arrayMaxSize) {
        maxSize = arrayMaxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    /**
     * 判断队列是否满了
     * @return  boolean
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
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
        // 分析front是指向队列的第一个元素
        // 1.先把front对应的值保留到一个临时变量
        // 2.将front后移
        // 3.将临时变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**
     * 显示队列的所有数据
     */
    public void showArr() {
        if (isEmpty()) {
            System.out.println("队列是空的，没有数据");
            return;
        }
        // 从front开始遍历

        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 求出当前队列有效数据的个数
     * @return
     */
    public int size () {
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 显示队列的头数据和尾数据
     *
     * @return  int
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的，没有头数据");
        }
        return arr[front];
    }

}

