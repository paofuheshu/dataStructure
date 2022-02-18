package com.paofu.data_structure.demo;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/24 17:29
 */
public class Test1 {

    private static final List<String> LIST = Arrays.asList("1", "2", "3", "4", "5");

    public static void main(String[] args) {
        System.out.println(LIST.contains("2"));
    }
}
