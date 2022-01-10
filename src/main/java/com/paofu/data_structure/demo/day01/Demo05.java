package com.paofu.data_structure.demo.day01;

import com.sun.org.apache.regexp.internal.RE;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/7 17:17
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + I
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，
 * 所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900
 * 给定一个罗马数字，将其转换成整数
 * 示例 1:
 * 输入: s = "III"
 * 输出: 3
 * 示例 2:
 * 输入: s = "IV"
 * 输出: 4
 * 示例 3:
 * 输入: s = "IX"
 * 输出: 9
 * 示例 4:
 * 输入: s = "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例 5:
 * 输入: s = "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 *
 */
public class Demo05 {

    public static void main(String[] args) {
        String str = "IX";
        int i = romanToInt1(str);
        System.out.println(i);
    }


    public static int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>(16);
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);
        int step;
        int res = 0;
        for (int i = 0; i < s.length(); i += step) {
            step = 2;
            if (i + step > s.length()) {
                step = 1;
            }
            String substring = s.substring(i, i + step);
            Integer integer = map.get(substring);
            if (integer == null) {
                step = 1;
                String substring1 = s.substring(i, i + step);
                Integer integer1 = map.get(substring1);
                res = res + integer1;
            } else {
                res = res + integer;
            }
        }
        return res;
    }

    public static int romanToInt1(String s) {
        s = s.replace("IV", "a");
        s = s.replace("IX", "b");
        s = s.replace("XL", "c");
        s = s.replace("XC", "d");
        s = s.replace("CD", "e");
        s = s.replace("CM", "f");
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int value = getValue(s.charAt(i));
            res = res + value;
        }
        return res;
    }

    public static int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            case 'a':
                return 4;
            case 'b':
                return 9;
            case 'c':
                return 40;
            case 'd':
                return 90;
            case 'e':
                return 400;
            case 'f':
                return 900;
            default:
                return 0;
        }
    }
}
