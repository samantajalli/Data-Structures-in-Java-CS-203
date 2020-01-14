package lab7;
import java.util.List;
import java.util.ArrayList;

public class Node<Person> implements Comparable<Node> {
	private Integer id;							
	private Person self; // the "current" node
	private Node<Person> parent1 = null; // 1st root node
	private Node<Person> parent2 = null; // 2nd root node
	private List<Node<Person>> children = new ArrayList<Node<Person>>(); // self's children
	
	public Node(Person a, Integer id){
		this.id = id; // the first node has id #1, etc
		self = a; 
	}
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	
	public Node<Person> getParent1(){
		return parent1;
	}
	
	public void setParent1(Node<Person> parent1){
		this.parent1 = parent1;
	}
	
	public Node<Person> getParent2(){
		return parent2;
	}
	
	public void setParent2(Node<Person> parent2){
		this.parent2 = parent2;
	}
	
	public List<Node<Person>> getChildren(){
		return children;
	}
	
	public void setChildren(List<Node<Person>> children){
		this.children = children;
	}
	
	public void addChildren(Node<Person> child){
		children.add(child);
		
	}
	// compareTo does not have to be used
	public int compareTo(Node other){ // compare by node id's. .
		return id.compareTo(other.id);
	}
	
	public String toString(){
		return "Node ID: " + id + " " + self.toString();
	}

	public Person getSelf() {
		// TODO Auto-generated method stub
		return self;
	}


	
}
