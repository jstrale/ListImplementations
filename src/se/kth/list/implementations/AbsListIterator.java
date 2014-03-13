package se.kth.list.implementations;

import java.util.ListIterator;

public abstract class AbsListIterator<E> implements ListIterator<E> {

	protected int currentIndex;
	protected int previousIndex;
	protected boolean okToRemove = false;
	
	public AbsListIterator() {
		this(0);
	}

	public AbsListIterator(int index) {
		currentIndex = index;
		previousIndex = -1;
	}
	
	@Override
	public boolean hasPrevious() {
		
		if(currentIndex >= 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public int previousIndex() {
		if(currentIndex == 0)
			return -1;
		else
			return currentIndex - 1;
	}
	
	@Override
	public void remove() {
		if (okToRemove) {
			removeIndex();
			okToRemove = false;
		}
	}
	
	protected void removeIndex() { }

	@Override
	public void set(E e) {
		throw new UnsupportedOperationException();
	}
}
