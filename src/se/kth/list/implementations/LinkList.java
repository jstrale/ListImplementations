/**
 * Description: An implementation of the List interface using a linked list
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

public class LinkList<E> extends AbsList<E> {

	private ListItem<E> mFirst;
	private ListItem<E> mLast;

	public LinkList() {
		mSize = 0;
		mFirst = null;
		mLast = null;
	}

	@Override
	public boolean add(E e) {
		if(mSize == 0) {
			mFirst = new ListItem<E>(e);
			mLast = mFirst;
		}
		else {
			ListItem<E> newItem = new ListItem<E>(e);
			mLast.mNext = newItem;
			newItem.mPrevious = mLast;
			mLast = newItem;
		}
		mSize++;
		return true;
	}

	@Override
	public void add(int index, E element) {
		if(index < 0 || index > mSize)
			throw new IndexOutOfBoundsException("Index: " + index);

		ListItem<E> newItem = new ListItem<E>(element); 
		ListItem<E> oldItem;

		if(mSize == 0) {				// First item added
			mFirst = newItem;
			mLast = mFirst;
		} else if(index == 0) {			// New First item
			mFirst.mPrevious = newItem;
			newItem.mNext = mFirst;
			mFirst = newItem;
		} else if(index == mSize) {		// New Last item
			mLast.mNext = newItem;
			newItem.mPrevious = mLast;
			mLast = newItem;
		} else {						// Added in existing list
			if(index <= mSize/2)
				oldItem = traverseFromFirst(index);
			else
				oldItem = traverseFromLast(index);
			
			oldItem.mPrevious.mNext = newItem;
			newItem.mPrevious = oldItem.mPrevious;
			oldItem.mPrevious = newItem;
			newItem.mNext = oldItem;
		}
		mSize++;
	}

	@Override
	public E get(int index) {
		ListItem<E> item;

		checkBounds(index);

		if(index <= mSize/2)
			item = traverseFromFirst(index);
		else
			item = traverseFromLast(index);
		return item.mContent;
	}

	@Override
	public E remove(int index) {
		checkBounds(index);

		ListItem<E> itemToRemove;

		if(index <= mSize/2)
			itemToRemove = traverseFromFirst(index);
		else
			itemToRemove = traverseFromLast(index);

		E elementToRemove = itemToRemove.mContent;

		if(mSize == 1) {				// Last item removed
			mFirst = null;
			mLast = null;
		} else if(index == 0) {			// New First
			mFirst = mFirst.mNext;
			mFirst.mPrevious = null;
		} else if(index == mSize-1) {	// New Last
			mLast = mLast.mPrevious;
			mLast.mNext = null;
		} else {						// Remove from middle of list
			itemToRemove.mPrevious.mNext = itemToRemove.mNext;
			itemToRemove.mNext.mPrevious = itemToRemove.mPrevious;
			itemToRemove = null;
		}
		mSize--;
		return elementToRemove;
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkIterator<E>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray() {
		E[] array = (E[]) new Object[mSize];
		int index = 0;
		ListItem<E> currentItem = mFirst;

		while(currentItem != null) {
			array[index] = currentItem.mContent;
			currentItem = currentItem.mNext;
			index++;
		}
		return array;
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

		int index = 0;
		ListItem<E> currentItem = mFirst;

		while(currentItem != null) {
			a[index] = (T) currentItem.mContent;
			currentItem = currentItem.mNext;
			index++;
		}
		return a;
	}

	@Override
	public void clear() {
		mFirst = null;
		mLast = null;
		mSize = 0;
	}

	@Override
	public int indexOf(Object o) {
		ListItem<E> currentItem = mFirst;
		int currentIndex = 0;

		while(currentItem != null) {
			if(currentItem.mContent.equals(o)) {
				return currentIndex;
			}
			currentItem = currentItem.mNext;
			currentIndex++;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		ListItem<E> currentItem = mLast;
		int currentIndex = mSize-1;

		while(currentItem != null) {
			if(currentItem.mContent.equals(o)) {
				return currentIndex;
			}
			currentItem = currentItem.mPrevious;
			currentIndex--;
		}
		return -1;
	}

	@Override
	public ListIterator<E> listIterator() {
		return new LinkListIterator<E>();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkListIterator<E>(index);
	}

	private ListItem<E> traverseFromLast(int index) {
		int currentIndex = mSize - 1;
		ListItem<E> currentItem = mLast;
		
		while(currentIndex != index) {
			currentIndex--;
			currentItem = currentItem.mPrevious; 
		}
		return currentItem;
	}

	private ListItem<E> traverseFromFirst(int index) {
		int currentIndex = 0;
		ListItem<E> currentItem = mFirst;

		while(currentIndex != index) {
			currentIndex++;
			currentItem = currentItem.mNext; 
		}
		return currentItem;
	}

	private class ListItem<T> {
		private ListItem<T> mNext;
		private ListItem<T> mPrevious;
		private T mContent;

		public ListItem(T content) {
			mContent = content;
			mNext = null;
			mPrevious = null;
		}
	}
	
	// *******************************************----ITERATOR----******************************************* //
	private class LinkIterator<T> extends AbsIterator<T> {

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
				LinkList.this.remove(previousIndex);
				okToRemove = false;
			}
		}
	}
		
	// *****************************************----LIST ITERATOR----***************************************** //	
	private class LinkListIterator<T> extends AbsListIterator<T> {
	
		public LinkListIterator() {
			super();
		}
		
		public LinkListIterator(int index) {
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
			LinkList.this.remove(previousIndex);
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public void add(T e) {
			okToRemove = false;
			LinkList.this.add(currentIndex++, (E) e);
		}
	}
}
