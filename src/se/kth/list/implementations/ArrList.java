/**
 * Description: An implementation of the List interface using an array
 * as internal representation
 * 
 * @author Helena Lindén, Johan Stråle
 * @since 2014-03-11
 */
package se.kth.list.implementations;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrList<E> extends AbsList<E> {

	private static final int START_SIZE = 10;
	private E[] mList;

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
	public E remove(int index) {

		checkBounds(index);
		E removedObject = mList[index];
		System.arraycopy(mList, index + 1, mList, index, mSize - index);
		mSize--;
		return removedObject;
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
		return new ArrListIterator<E>();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		checkBounds(index);
		return new ArrListIterator<E>(index);
	}

	private void doubleCapacity() {
	
		@SuppressWarnings("unchecked")
		E[] newList = (E[]) new Object[mList.length * 2];
		System.arraycopy(mList, 0, newList, 0, mSize);
		mList = newList;
	}

	// *******************************************----ITERATOR----******************************************* //
			private class ArrIterator<T> extends AbsIterator<T> {

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
						okToRemove = true;
						return (T) get(currentIndex++);
					} else {
						throw new NoSuchElementException();
					}
				}

				@Override
				public void remove() {

					if (okToRemove) {
						ArrList.this.remove(previousIndex);
						okToRemove = false;
					}
				}
			}
			
			// *****************************************----LIST ITERATOR----***************************************** //	
			private class ArrListIterator<T> extends AbsListIterator<T> {
				
				public ArrListIterator() {
					super();
				}
				
				public ArrListIterator(int index) {
					super(index);
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
						if(currentIndex < 0)
							currentIndex++;
						previousIndex = currentIndex;
						okToRemove = true;
						return (T) get(currentIndex++);
					} else {
						throw new NoSuchElementException();
					}
				}

				@SuppressWarnings("unchecked")
				@Override
				public T previous() {
					if (currentIndex >= 0) {
						if(currentIndex == mSize)
							currentIndex--;
						previousIndex = currentIndex;
						okToRemove = true;
						return (T) get(currentIndex--);
					
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
				protected void removeIndex() {
					ArrList.this.remove(previousIndex);
				}

				@SuppressWarnings("unchecked")
				@Override
				public void add(T e) {
					okToRemove = false;
					ArrList.this.add(currentIndex++, (E)e);
				}
			}
	// **************************************************************************************************************//
}
