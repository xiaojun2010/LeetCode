package l06.set;

public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;


    public LinkedListSet(LinkedList<E> list) {
        this.list = list;
    }

    @Override
    public void add(E e) {
        if (!contains(e)) {
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
