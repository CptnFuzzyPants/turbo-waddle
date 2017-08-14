package tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import org.junit.Test;

import main.*;

public class RotationTests {

	@Test public void oneRotation() {
		String input = "create e 0\n"+
					   "pass\n"+
					   "create x 0\n"+
					   "rotate x 1";
		String expected ="-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|  X  |  X  |     |     |     |     |     |     |     |     |\n" +
				"| XXX | XXX |     |     |     |     |     |     |     |     |\n" +
				"|  X  |  X  |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|  X  |     |     |     |     |     |     |     |     |     |\n" +
				"| XXX |  1  |     |     |     |     |     |     |     |     |\n" +
				"|  X  |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |  e  |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |  #  |     |     |\n" +
				"|     |     |     |     |     |     |     | #X  |     |     |\n" +
				"|     |     |     |     |     |     |     |  #  |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |  x  |\n" +
				"|     |     |     |     |     |     |     |     |  0  | xxx |\n" +
				"|     |     |     |     |     |     |     |     |     |  x  |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |  x  |  x  |\n" +
				"|     |     |     |     |     |     |     |     | xxx | xxx |\n" +
				"|     |     |     |     |     |     |     |     |  x  |  x  |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		Main attempt = new Main(in);
		try {attempt.startGame();
		} catch (NoSuchElementException e) {

			assertEquals(attempt.board.toString(), expected);			
		}		
	}
	
	@Test public void twoRotation() {
		String input = "create e 0\n"+
					   "pass\n"+
					   "create x 0\n"+
					   "rotate x 2";
		String expected ="-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|  X  |  X  |     |     |     |     |     |     |     |     |\n" +
				"| XXX | XXX |     |     |     |     |     |     |     |     |\n" +
				"|  X  |  X  |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|  X  |     |     |     |     |     |     |     |     |     |\n" +
				"| XXX |  1  |     |     |     |     |     |     |     |     |\n" +
				"|  X  |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |  e  |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |  #  |     |     |\n" +
				"|     |     |     |     |     |     |     | #X# |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |  x  |\n" +
				"|     |     |     |     |     |     |     |     |  0  | xxx |\n" +
				"|     |     |     |     |     |     |     |     |     |  x  |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |  x  |  x  |\n" +
				"|     |     |     |     |     |     |     |     | xxx | xxx |\n" +
				"|     |     |     |     |     |     |     |     |  x  |  x  |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		Main attempt = new Main(in);
		try {attempt.startGame();
		} catch (NoSuchElementException e) {

			assertEquals(attempt.board.toString(), expected);			
		}		
	}
	
	@Test public void threeRotation() {
		String input = "create e 0\n"+
					   "pass\n"+
					   "create x 0\n"+
					   "rotate x 3";
		String expected ="-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|  X  |  X  |     |     |     |     |     |     |     |     |\n" +
				"| XXX | XXX |     |     |     |     |     |     |     |     |\n" +
				"|  X  |  X  |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|  X  |     |     |     |     |     |     |     |     |     |\n" +
				"| XXX |  1  |     |     |     |     |     |     |     |     |\n" +
				"|  X  |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |  e  |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |  #  |     |     |\n" +
				"|     |     |     |     |     |     |     |  X# |     |     |\n" +
				"|     |     |     |     |     |     |     |  #  |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |  x  |\n" +
				"|     |     |     |     |     |     |     |     |  0  | xxx |\n" +
				"|     |     |     |     |     |     |     |     |     |  x  |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |  x  |  x  |\n" +
				"|     |     |     |     |     |     |     |     | xxx | xxx |\n" +
				"|     |     |     |     |     |     |     |     |  x  |  x  |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		Main attempt = new Main(in);
		try {attempt.startGame();
		} catch (NoSuchElementException e) {

			assertEquals(attempt.board.toString(), expected);			
		}		
	}
	
