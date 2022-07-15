package com.paofu.data_structure.demo.kmp;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/4/6 13:51
 */
public class KmpDemo {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.println(str1.substring(violenceMatch(str1, str2), violenceMatch(str1, str2) + str2.length()));
    }

    /**
     * 暴力匹配
     *
     * @param str1 字符串
     * @param str2 匹配串
     * @return 开始下标
     */
    public static int violenceMatch(String str1, String str2) {
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        int len1 = char1.length;
        int len2 = char2.length;

        int i = 0;
        int j = 0;
        while (i < len1 && j < len2) {
            if (char1[i] == char2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == len2) {
            return i - j;
        } else {
            return -1;
        }
    }
}
