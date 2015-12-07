package BlockIt.Piece;

import java.util.ArrayList;
import java.util.List;

import BlockIt.Control.Move;
import BlockIt.Model.Piece;
import BlockIt.Model.Position;

public class Pawn extends Piece {

	private boolean firstMove = true;

	public Pawn(pieceColor color, Position position) {
		super(color, position);
	}

	@Override
	public void setPosition(Position position) {
		firstMove = false;
		super.setPosition(position);
	}

	@Override
	protected List<Move> getMove() {
		List<Move> moves = new ArrayList<Move>();
		if (firstMove) {
			moves.add(Move.getMove(this, 0, 2));
		}
		moves.add(Move.getMove(this, 0, 1));
		return moves;
	}

	@Override
	public Piece copy() {
		Piece p = super.copy();
		Pawn pawn = (Pawn) p;
		pawn.firstMove = this.firstMove;
		return p;
	}
}
