package tictactoe;

import tictactoe.Game.Player;

public interface Move {
	public int getRow();

	public int getCol();

	public Player getPlayer();

}
