package BlockIt.Piece;

import java.util.ArrayList;
import java.util.List;

import BlockIt.Control.Move;
import BlockIt.Model.Piece;
import BlockIt.Model.Position;

public class Knight extends Piece {

	public Knight(pieceColor color, Position position) {
		super(color, position);
	}

	@Override
	protected List<Move> getMove(Position[][] board) {
		List<Move> moves = new ArrayList<Move>();
		moves.add(Move.getMove(this, 1, forward * 2));
		moves.add(Move.getMove(this, -1, forward * 2));
		moves.add(Move.getMove(this, 2, forward * 1));
		moves.add(Move.getMove(this, -2, forward * 1));
		moves.add(Move.getMove(this, 2, forward * -1));
		moves.add(Move.getMove(this, -2, forward * -1));
		moves.add(Move.getMove(this, 1, forward * -2));
		moves.add(Move.getMove(this, -1, forward * -2));
		return moves;
	}
}
