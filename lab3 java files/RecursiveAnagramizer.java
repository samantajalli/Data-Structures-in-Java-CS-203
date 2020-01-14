
import java.util.*;
import java.io.*;

import javax.swing.*;
public class RecursiveAnagramizer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Anagramizer a = new Anagramizer();
		String input = JOptionPane.showInputDialog("Enter a string");
		a.anagramize(input);
		
		}

	
	 
}

