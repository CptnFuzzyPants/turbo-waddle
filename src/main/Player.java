package main;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

	private static final long serialVersionUID = -571659304501880844L;
	
	public ArrayList<Piece> available = new ArrayList<Piece>(); //Player available pieces
	//private ArrayList<Piece> graveyard = new ArrayList<Piece>(); //Player graveyard
	public ArrayList<Piece> board = new ArrayList<Piece>(); //Player board
	
	private int placeX;
	private int placeY;
	
	public Player(ArrayList<Piece> starting, int placeX, int placeY) {
		this.available = starting;
		this.placeX = placeX;
		this.placeY = placeY;
	}
	
	public int getPlaceX() {
		return placeX;
	}

	public int getPlaceY() {
		return placeY;
	}
	
	public Player clone() {
		Player clone = null;
		
		try {
			FileOutputStream fout = new FileOutputStream("clone.dat");
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(this);
			out.close();
			
			FileInputStream fin = new FileInputStream("clone.dat");
			ObjectInputStream in = new ObjectInputStream(fin);
			clone = (Player) in.readObject();
			in.close();
		} catch (IOException | ClassNotFoundException e) {
			
			System.out.println(e);
		}
		return clone;
	}
}
