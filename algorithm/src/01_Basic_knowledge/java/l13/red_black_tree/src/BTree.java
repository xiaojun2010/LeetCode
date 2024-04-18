package l13.red_black_tree.src;

// A BTree
public class BTree {
    public BTreeNode root; // Pointer to root node
    public int t; // Minimum degree

    // Constructor (Initializes tree as empty)
    BTree(int t)
    {
        this.root = null;
        this.t = t;
    }

    // function to traverse the tree
    public void traverse()
    {
        if (this.root != null){
            this.root.traverse();
        }

    }

    // function to search a key in this tree
    public BTreeNode search(int k)
    {
        if (this.root == null)
            return null;
        else
            return root.search(k);
    }

    void insert(int k) {
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = k;
            root.n = 1;
        } else {
            if (root.n == 2 * t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.C[0] = root;
                s.splitChild(0, root);
                int i = 0;
                if (s.keys[0] < k) {
                    i++;
                }
                s.C[i].insertNonFull(k);
                root = s;
            } else {
                root.insertNonFull(k);
            }
        }
    }

}