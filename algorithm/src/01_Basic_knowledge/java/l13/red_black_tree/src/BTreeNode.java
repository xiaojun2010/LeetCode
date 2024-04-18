package l13.red_black_tree.src;

//https://www.geeksforgeeks.org/introduction-of-b-tree-2/
// A BTree node
public class BTreeNode {
    // An array of keys (key的集合)
    int[] keys;
    // Minimum degree (defines the range for number of keys)
    // 最小度数（定义键值数量范围）
    int t;
    // An array of child pointers
    BTreeNode[] C;
    // Current number of keys
    int n;
    // Is true when node is leaf. Otherwise false
    boolean leaf;

    // Constructor
    BTreeNode(int t, boolean leaf) {
        /**
         * 在计算机科学中，B树是一种自平衡的树数据结构，它维护数据的排序，并允许搜索、顺序访问、插入和删除操作在log(n)时间内进行。B树的“度”是一个非常重要的概念，它定义了树的分支因子，即节点中子指针的数量范围。
         *
         * 具体来说，B树的度通常指的是树中节点的最小子节点数目。对于一个给定的非根节点：
         *
         * 最小子节点数（即最小度数）通常表示为 ( t )。
         * 每个非叶子节点（除了根节点）至少有 ( t ) 个子节点，最多有 ( 2t ) 个子节点。
         * 每个非叶子节点（除了根节点）至少有 ( t-1 ) 个键（key），最多有 ( 2t-1 ) 个键。
         * 根节点是一个特例，它至少有一个键，并且如果它不是叶子节点，至少有两个子节点。
         *
         * B树的这种设计使得树在增长时能够保持平衡，因为节点会在达到最大键数目之前分裂，而且所有的叶子节点都在同一层。这种结构特别适合用于读写相对较慢的存储介质（如磁盘存储），因为它可以减少访问磁盘的次数，从而提高效率。
         */
        this.t = t;                    //最小度数，最少子节点数
        this.leaf = leaf;              //是否叶子节点
        this.keys = new int[2 * t - 1];//最多有 ( 2t-1 ) 个键
        this.C = new BTreeNode[2 * t]; //最多有 ( 2t ) 个子节点
        this.n = 0;                    //keys的个数
    }

    // A function to traverse all nodes in a subtree rooted
    // with this node
    public void traverse() {

        // There are n keys and n+1 children, traverse
        // through n keys and first n children
        int i = 0;
        for (i = 0; i < n; i++) {

            // If this is not leaf, then before printing
            // key[i], traverse the subtree rooted with
            // child C[i].
            if (!leaf) {
                C[i].traverse();
            }
            System.out.print(" " + keys[i]);
        }

        // Print the subtree rooted with last child
        if (!leaf) {
            C[n].traverse();
        }
    }

    // A function to search a key in the subtree rooted with
    // this node.
    public BTreeNode search(int k) { // returns NULL if k is not present.

        // Find the first key greater than or equal to k
        int i = 0;
        while (i < n && k > keys[i]){
            i++;
        }

        // If the found key is equal to k, return this node
        if (i < n && k == keys[i])
            return this;

        // If the key is not found here and this is a leaf
        // node
        if (leaf)
            return null;

        // Go to the appropriate child
        return C[i].search(k);
    }


    void insertNonFull(int k) {
        int i = n - 1;
        if (leaf) {
            while (i >= 0 && keys[i] > k) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = k;
            n++;
        } else {
            while (i >= 0 && keys[i] > k) {
                i--;
            }
            if (C[i + 1].n == 2 * t - 1) {
                splitChild(i + 1, C[i + 1]);
                if (keys[i + 1] < k) {
                    i++;
                }
            }
            C[i + 1].insertNonFull(k);
        }
    }

    void splitChild(int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(y.t, y.leaf);
        z.n = t - 1;
        for (int j = 0; j < t - 1; j++) {
            z.keys[j] = y.keys[j + t];
        }
        if (!y.leaf) {
            for (int j = 0; j < t; j++) {
                z.C[j] = y.C[j + t];
            }
        }
        y.n = t - 1;
        for (int j = n; j > i; j--) {
            C[j + 1] = C[j];
        }
        C[i + 1] = z;
        for (int j = n - 1; j >= i; j--) {
            keys[j + 1] = keys[j];
        }
        keys[i] = y.keys[t - 1];
        n++;
    }

}

// This code is contributed by Rajput-Ji