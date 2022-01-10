package com.paofu.data_structure.demo.day01;

import org.springframework.util.StringUtils;

import java.util.Stack;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/10 14:05
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合
 * 左括号必须以正确的顺序闭合。
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 * 输入：s = "{[]}"
 * 输出：true
 * {}{}()[]
 * (([]){})
 */
public class Demo07 {

    public static void main(String[] args) {
        String str = "(([]){})";
        System.out.println(isValid(str));
    }

    public static boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        if(s.isEmpty()) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '(') {
                stack.push(')');
            } else if(c == '{') {
                stack.push('}');
            } else if(c == '[') {
                stack.push(']');
            } else if(stack.empty() || c != stack.pop()) {
                return false;
            }
        }
        return stack.empty();
    }
}
