package com.paofu.data_structure.demo.DynamicProgramming;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/3/2 15:58
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 */
public class Demo01 {

    public static void main(String[] args) {
        String str = "babad";
        System.out.println(str);
        test("s");
    }

    public static String test(String s) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        List<Integer> page = CollUtil.page(2, 3, list);
        for (Integer integer : page) {
            System.out.println(integer);
        }
        return "";
    }
}
