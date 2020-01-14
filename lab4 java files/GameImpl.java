package tictactoe;

public class GameImpl implements Game {

	private Player whoseTurn(Player play){
		if(play == Player.X)
			next = Player.O; 
		if(play == Player.O)
			next = Player.X;
		temp = play;
		play = next;
		current  = next;//swap the role of current player/next player
						//because we are only using current as arg
		return temp;
	}
	private MoveImpl m ;
	private SolverImpl solve;
	private Player current;
	private Player next;
	private Player temp;
	private Character[][] board = gameboard();
	
	public Character[][] gameboard(){
		int numRows = 3;
		int numCols = 3;
		Character[][] newboard = new Character[numRows][numCols];
		for(int i = 0; i < numRows; i++)
			for(int j = 0; j < numCols; j++)
				newboard[i][j] = ' ';
		return newboard;
	}
	
	GameImpl(Player p){ // Constructor for GameImpl accepting player/auto as arg
		current = p;
	}
	
	@Override
	public Player getTurn() {
		
		return whoseTurn(current);
	}

	//@Override
	public Move getAutoMove() {//auto makes move on board and gets pushed onto stack
	
		if(board[1][1] == ' '){
			if (getTurn() == Player.X)		
				board[1][1] = 'X';
			if(getTurn() == Player.O)
				board[1][1] = 'O';
			return solve.getMove(whoseTurn(current),board);
		}
		
		if(board[0][0] == ' '){
			if (getTurn() == Player.X)		
				board[0][0] = 'X';
			if(getTurn() == Player.O)
				board[0][0] = 'O';
			return solve.getMove(whoseTurn(current),board);
		}
		if(board[0][2] == ' '){
			if (getTurn() == Player.X)		
				board[0][2] = 'X';
			if(getTurn() == Player.O)
				board[0][2] = 'O';
			return solve.getMove(whoseTurn(current),board);
		}
		if(board[2][0] == ' '){
			if (getTurn() == Player.X)		
				board[2][0] = 'X';
			if(getTurn() == Player.O)
				board[2][0] = 'O';
			return solve.getMove(whoseTurn(current),board);
		}
		if(board[2][2] == ' '){
			if (getTurn() == Player.X)		
				board[2][2] = 'X';
			if(getTurn() == Player.O)
				board[2][2] = 'O';
			return solve.getMove(whoseTurn(current),board);
		}
		
		if(board[0][1] == ' '){
			if (getTurn() == Player.X)		
				board[0][1] = 'X';
			if(getTurn() == Player.O)
				board[0][1] = 'O';
			return solve.getMove(whoseTurn(current),board);
		}
		if(board[1][0] == ' '){
			if (getTurn() == Player.X)		
				board[1][0] = 'X';
			if(getTurn() == Player.O)
				board[1][0] = 'O';
			return solve.getMove(whoseTurn(current),board);
		}
		if(board[1][2] == ' '){
			if (getTurn() == Player.X)		
				board[1][2] = 'X';
			if(getTurn() == Player.O)
				board[1][2] = 'O';
			return solve.getMove(whoseTurn(current),board);
		}
		return null;
		
		
	}

	
	@Override
	public Move getPlayerMove(int row, int col) {
		
		Move mov = new MoveImpl(current, row, col);//push human player onto stack
		if (getTurn() == Player.X)		
			board[row][col] = 'X';
		if(getTurn() == Player.O)
			board[row][col] = 'O';
		return mov;
	}

	@Override
	public Character[][] getBoard() {
		return board;
	}

	@Override
	public boolean checkForVictory() {
		return false;
	}

	@Override
	public boolean isStalemated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return current;
	}

	@Override
	public void goBack() {
		
		m.Rewind();//undo auto move because they went just before user
		m.Rewind();//undo your last move so you can try again
	}

	@Override
	public Move redo() {
		// TODO Auto-generated method stub
		m.Rewind(); //first undo auto move
		return getAutoMove(); //now auto tries again
		
	}
	

}
