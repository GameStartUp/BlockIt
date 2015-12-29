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
	protected List<Move> getMove(Position[][] board) {
		List<Move> moves = new ArrayList<Move>();
		moves.addAll(getLongMoves(board, 0, forward * 1));
		moves.addAll(getLongMoves(board, 1, forward * 0));
		moves.addAll(getLongMoves(board, -1, forward * 0));
		moves.addAll(getLongMoves(board, 0, forward * -1));
		return moves;
	}
}
