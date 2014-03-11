/**
 * Description: An implementation of the List interface using an array
 * as internal representation
 * 
 * @author Helena Lindén, Johan Stråle
 * @since 2014-03-11
 */
package se.kth.list.implementations;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrList<E> implements List<E> {

	private static final int START_SIZE = 10;
	private E[] mList;
	private int mSize;

	@SuppressWarnings("unchecked")
	public ArrList() {
		mList = (E[]) new Object[START_SIZE];
		mSize = 0;
	}

	@Override
	public boolean add(E e) {

		if (mSize >= mList.length) {
			doubleCapacity();
		}
		mList[mSize++] = e;
		return true;
	}

	@Override
	public void add(int index, E element) {

		if (index < 0 || index > mSize) {
			throw new IndexOutOfBoundsException("Index: " + index);
		}
		if (mSize >= mList.length) {
			doubleCapacity();
		}
		System.arraycopy(mList, index, mList, index + 1, mSize - index);
		mList[index] = element;
		mSize++;
	}

	@Override
	public E get(int index) {

		checkBounds(index);
		return mList[index];
	}

	@Override
	public boolean remove(Object o) {

		boolean isFound = false;

		for (int i = 0; i < mSize; i++) {
			if (mList[i].equals(o)) {
				remove(i);
				isFound = true;
				break;
			}
		}
		return isFound;
	}

	@Override
	public E remove(int index) {

		checkBounds(index);
		E removedObject = mList[index];
		System.arraycopy(mList, index + 1, mList, index, mSize - index);
		mSize--;
		return removedObject;
	}

	@Override
	public int size() {
		return mSize;
	}

	@Override
	public boolean isEmpty() {
		if (mSize == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Object o) {
		for (int i = 0; i < mSize; i++) {
			if (mList[i].equals(o))
				return true;
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new ArrIterator<E>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray() {

		E[] objects = (E[]) new Object[mSize];

		for (int i = 0; i < mSize; i++) {
			objects[i] = (E) mList[i];
		}

		return objects;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {

		if (a.length < mSize) {
			a = (T[]) Array.newInstance(a.getClass().getComponentType(), mSize);
		}
		if (a.length > mSize) {
			a[mSize] = null;
		}
		System.arraycopy(mList, 0, a, 0, mSize);
		return a;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		
		boolean isFound = false;
		
		for(Object o : c) {
			for(int i = 0; i < mSize; i++) {
				if(mList[i].equals(o))
					isFound = true;
			}
			if(!isFound)
				return false;
			else
				isFound = false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		mList = (E[]) new Object[START_SIZE];
		mSize = 0;
	}

	@Override
	public int indexOf(Object o) {
		int index = -1;

		for (int i = 0; i < mSize; i++) {
			if (o.equals(mList[i])) {
				index = i;
				break;
			}
		}
		return index;
	}

	@Override
	public int lastIndexOf(Object o) {

		int index = -1;

		for (int i = 0; i < mSize; i++) {
			if (o.equals(mList[i])) {
				index = i;
			}
		}
		return index;
	}

	@Override
	public ListIterator<E> listIterator() {
		return new arrListIterator<E>();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		checkBounds(index);
		return new arrListIterator<E>(index);
	}

	private void doubleCapacity() {
	
		@SuppressWarnings("unchecked")
		E[] newList = (E[]) new Object[mList.length * 2];
		System.arraycopy(mList, 0, newList, 0, mSize);
		mList = newList;
	}

	private void checkBounds(int index) {
		if (index < 0 || index >= mSize) {
			throw new IndexOutOfBoundsException("Index: " + index);
		}
	}

	// *******************************************----ITERATOR----******************************************* //
	private class ArrIterator<T> implements Iterator<T> {

		private int currentIndex;
		private int previousIndex;

		public ArrIterator() {
			currentIndex = 0;
			previousIndex = -1;
		}

		@Override
		public boolean hasNext() {

			if (currentIndex < mSize) {
				return true;
			}
			return false;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (currentIndex < mSize) {
				previousIndex = currentIndex;
				return (T) get(currentIndex++);
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {

			if (previousIndex != -1) {
				ArrList.this.remove(previousIndex);
				previousIndex = -1;
			}
		}
	}

	// *****************************************----LIST ITERATOR----***************************************** //	
	private class arrListIterator<T> implements ListIterator<T> {

		private int currentIndex;
		private int previousIndex;
		
		public arrListIterator() {
			this(0);
		}
		
		public arrListIterator(int index) {
			currentIndex = index;
			previousIndex = -1;
		}
		
		@Override
		public boolean hasNext() {
			
			if (currentIndex < mSize) {
				return true;
			}
			return false;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (currentIndex < mSize) {
				previousIndex = currentIndex;
				return (T) get(currentIndex++);
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public boolean hasPrevious() {
			
			if(currentIndex > 0) {
				return true;
			}
			return false;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T previous() {
			if (currentIndex > 0) {
				previousIndex = currentIndex;
				return (T) get(--currentIndex);
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public int nextIndex() {
			if(currentIndex == mSize)
				return mSize;
			else
				return currentIndex + 1;
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
			if (previousIndex != -1) {
				ArrList.this.remove(previousIndex);
				previousIndex = -1;
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public void add(T e) {
			ArrList.this.add(currentIndex++, (E)e);
		}

		@Override
		public void set(T e) {
			throw new UnsupportedOperationException();
		}
	}
	// **************************************************************************************************************//

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
}
