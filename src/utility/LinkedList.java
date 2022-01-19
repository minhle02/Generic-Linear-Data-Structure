package utility;

import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {
    private Node first;
    private Node last;
    private int size;

    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean add(E item) {
        int oldSize = size;
        this.append(item);
        return size == oldSize+1;
    }

    public void add(int index, E item) {
        checkIndex(index);
        Node newNode = new Node(null, null, item);
        if (index == 0) {
            if (size==0) {
                first = newNode;
                last = newNode;
            } else {
                first.prev = newNode;
                newNode.next = first;
                first = newNode;
            }

        }
        else {
            Node curr = node(index - 1);
            newNode.next = curr.next;
            curr.next = newNode;
            newNode.prev = curr;
            if (newNode.next != null)
                newNode.next.prev = newNode;
        }
        size++;
    }

    private void append(E item) {
        Node newNode = new Node(null, null, item);
        if (first == null) {
            first = newNode;
            last = first;
        }
        else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
    }

    private void checkIndex(int index) {
        if (index<0 || index > size)
            throw new IndexOutOfBoundsException();
    }

    public boolean contains(E item) {
        return indexOf(item) != -1;
    }

    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    private E detach(int index) {
        Node curr = node(index);
        E element = (E) curr.data;
        if (curr.prev == null) {
            if (curr.next == null)
                clear();
            else {
                first = curr.next;
                first.prev = null;
            }
        }
        else {
            if (curr.next == null) {
                last = curr.prev;
                last.next = null;
            }
            else {
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
            }
        }
        curr.next = null;
        curr.prev = null;
        size--;
        return element;
    }

    public E get(int index) {
        checkIndex(index);
        Node curr = first;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return (E) curr.data;
    }

    public int indexOf(E item) {
        for (int i = 0; i < size; i++) {
            if (item == get(i))
                return i;
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<E> iterator() {
        return new LinkedIterator();
    }

    private Node node(int index) {
        checkIndex(index);
        Node curr = first;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public E remove(int index) {
        return detach(index);
    }

    public boolean remove(E item) {
        int oldSize = size;
        int index = indexOf(item);
        detach(index);
        return size == oldSize-1;
    }

    public E set(int index, E item) {
        checkIndex(index);
        E element = get(index);
        Node curr = node(index);
        curr.data = item;
        return element;
    }

    public int size() {
        return size;
    }

    public String toString() {
        if (size == 0)
            return "[]";
        else {
            E element = get(0);
            StringBuilder result = new StringBuilder("[" + element);
            for (int i = 1; i < size; i++) {
                element = get(i);
                result.append(", ").append(element);
            }
            result.append("]");
            return result.toString();
        }
    }

    private static class Node<E> {
        public E data;
        public Node next;
        public Node prev;

        public Node(Node prev, E data) {
            this(prev, null, data);
        }

        public Node(Node prev, Node next, E data) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public class LinkedIterator implements Iterator<E> {

        //data fields
        private int position;
        private boolean isRemovable;

        //constructor
        public LinkedIterator() {
            position = 0;
            isRemovable = false;
        }

        //methods
        public boolean hasNext() {
            return position < size();
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            position++;
            isRemovable = true;
            return get(position - 1);
        }

        public void remove() {
            if(!isRemovable) {
                throw new IllegalStateException();
            }
            LinkedList.this.remove(position - 1);
            position--;
            isRemovable = false;
        }
    }
}
