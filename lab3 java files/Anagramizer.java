
import java.util.*;
import java.io.*;

public class Anagramizer {

	
	List<String> parselis = parse("C:\\Users\\Saman\\Desktop\\twl06.txt");
	
	
	
	public void anagramize(String inString){
		
		System.out.println("input string: " + inString.trim());
		List<String> l = filter(anagramizeRecursive(inString.toLowerCase()));
		System.out.println("Anagrams: " + l);
	}

	

	public List<String> anagramizeRecursive(String s){
		List<String> recurselis = new ArrayList<String>();	
		if (s.length() == 0){ //base case
			recurselis.add("");//when you get to end of string put an empty string in list to filter out later					
		    return recurselis;
		}
			char first = s.charAt(0);
			String rest = s.substring(1);
			for (String anagram : anagramizeRecursive(rest)){//return smaller string
			recurselis.addAll(insertAtAllPositions(first,anagram));
				
		} 
			return recurselis;
		
	}
	public static List<String> insertAtAllPositions(char ch, String s){
		List<String> out = new ArrayList<String>();//perform anagram creation by putting each char in each position
		for(int i = 0; i <= s.length();i++){
			String insert = s.substring(0,i) + ch + s.substring(i);
			out.add(insert);
		}
		return out;
	}
	
	public List<String> filter(List<String> lis){//nonrecursivefilter
		 List<String> newlis = new ArrayList<String>();
		for(String s : lis ){//foreach string in list lis
			s.split("");//after string split,
			newlis.add(s);//add all of old list to a new list
			recursefilter(newlis);
		}
		return newlis;
	}
	
	public String recursefilter(List<String> lis){
		for(String s : lis){ // for all strings in list
			for(String t : parselis) // if they are all in dictionary file list
				if(parselis.contains(lis))
					return s+=lis; // return string containing concatenation of list
				else
					return null;
		}
		return null; // this never happens. . .
	}
	
	
	
	
	List<String> parse(String filename){//reads the file and adds words
										//to list
		 List<String> list = new ArrayList<String>();
		 File inFile = new File(filename);
		 Scanner freader;
		 String line;
		 
		 try{
			 freader = new Scanner(inFile);
			 while(freader.hasNextLine())
			 {
				 line = freader.nextLine();
				 list.add(line.toLowerCase());
				
			 }
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 } 
		 return list;
	
	 }
}
