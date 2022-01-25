package com.paofu.data_structure.demo.day01;

import org.springframework.util.StringUtils;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/17 15:07
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串
 * 示例 1：
 * 输入：s = "Hello World"
 * 输出：5
 * 示例 2：
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * 示例 3：
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 */
public class Demo13 {

    public static void main(String[] args) {
        String str = "   fly me   to   the moon  ";
        System.out.println(lengthOfLastWord(str));
    }

    public static int lengthOfLastWord(String s) {
        int index = s.lastIndexOf(' ');
        while (index == s.length() - 1) {
            s = s.substring(0, index);
            index = s.lastIndexOf(' ');
        }
        if (index != -1) {
            s = s.substring(index + 1);
        }
        return s.length();
    }
}
