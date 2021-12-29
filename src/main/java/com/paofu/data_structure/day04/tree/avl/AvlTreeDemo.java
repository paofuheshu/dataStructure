package com.paofu.data_structure.day04.tree.avl;

import lombok.Data;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/29 19:22
 * 平衡二叉树(基于二叉排序树)
 */
public class AvlTreeDemo {

    public static void main(String[] args) {
//        int[] array = {4, 3, 6, 5, 7, 8};
//        int[] array = {10, 12, 8, 9, 7, 6};
        int[] array = {10, 11, 7, 6, 8, 9};
        AvlTree avlTree = new AvlTree();
        for (int i = 0; i < array.length; i++) {
            avlTree.add(new Node(array[i]));
        }
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
    }
}

@Data
class AvlTree {
    private Node root;

    /**
     * 添加加点
     * @param node 节点
     */
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder() {
        if (root != null) {
            root.midOrder();
        }
    }

    /**
     * 查找要删除的节点
     */
    public Node searchDelNode(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchDelNode(value);
        }
    }

    /**
     * 查找要删除的节点的父节点
     */
    public Node searchDelParentNode(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchDelParentNode(value);
        }
    }

    /**
     * 返回以node为根节点的二叉排序树的最小节点值，并删除
     * @param node  传入节点(当做二叉排序树的根节点)
     * @return 返回以node为根节点的二叉排序树的最小节点值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        while (target.getLeft() != null) {
            target = target.getLeft();
        }
        delNode(target.getValue());
        return target.getValue();
    }

    /**
     * 删除节点
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            Node targetNode = searchDelNode(value);
            if (targetNode == null) {
                return;
            }
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
                return;
            }
            Node parentNode = searchDelParentNode(value);
            if (targetNode.getLeft() == null && targetNode.getRight() == null) {
                if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == value) {
                    parentNode.setLeft(null);
                } else if (parentNode.getRight() != null && parentNode.getRight().getValue() == value) {
                    parentNode.setRight(null);
                }
            } else if (targetNode.getLeft() != null && targetNode.getRight() != null) {
                int minValue = delRightTreeMin(targetNode.getRight());
                targetNode.setValue(minValue);
            } else {
                if (targetNode.getLeft() != null) {
                    if (parentNode == null) {
                        root = targetNode.getLeft();
                    } else {
                        if (value == parentNode.getLeft().getValue()) {
                            parentNode.setLeft(targetNode.getLeft());
                        } else {
                            parentNode.setRight(targetNode.getLeft());
                        }
                    }
                } else {
                    if (parentNode == null) {
                        root = targetNode.getRight();
                    } else {
                        if (value == parentNode.getLeft().getValue()) {
                            parentNode.setLeft(targetNode.getRight());
                        } else {
                            parentNode.setRight(targetNode.getRight());
                        }
                    }
                }
            }
        }
    }
}

@Data
class Node {
    private int value;

    private Node left;

    private Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 返回当前节点的高度，以该节点为根节点的树的高度
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 返回左子树的高度
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * 返回左子树的高度
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 添加节点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.getLeft() == null) {
                this.setLeft(node);
            } else {
                this.getLeft().add(node);
            }
        } else {
            if (this.getRight() == null) {
                this.setRight(node);
            } else {
                this.getRight().add(node );
            }
        }
        // 当添加完一个节点后，如果右子树的高度和左子树的高度的值大于1，就需要进行左旋转
        if (rightHeight() - leftHeight() > 1) {
            // 如果它的右子树的左子树的高度大于右子树的高度
            if (right != null && right.rightHeight() < right.leftHeight()) {
                // 先对当前节点的右子树进行右旋转
                right.rightRotate();
            }
            leftRotate();
            return;
        }
        if (leftHeight() - rightHeight() > 1) {
            // 如果它的左子树的右子树的高度大于左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                // 先对当前节点的左子树进行左旋转
                left.leftRotate();
                // 在对当前节点进行右旋转
            }
            rightRotate();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 中序遍历
     */
    public void midOrder() {
        if (this.getLeft() != null) {
            this.getLeft().midOrder();
        }
        System.out.println(this);
        if (this.getRight() != null) {
            this.getRight().midOrder();
        }
    }

    /**
     * 查找要删除的节点
     */
    public Node searchDelNode(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value){
            if (this.getLeft() != null) {
                return this.getLeft().searchDelNode(value);
            } else {
                return null;
            }
        } else {
            if (this.getRight() != null) {
                return this.getRight().searchDelNode(value);
            } else {
                return null;
            }
        }
    }

    /**
     * 查找删除节点的父节点
     */
    public Node searchDelParentNode(int value) {
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchDelParentNode(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchDelParentNode(value);
            } else {
                return null;
            }
        }
    }

    /**
     * 左旋转
     */
    public void leftRotate() {
        // 创建新的节点 以当前根节点的值
        Node newNode = new Node(value);
        // 把新的节点的左子树设置为当前节点的左子树
        newNode.left = left;
        // 把新的节点的右子树设置为当前节点右子树的左子树
        newNode.right = right.left;
        // 把当前节点的值替换成右子节点的值
        value = right.value;
        // 把当前节点的右子树设置成右子树的右子树
        right = right.right;
        // 把当前节点的左子树设置成新的节点
        left = newNode;
    }

    /**
     * 右旋转
     */
    public void rightRotate() {
        // 创建新的节点 以当前根节点的值
        Node newNode = new Node(value);
        // 把新的节点的右子树设置为当前节点的右子树
        newNode.right = right;
        // 把新的节点的左子树设置为当前节点左子树的右子树
        newNode.left = left.right;
        // 把当前节点的值替换成左子节点的值
        value = left.value;
        // 把当前节点的左子树设置成左子树的左子树
        left = left.left;
        // 把当前节点的右子树设置成新的节点
        right = newNode;
    }

}
