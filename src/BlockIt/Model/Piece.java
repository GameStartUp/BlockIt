package BlockIt.Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
	public static enum pieceColor{WHITE, BLACK};
	protected Position position;
	protected pieceColor color;
	
	public List<Position> getPossibleMove(){
		List<Position> moves=getMove();
		
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
	
	protected List<Position> getLongMoves(int x, int y){
		List<Position> moves=new ArrayList<Position>();
		while(Position.getMovePosition(this, x, y)!=null && Position.getMovePosition(this, x, y).getPiece()==null){
			moves.add(Position.getMovePosition(this, x, y));
			if(x>0)
				x++;
			else if(x<0)
				x--;
			
			if(y>0)
				y++;
			else if(y<0)
				y--;
		}
		return moves;
	}
	
	protected abstract List<Position> getMove();
}
