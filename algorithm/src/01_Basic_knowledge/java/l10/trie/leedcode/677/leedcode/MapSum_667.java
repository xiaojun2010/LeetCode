import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/map-sum-pairs/description/
 */
public class MapSum_667 {

    class Node {
        int val;
        Map<Character, Node> next;

        public Node(int val) {
            this.val = val;
            this.next = new HashMap<>();
        }
        public Node() {
            this(0);
        }
    }
    Node root;

    public MapSum_667() {
        root = new Node();
    }

    public void insert(String key, int val) {
        Node cur = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.val = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return 0;
            }
            cur = cur.next.get(c);
        }
        return sum(cur);
    }

    private int sum(Node cur) {
        if (cur.next.size() == 0) {
            return cur.val;
        }
        int sum = cur.val;
        for (char c : cur.next.keySet()) {
            Node nextNode = cur.next.get(c);
            sum += sum(nextNode);
        }
        return sum;
    }
}
