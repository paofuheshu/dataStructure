package com.paofu.data_structure.demo.day02;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/25 16:04
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * 输入：p = [1,2,3], q = [1,2,3]
 * 输出：true
 * 输入：p = [1,2], q = [1,null,2]
 * 输出：false
 * 输入：p = [1,2,1], q = [1,1,2]
 * 输出：false
 * [10,5,15]
 * [10,5,null,null,15]
 * false
 */
public class Demo01 {

    public static void main(String[] args) {

    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        boolean flag = true;
        if (p == null && q != null) {
            flag = false;
        } else if (p != null && q == null) {
            flag = false;
        } else if (p != null) {
            if (p.val != q.val) {
                flag = false;
            } else {
                if (p.left != null && q.left == null) {
                    flag = false;
                } else if (p.left == null && q.left != null) {
                    flag = false;
                } else  {
                    if (isSameTree(p.left, q.left)) {
                        if (p.right != null && q.right == null) {
                            flag = false;
                        } else if (p.right == null && q.right != null) {
                            flag = false;
                        } else if (p.right != null && p.right.val != q.right.val) {
                            flag = false;
                        } else if (p.right != null) {
                            return isSameTree(p.right, q.right);
                        }
                    } else {
                        return false;
                    }
                }

            }
        }
        return flag;
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
