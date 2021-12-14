package com.paofu.data_structure.day02;

import com.paofu.data_structure.day02.linkedList.SingleLinkedListDemo;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        int[] array = {3, 9, 7, 2, -1};
        sort(array);
    }

    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] < array[j]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }
}

@Data
class Test2 {
    public String name;

    public Test2(String name) {
        this.name = name;
    }
}
