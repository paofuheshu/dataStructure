package com.paofu.data_structure.day04.hashtable;

import lombok.Data;

import java.util.Scanner;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/20 20:39
 */
public class HashTableDemo {

    public static void main(String[] args) {

        HashTable hashTable = new HashTable(10);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加员工");
            System.out.println("list: 遍历员工");
            System.out.println("find: 查找员工");
            System.out.println("exit: 退出");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入id");
                    int id = scanner.nextInt();
                    System.out.println("请输入员工姓名");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTable.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }
}

/**
 * 创建hashTable,用来管理多条链表
 */
class HashTable {
    private EmpLinkedList[] empLinkedListArray;

    private int size;

    public HashTable(int size) {
        this.size = size;
        // 初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        // 不要忘记初始化hashTable内部的每条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加员工
     * @param emp 员工
     */
    public void add(Emp emp) {
        // 根据员工id，得到该员工应该加到哪一条链表
        int empLinkedListNumber = hashFunction(emp.id);
        // 将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNumber].add(emp);
    }

    /**
     * 遍历所有链表
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    /**
     * 根据id查找员工
     */
    public void findEmpById(int id) {
        int empLinkedListNumber = hashFunction(id);
        Emp emp = empLinkedListArray[empLinkedListNumber].findEmpById(id);
        if (emp != null) {
            System.out.println("在第" + (id + 1) + "条链表找到了该员工，信息为:" + emp);
        } else {
            System.out.println("没有找到该员工");
        }
    }

    /**
     * 编写一个散列函数(取模法)
     */
    public int hashFunction(int id) {
        return id % size;
    }
}

/**
 * 表示员工
 */
@Data
class Emp {

    public Integer id;

    public String name;

    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

/**
 * 创建链表，存放员工
 */
class EmpLinkedList {

    /**
     * 头指针，指向第一个Emp
     */
    private Emp head;

    /**
     * 添加员工到链表
     * 假设，添加员工时，id是字增长的，即id的分配是从小到大的
     * @param emp 员工
     */
    public void add(Emp emp) {
        // 如果是添加第一个员工
        if (head == null) {
            head = emp;
            return;
        }
        // 如果不是第一个，使用辅助指针找到最后一个
        Emp temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = emp;
    }

    /**
     * 遍历链表
     */
    public void list(int number) {
        if (head == null) {
            System.out.println("第" + (number + 1) + "链表为空");
            return;
        }
        System.out.print("第" + (number + 1) + "链表信息为:");
        Emp temp = head;
        while (true) {
            System.out.printf("--> id=%d name=%s\t\n", temp.id, temp.name);
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
    }

    /**
     * 根据id查找员工(找到就返回emp 没有就返回null)
     */
    public Emp findEmpById(int id) {
        if (head == null) {
            return null;
        }
        Emp temp = head;
        while (true) {
            if (temp.id == id) {
                break;
            }
            if (temp.next == null) {
                temp = null;
                break;
            }
            temp = temp.next;
        }
        return temp;
    }


}
