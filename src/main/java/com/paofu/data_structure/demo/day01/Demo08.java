package com.paofu.data_structure.demo.day01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/11 16:38
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 */
public class Demo08 {

    public static void main(String[] args) {
        ListNode1 listNode1 = new ListNode1(1);
        ListNode1 listNode2 = new ListNode1(2);
        ListNode1 listNode3 = new ListNode1(4);
        ListNode1 listNode4 = new ListNode1(1);
        ListNode1 listNode5 = new ListNode1(3);
        ListNode1 listNode6 = new ListNode1(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        ListNode1 listNode11 = mergeTwoLists(listNode1, listNode4);
        System.out.println(listNode11);
    }

    public static ListNode1 mergeTwoLists(ListNode1 list1, ListNode1 list2) {
        if (list1 == null ) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode1 dummy1 = new ListNode1(0, list1);
        ListNode1 dummy2 = new ListNode1(0, list2);
        ListNode1 res = new ListNode1();
        ListNode1 current1 = list1;
        ListNode1 current2 = list2;
        ListNode1 current = res;
        while (current1 != null && current2 != null) {
            if (current1.val <= current2.val) {
                current.next = current1;
                dummy1.next = current1.next;
                current1 = dummy1.next;
            } else {
                current.next = current2;
                dummy2.next = current2.next;
                current2 = dummy2.next;
            }
            current = current.next;
            current.next = null;
        }
        if (current1 == null) {
            current.next = current2;
        }
        if (current2 == null) {
            current.next = current1;
        }
        return res.next;
    }
}

class ListNode1 {
    int val;
    ListNode1 next;
    ListNode1() {}
    ListNode1(int val) { this.val = val; }
    ListNode1(int val, ListNode1 next) { this.val = val; this.next = next; }
}
