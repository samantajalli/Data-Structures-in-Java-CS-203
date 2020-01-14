package tictactoe;

import tictactoe.Game.Player;


public abstract class SolverImpl implements TicTacToeSolver {

	private Move checkForWinningMove(Player p, Character[][] board) {
		Character[][] newBoard;
		Character currChar = String.valueOf(p).charAt(0);

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				newBoard = copyBoard(board);
				if (newBoard[row][col] == ' ') {
					newBoard[row][col] = currChar;

				if (checkForVictory(newBoard))
					return new MoveImpl(p, row, col);}
			}
		}
		return null;

	}

	@Override
	public Move getMove(Player turn, Character[][] board) {
		Move m = checkForWinningMove(turn, board);
		if (m != null) return m;

		Player opponent = (turn == Player.X) ? Player.O: Player.X;
		m = checkForWinningMove(opponent, board);
		if (m != null) return new MoveImpl(turn, m.getRow(), m.getCol());
		
		// occupy center square
		if (board[1][1] == ' ')
			return new MoveImpl(turn, 1, 1);

		// occupy corner squares
		if (board[0][0] == ' ')
			return new MoveImpl(turn, 0, 0);
		if (board[0][2] == ' ')
			return new MoveImpl(turn, 0, 2);
		if (board[2][0] == ' ')
			return new MoveImpl(turn, 2, 0);
		if (board[2][2] == ' ')
			return new MoveImpl(turn, 2, 2);

		// occupy whatever is left
		if (board[0][1] == ' ')
			return new MoveImpl(turn, 0, 1);
		if (board[1][0] == ' ')
			return new MoveImpl(turn, 1, 0);
		if (board[1][2] == ' ')
			return new MoveImpl(turn, 1, 2);
		else
			return new MoveImpl(turn, 2, 1);
	}

	private Character[][] copyBoard(Character[][] board) {
		int numRows = board.length;
		int numCols = board[0].length;
		Character[][] newBoard = new Character[numRows][numCols];
		for (int row = 0; row < numRows; row++)
			for (int col = 0; col < numCols; col++)
				newBoard[row][col] = new Character(board[row][col]);
		return newBoard;
	}

	private boolean checkForRowVictory(Character[][] board) {
		char curr = ' ';
		for (int row = 0; row < 3; row++) {
			curr = board[row][0];
			if (curr != ' ' && curr == board[row][1] && curr == board[row][2])
				return true;
		}
		return false;
	}

	private boolean checkForColVictory(Character[][] board) {
		char curr = ' ';
		for (int col = 0; col < 3; col++) {
			curr = board[0][col];
			if (curr != ' ' && curr == board[1][col] && curr == board[2][col])
				return true;
		}
		return false;
	}

	private boolean checkForDiagonalVictory(Character[][] board) {
		char curr = board[0][0];
		if (curr != ' ') {
			if (board[1][1] == curr && board[2][2] == curr)
				return true;
		}
		curr = board[0][2];
		if (curr != ' ') {
			if (board[1][1] == curr & board[2][0] == curr)
				return true;
		}
		return false;
	}

	private boolean checkForVictory(Character[][] board) {
		if (checkForRowVictory(board) || checkForColVictory(board)
				|| checkForDiagonalVictory(board))
			return true;
		return false;
	}
}
