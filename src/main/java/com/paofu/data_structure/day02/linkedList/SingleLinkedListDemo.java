package com.paofu.data_structure.day02.linkedList;

import lombok.Data;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/6 19:57
 * 单向列表
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode node2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode node3 = new HeroNode(3, "吴用", "智多星");
        HeroNode node4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode node5 = new HeroNode(2, "李逵", "黑旋风");
        // 创建单向链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(node3);
        singleLinkedList.addByOrder(node2);
        singleLinkedList.addByOrder(node1);
        singleLinkedList.addByOrder(node4);
        // 修改2号
        singleLinkedList.update(node5);
        // 删除3号
        singleLinkedList.delete(node3);
        singleLinkedList.list();
        // 获取链表的有效个数
        int linkedListNum = SingleLinkedListUtil.getLinkedListNum(singleLinkedList.getHead());
        System.out.println("有效节点个数为：" + linkedListNum + "个");
        // 查找倒数第二个节点
        System.out.println(SingleLinkedListUtil.getLastIndexNode(singleLinkedList.getHead(), 1));
        // 翻转单链表
        SingleLinkedListUtil.reverseList(singleLinkedList.getHead());
        singleLinkedList.list();
        // 逆序打印(链表本身结构没有发生变化)
        SingleLinkedListUtil.showStackList(singleLinkedList.getHead());

    }
}

/**
 * 定义一个singleLinkedList管理
 */
class SingleLinkedList {
    /**
     * 先初始化一个头节点，不存放数据  头节点不能动
     */
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 不考虑编号顺序时，找到最后一个节点指向本节点
     * 添加节点到单向列表
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        // 当退出while时 temp指向最后一个节点
        temp.next = heroNode;
    }

    /**
     * 根据顺序添加
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        // 标识添加的编号是否存在
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.number > heroNode.number) {
                break;
            } else if (temp.next.number  == heroNode.number) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            System.out.println("编号已经存在，无法添加");
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 根据编号修改节点
     */
    public void update(HeroNode heroNode) {
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp.getNumber() == heroNode.getNumber()) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else {
            System.out.println("没有找到编号相同的节点，无法修改");
        }
    }

    /**
     * 根据编号删除节点
     */
    public void delete(HeroNode heroNode) {
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp.next.getNumber() == heroNode.getNumber()) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("没有找到编号相同的节点，无法修改");
        }
    }

    /**
     * 显示遍历链表
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            // 将temp后移
            temp = temp.next;
        }
    }

}

/**
 * 定义HeroNode节点对象
 */
@Data
class HeroNode {
    public int number;

    public String name;

    public String nickname;

    public HeroNode next;

    public HeroNode(int heroNumber, String heroName, String heroNickname) {
        this.number = heroNumber;
        this.name = heroName;
        this.nickname = heroNickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
