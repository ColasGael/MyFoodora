package fr.ecp.is1220.MyFoodoraTests;

import static org.junit.Assert.*;
import fr.ecp.is1220.MyFoodora.*;
import org.junit.*;

public class BoardTest {

	private static Board<String> board;
	
	@BeforeClass
	public static void createBoard(){
		board = new Board<String>();
		String offer = "At Dominos the meal family is 5% cheaper than usually !\n";
		board.addObs(offer);
	}
	
	@Test
	public void testToString() {
		System.out.println(board);;
	}
	
	@Test
	public void testClearBoard() {
		board.clearBoard();
		System.out.println(board);
	}
}
