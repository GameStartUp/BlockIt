package BlockIt.Piece;

import java.util.ArrayList;
import java.util.List;

import BlockIt.Control.Move;
import BlockIt.Model.Piece;
import BlockIt.Model.Position;

public class King extends Piece {

	public King(pieceColor color, Position position) {
		super(color, position);
	}

	@Override
	protected List<Move> getMove(Position[][] board) {
		List<Move> moves = new ArrayList<Move>();
		moves.add(Move.getMove(this, 0, 1));
		moves.add(Move.getMove(this, 1, 1));
		moves.add(Move.getMove(this, 1, 0));
		moves.add(Move.getMove(this, 1, -1));
		moves.add(Move.getMove(this, 0, -1));
		moves.add(Move.getMove(this, -1, -1));
		moves.add(Move.getMove(this, -1, 0));
		moves.add(Move.getMove(this, -1, 1));
		return moves;
	}
}
