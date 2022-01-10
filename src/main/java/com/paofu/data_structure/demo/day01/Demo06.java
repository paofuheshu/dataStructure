package com.paofu.data_structure.demo.day01;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/10 10:32
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 */
public class Demo06 {

    public static void main(String[] args) {
        String[] strs = {"ab", "ba"};
        String s = longestCommonPrefix(strs);
        System.out.println(s);
    }

    public static String longestCommonPrefix(String[] strs) {
        String res = "";
        boolean flag = false;
        String minLengthStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < minLengthStr.length()) {
                minLengthStr = strs[i];
            }
        }
        for (int i = 0; i < minLengthStr.length(); i++) {
            int count = 0;
            res = minLengthStr.substring(0, minLengthStr.length() - i);
            for (String str : strs) {
                if (str.indexOf(res) == 0) {
                    count++;
                }
            }
            if (count == strs.length) {
                flag = true;
                break;
            }
        }
        if (flag) {
            return res;
        } else {
            return "";
        }
    }
}
