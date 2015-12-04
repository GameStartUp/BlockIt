package BlockIt.Piece;

import java.util.List;

import BlockIt.Model.Piece;
import BlockIt.Model.Position;

public class King extends Piece {
	private static int[] xMove = { 0, 1, 1, 1, 0, -1, -1, -1 };
	private static int[] yMove = { 1, 1, 0, -1, -1, -1, 0, 1 };

	public King(pieceColor color, Position position) {
		super(color, position);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int[] getXMove() {
		// TODO Auto-generated method stub
		return xMove;
	}

	@Override
	protected int[] getYMove() {
		// TODO Auto-generated method stub
		return yMove;
	}
}
