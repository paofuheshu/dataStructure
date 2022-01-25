package com.paofu.data_structure.demo.day01;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/11 17:26
 * 实现 strStr() 函数。
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
 * 如果不存在，则返回 -1 。
 * 说明：
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 * 示例 1：
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 * 示例 2：
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 * 示例 3：
 * 输入：haystack = "", needle = ""
 * 输出：0
 *
 */
public class Demo10 {

    public static void main(String[] args) {
        String str1 = "hello";
        String str2 = "ll";
        System.out.println(strStr(str1, str2));
    }

    public static int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        if ("".equals(haystack)) {
            return -1;
        }
        return kmp(haystack, needle, getDeptMatching(needle));
    }

    public static int kmp(String str1, String str2, int[] array) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = array[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public static int[] getDeptMatching(String dest) {
        // 创建数组保存部分匹配值
        int[] array = new int[dest.length()];
        // 如果字符串长度为1，部分匹配值为0
        array[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // dest.charAt(i) == dest.charAt(j) 部分匹配值就是+1
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = array[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            array[i] = j;
        }
        return array;
    }
}
