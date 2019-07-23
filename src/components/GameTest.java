package components;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_Game() {
		Game game=new Game("chess","a","b");
		String gameName=game.getGameName();
		assertEquals(gameName,"chess");
	}
	
	@Test
	public void test_getOwnerAtCord() {
		Game game=new Game("chess","p1","p2");
		Position pos=new Position(0,0);
		player pp=new player();
		try {
			pp = game.getOwnerAtCord(pos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(pp.getPlayerName(),"p1");
	}
	
	@Test
	public void test_getNumOfPieces() {
		Game game=new Game("chess","p1","p2");
		player p1=game.getPlayer1();
		int num1=game.getNumOfPiecesInBoard(p1);
		assertEquals(16,num1);
	}
	
	@Test
	public void test_putPiece() throws Exception {
		Game game=new Game("go","p1","p2");
		player p1=game.getPlayer1();
		piece p=new piece("white",0,0,0);
		Position pos=new Position(0,0);
		game.putPiece(p1, p, pos);
		assertEquals(game.getGameBoard().getBoardPieces()[0][0].getPieceName(),"white");
	}
	
	@Test
	public void test_eatPiece() throws Exception {
		Game game=new Game("chess","p1","p2");
		player p1=game.getPlayer1();
		player p2=game.getPlayer2();
		
		Position pos1=new Position(0,0);
		Position pos2=new Position(0,7);
		
		game.eatPiece(p1, pos1, p2, pos2);
		
		assertEquals(game.getGameBoard().getBoardPieces()[0][7].getPieceName(),"rook");
	}
	
	@Test
	public void test_movePiece() throws Exception {
		Game game=new Game("chess","p1","p2");
		player p1=game.getPlayer1();
		Position pos1=new Position(0,1);
		Position pos2=new Position(0,2);
		game.movePiece(p1, pos1, pos2);
		assertEquals(game.getGameBoard().getBoardPieces()[0][2].getPieceName(),"pawn");
	}
	
	@Test
	public void test_removePiece() throws Exception {
		Game game=new Game("chess","p1","p2");
		player p1=game.getPlayer1();
		Position pos=new Position(1,6);
		game.removePiece(p1, pos);
		assertEquals(game.getGameBoard().getBoardPiece(pos.x, pos.y),null);
	}
}
