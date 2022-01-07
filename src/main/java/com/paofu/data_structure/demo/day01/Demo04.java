package com.paofu.data_structure.demo.day01;

import java.util.Stack;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/7 15:17
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是
 * 示例 1：
 * 输入：x = 121
 * 输出：true
 * 示例 2：
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数
 * 示例 3：
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 */
public class Demo04 {

    public static void main(String[] args) {
        int num = 1001;
        System.out.println("数字:" + num + (isPalindrome2(num) ? "是" : "不是") + "一个回文数");
    }

    public static boolean isPalindrome(int num) {
        if (num < 0) {
            return false;
        }
        String str = num + "";
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            stack.add(str.substring(i, i + 1));
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            String pop = stack.pop();
            res.append(pop);
        }
        return str.equals(res.toString());
    }

    public static boolean isPalindrome1(int x) {
        if (x < 0) {
            return false;
        }
        String str = x + "";
        StringBuilder resStr = new StringBuilder();
        for (int i = str.length(); i > 0; i--) {
            char c = str.charAt(i - 1);
            resStr.append(c);
        }
        return str.equals(resStr.toString());
    }

    public static boolean isPalindrome2(int x) {
        String reversedStr = (new StringBuilder(x + "")).reverse().toString();
        return (x + "").equals(reversedStr);
    }
}
