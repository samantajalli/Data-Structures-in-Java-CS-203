package tictactoe;
import java.util.*;
public class tictactoestack<T> {
		private ArrayList<T> stack = new ArrayList<T>();
		private int top = 0;
		
		public int size(){
			return top;
		}
		public T peek(){
			return stack.get(top);
		}
		public void push(T item){
			stack.add(top++,item);
		}
		
		public T pop(){
			return stack.remove(--top);
		}
		
		
}
