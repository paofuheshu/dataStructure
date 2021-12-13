package com.paofu.data_structure.day02.stack;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/8 21:16
 * 利用栈直接计算字符串数字的结果
 * 计算思路：
 * 1：通过一个index值(索引)，来遍历我们的字符串表达式
 * 2：如果我们发现是一个数字，就直接入数栈
 * 3：如果发现是一个符号，继续分析
 * 3.1：判断当前符号栈是否为空， 为空直接加入
 * 3.2 如果符号栈有操作符，进行比较，如果当前操作符的优先级小于或等于栈顶的操作符
 * 就需要从数栈中pop出两个数，并从符号栈中pop出一个符号，进行运算，再把运算结果入数栈，操作符入操作符栈
 * 3.3：如果符号栈有操作符，进行比较，如果当前操作符的优先级大于栈顶的操作符
 * 4：当表达式扫描完毕，就顺序的从数栈和符号栈取出相应的数和符号，并运行
 * 5：最后在数栈中只有一个数字，就是表达式的结果
 */
public class Calculator {

    public static void main(String[] args) {
        String expresion = "1*2+4-2";
        // 创建两个栈，一个数字栈，一个操作符栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operatorStack = new ArrayStack2(10);
        // 定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int operator = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";
        while (true) {
            // 依次得到每一个字符
            ch = expresion.substring(index, index + 1).charAt(0);
            if (operatorStack.isOperator(ch)) {
                // 判断当前符号栈是否为空
                if (operatorStack.isEmpty()) {
                    // 为空直接加入
                    operatorStack.push(ch);
                } else {
                    // 如果符号栈有操作符，进行比较，如果当前操作符的优先级小于或等于栈顶的操作符
                    // 就需要从数栈中pop出两个数，并从符号栈中pop出一个符号，进行运算，再把运算结果入数栈，操作符入操作符栈
                    if (operatorStack.priority(ch) <= operatorStack.priority(operatorStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operator = operatorStack.pop();
                        res = numStack.cal(num1, num2, operator);
                        // 把结果入栈
                        numStack.push(res);
                        operatorStack.push(ch);
                    } else {
                        // 如果符号栈有操作符，进行比较，如果当前操作符的优先级大于栈顶的操作符
                        operatorStack.push(ch);
                    }
                }
            } else {
                // 根据ASCII字符表 '1'代表49 '2'代表50，所以此处入数栈需要减去48
                // 此处需要判断是几位小数，不能直接将ch入栈，否则31会变成3和1入栈
                // 在处理时，需要对expresion的index再向后一位查看，如果是数就在接着扫描，如果是符号，就拼接入栈
                // numStack.push(ch - 48);
                keepNum += ch;
                if (index == expresion.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operatorStack.isOperator(expresion.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index >= expresion.length()) {
                break;
            }
        }
        // 当表达式扫描完毕，就顺序的从数栈和符号栈取出相应的数和符号，并运行
        while (true) {
            // 如果符号栈为空，则计算得到最后的结果，数栈中只有一个数字
            if (operatorStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            operator = operatorStack.pop();
            res = numStack.cal(num1, num2, operator);
            numStack.push(res);
        }
        // 最后在数栈中只有一个数字，就是表达式的结果
        System.out.println("表达式：" + expresion + "的计算结果为：" + numStack.pop());

    }
}

/**
 *
 */
/**
 * 定义一个栈结构
 */
class ArrayStack2 {

    /**
     * 栈的大小
     */
    private int maxSize;

    /**
     * 数组模拟栈，数据存放在数组中
     */
    private int[] stack;

    /**
     * 栈顶  初始化为-1
     */
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 判断栈满
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     * @param value 入栈值
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已经满了，无法入栈");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已经空了，无法出栈");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历栈
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.println("栈的第" + i + "个元素值为：" + stack[i]);
        }
    }

    /**
     * 查看栈顶的元素，但不出栈
     */
    public int peek() {
        return stack[top];
    }

    /**
     * 返回运算符的优先级
     * 优先级用数字表示，数字越大，优先级越高
     * @param operator 操作符
     */
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean isOperator(int operator) {
        return (operator == '*' || operator == '/' || operator == '+' || operator == '-');
    }

    /**
     * 计算方法
     * @param num1  数字1
     * @param num2  数字2
     * @param operator  操作符
     * @return
     */
    public int cal(int num1, int num2, int operator) {
        int res = 0;
        switch (operator) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                // 注意此处的顺序
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                // 注意此处的顺序
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
