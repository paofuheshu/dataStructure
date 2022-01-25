package com.paofu.data_structure.demo.day01;


/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/18 17:02
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 */
public class Demo15 {

    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        System.out.println(addBinary(a, b));
    }

    public static String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        //是否进一位
        int count = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = count;
            // 获取字符串a对应的某一位的值 当i<0是 sum+=0（向前补0） 否则取原值 ‘1’的char类型和‘0’的char类型刚好相差为1
            sum += (i >= 0 ? a.charAt(i) - '0' : 0);
            // 获取字符串b对应的某一位的值 当i<0是 sum+=0（向前补0） 否则取原值 ‘1’的char类型和‘0’的char类型刚好相差为1
            sum +=( j >= 0 ? b.charAt(j) - '0' : 0);
            // 如果二者都为1  那么sum%2应该刚好为0 否则为1
            res.append(sum % 2);
            // 如果二者都为1  那么ca 应该刚好为1 否则为0
            count = sum / 2;
        }
        // 判断最后一次计算是否有进位  有则在最前面加上1 否则原样输出
        res.append(count == 1 ? count : "");
        return res.reverse().toString();
    }
}
