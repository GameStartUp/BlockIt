package BlockIt.Model;

public class Position {

	public int x;
	public int y;
	
	private Piece piece;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the piece
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * @param piece the piece to set
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public static Position getPosition(int x, int y){
		if(x>=0 && x < Game.boardSize && y >=0 && y < Game.boardSize)
			return Game.board[x][y];
		else
			return null;
	}
}
