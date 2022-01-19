package utility;

public class SinglyLinkedList<E> {
    private Node head;
    private int size;

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    public void append(E item) {
        Node newNode = new Node(item);
        if (size == 0)
            head = newNode;
        else {
            Node curr = node(size-1);
            curr.next = newNode;
        }
        size++;
    }

    public void prepend(E item) {
        Node newNode = new Node(item);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public int indexOf(E item) {
        Node curr = head;
        for (int i = 0; i < size; i++) {
            if (item == curr.data)
                return i;
            curr = curr.next;
        }
        return -1;
    }

    public E get(int index) {
        checkIndex(index);
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    private void checkIndex(int index) {
        if (index<0 || index>=size)
            throw new IllegalArgumentException();
    }

    private Node node(int index) {
        checkIndex(index);
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public void remove(int index) {
        Node curr = node(index);
        if (index == 0) {
            head = curr.next;
            curr.next = null;
        } else {
            Node prev = node(index-1);
            prev.next = curr.next;
            curr.next = null;
        }
        size--;
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

    private class Node {
        public E data;
        public Node next;

        public Node(E data) {
            this.data = data;
            next = null;
        }
    }
}
