package main;

public class GameState {

	private Board board;
	private Player player1;
	private Player player2;
	private boolean turn;
	private String place;

	public GameState(Board board, Player player1, Player player2, boolean turn, String place) {
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
		this.turn = turn;
		this.place = place;
	}
	
	
	public Board getBoard() {
		return board;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public boolean isTurn() {
		return turn;
	}

	public String getPlace() {
		return place;
	}

}
