package tictactoe;

public interface Game {

	public enum Player {X, O}
	
	public Player getTurn();

	public Move getAutoMove();

	public Move getPlayerMove(int row, int col);

	public Character[][] getBoard();

	public boolean checkForVictory();

	public boolean isStalemated();

	public Player getPlayer();

	public void goBack();
	
	public Move redo();
}
