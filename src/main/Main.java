package main;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	Scanner sc;
	// Scanner sc = new Scanner(System.in);
	// Scanner test = new Scanner(System.setIn(in));

	public Player Player1;
	public Player Player2;

	public ArrayList<Piece> a1 = new ArrayList<Piece>(); // Player one available
															// starting pieces
	public ArrayList<Piece> a2 = new ArrayList<Piece>(); // Player two available
															// starting pieces

	private Stack<GameState> undo = new Stack<GameState>();

	public Board board;

	private boolean player1turn = true;
	boolean first = true;

	public Main(InputStream in) {
		createScanner(in);
		// new Piece("new","up", "right", "down", "left");
		// The pieces available to player one (Start of the game, so all of
		// them)
		a1.add(new Piece("a", "|", "#", "|", "-"));
		a1.add(new Piece("b", "|", " ", "|", "-"));
		a1.add(new Piece("c", "#", "#", "#", "#"));
		a1.add(new Piece("d", "|", " ", "#", " "));
		a1.add(new Piece("e", " ", " ", " ", " "));
		a1.add(new Piece("f", "|", "#", "#", "-"));
		a1.add(new Piece("g", "|", "-", "|", "-"));
		a1.add(new Piece("h", "|", " ", "#", "#"));
		a1.add(new Piece("i", " ", "#", " ", " "));
		a1.add(new Piece("j", "|", "#", "|", "#"));
		a1.add(new Piece("k", "|", "#", " ", "-"));
		a1.add(new Piece("l", "|", " ", " ", " "));
		a1.add(new Piece("m", "|", "#", "#", " "));
		a1.add(new Piece("n", " ", "#", "#", " "));
		a1.add(new Piece("o", "|", " ", "|", "#"));
		a1.add(new Piece("p", "|", " ", "#", "-"));
		a1.add(new Piece("q", "|", " ", " ", "#"));
		a1.add(new Piece("r", "|", "#", " ", "#"));
		a1.add(new Piece("s", " ", "#", " ", "#"));
		a1.add(new Piece("t", "|", " ", "|", " "));
		a1.add(new Piece("u", "|", " ", " ", "-"));
		a1.add(new Piece("v", "|", "#", " ", " "));
		a1.add(new Piece("w", "|", "#", "#", "#"));
		a1.add(new Piece("x", " ", "#", "#", "#"));

		// The pieces available to player two (Start of the game, so all of
		// them)
		a2.add(new Piece("A", "|", "#", "|", "-"));
		a2.add(new Piece("B", "|", " ", "|", "-"));
		a2.add(new Piece("C", "#", "#", "#", "#"));
		a2.add(new Piece("D", "|", " ", "#", " "));
		a2.add(new Piece("E", " ", " ", " ", " "));
		a2.add(new Piece("F", "|", "#", "#", "-"));
		a2.add(new Piece("G", "|", "-", "|", "-"));
		a2.add(new Piece("H", "|", " ", "#", "#"));
		a2.add(new Piece("I", " ", "#", " ", " "));
		a2.add(new Piece("J", "|", "#", "|", "#"));
		a2.add(new Piece("K", "|", "#", " ", "-"));
		a2.add(new Piece("L", "|", " ", " ", " "));
		a2.add(new Piece("M", "|", "#", "#", " "));
		a2.add(new Piece("N", " ", "#", "#", " "));
		a2.add(new Piece("O", "|", " ", "|", "#"));
		a2.add(new Piece("P", "|", " ", "#", "-"));
		a2.add(new Piece("Q", "|", " ", " ", "#"));
		a2.add(new Piece("R", "|", "#", " ", "#"));
		a2.add(new Piece("S", " ", "#", " ", "#"));
		a2.add(new Piece("T", "|", " ", "|", " "));
		a2.add(new Piece("U", "|", " ", " ", "-"));
		a2.add(new Piece("V", "|", "#", " ", " "));
		a2.add(new Piece("W", "|", "#", "#", "#"));
		a2.add(new Piece("X", " ", "#", "#", "#"));

		Player1 = new Player(a1, 2, 2);
		Player2 = new Player(a2, 7, 7);

		board = new Board(Player1, Player2);
		undo.push(new GameState(board.clone(), Player1.clone(), Player2.clone(), player1turn, "start"));

	}

	public void startGame() {
		Player Active;
		boolean undoing = false;
		String restart = "";
		while (true) {

			if (player1turn) {
				Active = Player1;
			} else {
				Active = Player2;
			}

			if ((undoing && restart.equals("start")) || !undoing) {
				undoing = !stage1(Active);
				restart = "";
			}

			if (first) {
				first = false;
			}

			if ((undoing && restart.equals("move")) || !undoing) {
				System.out.println(board);
				undoing = !stage2(Active);
				restart = "";
			}

			player1turn = !player1turn;

			if (undoing) {

				GameState revert = undo.pop();

				board = revert.getBoard();
				Player1 = revert.getPlayer1();
				Player2 = revert.getPlayer2();
				player1turn = revert.isTurn();
				restart = revert.getPlace();
				System.out.println(restart);
				System.out.println(board);
			}

		}
	}

	private boolean stage1(Player turn) {

		boolean progress = false;
		System.out.println("These are the pieces available to you");
		System.out.println(turn.available);

		while (!progress) {
			System.out.println("What would you like to do?");
			String action = sc.nextLine();
			String[] actionsplit = action.split(" ");
			if (actionsplit[0].equals("create")) {
				String name = null;
				String rotation = null;
				try {
					if (actionsplit[1] != null) {
						name = actionsplit[1];
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Create usage: create <letter> <0/90/180/270>");
				}
				try {
					if (actionsplit[2] != null) {
						rotation = actionsplit[2];
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Create usage: create <letter> <0/90/180/270>");
				}
				try {
					String test = actionsplit[3];
					System.out.println("Create usage: create <letter> <0/90/180/270>");
				} catch (ArrayIndexOutOfBoundsException e) {
					if (name != null && rotation != null) {
						undo.push(new GameState(board.clone(), Player1.clone(), Player2.clone(), player1turn, "start"));
						// progress = create(name, rotation, turn);
						if (create(name, rotation, turn)) {
							progress = true;
							if (!Reaction(name, turn)) {
								return false;
							}
						}
					}
				}
			} else if (actionsplit[0].equals("pass")) {
				progress = true;
			} else if (actionsplit[0].equals("undo")) {
				return false;
			} else {
				System.out.println("This is an invalid instruction at this point");
			}
		}
		return true;
	}

	private boolean stage2(Player turn) {

		boolean progress = false;
		ArrayList<Piece> moveable = new ArrayList<Piece>();
		for (int i = 0; i < turn.board.size(); i++) {
			if (!turn.board.get(i).isMoved()) {
				moveable.add(turn.board.get(i));
			}
		}
		while (!progress) {
			System.out.println("These pieces are available to move");
			System.out.println(moveable);
			System.out.println("What would you like to do?");
			String action = sc.nextLine();
			String[] actionsplit = action.split(" ");
			if (actionsplit[0].equals("rotate")) {
				String name = null;
				String rotation = null;
				try {
					if (actionsplit[1] != null) {
						name = actionsplit[1];
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Rotate usage: rotate <letter> <1-4>");
				}
				try {
					if (actionsplit[2] != null) {
						rotation = actionsplit[2];
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Rotate usage: rotate <letter> <1-4>");
				}
				try {
					String test = actionsplit[3];
					System.out.println("Rotate usage: rotate <letter> <1-4>");
				} catch (ArrayIndexOutOfBoundsException e) {
					if (name != null && rotation != null) {
						undo.push(new GameState(board.clone(), Player1.clone(), Player2.clone(), player1turn, "move"));
						if (rotate(name, rotation, turn, moveable)) {
							if (!Reaction(name, turn)) {
								return false;
							}
							System.out.println(board);
						}
					}
				}
			} else if (actionsplit[0].equals("move")) {
				String name = null;
				String dir = null;
				try {
					if (actionsplit[1] != null) {
						name = actionsplit[1];
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Move usage: move <letter> <up/right/down/left>");
				}
				try {
					if (actionsplit[2] != null) {
						dir = actionsplit[2];
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Move usage: move <letter> <up/right/down/left>");
				}
				try {
					String test = actionsplit[3];
					System.out.println("Move usage: move <letter> <up/right/down/left>");
				} catch (ArrayIndexOutOfBoundsException e) {
					if (name != null && dir != null) {
						undo.push(new GameState(board.clone(), Player1.clone(), Player2.clone(), player1turn, "move"));
						if (move(name, dir, turn, moveable)) {
							if (!Reaction(name, turn)) {
								return false;
							}
							System.out.println(board);
						}
					}
				}
			} else if (actionsplit[0].equals("pass")) {
				progress = true;
			} else if (actionsplit[0].equals("undo")) {
				return false;
			} else {
				System.out.println("This is an invalid instruction at this point");
			}
			moveable = new ArrayList<Piece>();
			for (int i = 0; i < turn.board.size(); i++) {
				if (!turn.board.get(i).isMoved()) {
					moveable.add(turn.board.get(i));
				}
			}

		}
		for (int i = 0; i < turn.board.size(); i++) {
			turn.board.get(i).setMoved(false);
		}
		return true;
	}

	private Boolean move(String name, String dir, Player turn, ArrayList<Piece> moveable) {
		Piece p = null;
		if (!dir.equals("up") && !dir.equals("down") && !dir.equals("right") && !dir.equals("left")) {
			System.out.println("Move usage: move <letter> <up/right/down/left>");
			return false;
		}

		for (int i = 0; i < moveable.size(); i++) {
			if (moveable.get(i).getName().equalsIgnoreCase(name)) {
				p = moveable.get(i);
				break;
			}
		}

		if (p != null) {
			if (board.move(dir, p)) {
				p.setMoved(true);
				return true;
			}
		} else {
			System.out.println("That not a valid piece");
			return false;
		}

		return false;
	}

	private Boolean rotate(String name, String rotation, Player turn, ArrayList<Piece> moveable) {
		int rot;
		Piece p = null;

		try {
			rot = Integer.parseInt(rotation);
			if (rot != 1 && rot != 2 && rot != 3 && rot != 4) {
				System.out.println("Rotate usage: rotate <letter> <1-4>");
				return false;
			}
		} catch (NumberFormatException e) {
			System.out.println(e);
			System.out.println("Rotate usage: rotate <letter> <1-4>");
			return false;
		}

		for (int i = 0; i < moveable.size(); i++) {
			if (moveable.get(i).getName().equalsIgnoreCase(name)) {
				p = moveable.get(i);
				break;
			}
		}

		if (p != null) {
			p.rotate(rot);
			p.setMoved(true);
			return true;
		} else {
			System.out.println("That not a valid piece");
			return false;
		}
	}

	private Boolean create(String name, String rotation, Player turn) {
		int rot;
		Piece p = null;

		try {
			rot = Integer.parseInt(rotation);
			if (rot != 0 && rot != 90 && rot != 180 && rot != 270) {
				System.out.println("Create usage: create <letter> <0/90/180/270>");
				return false;
			}
			rot = (rot / 90);
		} catch (NumberFormatException e) {
			System.out.println(e);
			System.out.println("Create usage: create <letter> <0/90/180/270>");
			return false;
		}
		if (first && !name.equalsIgnoreCase("e")) {
			System.out.println("The first turn can only play e");
			return false;
		}
		for (int i = 0; i < turn.available.size(); i++) {
			if (turn.available.get(i).getName().equalsIgnoreCase(name)) {
				p = turn.available.get(i);
				break;
			}
		}
		if (p != null) {
			p.rotate(rot);
			if (board.add(turn.getPlaceX(), turn.getPlaceY(), p)) {
				turn.available.remove(p);
				turn.board.add(p);
				return true;
			}
			return false;
		} else {
			System.out.println("That piece isn't available.");
			return false;
		}
	}

	private boolean Reaction(String name, Player turn) {
		Piece p = null;
		for (int i = 0; i < turn.board.size(); i++) {
			if (turn.board.get(i).getName().equalsIgnoreCase(name)) {
				p = turn.board.get(i);
				break;
			}
		}
		ArrayList<String> reactions = board.getReactions();
		if (reactions.isEmpty()) {
			return true;
		}
		while (!reactions.isEmpty()) {
			System.out.println("These reations are available:");
			System.out.println(reactions);
			System.out.println("What pieces should react? (Case Sensitive)");

			String action = sc.nextLine();
			String[] actionsplit = action.split(" ");

			if (reactions.contains(action)) {
				if (actionsplit[0].equals("face")) {
					System.out.println("Game Over");
					gameOver();
				}
				board.reaction(actionsplit[0], actionsplit[1]);
				reactions = board.getReactions();
				reactions.remove(action);
			} else if (action.equalsIgnoreCase("undo")) {
				return false;
			} else {
				System.out.println("That is not a valid reaction");
			}
		}
		return true;
	}
	
	public void gameOver() {
		System.exit(0);
	}

	public static void main(String[] args) {
		Main game = new Main(null);
		game.startGame();

	}

	private void createScanner(InputStream in) {
		if (in == null) {
			sc = new Scanner(System.in);
		} else {
			sc = new Scanner(in);
		}
	}

}
