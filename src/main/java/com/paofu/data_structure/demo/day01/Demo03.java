package com.paofu.data_structure.demo.day01;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/7 11:18
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 * 输入: s = ""
 * 输出: 0
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */
public class Demo03 {

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<String, Integer> resMap = new HashMap<>(16);
        for (int j = 0; j < s.length(); j++) {
            Map<String, Integer> map = new HashMap<>(16);
            for (int i = j; i < s.length(); i++) {
                String substring = s.substring(i, i + 1);
                if (map.get(substring) == null) {
                    map.put(substring, i);
                } else {
                    break;
                }
            }
            if (map.size() > resMap.size()) {
                resMap = map;
            }
        }
        return resMap.size();
    }
}
