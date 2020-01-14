package lab7;

import java.util.Calendar;
import java.util.GregorianCalendar;



public class Person implements Comparable<Person>{

	private String firstName;
	private String lastName;
	private Calendar birth = new GregorianCalendar();
	private Calendar death = new GregorianCalendar();
	
	public Person(String lastName, String firstName, Calendar birth, Calendar death) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.birth = birth;
		this.death = death;
	}
	
	public void setFirstName(String firstName){//setter
		this.firstName = firstName;
	}
	
	public String getFirstName(){//getter
		return firstName;
	}
	
	public void setLastName(String lastName){ // setter
		this.lastName = lastName;
	}

	public String getLastName(){ // getter
		return lastName;
	}
	
	public boolean equals(Person other){ // checks if students are equal
		if (this.firstName == other.getFirstName() && this.lastName == other.getLastName())
		  return true;
		else
		  return false;
	}
	
	@Override
	public int compareTo(Person other){
		if (firstName == other.getFirstName() && lastName == other.getLastName())
		return 0; // this student and the other student have the same name
		
		else{ // the last names are not the same
			if(lastName != other.getLastName()) // if last names are equal then just sort first name
				return lastName.compareTo(other.getLastName()); // last names not in order, sort them
			else {// last names are same, so sort the first names
			return firstName.compareTo(other.getFirstName()); // last names are the same, first names are not
			}
			
	     }
		
}
	
	public String toString(){
		return getFirstName() + " " + getLastName() + " birth: " + birth.get(Calendar.YEAR)
				+ " " + birth.get(Calendar.MONTH) + " " + birth.get(Calendar.DATE)
				+ " death: " + death.get(Calendar.YEAR) + " " + death.get(Calendar.MONTH) 
				+ " " + death.get(Calendar.DATE) +  "\n";
	}
}