package com.paofu.data_structure.day04.tree.HuffTree;

import lombok.Data;

import java.io.*;
import java.util.*;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/27 19:57
 * 赫夫曼编码
 */
public class HuffCode {

    private static Map<Byte, String> huffmanCode = new HashMap<>(16);
    private static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
////        // 40
////        HuffNode huffTree = createHuffTree(getNodes(contentBytes));
////        getHuffTreeCode(huffTree);
////        byte[] zip = zip(contentBytes, huffmanCode);
//        byte[] zip = huffZip(contentBytes);
//        byte[] source = decode(huffmanCode, zip);
//        System.out.println("原来的字符串：" + new String(source));
        // 测试压缩文件的代码
//        String srcFile = "D:\\test.png";
//        String dstFile = "D:\\test.zip";
//        zipFile(srcFile, dstFile);
        // 测试解压文件
        String srcFile = "D:\\test.zip";
        String dstFile = "D:\\test_zip.png";
        unZipFile(srcFile, dstFile);
    }

    /**
     *  编写一个方法，对文件进行压缩
     * @param srcFile 压缩文件的路径
     * @param dstFile 压缩后文件路径
     */
    public static void zipFile(String srcFile, String dstFile) throws IOException {
        // 创建输出流
        OutputStream os = null;
        // 创建文件的输入流
        FileInputStream fileInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream(srcFile);
            // 创建一个和原文件大小一样的byte[]
            byte[] b = new byte[fileInputStream.available()];
            // 读取文件
            fileInputStream.read(b);
            // 直接对源文件压缩
            byte[] huffManBytes = huffZip(b);
            // 创建文件的输出流， 存放压缩文件
            os = new FileOutputStream(dstFile);
            // 创建一个和文件输出流关联的ObjectOutPutStream
            objectOutputStream = new ObjectOutputStream(os);
            // 以对象流的方式写入赫夫曼编码  为了以后恢复原文件时使用
            // 把赫夫曼编码后的字节数组写入压缩文件
            objectOutputStream.writeObject(huffManBytes);
            // 注意一定要把赫夫曼编码写入压缩文件
            objectOutputStream.writeObject(huffmanCode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            fileInputStream.close();
            os.close();
            objectOutputStream.close();
        }
    }

    /**
     * 对压缩文件的解压
     * @param zipFile 解压文件
     * @param dstFile 解压后的文件
     */
    public static void unZipFile(String zipFile, String dstFile) {
        // 定义文件输入流
        InputStream is = null;
        // 定义对象输入流
        ObjectInputStream ois = null;
        // 定义文件输出流
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            // 读取byte数组
            byte[] huffManBytes = (byte[]) ois.readObject();
            // 读取赫夫曼编码表
            Map<Byte, String> huffManCodes = (Map<Byte, String>) ois.readObject();
            // 解码
            byte[] bytes = decode(huffManCodes, huffManBytes);
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将一个byte转成一个二进制的字符串
     * @param flag 标识是否需要补高位
     */
    private static String byteToBitString(boolean flag, byte b) {
        // 使用一个变量保存b
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        // 此处返回的是temp对应的二进制的补码
        String string = Integer.toBinaryString(temp);
        if (flag) {
            return string.substring(string.length() - 8);
        } else {
            return string;
        }
    }

    /**
     * 对压缩数据的解码
     * @param huffmanCodes  赫夫曼编码表
     * @param huffManBytes  赫夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffManBytes) {
        // 1. 先得到huffManBytes对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 2. 将byte数组转成二进制的字符串
        for (int i = 0; i < huffManBytes.length; i++) {
            // 判断是不是最后一个字节
            boolean flag = (i == huffManBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffManBytes[i]));
        }
//        System.out.println("赫夫曼字节数组对应的二进制字符串=" + stringBuilder.toString());
        // 把字符串按照指定的赫夫曼编码进行解码
        Map<String, Byte> map = new HashMap<>(16);
        for (Map.Entry<Byte, String> byteStringEntry : huffmanCodes.entrySet()) {
            map.put(byteStringEntry.getValue(), byteStringEntry.getKey());
        }

        // 创建集合存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    // 说明没有配到
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 使用一个方法将方法进行封装
     * @param bytes 原始的字符串对应的字节数组
     * @return 经过赫夫曼编码处理后的字节数组
     */
    public static byte[] huffZip(byte[] bytes) {
        // 将原始字符串字节数组转为节点集合
        List<HuffNode> nodes = getNodes(bytes);
        // 将节点集合转为赫夫曼树
        HuffNode huffTree = createHuffTree(nodes);
        // 生成对应的赫夫曼编码
        Map<Byte, String> huffTreeCode = getHuffTreeCode(huffTree);
        // 根据生成的赫夫曼编码压缩得到赫夫曼编码字节数组
        byte[] zip = zip(bytes, huffTreeCode);
        return zip;
    }

    /**
     * 将数组中的data和次数提取出来
     * @param bytes  数组
     * @return List<HuffNode>
     */
    public static List<HuffNode> getNodes(byte[] bytes) {
        List<HuffNode> nodes = new ArrayList<>();
        Map<Byte, Integer> counts = new HashMap<>(16);
        for (byte aByte : bytes) {
//            Integer count = counts.get(aByte);
//            if (count == null) {
//                counts.put(aByte, 1);
//            } else {
//                counts.put(aByte, count + 1);
//            }
            counts.merge(aByte, 1, Integer::sum);
        }
        // 把每个键值对变为Node
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new HuffNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     * 将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
     * @param bytes  原始的字符串对应的byte数组
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 赫夫曼编码处理后的byte[]
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 1.先利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            stringBuilder.append(huffmanCodes.get(aByte));
        }
        // 将对应的字符串转为byte数组
        // 统计返回的huffmanCodes得长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 创建存储压缩后的byte数组
        byte[] huffmanCodesBytes = new byte[len];
        // 每8位对应一个byte 所以步长是8
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodesBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodesBytes;
    }

    public static Map<Byte, String> getHuffTreeCode(HuffNode node) {
        if (node == null) {
            return null;
        }
        getHuffTreeCode(node.getLeft(), "0", stringBuilder);
        getHuffTreeCode(node.getRight(), "1", stringBuilder);
        return huffmanCode;
    }

    /**
     * 将传入的node节点的所有叶子节点生成对应的赫夫曼编码
     * @param node 节点
     * @param code 路径  左子节点为0 右子节点为1
     * @param stringBuilder  拼接路径
     */
    public static void getHuffTreeCode(HuffNode node, String code, StringBuilder stringBuilder) {
        StringBuilder str = new StringBuilder(stringBuilder);
        str.append(code);
        if (node != null) {
            // 判断当前节点是叶子节点还是非叶子节点
            if (node.getData() == null) {
                // 非叶子节点
                // 向左递归处理
                getHuffTreeCode(node.getLeft(), "0", str);
                // 向右递归
                getHuffTreeCode(node.getRight(), "1", str);
            } else {
                // 叶子节点
                huffmanCode.put(node.getData(), str.toString());
            }
        }
    }

    /**
     * 创建赫夫曼树
     * @param nodes 节点集合
     * @return  HuffNode
     */
    public static HuffNode createHuffTree(List<HuffNode> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            // 取出权值最小的节点(二叉树)
            HuffNode leftNode = nodes.get(0);
            HuffNode rightNode = nodes.get(1);
            // 构建一颗新的二叉树
            HuffNode parentNode = new HuffNode(null, leftNode.getWight() + rightNode.getWight());
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);
            // 从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将parent加入List
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }
}

@Data
class HuffNode implements Comparable<HuffNode>{

    private Byte data;

    private int wight;

    private HuffNode left;

    private HuffNode right;

    public HuffNode(Byte data, int wight) {
        this.data = data;
        this.wight = wight;
    }

    @Override
    public int compareTo(HuffNode o) {
        return this.getWight() - o.getWight();
    }

    @Override
    public String toString() {
        return "HuffNode{" +
                "data=" + data +
                ", wight=" + wight +
                '}';
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
