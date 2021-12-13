package com.paofu.data_structure.day02;

import com.paofu.data_structure.day02.linkedList.SingleLinkedListDemo;
import lombok.Data;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/6 21:16
 */
public class Test1 {

    public static void main(String[] args) {
        Test test = new Test("泡芙和树");
        System.out.println(test.getName());
        System.out.println(test.name);
        Test2 test1 = new Test2("泡芙和树");
        System.out.println(test1.name);
        System.out.println(test1.getName());
    }
}

@Data
class Test2 {
    public String name;

    public Test2(String name) {
        this.name = name;
    }
}
