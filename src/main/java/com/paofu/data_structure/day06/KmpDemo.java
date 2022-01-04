package com.paofu.data_structure.day06;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2022/1/4 20:20
 * KMP算法示例
 */
public class KmpDemo {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.println(violenceMatch(str1, str2));
        System.out.println(str1.substring(violenceMatch(str1, str2), violenceMatch(str1, str2) + str2.length()));
        int[] deptMatching = getDeptMatching(str2);
        int kmp = kmp(str1, str2, deptMatching);
        System.out.println(kmp);
        System.out.println(str1.substring(kmp, kmp + str2.length()));
    }

    /**
     * 暴力匹配算法
     */
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;
        int j = 0;
        while (i < s1Len && j < s2Len) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }

    /**
     * kmp算法
     * @param str1  原字符串
     * @param str2  子字符串
     * @param array 部分匹配表
     * @return 下标
     */
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
