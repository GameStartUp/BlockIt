package BlockIt.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import BlockIt.Control.MinMax;
import BlockIt.Control.Move;
import BlockIt.Model.Game;
import BlockIt.Model.Piece;
import BlockIt.Model.Piece.pieceColor;
import BlockIt.Model.Player;
import BlockIt.Model.Position;
import BlockIt.Model.Tree;
import BlockIt.Piece.Bishop;
import BlockIt.Piece.King;
import BlockIt.Piece.Knight;
import BlockIt.Piece.Pawn;
import BlockIt.Piece.Queen;
import BlockIt.Piece.Rook;

public class testMinMax {
	
	@Before
	public void init(){
		List<Piece> pieces= new ArrayList<Piece>();
		int size=6;
		pieces.add(new Pawn(pieceColor.WHITE, new Position(2, 2)));
		pieces.add(new Queen(pieceColor.WHITE, new Position(3, 2)));
		pieces.add(new Knight(pieceColor.WHITE, new Position(0, 2)));
		pieces.add(new King(pieceColor.WHITE, new Position(1, 2)));
		pieces.add(new Bishop(pieceColor.WHITE, new Position(4, 2)));
		pieces.add(new Rook(pieceColor.WHITE, new Position(5, 2)));
		pieces.add(new Pawn(pieceColor.BLACK, new Position(2, 5)));
		pieces.add(new Pawn(pieceColor.BLACK, new Position(3, 5)));
		new Game(size, pieces);
	}
	
	@Test
	public void testMinMax(){
		System.out.println(System.currentTimeMillis());
		new Tree(7);
		System.out.println(System.currentTimeMillis());
		Move move=MinMax.bestMove();
		System.out.println(System.currentTimeMillis());
		Move.move(Game.board, move);
		Position.printBoard(Game.board);
	}
}
