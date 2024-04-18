package l04.linkedlist;

/**
 * https://note.youdao.com/web/#/file/WEBaf18f4aa8134db3e2052cbfe403220c2/note/WEBdd9da2713dc36125542f9674c6e65dd6/
 * @param <E>
 */
public class LinkedList<E> {
    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }
        public Node(){
            this(null,null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head;
    int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * 获取链表中的元素个数
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 返回链表是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 在链表头添加新的元素e
     * @param e
     */
    public void addFirst(E e){
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
//        size ++;
        head = new Node(e,head);
        size++;
    }

    /**
     * 在链表的 index (0 - based)位置添加新的元素e
     * 在链表中不是一个常用的操作，练习用
     * @param index
     * @param e
     */
    public void add(int index ,E e){
        if (index <0 || index > size){
            throw new IllegalArgumentException("Add failed . Illegal index .");
        }
        if (index == 0){
            addFirst(e);
        }else {
            Node prev = head;
            for (int i = 0; i < index-1; i++) {
                prev = prev.next;
            }

//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;

            prev.next = new Node(e,prev.next);
            size ++;
        }
    }

    /**
     * 在链表末尾添加新的元素e
     * @param e
     */
    public void addLast(E e){
        add(size , e);
    }

}
