package BlockIt.Piece;

import java.util.ArrayList;
import java.util.List;

import BlockIt.Model.Piece;
import BlockIt.Model.Position;

public class King extends Piece {

	public King(pieceColor color, Position position) {
		super(color, position);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<Position> getMove() {
		List<Position> moves=new ArrayList<Position>();
		moves.add(Position.getMovePosition(this, 0, 1));
		moves.add(Position.getMovePosition(this, 1, 1));
		moves.add(Position.getMovePosition(this, 1, 0));
		moves.add(Position.getMovePosition(this, 1, -1));
		moves.add(Position.getMovePosition(this, 0, -1));
		moves.add(Position.getMovePosition(this, -1, -1));
		moves.add(Position.getMovePosition(this, -1, 0));
		moves.add(Position.getMovePosition(this, -1, 1));
		return moves;
	}
}
