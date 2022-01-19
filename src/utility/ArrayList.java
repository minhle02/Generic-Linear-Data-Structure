package utility;

import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E>{
    private int size;
    private E[] data;

    public static int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity<0)
            throw new IllegalArgumentException();
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public boolean add(E item) {
        ensureCapacity(size+1);
        int oldSize = size;
        data[size] = item;
        size++;
        return size == (oldSize + 1);
    }

    public void add(int index, E item) {
        ensureCapacity(size+1);
        shiftRight(index);
        data[index] = item;
        size++;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void clear() {
        for (int i = 0; i < size; ++i) {
            data[i] = null;
        }
        size = 0;
    }

    public boolean contains(E item) {
        return indexOf(item) != -1;
    }

    public void ensureCapacity(int capacity) {
        if (capacity > data.length) {
            int newCapacity = data.length * 2 + 1;
            if (capacity > newCapacity) {
                newCapacity = capacity;
            }
            E[] temp = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                temp[i] = data[i];
            }
            data = temp;
        }
    }

    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element))
                return i;
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<E> iterator() {
        return new ArrayIterator();
    }


    public E remove(int index) {
        checkIndex(index);
        E oldElement = get(index);
        shiftLeft(index);
        size--;
        data[size] = null;
        return oldElement;
    }

    public boolean remove (E item) {
        int index = indexOf(item);
        if (index == -1)
            return false;
        remove(index);
        return true;
    }

    public E set(int index, E item) {
        checkIndex(index);
        E oldElement = get(index);
        data[index] = item;
        return oldElement;
    }

    private void shiftLeft(int index) {
        for (int i = index; i < size; i++) {
            data[i] = data[i + 1];
        }
    }

    private void shiftRight(int index) {
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
    }

    public int size() {
        return size;
    }

    public String toString() {
        if (size == 0)
            return "[]";
        else {
            StringBuilder result = new StringBuilder("[" + data[0]);
            for (int i = 1; i < size; i++) {
                result.append(", ").append(data[i]);
            }
            result.append("]");
            return result.toString();
        }
    }

    //iterator
    public class ArrayIterator implements Iterator<E> {

        //data fields
        private int position;
        private boolean isRemovable;

        //constructor
        public ArrayIterator() {
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
            ArrayList.this.remove(position - 1);
            position--;
            isRemovable = false;
        }
    }
}
