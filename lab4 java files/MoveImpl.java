package tictactoe;

import tictactoe.Game.Player;



public class MoveImpl implements Move{

	tictactoestack<Player> movestack = new tictactoestack<Player>();
	
	private int row;
	private int col;
	//private Player play;
	MoveImpl(Player p, int rowln, int colln){
		movestack.push(p);//push user move onto stack
		row = rowln;
		col = colln;
	}
	
	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return row;
	}

	@Override
	public int getCol() {
		// TODO Auto-generated method stub
		return col;
	}

	@Override
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return movestack.peek();
	}
	
	public void Rewind(){
		movestack.pop();
	}
	
	
	}
