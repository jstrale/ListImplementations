package se.kth.list.implementations;

import java.util.Iterator;

public abstract class AbsIterator<E> implements Iterator<E> {

	protected int currentIndex;
	protected int previousIndex;
	protected boolean okToRemove = false;
	
	public AbsIterator() {
		currentIndex = 0;
		previousIndex = -1;
	}
}
