package com.paofu.data_structure.day03.search;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/16 20:33
 * 线性查找
 */
public class SeqSearch {

    public static void main(String[] args) {
        int[] array = {1, 9, 11, -1, 34, 89};
        System.out.println(seqSearch(array, 10));
    }

    /**
     * 此处线性查找是找到相同的值就直接返回
     * @param array  数组
     * @param value  查找的值
     * @return
     */
    public static String seqSearch(int[] array, int value) {
        // 线性查找是逐一比对，发现有相同值，就返回下标
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return "找到值为" + value + "的下标为：" + i;
            }
        }
        return "该数组中没有值为：" + value;
    }
}
