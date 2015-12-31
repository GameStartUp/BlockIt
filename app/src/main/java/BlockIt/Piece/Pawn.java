package BlockIt.Piece;

import java.util.ArrayList;
import java.util.List;

import BlockIt.Control.Move;
import BlockIt.Model.Piece;
import BlockIt.Model.Position;

public class Pawn extends Piece {

	private boolean firstMove = true;
	private int orgX, orgY;

	public Pawn(pieceColor color, Position position) {
		super(color, position);
		orgX=position.x;
		orgY=position.y;
	}

	@Override
	public void setPosition(Position position) {
		firstMove = false;
		if(position.x==orgX && position.y==orgY)
			firstMove=true;
		super.setPosition(position);
	}

	@Override
	protected List<Move> getMove(Position[][] board) {
		List<Move> moves = new ArrayList<Move>();
		int i=1;
		if(color==pieceColor.BLACK)
			i=-1;
		
		if (firstMove && Position.getMovePosition(board, this, 0, i*1).getPiece()==null) {
			moves.add(Move.getMove(this, 0, i*2));
		}
		moves.add(Move.getMove(this, 0, i*1));
		return moves;
	}

	@Override
	public Piece copy(Position position) {
		Piece p = super.copy(position);
		Pawn pawn = (Pawn) p;
		pawn.firstMove = this.firstMove;
		return p;
	}
}
