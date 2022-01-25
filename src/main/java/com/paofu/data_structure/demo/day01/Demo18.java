package com.paofu.data_structure.demo.day01;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/24 14:30
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 */
public class Demo18 {

    public static List<Integer> resList = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode(1);
        TreeNode rightNode = new TreeNode(2);
        TreeNode secondLeftNode = new TreeNode(3);
        rootNode.right = rightNode;
        rightNode.left = secondLeftNode;
        System.out.println(inorderTraversal(rootNode));
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        if (root != null) {
            if (root.left != null) {
                inorderTraversal(root.left);
            }
            resList.add(root.val);
            if (root.right != null) {
                inorderTraversal(root.right);
            }
        }
        return resList;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) { this.val = val; }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
