package com.paofu.data_structure.demo.day02;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/25 17:18
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 */
public class Demo02 {

    public static void main(String[] args) {
        TreeNode1 root = new TreeNode1();
        TreeNode1 node1 = new TreeNode1();
        TreeNode1 node2 = new TreeNode1();
        TreeNode1 node3 = new TreeNode1();
        TreeNode1 node4 = new TreeNode1();
        TreeNode1 node5 = new TreeNode1();
        TreeNode1 node6 = new TreeNode1();
        root.val = 1;
        node1.val = 2;
        node2.val = 2;
        node3.val = 3;
        node4.val = 4;
        node5.val = 4;
        node6.val = 3;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        root.left = node1;
        root.right = node2;
        isSymmetric(root);
    }

    public static boolean isSymmetric(TreeNode1 root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        //


        return false;
    }



}

class TreeNode1 {
    int val;
    TreeNode1 left;
    TreeNode1 right;
    TreeNode1() {}
    TreeNode1(int val) { this.val = val; }
    TreeNode1(int val, TreeNode1 left, TreeNode1 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
