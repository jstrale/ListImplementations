/**
 * Description: Testing LinkList
 * 
 * @author Helena Lindén, Johan Stråle
 * @since 2014-03-11
 */

package se.kth.list.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

import se.kth.list.implementations.LinkList;
import se.kth.list.implementations.Obj;

public class LinkListTests {

	@Test
	public void shouldReturnAddTrue() {
		List<Obj> list = new LinkList<Obj>();
		
		assertTrue(list.add(new Obj(1)));

	}
	
	@Test 
	public void shouldAddAtSpecificIndex() {
		List<Obj> list = new LinkList<Obj>();
		list.add(0, new Obj(1));
		list.add(new Obj(2));
		list.add(new Obj(3));
		list.add(0, new Obj(0));
		list.add(2, new Obj(5));
		
		assertEquals(0, list.get(0).getTest());
		assertEquals(1, list.get(1).getTest());
		assertEquals(5, list.get(2).getTest());
		assertEquals(2, list.get(3).getTest());
		assertEquals(3, list.get(4).getTest());
	}
	
	@Test
	public void shouldGetCorrectObject() {
		List<Obj> list = new LinkList<Obj>();
		list.add(new Obj(1));
		list.add(new Obj(2));
		list.add(new Obj(3));
		
		assertEquals(1, list.get(0).getTest());
		assertEquals(2, list.get(1).getTest());
		assertEquals(3, list.get(2).getTest());
		
	}
	
	@Test
	public void shouldRemoveCorrectElementAtIndex() {
		List<Obj> list = new LinkList<Obj>();
		
		Obj o1 = new Obj(1);
		Obj o2 = new Obj(2);
		Obj o3 = new Obj(3);
		
		list.add(o1);
		list.add(o2);
		list.add(o3);
		
		assertEquals(o1, list.remove(0));
		assertEquals(o2, list.remove(0));
		assertEquals(o3, list.remove(0));
		
	}
	
	@Test
	public void shouldRemoveCorrectElement() {
		List<Obj> list = new LinkList<Obj>();
		
		Obj o1 = new Obj(1);
		Obj o2 = new Obj(2);
		Obj o3 = new Obj(3);
		
		list.add(o1);
		list.add(o2);
		list.add(o3);
		
		assertTrue(list.remove(o3));
		assertTrue(list.remove(o2));
		assertTrue(list.remove(o1));
		assertFalse(list.remove(new Obj(5)));
		assertTrue(list.isEmpty());
		
	}
	
	@Test
	public void shouldClearList() {
		List<Obj> list = new LinkList<Obj>();
		
		Obj o1 = new Obj(1);
		Obj o2 = new Obj(2);
		Obj o3 = new Obj(3);
		
		list.add(o1);
		list.add(o2);
		list.add(o3);
		
		list.clear();
		assertTrue(list.isEmpty());
		
	}
	
	@Test
	public void shouldReturnCorrectFirstIndex() {
		List<Obj> list = new LinkList<Obj>();
		
		Obj o1 = new Obj(1);
		Obj o2 = new Obj(2);
		Obj o3 = new Obj(3);
		
		list.add(o1);
		list.add(o2);
		list.add(o3);
		list.add(o1);
		
		assertEquals(0, list.indexOf(o1));
	}
	
	@Test
	public void shouldReturnCorrectLastIndex() {
		List<Obj> list = new LinkList<Obj>();
		
		Obj o1 = new Obj(1);
		Obj o2 = new Obj(2);
		Obj o3 = new Obj(3);
		
		list.add(o1);
		list.add(o2);
		list.add(o3);
		list.add(o1);
		
		assertEquals(3, list.lastIndexOf(o1));
	}
	
	@Test
	public void shouldReturnCorrectToArray() {
		List<Obj> list = new LinkList<Obj>();
		
		Obj o1 = new Obj(1);
		Obj o2 = new Obj(2);
		Obj o3 = new Obj(3);
		
		list.add(o1);
		list.add(o2);
		list.add(o3);
		
		Object[] array = list.toArray();
		
		for(int i=0; i<array.length; i++) {
			assertEquals(i+1, ((Obj)array[i]).getTest());
		}
		
		Obj[] objArray = new Obj[3];
		
		objArray = list.toArray(objArray);
		
		for(int i=0; i<objArray.length; i++) {
			assertEquals(i+1, ((Obj)objArray[i]).getTest());
		}
	}
	
	@Test
	public void shouldTestContain() {
		List<Obj> list = new LinkList<Obj>();
		
		Obj o1 = new Obj(1);
		Obj o2 = new Obj(2);
		Obj o3 = new Obj(3);
		
		list.add(o1);
		list.add(o2);
		list.add(o3);
		
		assertTrue(list.contains(o1));
		assertFalse(list.contains(new Obj(1)));
	}
	
	@Test
	public void shouldTestContainsAll() {
		List<Obj> list = new LinkList<Obj>();
		
		Obj o1 = new Obj(1);
		Obj o2 = new Obj(2);
		Obj o3 = new Obj(3);
		
		list.add(o1);
		list.add(o2);
		list.add(o3);
		
		List<Obj> arrayList = new ArrayList<Obj>();
		arrayList.add(o1);
		arrayList.add(o3);
		
		assertTrue(list.containsAll(arrayList));
		
		arrayList.add(new Obj(1));
		
		assertFalse(list.containsAll(arrayList));
	}
	
	@Test
	public void shouldTestIterator() {
		List<Obj> list = new LinkList<Obj>();
		
		Obj o1 = new Obj(1);
		Obj o2 = new Obj(2);
		Obj o3 = new Obj(3);
		
		list.add(o1);
		list.add(o2);
		list.add(o3);
		
		int i = 1;
		for(Obj o : list) {
			assertEquals(i, o.getTest());
			i++;
		}
	}
	
	@Test
	public void shouldTestListIterator() {
		List<Obj> list = new LinkList<Obj>();
		
		Obj o1 = new Obj(1);
		Obj o2 = new Obj(2);
		Obj o3 = new Obj(3);
		
		list.add(o1);
		list.add(o2);
		list.add(o3);
		
		int i = 1;
		
		ListIterator<Obj> it = list.listIterator();
		while(it.hasNext()) {
			assertEquals(i, it.next().getTest());
			i++;
		}
		
		while(it.hasPrevious()) {
			i--;
			System.out.println(i);
			assertEquals(i, it.previous().getTest());
		}
		
		ListIterator<Obj> iter = list.listIterator(2);
		i = 4;
		while(iter.hasPrevious()) {
			i--;
			assertEquals(i, iter.previous().getTest());
		}
		
		while(iter.hasNext()) {
			assertEquals(i, iter.next().getTest());
			i++;
		}
		
	}
}
