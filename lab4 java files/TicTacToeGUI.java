package tictactoe;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EmptyStackException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import tictactoe.Game.Player;



public class TicTacToeGUI {
	private JFrame frame;
	private JPanel startPanel;
	private JButton oStartButton;
	private JButton xStartButton;
	private JButton backButton;
	private JButton redoButton;
	private JPanel boardPanel;
	private JLabel[][] labels;
	private Game game;

	private void setUpGUI() {
		game = new GameImpl(Player.X);
		labels = new JLabel[3][3];
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		startPanel = new JPanel();
		boardPanel = new JPanel(new GridLayout(3, 3));
		setUpStartPanel();
		setUpLabels();

		frame.add(startPanel, BorderLayout.NORTH);
		frame.add(boardPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private void setUpLabels() {
		for (int row = 0; row < 3; row++)
			for (int col = 0; col < 3; col++) {
				JLabel l = new JLabel();
				labels[row][col] = l;
				setUpLabel(l, row, col);
				boardPanel.add(l);
			}
	}

	private void setUpLabel(final JLabel l, final int row, final int col) {
		l.setPreferredSize(new Dimension(85,85));
		setLabelBorder(l, row, col);
		l.setFont(new Font(null, 0, 40));
		l.setHorizontalAlignment(0);

		l.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (game.getBoard()[row][col] == ' '
						&& game.getPlayer() == game.getTurn()
						&& !game.checkForVictory()) {
					playerMove(l, row, col);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
	}

	private void setLabelBorder(JLabel l, int row, int col) {
		Border blackline = BorderFactory.createLineBorder(Color.black);
		if (row == 1) {
			if (col == 1)
				l.setBorder(BorderFactory
						.createMatteBorder(1, 1, 1, 1, Color.black));			else
				l.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0,
						Color.black));
		} else if (col == 1) {
			l.setBorder(BorderFactory
					.createMatteBorder(0, 1, 0, 1, Color.black));
		}
	}

	private void updateLabels() {
		Character[][] board = game.getBoard();

		for (int row = 0; row < 3; row++)
			for (int col = 0; col < 3; col++) {
				labels[row][col].setText(String.valueOf(board[row][col]));
			}
	}

	private void setUpStartPanel() {
		setUpOButton();
		setUpXButton();
		setUpBackButton();
		setUpRedoButton();
		startPanel.add(oStartButton);
		startPanel.add(xStartButton);
		startPanel.add(backButton);
		startPanel.add(redoButton);
	}
	
	private void setUpOButton() {
		oStartButton = new JButton("Play O");
		oStartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				game = new GameImpl(Player.O);
				updateLabels();
			}
		});
	}

	private void setUpXButton() {
		xStartButton = new JButton("Play X");
		xStartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game = new GameImpl(Player.X);
				updateLabels();
			}
		});

	}

	private void setUpBackButton(){
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					game.goBack();
				} catch (EmptyStackException e) {
					JOptionPane.showMessageDialog(null, "Can't go back");
				} catch (Exception e) {
					e.printStackTrace();
				}
				updateLabels();
			}
		});}
		
		private void setUpRedoButton(){
			redoButton = new JButton("Redo");
			redoButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						game.redo();
					} catch (EmptyStackException e) {
						JOptionPane.showMessageDialog(null, "Can't Redo");
					} catch (Exception e) {
						e.printStackTrace();
					}
					updateLabels();
				}
			});
		
	}

	public void play() {
		while (true) {
			if (game.getTurn() != game.getPlayer() && !game.checkForVictory()
					&& !game.isStalemated())
				getAutoMove();
			else
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}

	public void executeMove(Move m) {
		updateLabels();
		if (game.checkForVictory()) {
			char victorChar;
			if (game.getTurn() == Player.X)
				victorChar = 'O';
			else
				victorChar = 'X';
			JOptionPane.showMessageDialog(null, victorChar + " wins!");
		}
	}

	private void playerMove(JLabel l, int row, int col) {
		Character[][] board = game.getBoard();
		if (board[row][col] == ' ') {
			Move m = game.getPlayerMove(row, col);
			executeMove(m);
		}
	}

	private void getAutoMove() {
		Move m = game.getAutoMove();
		executeMove(m);
	}

	public static void main(String[] args) {
		TicTacToeGUI gui = new TicTacToeGUI();
		gui.setUpGUI();
		gui.play();
	}
}
