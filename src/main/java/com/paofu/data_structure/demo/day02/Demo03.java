package com.paofu.data_structure.demo.day02;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/26 11:08
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * 返回它的最大深度 3 。
 */
public class Demo03 {

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(root.left == null ? 0 : maxDepth(root.left),
                root.right == null ? 0 : maxDepth(root.right)) + 1;
    }

}
