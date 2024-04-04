package l04.linkedlist;

public class Main {
    public static void main(String[] args) {
        LinkedList2<Integer> linkedList2 = new LinkedList2<>();

        for (int i = 0; i < 5; i++) {
            linkedList2.addFirst(i);
            System.out.println(linkedList2);
        }
        linkedList2.add(2, 666);
        System.out.println(linkedList2);

        linkedList2.remove(2);
        System.out.println(linkedList2);


        linkedList2.removeLast();
        System.out.println(linkedList2);
    }
}
