package BlockIt.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import BlockIt.Model.Piece;
import BlockIt.Model.Position;

public class Pawn extends Piece{
	
	private boolean firstMove=true;
	private static int[] xMove={0,0};
	private static int[] yMove={1,2};

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
	protected int[] getXMove() {
		if(!firstMove){
			return Arrays.copyOfRange(xMove, 0, xMove.length-1);
		}
		return xMove;
	}

	@Override
	protected int[] getYMove() {
		if(!firstMove){
			return Arrays.copyOfRange(yMove, 0, yMove.length-1);
		}
		return yMove;
	}
}
