package com.paofu.data_structure.day04.tree;

import lombok.Data;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/22 20:20
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        // 先创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "迪丽热巴");
        HeroNode heroNode1 = new HeroNode(2, "古力娜扎");
        HeroNode heroNode2 = new HeroNode(3, "马尔扎哈");
        HeroNode heroNode3 = new HeroNode(4, "哇卡哇卡");
        HeroNode heroNode4 = new HeroNode(5, "玛卡巴卡");

        // 手动创建二叉树
        root.setLeft(heroNode1);
        root.setRight(heroNode2);
        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode3);
        binaryTree.setRoot(root);
        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.midOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();
        // 前序遍历查找
        int number = 5;
        System.out.println("前序遍历查找-->" + binaryTree.preOrderFind(number));
        // 中序遍历查找
        System.out.println("中序遍历查找-->" + binaryTree.midOrderFind(number));
        // 后序遍历查找
        System.out.println("后序遍历查找-->" + binaryTree.postOrderFind(number));
    }
}

/**
 * 定义一个二叉树
 */
@Data
class BinaryTree {
    private HeroNode root;

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder() {
        if (this.root != null) {
            this.root.midOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        }
    }

    /**
     * 前序遍历查找
     */
    public HeroNode preOrderFind(int number) {
        if (this.root != null) {
            return this.root.preOrderFind(number);
        } else {
            return null;
        }
    }

    /**
     * 中序遍历查找
     */
    public HeroNode midOrderFind(int number) {
        if (this.root != null) {
            return this.root.midOrderFind(number);
        } else {
            return null;
        }
    }

    /**
     * 后序遍历查找
     */
    public HeroNode postOrderFind(int number) {
        if (this.root != null) {
            return this.root.postOrderFind(number);
        } else {
            return null;
        }
    }
}

/**
 * 创建HeroNode节点
 */
@Data
class HeroNode {

    private int number;

    private String name;

    private HeroNode left;

    private HeroNode right;

    public HeroNode(int number, String name) {
        super();
        this.number = number;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历方法
     */
    public void preOrder() {
        // 先输出父节点(当前节点)
        System.out.println(this);
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历方法
     */
    public void midOrder() {
        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.midOrder();
        }
        // 输出父节点(当前节点)
        System.out.println(this);
        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    /**
     * 后序遍历方法
     */
    public void postOrder() {
        // 递归向左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        // 递归向右子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        // 输出父节点(当前节点)
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     */
    public HeroNode preOrderFind(int number) {
        System.out.println("进入前序遍历");
        // 先查找父节点(当前节点)
        if (this.getNumber() == number) {
            return this;
        }
        HeroNode resNode = null;
        // 递归向左子树前序遍历查找
        if (this.left != null) {
            resNode = this.left.preOrderFind(number);
        }
        if (resNode != null) {
            return resNode;
        }
        // 递归向右子树前序遍历查找
        if (this.right != null) {
            resNode = this.right.preOrderFind(number);
        }
        return resNode;
    }

    /**
     * 中序遍历查找
     */
    public HeroNode midOrderFind(int number) {
        HeroNode resNode = null;
        // 递归向左子树中序遍历查找
        if (this.left != null) {
            resNode = this.left.midOrderFind(number);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序遍历");
        // 查找父节点(当前节点)
        if (this.getNumber() == number) {
            return this;
        }
        // 递归向右子树中序遍历查找
        if (this.right != null) {
            resNode = this.right.midOrderFind(number);
        }
        return resNode;
    }

    /**
     * 后序遍历查找
     */
    public HeroNode postOrderFind(int number) {

        HeroNode resNode = null;
        // 递归向左子树后序遍历查找
        if (this.left != null) {
            resNode = this.left.postOrderFind(number);
        }
        if (resNode != null) {
            return resNode;
        }
        // 递归向右子树后序遍历查找
        if (this.right != null) {
            resNode = this.right.postOrderFind(number);
        }
        if (resNode != null) {
            return resNode;
        }
        // 查找父节点(当前节点)
        System.out.println("进入后序遍历");
        if (this.getNumber() == number) {
            return this;
        } else {
            return null;
        }
    }
}
