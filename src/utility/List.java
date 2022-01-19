package utility;

public interface List<E> {
    public boolean add(E item);
    public void add(int index, E item);
    public void clear();
    public boolean contains(E item);
    public E get(int index);
    public int indexOf(E item);
    public boolean isEmpty();
    public Iterator<E> iterator();
    public boolean remove (E item);
    public E remove(int index);
    public E set(int index, E item);
    public int size();
    public String toString();


}
