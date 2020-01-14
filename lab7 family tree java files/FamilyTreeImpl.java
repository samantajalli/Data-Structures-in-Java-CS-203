package lab7;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Set;
import java.util.ListIterator;

public class FamilyTreeImpl implements FamilyTree{
	private Node<Person> rootA = null; // 1st root
	private Node<Person> rootB = null; // 2nd root
	private Set<Node<Person>> set = new HashSet<Node<Person>>();
	private Integer nodeNum = 0 ; // instance var of id
	
	List<Node<Person>> childList = new ArrayList<>(set);
	
	
	
	public FamilyTreeImpl(Person a, Person b){
		nodeNum++;
		rootA = new Node<Person>(a, nodeNum); // 1st root node assigned, both parent ref null
		nodeNum++;
		rootB = new Node<Person>(b, nodeNum); // 2nd root node assigned, both parent ref null
		set.add(rootA);
		set.add(rootB);
		rootA.setChildren(new ArrayList<Node<Person>>()); 
		rootB.setChildren(new ArrayList<Node<Person>>());
		
	}
	
	/**
	 *For adding a node, 2 possibilities: 1.) at least one parent matches with idnum, 2.) no match
	 * Don't worry about p1 and p2 happening twice because our data structure is a set:{ } and 
	 * objects in set's occur only once
	 */
	@Override
	public void add(Person self, Integer p1, Integer p2) { // right now does not check nodes correctly
		// TODO Auto-generated method stub
		nodeNum++;
		Node<Person> newNode = new Node<Person>(self, nodeNum); // make a new node
		
		for(Node<Person> p : set){
			
			if(p.getId() == p1){ // if the node's id is equal to one of the parents of this new node
					newNode.setParent1(p);
					childList.add(newNode);
					p.setChildren(childList);
					childList.clear();// after setting the parent's children we clear the childList
									  // for the next parent's children
			}
			
			if(p1 == 0)
				newNode.setParent1(null);
			
			if(p.getId() == p2){
				newNode.setParent2(p);
				childList.add(newNode);
				p.setChildren(childList);
				childList.clear();
			}
			
			if(p2 == 0)
				newNode.setParent2(null);
		}
		/**
		for(Node<Person> p : set){
			if(p1 == 0 && p2 != 0){ // the case where p1 is null, p2 is not
				if(p.getId() == p2){
					newNode.setParent1(null);
					newNode.setParent2(p);
					p.addChildren(newNode);						
				}
			}
			if(p.getId() == p1 ){ // if node containing 1st id is in set
				
				for(Node<Person> q : set){
					if(q.getId() == p2){ // if node containing 2nd id is in set
										// the case where p1 & p2 are not null
					newNode.setParent1(p); // set the node's parents
					newNode.setParent2(q);
					
					p.addChildren(newNode);
					q.addChildren(newNode);
					
					}
					
					else if ( p1 != 0 && p2 == 0){ // the case where p1 is not null, but p2 is 
						newNode.setParent1(p); // set the node's parents
						newNode.setParent2(null);
						p.addChildren(newNode);
					}
				}
			}
				
		}
		**/
		
		
		/**
		 * iterate through set
		 * if set has a Node with either p1 or p2 as IDs
		 * set that Node's children List with newNode in it(add newNode)
		 */
		set.add(newNode);
		
		
	}
	

	@Override
	public Set<Node<Person>> getAll() {
		// TODO Auto-generated method stub
		return set;
	}                                              

	/**	getDescendants algorithm:
	 *  for a user input ID number
	 *  find Node corresponding to that ID
	 *  Using 2 Queues - 1 temporary, 1 permanent
	 *  Get every descendant of that Node's ID(child, grandchild, etc)
	 */
	@Override                                            
	public Set<Person> getDescendants(int idNum) { // implement as Breadth first search, shall read node's children, grandchildren
		// TODO Auto-generated method stub
		
		Queue<Node<Person>> perm = new LinkedList<Node<Person>>(); // permanent queue
		Queue<Node<Person>> temp = new LinkedList<Node<Person>>(); // temporary queue to process elements
		
		for(Node<Person> p : set){ // puts root1 and root2 in temp queue
			System.out.println("Processing " + p.getSelf().toString() + " id num is " + p.getId());
			if(p.getId() <= 2)
				temp.offer(p); 
			
			 if(temp.contains(p.getParent1()) && temp.contains(p.getParent2()))
				temp.offer(p); // after the roots, if there are any more, put them in temp
		}
		for(Node<Person> p : temp){
			perm.offer(temp.poll()); // removes roots from temp and put in perm queue
			
		}
				
		Set<Person> descendants = new HashSet<Person>();
		
		for(Node<Person> s : perm){ // now take all the Nodes in perm queue and place them in descendants Set 
			System.out.println(s.toString());
			descendants.add(s.getSelf()); // in descendants set as Persons
		}
		
		return descendants;
		}
		

	

	@Override
	/** Implement this:
	 *  List<Person> getParents(int id){
	 *  foreach node in set
	 * 		if node.ID = id
	 * 			then check for parent match,return number of people matched
	 * }
	 */
	public List<Person> getParents(int id) { // look for way to iterate through set in reverse
		// TODO Auto-generated method stub
		//Collections.reverse(ListForGetParentsFunction);
		List<Person> people = null;
		for (Node<Person> item : set){
			if(set.contains(item.getParent1()) && set.contains(item.getParent2())){
					people.add(item.getParent1().getSelf());
					people.add(item.getParent2().getSelf());
					break;
			}
			if(set.contains(item.getParent1()) && !set.contains(item.getParent2())){// the case where p1 is present but p2 is not
					people.add(item.getParent1().getSelf());
					break;
			}
			else if(set.contains(item.getParent2()) && !set.contains(item.getParent1())){//case where p2 is present and p1 is not
					people.add(item.getParent2().getSelf());
					break;
			}
			
		}
		System.out.println("The size of the getParents list is " + people.size());
		return people;
		
		
	}

	@Override
	public Person getPersonById(int id) {
		// TODO Auto-generated method stub
		for(Node<Person> p : set){
			if(p.getId() == id){
				//System.out.println(p.getId() + "name: " + p.getSelf().getFirstName());
				return p.getSelf();
			}
			
		}
		return null; // should not be called
	}

	@Override
	public List getPersonsByName(String lastName, String firstName) {
		// TODO Auto-generated method stub
		List<Person> sameName = new ArrayList<Person>();
		for(Node<Person> p : set){
			if(p.getSelf().getLastName() == lastName && p.getSelf().getFirstName() == firstName)
				sameName.add(p.getSelf());
		}
		return sameName;
	}

	@Override
	public int size() {
		int count = 0;
		for(Node<Person> p :set){
			count++;
		}
		return count; // the number of nodes in FamilyTree
	}
}