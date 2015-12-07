package BlockIt.Piece;

import java.util.ArrayList;
import java.util.List;

import BlockIt.Control.Move;
import BlockIt.Model.Piece;
import BlockIt.Model.Position;

public class Rook extends Piece {

	public Rook(pieceColor color, Position position) {
		super(color, position);
	}

	@Override
	protected List<Move> getMove() {
		List<Move> moves = new ArrayList<Move>();
		moves.addAll(getLongMoves(0, 1));
		moves.addAll(getLongMoves(1, 0));
		moves.addAll(getLongMoves(0, -1));
		moves.addAll(getLongMoves(-1, 0));
		moves.addAll(getLongMoves(1, 1));
		moves.addAll(getLongMoves(1, -1));
		moves.addAll(getLongMoves(-1, -1));
		moves.addAll(getLongMoves(-1, 1));
		return moves;
	}
}
