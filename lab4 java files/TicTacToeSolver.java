package tictactoe;

import tictactoe.Game.Player;



public interface TicTacToeSolver {
	public Move getMove(Player turn, Character[][] board);
}

