package BlockIt.Piece;

import java.util.ArrayList;
import java.util.List;

import BlockIt.Model.Piece;
import BlockIt.Model.Position;

public class Bishop extends Piece{

	public Bishop(pieceColor color, Position position) {
		super(color, position);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<Position> getMove() {
		List<Position> moves= new ArrayList<Position>();
		moves.addAll(getLongMoves(1, 1));
		moves.addAll(getLongMoves(1, -1));
		moves.addAll(getLongMoves(-1, -1));
		moves.addAll(getLongMoves(-1, 1));
		return moves;
	}
}
