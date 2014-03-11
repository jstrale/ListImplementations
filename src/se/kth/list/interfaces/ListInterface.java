package se.kth.list.interfaces;

public interface ListInterface<E> {

	
	public boolean add(E e);
	public void add(int index, E element);
	public E get(int index);
	public boolean remove(Object o);
	public E remove(int index);
	public int size();
	public boolean isEmpty();
	
}
