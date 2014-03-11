package se.kth.list.test;

import java.util.List;

import org.junit.Test;

import se.kth.list.implementations.ArrList;
import se.kth.list.implementations.Obj;

public class ArrListTests {

	@Test
	public void testArrList() {

		List<Obj> list = new ArrList<Obj>();

		Obj o = new Obj(1);

		
		list.add(new Obj(2));
		list.add(o);
		list.add(o);
		
		System.out.println(list.lastIndexOf(new Obj(2)));

		for (Object os : list) {
				System.out.println(((Obj) os).getTest());
		}
	}

}
