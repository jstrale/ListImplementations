package se.kth.list.implementations;

import java.util.Collection;
import java.util.List;

public abstract class AbsList<E> implements List<E> {

	protected int mSize;

	@Override
	public int size() {
		return mSize;
	}

	@Override
	public boolean isEmpty() {
		if(mSize == 0)
			return true;
		return false;
	}
	
	@Override
	public boolean remove(Object o) {
		int indexToRemove = indexOf(o);
		if(indexToRemove != -1) {
			remove(indexToRemove);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean contains(Object o) {
		if(indexOf(o) == -1)
			return false;
		else
			return true;
	}
	
	@Override
	public boolean containsAll(Collection<?> c) {
		for(Object o : c) {
			if(indexOf(o) == -1)
				return false;
		}
		return true;
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	protected void checkBounds(int index) {
		if(index < 0 || index >= mSize)
			throw new IndexOutOfBoundsException("Index: " + index);
	}
}
