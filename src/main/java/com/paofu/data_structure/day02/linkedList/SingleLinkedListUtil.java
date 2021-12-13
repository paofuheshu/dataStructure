package com.paofu.data_structure.day02.linkedList;

import java.util.Stack;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/7 19:19
 */
public class SingleLinkedListUtil {

    /**
     * 获取单链表的节点的个数(如果是带头节点的链表，不统计头节点)
     * @param heroNode
     * @return
     */
    public static int getLinkedListNum(HeroNode heroNode) {
        int num = 0;
        if (heroNode.next == null) {
            return 0;
        }
        HeroNode next = heroNode.next;
        while (next != null) {
            num++;
            next = next.next;
        }
        return num;
    }

    /**
     * 查找倒数第index个元素
      * @param heroNode
     * @param index
     * @return
     */
    public static HeroNode getLastIndexNode(HeroNode heroNode, int index) {
        if (heroNode.next == null) {
            return null;
        }
        // 得到链表的长度
        int length = getLinkedListNum(heroNode);
        // 遍历
        if (index <= 0 || index > length) {
            return null;
        }
        HeroNode node = heroNode.next;
        for (int i = 0; i < length - index; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * 将单链表翻转
     * @param heroNode
     */
    public static void reverseList(HeroNode heroNode) {
        if (heroNode.next == null || heroNode.next.next == null) {
            return;
        }
        HeroNode node = heroNode.next;
        HeroNode next = null;
        HeroNode reverseNode = new HeroNode(0,"", "");
        while (node != null) {
            next = node.next;
            node.next = reverseNode.next;
            reverseNode.next = node;
            node = next;
        }
        heroNode.next = reverseNode.next;
    }

    /**
     * 使用栈来进行逆序打印
     * @param heroNode
     */
    public static void showStackList(HeroNode heroNode) {
        if (heroNode.next == null) {
            return;
        }
        Stack<HeroNode> heroNodeStack = new Stack<>();
        HeroNode temp = heroNode.next;
        while (temp != null) {
            heroNodeStack.push(temp);
            temp = temp.next;
        }
        while (heroNodeStack.size() > 0) {
            System.out.println(heroNodeStack.pop());
        }
    }

    /**
     * 将两个有序的单向链表合并成一个
     * @param heroNode
     */
    public static void addOtherList(HeroNode heroNode) {
        // todo
    }
}
