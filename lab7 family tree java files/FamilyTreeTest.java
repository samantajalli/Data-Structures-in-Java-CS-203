package lab7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

public class FamilyTreeTest {
	
	@Test
	public void testGetPersonById() {
		Person adam = new Person("Johnson", "Adam", new GregorianCalendar(0,01,01), new GregorianCalendar(100,12,31));
		Person eve = new Person("Johnson", "Eve", new GregorianCalendar(1,01,02), new GregorianCalendar(100,1,31));
		Person cain = new Person("Johnson", "Cain", new GregorianCalendar(21,6,30), new GregorianCalendar(38,6,30));
		Person lisa = new Person("Johnson", "Lisa", new GregorianCalendar(18,7,25), new GregorianCalendar(42,10,45));
		
		FamilyTree t = new FamilyTreeImpl(adam, eve);
		t.add(cain, 1, 2);
		t.add(lisa, 3, 0);
		assertEquals(t.getPersonById(1).getFirstName(), "Adam");
		assertEquals(t.getPersonById(4).getFirstName(), "Lisa");
	}
	
	@Test
	public void testGetAllSizeEqual(){
		Person adam = new Person("Johnson", "Adam", new GregorianCalendar(0,01,01), new GregorianCalendar(100,12,31));
		Person eve = new Person("Johnson", "Eve", new GregorianCalendar(1,01,02), new GregorianCalendar(100,1,31));
		Person cain = new Person("Johnson", "Cain", new GregorianCalendar(21,6,30), new GregorianCalendar(38,6,30));
		
		FamilyTree t = new FamilyTreeImpl(adam, eve);
		t.add(cain, 1, 2);
		
		Set<Person> set = new HashSet<Person>();
		set.add(adam);
		set.add(eve);
		set.add(cain);
		
		assertEquals(size(set), t.size());
	}
	

	@Test
	public void testGetDescendants(){
		Person adam = new Person("Johnson", "Adam", new GregorianCalendar(0,01,01), new GregorianCalendar(100,12,31));
		Person eve = new Person("Johnson", "Eve", new GregorianCalendar(1,01,02), new GregorianCalendar(100,1,31));
		Person cain = new Person("Johnson", "Cain", new GregorianCalendar(21,6,30), new GregorianCalendar(38,6,30));
		Person abel = new Person("Johnson", "Abel", new GregorianCalendar(22,3,20), new GregorianCalendar(30,4,30));
		Person lisa = new Person("Johnson", "Lisa", new GregorianCalendar(18,7,25), new GregorianCalendar(42,10,45));
		
		FamilyTree t = new FamilyTreeImpl(adam, eve);
		t.add(cain, 1, 2);
		t.add(abel, 1, 2);
		t.add(lisa, 3, 0);
		
		Set<Person> set = new HashSet<Person>();
		set.add(adam);
		set.add(eve);
		set.add(cain);
		set.add(abel);
		set.add(lisa);
		
		
		Set<Person> returnSet = new HashSet<Person>();
		returnSet = t.getDescendants(1);
		System.out.println("the size of returnSet is: " + returnSet.size());
		System.out.println("the size of set is: " + set.size());
		for(Person p : returnSet){
			for(Person q : set){
				if(!p.equals(q))
					fail();
			}
		}
	}
	
	@Test
	public void testGetParents(){
		Person adam = new Person("Johnson", "Adam", new GregorianCalendar(0,01,01), new GregorianCalendar(100,12,31));
		Person eve = new Person("Johnson", "Eve", new GregorianCalendar(1,01,02), new GregorianCalendar(100,1,31));
		Person cain = new Person("Johnson", "Cain", new GregorianCalendar(21,6,30), new GregorianCalendar(38,6,30));
		Person abel = new Person("Johnson", "Abel", new GregorianCalendar(22,3,20), new GregorianCalendar(30,4,30));
		Person lisa = new Person("Johnson", "Lisa", new GregorianCalendar(18,7,25), new GregorianCalendar(42,10,45));
		
		FamilyTree t = new FamilyTreeImpl(adam, eve);
		t.add(cain, 1, 2);
		t.add(abel, 1, 2);
		t.add(lisa, 3, 0);
		
		List<Person> list = new ArrayList<Person>();
		list.add(adam);
		list.add(eve);
		list.add(cain);
		list.add(abel);
		list.add(lisa);
		
		List<Person> returnList = t.getParents(5);
		
		assertEquals(1, returnList.size());
	}
	
	@Test
	public void testGetPersonsByName(){
		Person adam = new Person("Johnson", "Adam", new GregorianCalendar(0,01,01), new GregorianCalendar(100,12,31));
		Person eve = new Person("Johnson", "Eve", new GregorianCalendar(1,01,02), new GregorianCalendar(100,1,31));
		Person cain = new Person("Johnson", "Cain", new GregorianCalendar(21,6,30), new GregorianCalendar(38,6,30));
		
		FamilyTree t = new FamilyTreeImpl(adam, eve);
		t.add(cain, 1, 2);
		
		List<Person> lis = new ArrayList<Person>();
		lis.add(eve);
		assertEquals(t.getPersonsByName("Johnson", "Eve"), lis);
	}
	
	
	private int size(Set<Person> s) {
		int count = 0;
		for(Person p :s){
			count++;
		}
		return count; // the number of nodes in FamilyTree
	}

}

