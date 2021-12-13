package com.paofu.data_structure.day02.linkedList;

import lombok.Data;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/8 19:21
 */
public class CircleSingleLinkedListDemo {

    public static void main(String[] args) {
        // 测试构建和遍历
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(200);
        circleSingleLinkedList.list();
        circleSingleLinkedList.countBoy(10, 5, 10);
    }
}

/**
 * 创建环形单向列表
 */
class CircleSingleLinkedList {
    /**
     * 创建一个first节点，当前没有编号
     */
    private Boy first = null;

    /**
     * 添加小孩节点，形成环形链表
     * @param nums 共有几个节点
     */
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;
        // 创建链表
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }

        }
    }

    /**
     * 遍历链表
     */
    public void list() {
        if (first == null) {
            System.out.println("当前链表为空");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.println("小孩的编号为：" + curBoy.getNumber());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 根据输入 计算出小孩出圈的顺序
     * @param startNum  第几个小孩开始数
     * @param countNum  数几个
     * @param nums  最初有多少小孩
     */
    public void countBoy(int startNum, int countNum, int nums) {
        if (first == null || startNum < 1 || startNum > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        // 将helper首先指向最后一个节点
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        // 小孩报数前 先让first 和 helper 移动 startNum-1次
        for (int i = 0; i < startNum - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 小孩报数时，让first 和 helper移动countNum - 1次 然后出圈
        int count = 0;
        while (true) {
            if (helper == first) {
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // first指向的就是要出圈的
            System.out.println("此次出圈的小孩的编号为：" + first.getNumber());
            count++;
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后留在圈中的小孩的编号为：" + first.getNumber());
        System.out.println("总共出圈了：" + (count + 1) + "个小孩");
    }
}

/**
 * 创建boy类
 */
@Data
class Boy {
    private int number;

    private Boy next;

    public Boy(int number) {
        this.number = number;
    }
}
