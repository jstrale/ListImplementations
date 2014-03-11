package se.kth.list.test;

import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

import se.kth.list.implementations.ArrList;
import se.kth.list.implementations.Obj;

public class ArrListTests {

	@Test
	public void testArrList() {

		List<Obj> list = new ArrList<Obj>();

		Obj o1 = new Obj(1);
		Obj o2 = new Obj(2);
		Obj o3 = new Obj(3);
		Obj o4 = new Obj(4);
		
		list.add(o1);
		list.add(o2);
		list.add(o3);

		ListIterator<Obj> it = list.listIterator(2);
		
		it.add(o4);
		
		System.out.println(it.next().getTest());
	}

}
