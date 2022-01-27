package com.paofu.data_structure.demo.day01;


import java.util.StringJoiner;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/18 17:02
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 */
public class Demo15 {

    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        System.out.println(addBinary(a, b));
    }

    public static String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int count = 0;
        for (int i = a.length() - 1, j = b.length() - 1; j >= 0 || i >= 0; i--, j--) {
            int sum = count;
            sum += (i >= 0 ? a.charAt(i) - '0' : 0);
            sum += (j >= 0 ? b.charAt(j) - '0' : 0);
            res.append(sum % 2);
            count = sum / 2;
        }
        res.append(count == 1 ? count : "");
        return res.reverse().toString();
    }
}
