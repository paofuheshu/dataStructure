package com.paofu.data_structure.day02.linkedList;

import lombok.Data;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/7 21:18
 * 双向链表模拟
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode2 node1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 node2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 node3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 node4 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 node5 = new HeroNode2(2, "李逵", "黑旋风");
        // 创建单向链表
        DoubleLikedList doubleLikedList = new DoubleLikedList();
        doubleLikedList.addByOrder(node3);
        doubleLikedList.addByOrder(node1);
        doubleLikedList.addByOrder(node4);
        doubleLikedList.addByOrder(node2);

        doubleLikedList.list();
        System.out.println("--------------------");
        doubleLikedList.update(node5);
        doubleLikedList.list();
        System.out.println("--------------------");
        doubleLikedList.delete(node3);
        doubleLikedList.list();
    }
}

class DoubleLikedList {

    /**
     * 先初始化一个头节点，不存放数据
     */
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 不考虑编号顺序时，找到最后一个节点指向本节点
     * 添加节点到单向列表
     * @param heroNode
     */
    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        // 当退出while时 temp指向最后一个节点
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /**
     * 根据顺序添加
     * @param heroNode
     */
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
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
            heroNode.pre = temp;
            if (temp.next != null) {
                temp.next.pre = heroNode;
            }

            temp.next = heroNode;
        }
    }

    /**
     * 根据编号修改节点
     */
    public void update(HeroNode2 heroNode) {
        HeroNode2 temp = head.next;
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
    public void delete(HeroNode2 heroNode) {
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp.getNumber() == heroNode.getNumber()) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
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
        HeroNode2 temp = head.next;
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
class HeroNode2 {
    public int number;

    public String name;

    public String nickname;

    public HeroNode2 next;

    public HeroNode2 pre;

    public HeroNode2(int heroNumber, String heroName, String heroNickname) {
        this.number = heroNumber;
        this.name = heroName;
        this.nickname = heroNickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
