package lab7;

import java.util.List;
import java.util.Set;

public interface FamilyTree {
	public void add(Person self, Integer p1, Integer p2);
	public Set <Node<Person>> getAll();
	public Set <Person> getDescendants(int nodeNum);
	public List<Person> getParents(int id);
	public Person getPersonById(int id);
	public List <Person> getPersonsByName(String lastName, String firstName);
	public int size();
}
