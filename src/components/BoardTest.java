package components;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_getPiece() throws Exception {
		Game game=new Game("chess","p1","p2");
		Board board=game.getGameBoard();
		piece pp=board.getBoardPiece(2, 0);
		assertEquals(pp.getPieceName(),"bishop");
	}
	
	@Test
	public void test_setPiece() throws Exception {
		Game game=new Game("chess","p1","p2");
		Board board=game.getGameBoard();
		piece p=new piece("newName",0,2,0);
		board.setBoardPiece(2, 0, p);
		assertEquals("newName",board.getBoardPiece(2, 0).getPieceName());
	}
	
	@Test
	public void test_check() {
		Game game=new Game("chess","p1","p2");
		Board board=game.getGameBoard();
		boolean f=board.check(2,1);
		assertEquals(f,true);
	}
	
	@Test
	public void test_setPieceState() throws Exception {
		Game game=new Game("chess","p1","p2");
		Board board=game.getGameBoard();
		board.setBoardPieceState(0, 0, 2);
		assertEquals(board.getBoardPiece(0, 0).getPieceState(),2);
	}
}
