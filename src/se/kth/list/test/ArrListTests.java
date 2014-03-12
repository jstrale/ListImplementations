/**
 * Description: Tests for array implementation
 * 
 * @author Helena Lindén, Johan Stråle
 * @since 2014-03-11
 * 
 */
package se.kth.list.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import se.kth.list.implementations.ArrList;
import se.kth.list.implementations.Obj;

public class ArrListTests {

	@Test
	public void shouldTestAddAndGet() {

		List<Obj> list = new ArrList<Obj>();

		for (int i = 0; i < 15; i++) {
			list.add(new Obj(i));
		}

		for (int i = 0; i < 15; i++) {
			assertEquals(i, list.get(i).getTest());
		}
	}

	@Test
	public void shouldTestAddAtIndex() {

		List<Obj> list = new ArrList<Obj>();

		for (int i = 0; i < 15; i++) {
			list.add(i, new Obj(i));
			assertEquals(i, list.get(i).getTest());
		}
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void shouldTestAddAtOutOfBoundIndex() {

		List<Obj> list = new ArrList<Obj>();

		list.add(5, new Obj(1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void shouldTestGetOutOfBoundIndex() {

		List<Obj> list = new ArrList<Obj>();

		list.get(5);
	}

	@Test
	public void shouldTestRemoveObject() {

		List<Obj> list = new ArrList<Obj>();

		Obj o1 = new Obj(1);
		Obj o2 = new Obj(2);

		list.add(o1);
		list.add(o2);

		assertTrue(list.remove(o1));
		assertFalse(list.remove(o1));
		assertTrue(list.remove(o2));
		assertFalse(list.remove(o2));
	}

	@Test
	public void shouldTestRemoveAtIndex() {

		List<Obj> list = new ArrList<Obj>();

		for (int i = 0; i < 15; i++) {
			list.add(i, new Obj(i));
		}

		for (int i = 0; i < 15; i++) {
			assertEquals(i, list.remove(0).getTest());
		}

		assertTrue(list.isEmpty());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void shouldTestRemoveOutOfBoundIndex() {

		List<Obj> list = new ArrList<Obj>();

		list.remove(5);
	}

	@Test
	public void shouldTestSize() {

		List<Obj> list = new ArrList<Obj>();

		for (int i = 0; i < 15; i++) {
			list.add(i, new Obj(i));
		}

		assertEquals(15, list.size());
	}

	@Test
	public void shouldTestIsEmpty() {

		List<Obj> list = new ArrList<Obj>();

		assertTrue(list.isEmpty());
	}

	@Test
	public void shouldTestContains() {

		List<Obj> list = new ArrList<Obj>();

		Obj o1 = new Obj(1);
		Obj o2 = new Obj(2);

		list.add(o1);

		assertTrue(list.contains(o1));
		assertFalse(list.contains(o2));
	}

	@Test
	public void shouldTestContainsAll() {

		List<Obj> list = new ArrList<Obj>();

		Obj o1 = new Obj(1);
		Obj o2 = new Obj(2);
		Obj o3 = new Obj(3);
		Obj o4 = new Obj(4);

		ArrayList<Obj> list2 = new ArrayList<Obj>();

		list.add(o1);
		list.add(o2);
		list.add(o3);

		list2.add(o1);
		list2.add(o2);

		ArrayList<Obj> list3 = new ArrayList<Obj>();

		list3.add(o1);
		list3.add(o4);

		assertTrue(list.containsAll(list2));
		assertFalse(list.containsAll(list3));
	}

	@Test
	public void shouldTestClear() {

		List<Obj> list = new ArrList<Obj>();

		for (int i = 0; i < 15; i++) {
			list.add(i, new Obj(i));
		}

		list.clear();

		assertTrue(list.isEmpty());
	}

	@Test
	public void shouldTestIndexOfAndLastIndexOf() {

		List<Obj> list = new ArrList<Obj>();

		Obj o1 = new Obj(1);
		Obj o2 = new Obj(2);
		Obj o3 = new Obj(3);
		Obj o4 = new Obj(4);

		list.add(o1);
		list.add(o2);
		list.add(o3);
		list.add(o4);
		list.add(o1);

		assertEquals(0, list.indexOf(o1));
		assertEquals(4, list.lastIndexOf(o1));
	}

	@Test
	public void shouldTestSimpleToArray() {

		List<Obj> list = new ArrList<Obj>();

		for (int i = 0; i < 15; i++) {
			list.add(i, new Obj(i));
		}

		Object[] array = list.toArray();

		for (int i = 0; i < array.length; i++) {
			assertEquals(i, ((Obj) array[i]).getTest());
		}
	}

	@Test
	public void shouldTestAdvancedToArray() {

		List<Obj> list = new ArrList<Obj>();

		for (int i = 0; i < 15; i++) {
			list.add(i, new Obj(i));
		}

		Object[] array = new Object[10];

		array = list.toArray(array);

		for (int i = 0; i < array.length; i++) {
			assertEquals(i, ((Obj) array[i]).getTest());
		}
	}

	@Test
	public void shouldTestIterator() {

		List<Obj> list = new ArrList<Obj>();

		for (int i = 0; i < 15; i++) {
			list.add(i, new Obj(i));
		}

		Obj o1 = new Obj(16);

		list.add(o1);

		Iterator<Obj> it = list.iterator();
		int i = 0;
		while (it.hasNext()) {
			Obj o = it.next();
			if (o.equals(o1))
				it.remove();
			else {
				assertEquals(i, o.getTest());
				i++;
			}
		}
		assertFalse(list.contains(o1));
		i = 0;
		for (Obj o : list) {
			assertEquals(i, o.getTest());
			i++;
		}
	}

	@Test(expected = NoSuchElementException.class)
	public void shouldTestIteratorNoSuchElementException() {

		List<Obj> list = new ArrList<Obj>();

		Obj o1 = new Obj(16);

		list.add(o1);

		Iterator<Obj> it = list.iterator();

		it.next();
		it.next();
	}

	@Test(expected = NoSuchElementException.class)
	public void shouldTestListIteratorNoSuchElementExceptionOnNext() {

		List<Obj> list = new ArrList<Obj>();

		Obj o1 = new Obj(16);

		list.add(o1);

		ListIterator<Obj> it = list.listIterator();

		it.next();
		it.next();
	}

	@Test(expected = NoSuchElementException.class)
	public void shouldTestListIteratorNoSuchElementExceptionOnPrevious() {

		List<Obj> list = new ArrList<Obj>();

		Obj o1 = new Obj(16);

		list.add(o1);

		ListIterator<Obj> it = list.listIterator(0);

		it.previous();
		it.previous();
	}

	@Test
	public void shouldTestListIteratorWithoutIndex() {

		List<Obj> list = new ArrList<Obj>();

		for (int i = 0; i < 15; i++) {
			list.add(i, new Obj(i));
		}

		ListIterator<Obj> it = list.listIterator();
		int i = 0;
		while (it.hasNext()) {
			Obj o = it.next();
			assertEquals(i, o.getTest());
			i++;
		}
		i = 14;
		while(it.hasPrevious()) {
			Obj o = it.previous();
			assertEquals(i, o.getTest());
			i--;
		}
		
		assertEquals(0, it.nextIndex());
		it.next();
		assertEquals(0, it.previousIndex());
		
		it.remove();
		
		assertEquals(14, list.size());
		
		it.add(new Obj(1));
		
		assertEquals(15, list.size());
	}
	
	@Test
	public void shouldTestListIteratorWithIndex() {

		List<Obj> list = new ArrList<Obj>();

		for (int i = 0; i < 15; i++) {
			list.add(i, new Obj(i));
		}

		ListIterator<Obj> it = list.listIterator(14);
		int i = 14;
		while(it.hasPrevious()) {
			Obj o = it.previous();
			assertEquals(i, o.getTest());
			i--;
		}
		
		i = 0;
		while (it.hasNext()) {
			Obj o = it.next();
			assertEquals(i, o.getTest());
			i++;
		}
	}
}
