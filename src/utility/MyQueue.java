package utility;

import java.util.NoSuchElementException;

public class MyQueue<E> {
    private SinglyLinkedList<E> list;
    private int size;

    public MyQueue() {
        list = new SinglyLinkedList<>();
        size = 0;
    }

    public void add(E item) {
        list.append(item);
        size++;
    }

    public E remove() {
        if (isEmpty())
            throw new NoSuchElementException();
        E data = peek();
        list.remove(0);
        size--;
        return data;
    }

    public E peek() {
        if (isEmpty())
            throw new NoSuchElementException();
        return list.get(0);
    }

    public int size() {
        return this.size;
    }

    public String toString() {
        return list.toString();
    }

    public boolean isEmpty() {
        if (size==0)
            return true;
        return false;
    }
}
