package BlockIt.Model;

import java.util.List;

public abstract class Piece {
	public static enum pieceColor{WHITE, BLACK};
	private Position position;
	private pieceColor color;
	
	public abstract List<Position> getPossibleMove();
	
	public Piece(pieceColor color, Position position){
		this.color=color;
		this.position=position;
	}
	
	public Position getPosition(){
		return position;
	}
	
	public boolean move(Position position){
		if(this.getPossibleMove().contains(position)){
			this.position=position;
			return true;
		}else{
			return false;
		}
	}
}
