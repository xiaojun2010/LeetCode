package l11.union_find.study;

public class UnionFind4 implements UF {
    //第i个元素指向的节点
    private int[] parent;

    //rank[i]表示以i为根的集合所表示的树的层数
    private int[] rank;

    public UnionFind4(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            //每一个节点都指向自己，每一个节点是一颗独立的树
            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * 查看元素p 和 元素q 是否所属一个集合
     *
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并元素p 和 元素q 所属的集合
     * O(h)复杂度，h为数的高度
     *
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        //根据2个元素所在树的元素rank（高度）不同判断合并方向
        //将rank低的集合合并到rank高的集合上
        if (rank[pRoot] < rank[qRoot]) {
            //rank小的根节点指向rank高的集合根节点
            parent[pRoot] = qRoot;

        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }

    }

    /**
     * 查找过程，查找元素p所对应的集合编号
     * O(h)复杂度，h为数的高度
     *
     * @param p
     * @return
     */
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    @Override
    public int getSize() {
        return parent.length;
    }
}