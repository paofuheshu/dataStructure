package com.paofu.data_structure.day04.tree;

import lombok.Data;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/23 19:26
 * 以数组的形式存储顺序二叉树
 */
public class ArrayBinaryTreeDemo {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(array);
        arrayBinaryTree.preOrder();
    }

}

@Data
class ArrayBinaryTree {

    /**
     * 储存数据节点的数组
     */
    private int[] array;

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    /**
     * 重载preOrder
     */
    public void preOrder() {
        this.preOrder(0);
    }

    /**
     * 顺序存储二叉树的前序遍历
     * @param index 数组下标
     */
    public void preOrder(int index) {
        // 如果数组为空或者array.length = 0
        if (array == null || array.length == 0) {
            System.out.println("数组为空");
        }
        // 输出当前元素
        System.out.println(array[index]);
        // 向左递归遍历
        if (index * 2 + 1 < array.length) {
            preOrder(2 * index + 1);
        }
        // 向右递归遍历
        if (index * 2 + 2 < array.length) {
            preOrder(2 * index + 2);
        }
    }
}
