package main;
import java.io.Serializable;

public class Piece implements Serializable  {

	private static final long serialVersionUID = -5631062442624217449L;
	
	private String name;
	private String up;
	private String right;
	private String down;
	private String left;
	private int x = -1;
	private int y = -1;
	private boolean moved = false;
	

	public Piece(String name, String up, String right, String down, String left) {
		this.name = name;
		this.up = up;
		this.right = right;
		this.down = down;
		this.left = left;
	}
	
	public void rotate(int number) {
		int count = 0;
		String temp;
		while (count < number) {
			if (up.equals("|")) {
				temp = "-";
			} else {
				temp = up;
			}
			
			if (left.equals("-")) {
				up = "|";
			} else {
				up = left;
			}
			
			if (down.equals("|")) {
				left = "-";
			} else {
				left = down;
			}
			
			if (right.equals("-")) {
				down = "|";
			} else {
				down = right;
			}
			
			right = temp;
			count++;
		}
		
	}
	
	public String toString() {
		
		return ("\n " + up + " \n" + left + name + right + "\n " + down + " \n");
		
	}
	
	public String getName() {
		return name;
	}

	public String getUp() {
		return up;
	}

	public String getRight() {
		return right;
	}

	public String getDown() {
		return down;
	}

	public String getLeft() {
		return left;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

}
