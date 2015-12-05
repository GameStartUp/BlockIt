package BlockIt.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import BlockIt.Model.Piece;
import BlockIt.Model.Position;

public class Pawn extends Piece{
	
	private boolean firstMove=true;

	public Pawn(pieceColor color, Position position) {
		super(color, position);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean move(Position position){
		firstMove=false;
		return super.move(position);
	}

	@Override
	protected List<Position> getMove() {
		List<Position> moves=new ArrayList<Position>();
		if(firstMove){
			moves.add(Position.getMovePosition(this, 0, 2));
		}
		moves.add(Position.getMovePosition(this, 0, 1));
		return moves;
	}
}
