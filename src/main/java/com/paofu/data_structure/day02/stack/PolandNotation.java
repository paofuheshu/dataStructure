package com.paofu.data_structure.day02.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/9 19:20
 * 逆波兰表达式
 */
public class PolandNotation {

    public static void main(String[] args) {
        // 定义一个逆波兰表达式
        // (3+4)*5-6 -->  3 4 + 5 * 6 - 为了方便，逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 - ";
        // 1：先将suffixExpression放入arrayList
        System.out.println(getListString(suffixExpression));
        // 2：将arrayList传递给一个方法，配合栈，完成计算
        System.out.println("逆波兰表达式：" + suffixExpression + "的结果为：" + calculate(getListString(suffixExpression)));
        // 完成将一个中缀表达式转成后缀表达式
        // 如将 1+((2+3)*4)-5的中缀表达式转成 1 2 3 + 4 * + 5 -
        // 直接对字符串进行扫描，不方便，先将字符串转成中缀的集合
        String expression = "1+((2+3)*4)-5";
        // 将中缀表达式转为集合  [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        List<String> infixList = toInfixExpressionList(expression);
        System.out.println(infixList);
        // 将中缀表达式集合转为后缀表达式集合  [1, 2, 3, +, 4, *, +, 5, -]
        List<String> suffixList = parseSuffixExpression(infixList);
        System.out.println(suffixList);
        // 计算值
        int calculate = calculate(suffixList);
        System.out.println("逆波兰表达式：" + suffixList + "的计算结果为：" + calculate);
    }

    /**
     * 将逆波兰表达式，依次将数据和运算符放入到arrayList中
     */
    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }

    /**
     * 完成对逆波兰表达式的计算
     */
    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<String>();
        for (String s : list) {
            // 使用正则表达式取出数
            if (s.matches("\\d+")) {
                stack.push(s);
            } else {
                // pop出两个数并运算，并将结果入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if ("+".equals(s)) {
                    res = num1 + num2;
                } else if ("-".equals(s)) {
                    res = num1 - num2;
                } else if ("*".equals(s)) {
                    res = num1 * num2;
                } else if ("/".equals(s)) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("未知的操作符");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * 将字符串中缀表达式转为集合存放
     * @param expression  中缀表达式
     * @return  List<String>
     */
    public static List<String> toInfixExpressionList(String expression) {
        List<String> list = new ArrayList<>();
        int i = 0;
        String str;
        char ch;
        do {
            if ((ch =expression.charAt(i)) < 48 || (ch =expression.charAt(i)) > 57) {
                list.add("" + ch);
                i++;
            } else {
                // 考虑多位数拼接的问题
                str = "";
                while (i < expression.length() && ((ch =expression.charAt(i)) >= 48 && (ch =expression.charAt(i)) <= 57)) {
                    str += ch;
                    i++;
                }
                list.add(str);
            }
        } while (i < expression.length());
        return list;
    }

    /**
     * 将中缀表达式的list转换为后缀表达式的list
     * @param list  中缀表达式的list
     * @return  后缀表达式的list
     */
    public static List<String> parseSuffixExpression(List<String> list) {
        // 初始化栈
        Stack<String> stack = new Stack<>();
        // stack2在整个过程中没有pop的操作，并且后续需要逆序，所以此处直接使用arrayList
        List<String> resultList = new ArrayList<>();

        // 遍历list
        for (String data : list) {
            // 如果是一个数，直接入栈，即resultList
            if (data.matches("\\d+")) {
                resultList.add(data);
            } else if (("(").equals(data)) {
                // 如果是左括号“(”，则直接压入s1
                stack.push(data);
            } else if ((")").equals(data)) {
                // 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!("(".equals(stack.peek()))) {
                    resultList.add(stack.pop());
                }
                // 将栈中的"("弹出消除掉
                stack.pop();
            } else {
                // 当data的运算优先级小于或等于栈顶运算符的优先级
                // 将s1栈顶的运算符弹出并压入到s2中(resultList)，再次与s1中新的栈顶运算符相比较
                while (stack.size() != 0 && (getValue(data) <= getValue(stack.peek()))) {
                    resultList.add(stack.pop());
                }
                // 将data压入stack
                stack.push(data);
            }
        }
        // 将stack中剩余的运算符依次弹出并加入resultList
        while (!stack.isEmpty()) {
            resultList.add(stack.pop());
        }
        return resultList;
    }

    /**
     * 返回对应的优先级数字
     */
    public static int getValue(String operator) {
        int result = 0;
        switch (operator) {
            case "+":
            case "-":
                result = 1;
                break;
            case "*":
            case "/":
                result = 2;
                break;
            default:
                break;
        }
        return result;
    }
}
