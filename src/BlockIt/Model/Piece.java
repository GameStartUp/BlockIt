package BlockIt.Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
	public static enum pieceColor{WHITE, BLACK};
	private Position position;
	private pieceColor color;
	
	public List<Position> getPossibleMove(){
		List<Position> moves=new ArrayList<Position>();
		int[] xMove=getXMove();
		int[] yMove=getYMove();
		for(int i=0; i<xMove.length; i++){
			int x=position.x+xMove[i];
			int y=position.y+yMove[i];
			moves.add(Position.getPosition(x, y));
		}
		
		//clear up
		for(Position move:moves){
			if(move==null || move.getPiece()!=null){
				moves.remove(move);
			}
		}
		return moves;
	}
	
	public Piece(pieceColor color, Position position){
		this.color=color;
		this.position=position;
	}
	
	public Position getPosition(){
		return position;
	}
	
	public boolean move(Position position){
		if(this.getPossibleMove().contains(position)){
			this.position.setPiece(null);
			this.position=position;
			this.position.setPiece(this);
			return true;
		}else{
			return false;
		}
	}
	
	protected abstract int[] getXMove();
	protected abstract int[] getYMove();
}
