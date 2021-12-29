package com.paofu.data_structure.day04.tree.binaryTree;

import lombok.Data;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/28 20:17
 * 二叉排序树示例
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] array = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int value : array) {
            binarySortTree.add(new Node(value));
        }
        binarySortTree.midOrder();
    }
}

class BinarySortTree {

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

}
