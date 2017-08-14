package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable {

	private static final long serialVersionUID = -8259035677570729774L;

	private Piece[][] board = new Piece[10][10];

	Player p1;
	Player p2;

	public Board(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public Boolean add(int X, int Y, Piece piece) {
		if (board[X][Y] == null) {
			board[X][Y] = piece;
			piece.setX(X);
			piece.setY(Y);
			return true;
		}
		System.out.println("Creation Spot is taken");
		return false;
	}

	public ArrayList<String> getReactions() {
		ArrayList<String> reactions = new ArrayList<String>();
		for (int X = 0; X <= 9; X++) {
			for (int Y = 0; Y <= 9; Y++) {
				if (board[X][Y] != null) {
					if (X != 0) {
						if (board[X - 1][Y] != null) {
							if (board[X][Y].getLeft().equals("-")) {
								reactions.add(board[X - 1][Y].getName() + " " + board[X][Y].getName());
							}
						}
					}
					if (X != 9) {
						if (board[X + 1][Y] != null) {
							if (board[X][Y].getRight().equals("-")) {
								reactions.add(board[X + 1][Y].getName() + " " + board[X][Y].getName());

							}
						}
					}
					if (Y != 0) {
						if (board[X][Y - 1] != null) {
							if (board[X][Y].getUp().equals("|")) {
								reactions.add(board[X][Y - 1].getName() + " " + board[X][Y].getName());

							}
						}
					}
					if (Y != 9) {
						if (board[X][Y + 1] != null) {
							if (board[X][Y].getDown().equals("|")) {
								reactions.add(board[X][Y + 1].getName() + " " + board[X][Y].getName());

							}
						}
					}

					if (X - 1 == 1 && Y == 1) {
						if (board[X][Y].getLeft().equals(("-"))) {
							reactions.add("face " + board[X][Y].getName());
						}
					}
					if (X == 1 && Y - 1 == 1) {
						if (board[X][Y].getUp().equals(("|"))) {
							reactions.add("face " + board[X][Y].getName());
						}
					}
					if (X + 1 == 8 && Y == 8) {
						if (board[X][Y].getRight().equals(("-"))) {
							reactions.add("face " + board[X][Y].getName());
						}
					}
					if (X == 8 && Y + 1 == 8) {
						if (board[X][Y].getDown().equals(("|"))) {
							reactions.add("face " + board[X][Y].getName());
						}
					}
				}
			}
		}

		return reactions;
	}

	public void reaction(String name1, String name2) {
		Piece piece = null;
		String dir1 = "";
		String dir2 = "";
		String move1 = "";
		String move2 = "";
		for (int x = 0; x <= 9; x++) {
			for (int y = 0; y <= 9; y++) {
				if (board[x][y] != null) {
					if (board[x][y].getName().equals(name2)) {
						piece = board[x][y];
					}
				}
			}
		}
		if (piece == null) {
			return;
		}
		int X = piece.getX();
		int Y = piece.getY();
		int X2 = 0;
		int Y2 = 0;
		if (X != 0) {
			if (X != 0) {
				if (board[X - 1][Y] != null) {
					if (board[X - 1][Y].getName().equals(name1)) {
						X2 = X - 1;
						Y2 = Y;
						dir1 = piece.getLeft();
						dir2 = board[X - 1][Y].getRight();
						move1 = "left";
						move2 = "right";
					}
				}
			}
		}
		if (X != 9) {
			if (board[X + 1][Y] != null) {
				if (board[X + 1][Y].getName().equals(name1)) {
					X2 = X + 1;
					Y2 = Y;
					dir1 = piece.getRight();
					dir2 = board[X + 1][Y].getLeft();
					move1 = "right";
					move2 = "left";
				}
			}
		}
		if (Y != 0) {
			if (board[X][Y - 1] != null) {
				if (board[X][Y - 1].getName().equals(name1)) {
					X2 = X;
					Y2 = Y - 1;
					dir1 = piece.getUp();
					dir2 = board[X][Y - 1].getDown();
					move1 = "up";
					move2 = "down";
				}
			}
		}
		if (Y != 9) {
			if (board[X][Y + 1] != null) {
				if (board[X][Y + 1].getName().equals(name1)) {
					X2 = X;
					Y2 = Y + 1;
					dir1 = piece.getDown();
					dir2 = board[X][Y + 1].getUp();
					move1 = "down";
					move2 = "up";
				}
			}
		}

		if ((dir1.equals("|") || dir1.equals("-")) && (dir2.equals("|") || dir2.equals("-"))) {
			remove(board[X][Y]);
			remove(board[X2][Y2]);
			board[X][Y] = null;
			board[X2][Y2] = null;
		} else if ((dir1.equals("|") || dir1.equals("-")) && dir2.equals("#")) {
			System.out.println("Are u trying");
			move(move2, board[X][Y]);
		} else if (dir1.equals("#") && (dir2.equals("|") || dir2.equals("-"))) {
			System.out.println("Are u trying");
			move(move1, board[X2][Y2]);
		} else if ((dir1.equals("|") || dir1.equals("-")) && dir2.equals(" ")) {
			remove(board[X2][Y2]);
			board[X2][Y2] = null;
		} else if (dir1.equals(" ") && (dir2.equals("|") || dir2.equals("-"))) {
			remove(board[X][Y]);
			board[X][Y] = null;
		}

	}

	public Boolean move(String dir, Piece piece) {
		if (piece == null) {
			System.out.println("Not a valid piece");
			return false;
		}
		Piece temp;
		int X = piece.getX();
		int Y = piece.getY();
		int nX = 0;
		int nY = 0;
		if (dir.equals("up")) {
			nY = Y - 1;
			nX = X;
		} else if (dir.equals("right")) {
			nX = X + 1;
			nY = Y;
		} else if (dir.equals("down")) {
			nY = Y + 1;
			nX = X;
		} else if (dir.equals("left")) {
			nX = X - 1;
			nY = Y;
		} else {
			System.out.println("Not a valid direction");
			return false;
		}
		if ((nY == 1 && nX == 1) || (nY == 8 && nX == 8)) {
			board[X][Y] = null;
			remove(piece);
			return true;
		} // move into a face
		if (nY == -1 || nX == -1 || nY == 10 || nX == 10) {
			board[X][Y] = null;
			remove(piece);
			return true;
		} // move off edge
		if (nY == 0 && nX == 1) {
			board[X][Y] = null;
			remove(piece);
			return true;
		} // move into top left corner
		if (nY == 1 && nX == 0) {
			board[X][Y] = null;
			remove(piece);
			return true;
		} // move into top left corner
		if (nY == 8 && nX == 9) {
			board[X][Y] = null;
			remove(piece);
			return true;
		} // move into bottom right corner
		if (nY == 9 && nX == 8) {
			board[X][Y] = null;
			remove(piece);
			return true;
		} // move into bottom right corner
		if (board[nX][nY] != null) {
			if (move(dir, board[nX][nY])) {
				temp = board[X][Y];
				board[X][Y] = null;
				board[nX][nY] = piece;
				piece.setX(nX);
				piece.setY(nY);
				return true;
			}
		} else {
			temp = board[X][Y];
			board[X][Y] = null;
			board[nX][nY] = piece;
			piece.setX(nX);
			piece.setY(nY);
			return true;
		}
		System.out.println("Something went wrong");
		return false;

	}

	public Board clone() {
		Board clone = null;

		try {
			FileOutputStream fout = new FileOutputStream("clone.dat");
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(this);
			out.close();

			FileInputStream fin = new FileInputStream("clone.dat");
			ObjectInputStream in = new ObjectInputStream(fin);
			clone = (Board) in.readObject();
			in.close();
		} catch (IOException | ClassNotFoundException e) {

			System.out.println(e);
		}
		return clone;
	}

	public void remove(Piece p) {
		p1.board.remove(p);
		p2.board.remove(p);
	}

	public String toString() {
		return ("-------------------------------------------------------------\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n" + "|  X  |  X  |  "
				+ ((board[2][0] == null) ? " " : board[2][0].getUp()) + "  |  "
				+ ((board[3][0] == null) ? " " : board[3][0].getUp()) + "  |  "
				+ ((board[4][0] == null) ? " " : board[4][0].getUp()) + "  |  "
				+ ((board[5][0] == null) ? " " : board[5][0].getUp()) + "  |  "
				+ ((board[6][0] == null) ? " " : board[6][0].getUp()) + "  |  "
				+ ((board[7][0] == null) ? " " : board[7][0].getUp()) + "  |  "
				+ ((board[8][0] == null) ? " " : board[8][0].getUp()) + "  |  "
				+ ((board[9][0] == null) ? " " : board[9][0].getUp()) + "  |\n" + "| XXX | XXX | "
				+ ((board[2][0] == null) ? " " : board[2][0].getLeft())
				+ ((board[2][0] == null) ? " " : board[2][0].getName())
				+ ((board[2][0] == null) ? " " : board[2][0].getRight()) + " | "
				+ ((board[3][0] == null) ? " " : board[3][0].getLeft())
				+ ((board[3][0] == null) ? " " : board[3][0].getName())
				+ ((board[3][0] == null) ? " " : board[3][0].getRight()) + " | "
				+ ((board[4][0] == null) ? " " : board[4][0].getLeft())
				+ ((board[4][0] == null) ? " " : board[4][0].getName())
				+ ((board[4][0] == null) ? " " : board[4][0].getRight()) + " | "
				+ ((board[5][0] == null) ? " " : board[5][0].getLeft())
				+ ((board[5][0] == null) ? " " : board[5][0].getName())
				+ ((board[5][0] == null) ? " " : board[5][0].getRight()) + " | "
				+ ((board[6][0] == null) ? " " : board[6][0].getLeft())
				+ ((board[6][0] == null) ? " " : board[6][0].getName())
				+ ((board[6][0] == null) ? " " : board[6][0].getRight()) + " | "
				+ ((board[7][0] == null) ? " " : board[7][0].getLeft())
				+ ((board[7][0] == null) ? " " : board[7][0].getName())
				+ ((board[7][0] == null) ? " " : board[7][0].getRight()) + " | "
				+ ((board[8][0] == null) ? " " : board[8][0].getLeft())
				+ ((board[8][0] == null) ? " " : board[8][0].getName())
				+ ((board[8][0] == null) ? " " : board[8][0].getRight()) + " | "
				+ ((board[9][0] == null) ? " " : board[9][0].getLeft())
				+ ((board[9][0] == null) ? " " : board[9][0].getName())
				+ ((board[9][0] == null) ? " " : board[9][0].getRight()) + " |\n" + "|  X  |  X  |  "
				+ ((board[2][0] == null) ? " " : board[2][0].getDown()) + "  |  "
				+ ((board[3][0] == null) ? " " : board[3][0].getDown()) + "  |  "
				+ ((board[4][0] == null) ? " " : board[4][0].getDown()) + "  |  "
				+ ((board[5][0] == null) ? " " : board[5][0].getDown()) + "  |  "
				+ ((board[6][0] == null) ? " " : board[6][0].getDown()) + "  |  "
				+ ((board[7][0] == null) ? " " : board[7][0].getDown()) + "  |  "
				+ ((board[8][0] == null) ? " " : board[8][0].getDown()) + "  |  "
				+ ((board[9][0] == null) ? " " : board[9][0].getDown()) + "  |\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n"
				+ "-------------------------------------------------------------\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n" + "|  X  |     |  "
				+ ((board[2][1] == null) ? " " : board[2][1].getUp()) + "  |  "
				+ ((board[3][1] == null) ? " " : board[3][1].getUp()) + "  |  "
				+ ((board[4][1] == null) ? " " : board[4][1].getUp()) + "  |  "
				+ ((board[5][1] == null) ? " " : board[5][1].getUp()) + "  |  "
				+ ((board[6][1] == null) ? " " : board[6][1].getUp()) + "  |  "
				+ ((board[7][1] == null) ? " " : board[7][1].getUp()) + "  |  "
				+ ((board[8][1] == null) ? " " : board[8][1].getUp()) + "  |  "
				+ ((board[9][1] == null) ? " " : board[9][1].getUp()) + "  |\n" + "| XXX |  1  | "
				+ ((board[2][1] == null) ? " " : board[2][1].getLeft())
				+ ((board[2][1] == null) ? " " : board[2][1].getName())
				+ ((board[2][1] == null) ? " " : board[2][1].getRight()) + " | "
				+ ((board[3][1] == null) ? " " : board[3][1].getLeft())
				+ ((board[3][1] == null) ? " " : board[3][1].getName())
				+ ((board[3][1] == null) ? " " : board[3][1].getRight()) + " | "
				+ ((board[4][1] == null) ? " " : board[4][1].getLeft())
				+ ((board[4][1] == null) ? " " : board[4][1].getName())
				+ ((board[4][1] == null) ? " " : board[4][1].getRight()) + " | "
				+ ((board[5][1] == null) ? " " : board[5][1].getLeft())
				+ ((board[5][1] == null) ? " " : board[5][1].getName())
				+ ((board[5][1] == null) ? " " : board[5][1].getRight()) + " | "
				+ ((board[6][1] == null) ? " " : board[6][1].getLeft())
				+ ((board[6][1] == null) ? " " : board[6][1].getName())
				+ ((board[6][0] == null) ? " " : board[6][1].getRight()) + " | "
				+ ((board[7][1] == null) ? " " : board[7][1].getLeft())
				+ ((board[7][1] == null) ? " " : board[7][1].getName())
				+ ((board[7][1] == null) ? " " : board[7][1].getRight()) + " | "
				+ ((board[8][1] == null) ? " " : board[8][1].getLeft())
				+ ((board[8][1] == null) ? " " : board[8][1].getName())
				+ ((board[8][1] == null) ? " " : board[8][1].getRight()) + " | "
				+ ((board[9][1] == null) ? " " : board[9][1].getLeft())
				+ ((board[9][1] == null) ? " " : board[9][1].getName())
				+ ((board[9][1] == null) ? " " : board[9][1].getRight()) + " |\n" + "|  X  |     |  "
				+ ((board[2][1] == null) ? " " : board[2][1].getDown()) + "  |  "
				+ ((board[3][1] == null) ? " " : board[3][1].getDown()) + "  |  "
				+ ((board[4][1] == null) ? " " : board[4][1].getDown()) + "  |  "
				+ ((board[5][1] == null) ? " " : board[5][1].getDown()) + "  |  "
				+ ((board[6][1] == null) ? " " : board[6][1].getDown()) + "  |  "
				+ ((board[7][1] == null) ? " " : board[7][1].getDown()) + "  |  "
				+ ((board[8][1] == null) ? " " : board[8][1].getDown()) + "  |  "
				+ ((board[9][1] == null) ? " " : board[9][1].getDown()) + "  |\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n"
				+ "-------------------------------------------------------------\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n" + "|  "
				+ ((board[0][2] == null) ? " " : board[0][2].getUp()) + "  |  "
				+ ((board[1][2] == null) ? " " : board[1][2].getUp()) + "  |  "
				+ ((board[2][2] == null) ? " " : board[2][2].getUp()) + "  |  "
				+ ((board[3][2] == null) ? " " : board[3][2].getUp()) + "  |  "
				+ ((board[4][2] == null) ? " " : board[4][2].getUp()) + "  |  "
				+ ((board[5][2] == null) ? " " : board[5][2].getUp()) + "  |  "
				+ ((board[6][2] == null) ? " " : board[6][2].getUp()) + "  |  "
				+ ((board[7][2] == null) ? " " : board[7][2].getUp()) + "  |  "
				+ ((board[8][2] == null) ? " " : board[8][2].getUp()) + "  |  "
				+ ((board[9][2] == null) ? " " : board[9][2].getUp()) + "  |\n" + "| "
				+ ((board[0][2] == null) ? " " : board[0][2].getLeft())
				+ ((board[0][2] == null) ? " " : board[0][2].getName())
				+ ((board[0][2] == null) ? " " : board[0][2].getRight()) + " | "
				+ ((board[1][2] == null) ? " " : board[1][2].getLeft())
				+ ((board[1][2] == null) ? " " : board[1][2].getName())
				+ ((board[1][2] == null) ? " " : board[1][2].getRight()) + " | "
				+ ((board[2][2] == null) ? " " : board[2][2].getLeft())
				+ ((board[2][2] == null) ? " " : board[2][2].getName())
				+ ((board[2][2] == null) ? " " : board[2][2].getRight()) + " | "
				+ ((board[3][2] == null) ? " " : board[3][2].getLeft())
				+ ((board[3][2] == null) ? " " : board[3][2].getName())
				+ ((board[3][2] == null) ? " " : board[3][2].getRight()) + " | "
				+ ((board[4][2] == null) ? " " : board[4][2].getLeft())
				+ ((board[4][2] == null) ? " " : board[4][2].getName())
				+ ((board[4][2] == null) ? " " : board[4][2].getRight()) + " | "
				+ ((board[5][2] == null) ? " " : board[5][2].getLeft())
				+ ((board[5][2] == null) ? " " : board[5][2].getName())
				+ ((board[5][2] == null) ? " " : board[5][2].getRight()) + " | "
				+ ((board[6][2] == null) ? " " : board[6][2].getLeft())
				+ ((board[6][2] == null) ? " " : board[6][2].getName())
				+ ((board[6][2] == null) ? " " : board[6][2].getRight()) + " | "
				+ ((board[7][2] == null) ? " " : board[7][2].getLeft())
				+ ((board[7][2] == null) ? " " : board[7][2].getName())
				+ ((board[7][2] == null) ? " " : board[7][2].getRight()) + " | "
				+ ((board[8][2] == null) ? " " : board[8][2].getLeft())
				+ ((board[8][2] == null) ? " " : board[8][2].getName())
				+ ((board[8][2] == null) ? " " : board[8][2].getRight()) + " | "
				+ ((board[9][2] == null) ? " " : board[9][2].getLeft())
				+ ((board[9][2] == null) ? " " : board[9][2].getName())
				+ ((board[9][2] == null) ? " " : board[9][2].getRight()) + " |\n" + "|  "
				+ ((board[0][2] == null) ? " " : board[0][2].getDown()) + "  |  "
				+ ((board[1][2] == null) ? " " : board[1][2].getDown()) + "  |  "
				+ ((board[2][2] == null) ? " " : board[2][2].getDown()) + "  |  "
				+ ((board[3][2] == null) ? " " : board[3][2].getDown()) + "  |  "
				+ ((board[4][2] == null) ? " " : board[4][2].getDown()) + "  |  "
				+ ((board[5][2] == null) ? " " : board[5][2].getDown()) + "  |  "
				+ ((board[6][2] == null) ? " " : board[6][2].getDown()) + "  |  "
				+ ((board[7][2] == null) ? " " : board[7][2].getDown()) + "  |  "
				+ ((board[8][2] == null) ? " " : board[8][2].getDown()) + "  |  "
				+ ((board[9][2] == null) ? " " : board[9][2].getDown()) + "  |\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n"
				+ "-------------------------------------------------------------\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n" + "|  "
				+ ((board[0][3] == null) ? " " : board[0][3].getUp()) + "  |  "
				+ ((board[1][3] == null) ? " " : board[1][3].getUp()) + "  |  "
				+ ((board[2][3] == null) ? " " : board[2][3].getUp()) + "  |  "
				+ ((board[3][3] == null) ? " " : board[3][3].getUp()) + "  |  "
				+ ((board[4][3] == null) ? " " : board[4][3].getUp()) + "  |  "
				+ ((board[5][3] == null) ? " " : board[5][3].getUp()) + "  |  "
				+ ((board[6][3] == null) ? " " : board[6][3].getUp()) + "  |  "
				+ ((board[7][3] == null) ? " " : board[7][3].getUp()) + "  |  "
				+ ((board[8][3] == null) ? " " : board[8][3].getUp()) + "  |  "
				+ ((board[9][3] == null) ? " " : board[9][3].getUp()) + "  |\n" + "| "
				+ ((board[0][3] == null) ? " " : board[0][3].getLeft())
				+ ((board[0][3] == null) ? " " : board[0][3].getName())
				+ ((board[0][3] == null) ? " " : board[0][3].getRight()) + " | "
				+ ((board[1][3] == null) ? " " : board[1][3].getLeft())
				+ ((board[1][3] == null) ? " " : board[1][3].getName())
				+ ((board[1][3] == null) ? " " : board[1][3].getRight()) + " | "
				+ ((board[2][3] == null) ? " " : board[2][3].getLeft())
				+ ((board[2][3] == null) ? " " : board[2][3].getName())
				+ ((board[2][3] == null) ? " " : board[2][3].getRight()) + " | "
				+ ((board[3][3] == null) ? " " : board[3][3].getLeft())
				+ ((board[3][3] == null) ? " " : board[3][3].getName())
				+ ((board[3][3] == null) ? " " : board[3][3].getRight()) + " | "
				+ ((board[4][3] == null) ? " " : board[4][3].getLeft())
				+ ((board[4][3] == null) ? " " : board[4][3].getName())
				+ ((board[4][3] == null) ? " " : board[4][3].getRight()) + " | "
				+ ((board[5][3] == null) ? " " : board[5][3].getLeft())
				+ ((board[5][3] == null) ? " " : board[5][3].getName())
				+ ((board[5][3] == null) ? " " : board[5][3].getRight()) + " | "
				+ ((board[6][3] == null) ? " " : board[6][3].getLeft())
				+ ((board[6][3] == null) ? " " : board[6][3].getName())
				+ ((board[6][3] == null) ? " " : board[6][3].getRight()) + " | "
				+ ((board[7][3] == null) ? " " : board[7][3].getLeft())
				+ ((board[7][3] == null) ? " " : board[7][3].getName())
				+ ((board[7][3] == null) ? " " : board[7][3].getRight()) + " | "
				+ ((board[8][3] == null) ? " " : board[8][3].getLeft())
				+ ((board[8][3] == null) ? " " : board[8][3].getName())
				+ ((board[8][3] == null) ? " " : board[8][3].getRight()) + " | "
				+ ((board[9][3] == null) ? " " : board[9][3].getLeft())
				+ ((board[9][3] == null) ? " " : board[9][3].getName())
				+ ((board[9][3] == null) ? " " : board[9][3].getRight()) + " |\n" + "|  "
				+ ((board[0][3] == null) ? " " : board[0][3].getDown()) + "  |  "
				+ ((board[1][3] == null) ? " " : board[1][3].getDown()) + "  |  "
				+ ((board[2][3] == null) ? " " : board[2][3].getDown()) + "  |  "
				+ ((board[3][3] == null) ? " " : board[3][3].getDown()) + "  |  "
				+ ((board[4][3] == null) ? " " : board[4][3].getDown()) + "  |  "
				+ ((board[5][3] == null) ? " " : board[5][3].getDown()) + "  |  "
				+ ((board[6][3] == null) ? " " : board[6][3].getDown()) + "  |  "
				+ ((board[7][3] == null) ? " " : board[7][3].getDown()) + "  |  "
				+ ((board[8][3] == null) ? " " : board[8][3].getDown()) + "  |  "
				+ ((board[9][3] == null) ? " " : board[9][3].getDown()) + "  |\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n"
				+ "-------------------------------------------------------------\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n" + "|  "
				+ ((board[0][4] == null) ? " " : board[0][4].getUp()) + "  |  "
				+ ((board[1][4] == null) ? " " : board[1][4].getUp()) + "  |  "
				+ ((board[2][4] == null) ? " " : board[2][4].getUp()) + "  |  "
				+ ((board[3][4] == null) ? " " : board[3][4].getUp()) + "  |  "
				+ ((board[4][4] == null) ? " " : board[4][4].getUp()) + "  |  "
				+ ((board[5][4] == null) ? " " : board[5][4].getUp()) + "  |  "
				+ ((board[6][4] == null) ? " " : board[6][4].getUp()) + "  |  "
				+ ((board[7][4] == null) ? " " : board[7][4].getUp()) + "  |  "
				+ ((board[8][4] == null) ? " " : board[8][4].getUp()) + "  |  "
				+ ((board[9][4] == null) ? " " : board[9][4].getUp()) + "  |\n" + "| "
				+ ((board[0][4] == null) ? " " : board[0][4].getLeft())
				+ ((board[0][4] == null) ? " " : board[0][4].getName())
				+ ((board[0][4] == null) ? " " : board[0][4].getRight()) + " | "
				+ ((board[1][4] == null) ? " " : board[1][4].getLeft())
				+ ((board[1][4] == null) ? " " : board[1][4].getName())
				+ ((board[1][4] == null) ? " " : board[1][4].getRight()) + " | "
				+ ((board[2][4] == null) ? " " : board[2][4].getLeft())
				+ ((board[2][4] == null) ? " " : board[2][4].getName())
				+ ((board[2][4] == null) ? " " : board[2][4].getRight()) + " | "
				+ ((board[3][4] == null) ? " " : board[3][4].getLeft())
				+ ((board[3][4] == null) ? " " : board[3][4].getName())
				+ ((board[3][4] == null) ? " " : board[3][4].getRight()) + " | "
				+ ((board[4][4] == null) ? " " : board[4][4].getLeft())
				+ ((board[4][4] == null) ? " " : board[4][4].getName())
				+ ((board[4][4] == null) ? " " : board[4][4].getRight()) + " | "
				+ ((board[5][4] == null) ? " " : board[5][4].getLeft())
				+ ((board[5][4] == null) ? " " : board[5][4].getName())
				+ ((board[5][4] == null) ? " " : board[5][4].getRight()) + " | "
				+ ((board[6][4] == null) ? " " : board[6][4].getLeft())
				+ ((board[6][4] == null) ? " " : board[6][4].getName())
				+ ((board[6][4] == null) ? " " : board[6][4].getRight()) + " | "
				+ ((board[7][4] == null) ? " " : board[7][4].getLeft())
				+ ((board[7][4] == null) ? " " : board[7][4].getName())
				+ ((board[7][4] == null) ? " " : board[7][4].getRight()) + " | "
				+ ((board[8][4] == null) ? " " : board[8][4].getLeft())
				+ ((board[8][4] == null) ? " " : board[8][4].getName())
				+ ((board[8][4] == null) ? " " : board[8][4].getRight()) + " | "
				+ ((board[9][4] == null) ? " " : board[9][4].getLeft())
				+ ((board[9][4] == null) ? " " : board[9][4].getName())
				+ ((board[9][4] == null) ? " " : board[9][4].getRight()) + " |\n" + "|  "
				+ ((board[0][4] == null) ? " " : board[0][4].getDown()) + "  |  "
				+ ((board[1][4] == null) ? " " : board[1][4].getDown()) + "  |  "
				+ ((board[2][4] == null) ? " " : board[2][4].getDown()) + "  |  "
				+ ((board[3][4] == null) ? " " : board[3][4].getDown()) + "  |  "
				+ ((board[4][4] == null) ? " " : board[4][4].getDown()) + "  |  "
				+ ((board[5][4] == null) ? " " : board[5][4].getDown()) + "  |  "
				+ ((board[6][4] == null) ? " " : board[6][4].getDown()) + "  |  "
				+ ((board[7][4] == null) ? " " : board[7][4].getDown()) + "  |  "
				+ ((board[8][4] == null) ? " " : board[8][4].getDown()) + "  |  "
				+ ((board[9][4] == null) ? " " : board[9][4].getDown()) + "  |\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n"
				+ "-------------------------------------------------------------\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n" + "|  "
				+ ((board[0][5] == null) ? " " : board[0][5].getUp()) + "  |  "
				+ ((board[1][5] == null) ? " " : board[1][5].getUp()) + "  |  "
				+ ((board[2][5] == null) ? " " : board[2][5].getUp()) + "  |  "
				+ ((board[3][5] == null) ? " " : board[3][5].getUp()) + "  |  "
				+ ((board[4][5] == null) ? " " : board[4][5].getUp()) + "  |  "
				+ ((board[5][5] == null) ? " " : board[5][5].getUp()) + "  |  "
				+ ((board[6][5] == null) ? " " : board[6][5].getUp()) + "  |  "
				+ ((board[7][5] == null) ? " " : board[7][5].getUp()) + "  |  "
				+ ((board[8][5] == null) ? " " : board[8][5].getUp()) + "  |  "
				+ ((board[9][5] == null) ? " " : board[9][5].getUp()) + "  |\n" + "| "
				+ ((board[0][5] == null) ? " " : board[0][5].getLeft())
				+ ((board[0][5] == null) ? " " : board[0][5].getName())
				+ ((board[0][5] == null) ? " " : board[0][5].getRight()) + " | "
				+ ((board[1][5] == null) ? " " : board[1][5].getLeft())
				+ ((board[1][5] == null) ? " " : board[1][5].getName())
				+ ((board[1][5] == null) ? " " : board[1][5].getRight()) + " | "
				+ ((board[2][5] == null) ? " " : board[2][5].getLeft())
				+ ((board[2][5] == null) ? " " : board[2][5].getName())
				+ ((board[2][5] == null) ? " " : board[2][5].getRight()) + " | "
				+ ((board[3][5] == null) ? " " : board[3][5].getLeft())
				+ ((board[3][5] == null) ? " " : board[3][5].getName())
				+ ((board[3][5] == null) ? " " : board[3][5].getRight()) + " | "
				+ ((board[4][5] == null) ? " " : board[4][5].getLeft())
				+ ((board[4][5] == null) ? " " : board[4][5].getName())
				+ ((board[4][5] == null) ? " " : board[4][5].getRight()) + " | "
				+ ((board[5][5] == null) ? " " : board[5][5].getLeft())
				+ ((board[5][5] == null) ? " " : board[5][5].getName())
				+ ((board[5][5] == null) ? " " : board[5][5].getRight()) + " | "
				+ ((board[6][5] == null) ? " " : board[6][5].getLeft())
				+ ((board[6][5] == null) ? " " : board[6][5].getName())
				+ ((board[6][5] == null) ? " " : board[6][5].getRight()) + " | "
				+ ((board[7][5] == null) ? " " : board[7][5].getLeft())
				+ ((board[7][5] == null) ? " " : board[7][5].getName())
				+ ((board[7][5] == null) ? " " : board[7][5].getRight()) + " | "
				+ ((board[8][5] == null) ? " " : board[8][5].getLeft())
				+ ((board[8][5] == null) ? " " : board[8][5].getName())
				+ ((board[8][5] == null) ? " " : board[8][5].getRight()) + " | "
				+ ((board[9][5] == null) ? " " : board[9][5].getLeft())
				+ ((board[9][5] == null) ? " " : board[9][5].getName())
				+ ((board[9][5] == null) ? " " : board[9][5].getRight()) + " |\n" + "|  "
				+ ((board[0][5] == null) ? " " : board[0][5].getDown()) + "  |  "
				+ ((board[1][5] == null) ? " " : board[1][5].getDown()) + "  |  "
				+ ((board[2][5] == null) ? " " : board[2][5].getDown()) + "  |  "
				+ ((board[3][5] == null) ? " " : board[3][5].getDown()) + "  |  "
				+ ((board[4][5] == null) ? " " : board[4][5].getDown()) + "  |  "
				+ ((board[5][5] == null) ? " " : board[5][5].getDown()) + "  |  "
				+ ((board[6][5] == null) ? " " : board[6][5].getDown()) + "  |  "
				+ ((board[7][5] == null) ? " " : board[7][5].getDown()) + "  |  "
				+ ((board[8][5] == null) ? " " : board[8][5].getDown()) + "  |  "
				+ ((board[9][5] == null) ? " " : board[9][5].getDown()) + "  |\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n"
				+ "-------------------------------------------------------------\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n" + "|  "
				+ ((board[0][6] == null) ? " " : board[0][6].getUp()) + "  |  "
				+ ((board[1][6] == null) ? " " : board[1][6].getUp()) + "  |  "
				+ ((board[2][6] == null) ? " " : board[2][6].getUp()) + "  |  "
				+ ((board[3][6] == null) ? " " : board[3][6].getUp()) + "  |  "
				+ ((board[4][6] == null) ? " " : board[4][6].getUp()) + "  |  "
				+ ((board[5][6] == null) ? " " : board[5][6].getUp()) + "  |  "
				+ ((board[6][6] == null) ? " " : board[6][6].getUp()) + "  |  "
				+ ((board[7][6] == null) ? " " : board[7][6].getUp()) + "  |  "
				+ ((board[8][6] == null) ? " " : board[8][6].getUp()) + "  |  "
				+ ((board[9][6] == null) ? " " : board[9][6].getUp()) + "  |\n" + "| "
				+ ((board[0][6] == null) ? " " : board[0][6].getLeft())
				+ ((board[0][6] == null) ? " " : board[0][6].getName())
				+ ((board[0][6] == null) ? " " : board[0][6].getRight()) + " | "
				+ ((board[1][6] == null) ? " " : board[1][6].getLeft())
				+ ((board[1][6] == null) ? " " : board[1][6].getName())
				+ ((board[1][6] == null) ? " " : board[1][6].getRight()) + " | "
				+ ((board[2][6] == null) ? " " : board[2][6].getLeft())
				+ ((board[2][6] == null) ? " " : board[2][6].getName())
				+ ((board[2][6] == null) ? " " : board[2][6].getRight()) + " | "
				+ ((board[3][6] == null) ? " " : board[3][6].getLeft())
				+ ((board[3][6] == null) ? " " : board[3][6].getName())
				+ ((board[3][6] == null) ? " " : board[3][6].getRight()) + " | "
				+ ((board[4][6] == null) ? " " : board[4][6].getLeft())
				+ ((board[4][6] == null) ? " " : board[4][6].getName())
				+ ((board[4][6] == null) ? " " : board[4][6].getRight()) + " | "
				+ ((board[5][6] == null) ? " " : board[5][6].getLeft())
				+ ((board[5][6] == null) ? " " : board[5][6].getName())
				+ ((board[5][6] == null) ? " " : board[5][6].getRight()) + " | "
				+ ((board[6][6] == null) ? " " : board[6][6].getLeft())
				+ ((board[6][6] == null) ? " " : board[6][6].getName())
				+ ((board[6][6] == null) ? " " : board[6][6].getRight()) + " | "
				+ ((board[7][6] == null) ? " " : board[7][6].getLeft())
				+ ((board[7][6] == null) ? " " : board[7][6].getName())
				+ ((board[7][6] == null) ? " " : board[7][6].getRight()) + " | "
				+ ((board[8][6] == null) ? " " : board[8][6].getLeft())
				+ ((board[8][6] == null) ? " " : board[8][6].getName())
				+ ((board[8][6] == null) ? " " : board[8][6].getRight()) + " | "
				+ ((board[9][6] == null) ? " " : board[9][6].getLeft())
				+ ((board[9][6] == null) ? " " : board[9][6].getName())
				+ ((board[9][6] == null) ? " " : board[9][6].getRight()) + " |\n" + "|  "
				+ ((board[0][6] == null) ? " " : board[0][6].getDown()) + "  |  "
				+ ((board[1][6] == null) ? " " : board[1][6].getDown()) + "  |  "
				+ ((board[2][6] == null) ? " " : board[2][6].getDown()) + "  |  "
				+ ((board[3][6] == null) ? " " : board[3][6].getDown()) + "  |  "
				+ ((board[4][6] == null) ? " " : board[4][6].getDown()) + "  |  "
				+ ((board[5][6] == null) ? " " : board[5][6].getDown()) + "  |  "
				+ ((board[6][6] == null) ? " " : board[6][6].getDown()) + "  |  "
				+ ((board[7][6] == null) ? " " : board[7][6].getDown()) + "  |  "
				+ ((board[8][6] == null) ? " " : board[8][6].getDown()) + "  |  "
				+ ((board[9][6] == null) ? " " : board[9][6].getDown()) + "  |\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n"
				+ "-------------------------------------------------------------\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n" + "|  "
				+ ((board[0][7] == null) ? " " : board[0][7].getUp()) + "  |  "
				+ ((board[1][7] == null) ? " " : board[1][7].getUp()) + "  |  "
				+ ((board[2][7] == null) ? " " : board[2][7].getUp()) + "  |  "
				+ ((board[3][7] == null) ? " " : board[3][7].getUp()) + "  |  "
				+ ((board[4][7] == null) ? " " : board[4][7].getUp()) + "  |  "
				+ ((board[5][7] == null) ? " " : board[5][7].getUp()) + "  |  "
				+ ((board[6][7] == null) ? " " : board[6][7].getUp()) + "  |  "
				+ ((board[7][7] == null) ? " " : board[7][7].getUp()) + "  |  "
				+ ((board[8][7] == null) ? " " : board[8][7].getUp()) + "  |  "
				+ ((board[9][7] == null) ? " " : board[9][7].getUp()) + "  |\n" + "| "
				+ ((board[0][7] == null) ? " " : board[0][7].getLeft())
				+ ((board[0][7] == null) ? " " : board[0][7].getName())
				+ ((board[0][7] == null) ? " " : board[0][7].getRight()) + " | "
				+ ((board[1][7] == null) ? " " : board[1][7].getLeft())
				+ ((board[1][7] == null) ? " " : board[1][7].getName())
				+ ((board[1][7] == null) ? " " : board[1][7].getRight()) + " | "
				+ ((board[2][7] == null) ? " " : board[2][7].getLeft())
				+ ((board[2][7] == null) ? " " : board[2][7].getName())
				+ ((board[2][7] == null) ? " " : board[2][7].getRight()) + " | "
				+ ((board[3][7] == null) ? " " : board[3][7].getLeft())
				+ ((board[3][7] == null) ? " " : board[3][7].getName())
				+ ((board[3][7] == null) ? " " : board[3][7].getRight()) + " | "
				+ ((board[4][7] == null) ? " " : board[4][7].getLeft())
				+ ((board[4][7] == null) ? " " : board[4][7].getName())
				+ ((board[4][7] == null) ? " " : board[4][7].getRight()) + " | "
				+ ((board[5][7] == null) ? " " : board[5][7].getLeft())
				+ ((board[5][7] == null) ? " " : board[5][7].getName())
				+ ((board[5][7] == null) ? " " : board[5][7].getRight()) + " | "
				+ ((board[6][7] == null) ? " " : board[6][7].getLeft())
				+ ((board[6][7] == null) ? " " : board[6][7].getName())
				+ ((board[6][7] == null) ? " " : board[6][7].getRight()) + " | "
				+ ((board[7][7] == null) ? " " : board[7][7].getLeft())
				+ ((board[7][7] == null) ? " " : board[7][7].getName())
				+ ((board[7][7] == null) ? " " : board[7][7].getRight()) + " | "
				+ ((board[8][7] == null) ? " " : board[8][7].getLeft())
				+ ((board[8][7] == null) ? " " : board[8][7].getName())
				+ ((board[8][7] == null) ? " " : board[8][7].getRight()) + " | "
				+ ((board[9][7] == null) ? " " : board[9][7].getLeft())
				+ ((board[9][7] == null) ? " " : board[9][7].getName())
				+ ((board[9][7] == null) ? " " : board[9][7].getRight()) + " |\n" + "|  "
				+ ((board[0][7] == null) ? " " : board[0][7].getDown()) + "  |  "
				+ ((board[1][7] == null) ? " " : board[1][7].getDown()) + "  |  "
				+ ((board[2][7] == null) ? " " : board[2][7].getDown()) + "  |  "
				+ ((board[3][7] == null) ? " " : board[3][7].getDown()) + "  |  "
				+ ((board[4][7] == null) ? " " : board[4][7].getDown()) + "  |  "
				+ ((board[5][7] == null) ? " " : board[5][7].getDown()) + "  |  "
				+ ((board[6][7] == null) ? " " : board[6][7].getDown()) + "  |  "
				+ ((board[7][7] == null) ? " " : board[7][7].getDown()) + "  |  "
				+ ((board[8][7] == null) ? " " : board[8][7].getDown()) + "  |  "
				+ ((board[9][7] == null) ? " " : board[9][7].getDown()) + "  |\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n"
				+ "-------------------------------------------------------------\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n" + "|  "
				+ ((board[0][8] == null) ? " " : board[0][8].getUp()) + "  |  "
				+ ((board[1][8] == null) ? " " : board[1][8].getUp()) + "  |  "
				+ ((board[2][8] == null) ? " " : board[2][8].getUp()) + "  |  "
				+ ((board[3][8] == null) ? " " : board[3][8].getUp()) + "  |  "
				+ ((board[4][8] == null) ? " " : board[4][8].getUp()) + "  |  "
				+ ((board[5][8] == null) ? " " : board[5][8].getUp()) + "  |  "
				+ ((board[6][8] == null) ? " " : board[6][8].getUp()) + "  |  "
				+ ((board[7][8] == null) ? " " : board[7][8].getUp()) + "  |     |  x  |\n" + "| "
				+ ((board[0][8] == null) ? " " : board[0][8].getLeft())
				+ ((board[0][8] == null) ? " " : board[0][8].getName())
				+ ((board[0][8] == null) ? " " : board[0][8].getRight()) + " | "
				+ ((board[1][8] == null) ? " " : board[1][8].getLeft())
				+ ((board[1][8] == null) ? " " : board[1][8].getName())
				+ ((board[1][8] == null) ? " " : board[1][8].getRight()) + " | "
				+ ((board[2][8] == null) ? " " : board[2][8].getLeft())
				+ ((board[2][8] == null) ? " " : board[2][8].getName())
				+ ((board[2][8] == null) ? " " : board[2][8].getRight()) + " | "
				+ ((board[3][8] == null) ? " " : board[3][8].getLeft())
				+ ((board[3][8] == null) ? " " : board[3][8].getName())
				+ ((board[3][8] == null) ? " " : board[3][8].getRight()) + " | "
				+ ((board[4][8] == null) ? " " : board[4][8].getLeft())
				+ ((board[4][8] == null) ? " " : board[4][8].getName())
				+ ((board[4][8] == null) ? " " : board[4][8].getRight()) + " | "
				+ ((board[5][8] == null) ? " " : board[5][8].getLeft())
				+ ((board[5][8] == null) ? " " : board[5][8].getName())
				+ ((board[5][8] == null) ? " " : board[5][8].getRight()) + " | "
				+ ((board[6][8] == null) ? " " : board[6][8].getLeft())
				+ ((board[6][8] == null) ? " " : board[6][8].getName())
				+ ((board[6][8] == null) ? " " : board[6][8].getRight()) + " | "
				+ ((board[7][8] == null) ? " " : board[7][8].getLeft())
				+ ((board[7][8] == null) ? " " : board[7][8].getName())
				+ ((board[7][8] == null) ? " " : board[7][8].getRight()) + " |  0  | xxx |\n" + "|  "
				+ ((board[0][8] == null) ? " " : board[0][8].getDown()) + "  |  "
				+ ((board[1][8] == null) ? " " : board[1][8].getDown()) + "  |  "
				+ ((board[2][8] == null) ? " " : board[2][8].getDown()) + "  |  "
				+ ((board[3][8] == null) ? " " : board[3][8].getDown()) + "  |  "
				+ ((board[4][8] == null) ? " " : board[4][8].getDown()) + "  |  "
				+ ((board[5][8] == null) ? " " : board[5][8].getDown()) + "  |  "
				+ ((board[6][8] == null) ? " " : board[6][8].getDown()) + "  |  "
				+ ((board[7][8] == null) ? " " : board[7][8].getDown()) + "  |     |  x  |\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n"
				+ "-------------------------------------------------------------\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n" + "|  "
				+ ((board[0][9] == null) ? " " : board[0][9].getUp()) + "  |  "
				+ ((board[1][9] == null) ? " " : board[1][9].getUp()) + "  |  "
				+ ((board[2][9] == null) ? " " : board[2][9].getUp()) + "  |  "
				+ ((board[3][9] == null) ? " " : board[3][9].getUp()) + "  |  "
				+ ((board[4][9] == null) ? " " : board[4][9].getUp()) + "  |  "
				+ ((board[5][9] == null) ? " " : board[5][9].getUp()) + "  |  "
				+ ((board[6][9] == null) ? " " : board[6][9].getUp()) + "  |  "
				+ ((board[7][9] == null) ? " " : board[7][9].getUp()) + "  |  x  |  x  |\n" + "| "
				+ ((board[0][9] == null) ? " " : board[0][9].getLeft())
				+ ((board[0][9] == null) ? " " : board[0][9].getName())
				+ ((board[0][9] == null) ? " " : board[0][9].getRight()) + " | "
				+ ((board[1][9] == null) ? " " : board[1][9].getLeft())
				+ ((board[1][9] == null) ? " " : board[1][9].getName())
				+ ((board[1][9] == null) ? " " : board[1][9].getRight()) + " | "
				+ ((board[2][9] == null) ? " " : board[2][9].getLeft())
				+ ((board[2][9] == null) ? " " : board[2][9].getName())
				+ ((board[2][9] == null) ? " " : board[2][9].getRight()) + " | "
				+ ((board[3][9] == null) ? " " : board[3][9].getLeft())
				+ ((board[3][9] == null) ? " " : board[3][9].getName())
				+ ((board[3][9] == null) ? " " : board[3][9].getRight()) + " | "
				+ ((board[4][9] == null) ? " " : board[4][9].getLeft())
				+ ((board[4][9] == null) ? " " : board[4][9].getName())
				+ ((board[4][9] == null) ? " " : board[4][9].getRight()) + " | "
				+ ((board[5][9] == null) ? " " : board[5][9].getLeft())
				+ ((board[5][9] == null) ? " " : board[5][9].getName())
				+ ((board[5][9] == null) ? " " : board[5][9].getRight()) + " | "
				+ ((board[6][9] == null) ? " " : board[6][9].getLeft())
				+ ((board[6][9] == null) ? " " : board[6][9].getName())
				+ ((board[6][9] == null) ? " " : board[6][9].getRight()) + " | "
				+ ((board[7][9] == null) ? " " : board[7][9].getLeft())
				+ ((board[7][9] == null) ? " " : board[7][9].getName())
				+ ((board[7][9] == null) ? " " : board[7][9].getRight()) + " | xxx | xxx |\n" + "|  "
				+ ((board[0][9] == null) ? " " : board[0][9].getDown()) + "  |  "
				+ ((board[1][9] == null) ? " " : board[1][9].getDown()) + "  |  "
				+ ((board[2][9] == null) ? " " : board[2][9].getDown()) + "  |  "
				+ ((board[3][9] == null) ? " " : board[3][9].getDown()) + "  |  "
				+ ((board[4][9] == null) ? " " : board[4][9].getDown()) + "  |  "
				+ ((board[5][9] == null) ? " " : board[5][9].getDown()) + "  |  "
				+ ((board[6][9] == null) ? " " : board[6][9].getDown()) + "  |  "
				+ ((board[7][9] == null) ? " " : board[7][9].getDown()) + "  |  x  |  x  |\n"
				+ "|     |     |     |     |     |     |     |     |     |     |\n"
				+ "-------------------------------------------------------------\n");
	}

}
