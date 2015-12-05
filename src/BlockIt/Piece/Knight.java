package BlockIt.Piece;

import java.util.ArrayList;
import java.util.List;

import BlockIt.Model.Piece;
import BlockIt.Model.Position;

public class Knight extends Piece{

	public Knight(pieceColor color, Position position) {
		super(color, position);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<Position> getMove() {
		List<Position> moves=new ArrayList<Position>();
		moves.add(Position.getMovePosition(this, 1, 2));
		moves.add(Position.getMovePosition(this, 2, 1));
		moves.add(Position.getMovePosition(this, 1, -2));
		moves.add(Position.getMovePosition(this, 2, -1));
		moves.add(Position.getMovePosition(this, -1, -2));
		moves.add(Position.getMovePosition(this, -2, -1));
		moves.add(Position.getMovePosition(this, -1, 2));
		moves.add(Position.getMovePosition(this, -2, 1));
		return moves;
	}
}
