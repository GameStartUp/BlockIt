package BlockIt.Piece;

import java.util.List;

import BlockIt.Model.Piece;
import BlockIt.Model.Position;

public class Knight extends Piece{
	private static int[] xMove = { 1, 2, 2, 1, -1, -2, -2, -1 };
	private static int[] yMove = { 2, 1, -1, -2, -2, -1, 1, 2 };

	public Knight(pieceColor color, Position position) {
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
