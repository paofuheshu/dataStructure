package com.paofu.data_structure.demo.day01;

import lombok.Data;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/24 15:12
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 * 返回同样按升序排列的结果链表。
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 */
public class Demo19 {

    public static void main(String[] args) {
        ListNode2 listNode1 = new ListNode2(1);
        ListNode2 listNode2 = new ListNode2(1);
        ListNode2 listNode3 = new ListNode2(3);
        ListNode2 listNode4 = new ListNode2(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        System.out.println(deleteDuplicates(listNode1));
    }

    public static ListNode2 deleteDuplicates(ListNode2 head) {
        ListNode2 temp = head;
        while(temp != null && temp.next != null){
            if(temp.val == temp.next.val) {
                temp.next = temp.next.next;
            }else {
                temp = temp.next;
            }
        }
        return head;
    }
}

@Data
class ListNode2 {
    int val;

    ListNode2 next;
    ListNode2() {}

    ListNode2(int val) { this.val = val; }

    ListNode2(int val, ListNode2 next) { this.val = val; this.next = next; }
}
