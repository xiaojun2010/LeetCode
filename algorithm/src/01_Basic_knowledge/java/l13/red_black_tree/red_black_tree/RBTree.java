package l13.red_black_tree.red_black_tree;

import com.sun.org.apache.regexp.internal.RE;

public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        //true
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }


    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }


    public int getSize() {
        return size;
    }

    public boolean ieEmpty() {
        return size == 0;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    // 对节点node进行向左旋转操作，返回旋转后新的根节点x
    //  node                            x
    //  /  \                          /   \
    // T1   x      向左旋转          node  T3
    //     / \   - - - - - - - ->   / \
    //   T2  T3                    T1 T2

    private Node leftRotate(Node node){
        Node x = node.right;
        Node T2 = x.left;
        x.left = node;
        node.right = T2;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    // 对节点node进行向右旋转操作，返回旋转后新的根节点x
    //       node                            x
    //       / \                           /   \
    //      x   T2     向右旋转 (y)        y    node
    //     / \       - - - - - - - ->          / \
    //    y   T1                              T1 T2
    private Node rightRotate(Node node){
        Node x  = node.left;
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED; //和父节点融合在一起

        return x;
    }

    /**
     * 颜色翻转
     * @param node
     */
    private void flipColor(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    /**
     * 向二分搜索树中添加新的元素（key,value）
     *
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;//最终根节点为黑色
    }

    /**
     * 向以node为根的红黑树中插入元素(key,value)，递归算法
     * 返回插入新节点后红黑树的根
     *
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);//默认插入红色节点
        }
        if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else {
            node.value = value;
        }

        //不是 if -else
        if (isRed(node.right) && !isRed(node.left)){ 
            node = leftRotate(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }

        return node;
    }

    /**
     * 看二分搜索树中是否包含元素e
     *
     * @param key
     * @return
     */
    public boolean contains(K key) {
        return contains(root, key);
    }

    /**
     * 向以node为根的二分搜索树中搜索元素e
     *
     * @param node
     * @param key
     * @return
     */
    public boolean contains(Node node, K key) {
        if (node == null) {
            return false;
        }
        if (key.compareTo(node.key) == 0) {
            return true;
        } else if (key.compareTo(node.key) > 0) {
            return contains(node.right, key);
        } else {
            return contains(node.left, key);
        }
    }


}