	@Test public void fourRotation() {
		String input = "create e 0\n"+
					   "pass\n"+
					   "create x 0\n"+
					   "rotate x 4";
		String expected ="-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|  X  |  X  |     |     |     |     |     |     |     |     |\n" +
				"| XXX | XXX |     |     |     |     |     |     |     |     |\n" +
				"|  X  |  X  |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|  X  |     |     |     |     |     |     |     |     |     |\n" +
				"| XXX |  1  |     |     |     |     |     |     |     |     |\n" +
				"|  X  |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |  e  |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     | #X# |     |     |\n" +
				"|     |     |     |     |     |     |     |  #  |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |  x  |\n" +
				"|     |     |     |     |     |     |     |     |  0  | xxx |\n" +
				"|     |     |     |     |     |     |     |     |     |  x  |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |  x  |  x  |\n" +
				"|     |     |     |     |     |     |     |     | xxx | xxx |\n" +
				"|     |     |     |     |     |     |     |     |  x  |  x  |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		Main attempt = new Main(in);
		try {attempt.startGame();
		} catch (NoSuchElementException e) {

			assertEquals(attempt.board.toString(), expected);			
		}		
	}
	
	@Test public void invalidRotation() {
		String input = "create e 0\n"+
					   "pass\n"+
					   "create x 0\n"+
					   "rotate x 270";
		String expected ="-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|  X  |  X  |     |     |     |     |     |     |     |     |\n" +
				"| XXX | XXX |     |     |     |     |     |     |     |     |\n" +
				"|  X  |  X  |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|  X  |     |     |     |     |     |     |     |     |     |\n" +
				"| XXX |  1  |     |     |     |     |     |     |     |     |\n" +
				"|  X  |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |  e  |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     | #X# |     |     |\n" +
				"|     |     |     |     |     |     |     |  #  |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |  x  |\n" +
				"|     |     |     |     |     |     |     |     |  0  | xxx |\n" +
				"|     |     |     |     |     |     |     |     |     |  x  |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |  x  |  x  |\n" +
				"|     |     |     |     |     |     |     |     | xxx | xxx |\n" +
				"|     |     |     |     |     |     |     |     |  x  |  x  |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		Main attempt = new Main(in);
		try {attempt.startGame();
		} catch (NoSuchElementException e) {

			assertEquals(attempt.board.toString(), expected);			
		}		
	}
	
	@Test public void invalidPieceRotation() {
		String input = "create e 0\n"+
					   "pass\n"+
					   "create x 0\n"+
					   "rotate y 1";
		String expected ="-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|  X  |  X  |     |     |     |     |     |     |     |     |\n" +
				"| XXX | XXX |     |     |     |     |     |     |     |     |\n" +
				"|  X  |  X  |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|  X  |     |     |     |     |     |     |     |     |     |\n" +
				"| XXX |  1  |     |     |     |     |     |     |     |     |\n" +
				"|  X  |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |  e  |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     | #X# |     |     |\n" +
				"|     |     |     |     |     |     |     |  #  |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |  x  |\n" +
				"|     |     |     |     |     |     |     |     |  0  | xxx |\n" +
				"|     |     |     |     |     |     |     |     |     |  x  |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |  x  |  x  |\n" +
				"|     |     |     |     |     |     |     |     | xxx | xxx |\n" +
				"|     |     |     |     |     |     |     |     |  x  |  x  |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		Main attempt = new Main(in);
		try {attempt.startGame();
		} catch (NoSuchElementException e) {

			assertEquals(attempt.board.toString(), expected);			
		}		
	}
	
	@Test public void otherPlayerRotation() {
		String input = "create e 0\n"+
					   "pass\n"+
					   "create x 0\n"+
					   "pass\n"+
					   "pass\n"+
					   "rotate x 1";
		String expected ="-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|  X  |  X  |     |     |     |     |     |     |     |     |\n" +
				"| XXX | XXX |     |     |     |     |     |     |     |     |\n" +
				"|  X  |  X  |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|  X  |     |     |     |     |     |     |     |     |     |\n" +
				"| XXX |  1  |     |     |     |     |     |     |     |     |\n" +
				"|  X  |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |  e  |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     | #X# |     |     |\n" +
				"|     |     |     |     |     |     |     |  #  |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |     |  x  |\n" +
				"|     |     |     |     |     |     |     |     |  0  | xxx |\n" +
				"|     |     |     |     |     |     |     |     |     |  x  |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"|     |     |     |     |     |     |     |     |  x  |  x  |\n" +
				"|     |     |     |     |     |     |     |     | xxx | xxx |\n" +
				"|     |     |     |     |     |     |     |     |  x  |  x  |\n" +
				"|     |     |     |     |     |     |     |     |     |     |\n" +
				"-------------------------------------------------------------\n";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		Main attempt = new Main(in);
		try {attempt.startGame();
		} catch (NoSuchElementException e) {

			assertEquals(attempt.board.toString(), expected);			
		}		
	}
}


