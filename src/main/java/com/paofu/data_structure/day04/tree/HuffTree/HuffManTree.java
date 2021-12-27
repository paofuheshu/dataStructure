package com.paofu.data_structure.day04.tree.HuffTree;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/27 18:59
 * 赫夫曼树示例
 */
public class HuffManTree {

    public static void main(String[] args) {
        int[] array = {13, 7, 8, 3, 29, 6, 1};
        Node huffManTree = createHuffManTree(array);
        huffManTree.preOrder();
    }

    public static Node createHuffManTree(int[] array) {
        // 遍历array，将array的每个元素构建成一个Node，放入到List中
        List<Node> nodes = new ArrayList<>();
        for (int value : array) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            // 取出权值最小的节点(二叉树)
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            // 构建一颗新的二叉树
            Node parentNode = new Node(leftNode.getValue() + rightNode.getValue());
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);
            // 从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将parent加入List
            nodes.add(parentNode);
            System.out.println(nodes);
        }
        return nodes.get(0);
    }
}

@Data
class Node implements Comparable<Node>{

    /**
     * 节点权值
     */
    private int value;

    private Node left;

    private Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}