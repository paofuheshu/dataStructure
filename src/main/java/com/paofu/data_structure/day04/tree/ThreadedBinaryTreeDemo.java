package com.paofu.data_structure.day04.tree;

import lombok.Data;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/23 20:17
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        HeroNode1 heroNode1 = new HeroNode1(1, "aaa");
        HeroNode1 heroNode2 = new HeroNode1(3, "bbb");
        HeroNode1 heroNode3 = new HeroNode1(6, "ccc");
        HeroNode1 heroNode4 = new HeroNode1(8, "ddd");
        HeroNode1 heroNode5 = new HeroNode1(10, "eee");
        HeroNode1 heroNode6 = new HeroNode1(14, "fff");
        // 手动创建二叉树
        heroNode1.setLeft(heroNode2);
        heroNode1.setRight(heroNode3);
        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode5);
        heroNode3.setLeft(heroNode6);
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(heroNode1);
        threadedBinaryTree.threadedMidNodes();
        // 查找10号节点的左右指针判断是否线索化成功
        System.out.println("10号节点的前驱节点是：" + heroNode5.getLeft());
        System.out.println("10号节点的后继节点是：" + heroNode5.getRight());
        System.out.println("中序遍历中序线索化二叉树");
        // 8 3 10 1 14 6
        threadedBinaryTree.midThreadedOrder();
    }
}

/**
 * 定义线索化二叉树
 */
@Data
class ThreadedBinaryTree {

    private HeroNode1 root;

    /**
     * 创建一个指向当前节点的前驱节点指针
     */
    private HeroNode1 pre;

    public void threadedMidNodes() {
        this.threadedMidNodes(this.root);
    }

    /**
     * 对二叉树进行中序线索化的方法
     * @param heroNode 当前需要线索化的节点
     */
    public void threadedMidNodes(HeroNode1 heroNode) {
        if (heroNode == null) {
            return;
        }
        // 1:先线索化左子树
        threadedMidNodes(heroNode.getLeft());
        // 2:线索化当前节点
        // 2.1:先处理当前节点的前驱节点
        if (heroNode.getLeft() == null) {
            // 让当前节点的左指针指向前驱节点
            heroNode.setLeft(pre);
            // 修改当前节点的左指针的类型
            heroNode.setLeftType(1);
        }
        // 2.2:处理后继节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(heroNode);
            pre.setRightType(1);
        }
        // 每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = heroNode;
        // 3:线索化右子树
        threadedMidNodes(heroNode.getRight());
    }

    /**
     * 中序遍历中序线索化二叉树
     */
    public void midThreadedOrder() {
        HeroNode1 node = this.root;
        while (node != null) {
            // 先找到leftType = 1的节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            // 打印当前节点
            System.out.println(node);
            // 如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            // 替换遍历的节点
            node = node.getRight();
        }
    }

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
    public HeroNode1 preOrderFind(int number) {
        if (this.root != null) {
            return this.root.preOrderFind(number);
        } else {
            return null;
        }
    }

    /**
     * 中序遍历查找
     */
    public HeroNode1 midOrderFind(int number) {
        if (this.root != null) {
            return this.root.midOrderFind(number);
        } else {
            return null;
        }
    }

    /**
     * 后序遍历查找
     */
    public HeroNode1 postOrderFind(int number) {
        if (this.root != null) {
            return this.root.postOrderFind(number);
        } else {
            return null;
        }
    }

    /**
     * 删除子节点
     * @param number 节点编号
     */
    public void deleteNode(int number) {
        if (this.root != null) {
            if (this.root.getNumber() == number) {
                this.root = null;
            }  else {
                this.root.deleteNode(number);
            }
        }
    }
}

@Data
class HeroNode1 {

    private int number;

    private String name;

    private HeroNode1 left;

    private HeroNode1 right;

    /**
     * 0表示指向左子树
     * 1表示指向前驱节点
     */
    private int leftType;

    /**
     * 0表示指向右子树
     * 1表示指向后继节点
     */
    private int rightType;

    public HeroNode1(int number, String name) {
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
    public HeroNode1 preOrderFind(int number) {
        System.out.println("进入前序遍历");
        // 先查找父节点(当前节点)
        if (this.getNumber() == number) {
            return this;
        }
        HeroNode1 resNode = null;
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
    public HeroNode1 midOrderFind(int number) {
        HeroNode1 resNode = null;
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
    public HeroNode1 postOrderFind(int number) {

        HeroNode1 resNode = null;
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

    /**
     * 删除节点
     * 此处删除规则：
     * 如果删除的节点是叶子节点，则删除该节点
     * 如果删除的节点是非叶子节点，则删除该子树
     * @param number node编号
     */
    public void deleteNode(int number) {
        if (this.left != null && this.left.number == number) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.number == number) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.deleteNode(number);
        }
        if (this.right != null) {
            this.right.deleteNode(number);
        }
    }
}
