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
	 * @param piece
	 *            the piece to set
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public static Position getMovePosition(Piece piece, int x, int y) {
		return getPosition(piece.getPosition().x + x, piece.getPosition().y + y);
	}
	
	public static Position getMovePositionFromBoardCopy(Piece piece, int x, int y) {
		return getPositionFromBoardCopy(piece.getPosition().x + x, piece.getPosition().y + y);
	}

	public static Position getPosition(int x, int y) {
		if (x >= 0 && x < Game.boardSize && y >= 0 && y < Game.boardSize)
			return Game.board[y][x];
		else
			return null;
	}
	
	public static Position getPositionFromBoardCopy(int x, int y) {
		if (x >= 0 && x < Game.boardSize && y >= 0 && y < Game.boardSize)
			return Tree.boardCopy[y][x];
		else
			return null;
	}

	public Position copy() {
		Position p = new Position(this.x, this.y);
		if(this.getPiece()!=null)
			p.setPiece(this.getPiece().copy(this));
		return p;
	}
	
	public static void printBoard(Position[][] board){
		for(int i=Game.boardSize-1; i>=0; i--){
			for(int j=0; j<Game.boardSize; j++){
				char c='_';
				if(board[i][j].getPiece()!=null){
					switch(board[i][j].getPiece().getClass().getSimpleName()){
					case "Pawn":
						c = 'p';
						break;
					case "Rook":
						c = 'r';
						break;
					case "Knight":
						c = 'k';
						break;
					case "Bishop":
						c = 'b';
						break;
					case "Queen":
						c = 'q';
						break;
					case "King":
						c = 'K';
						break;
					}
				}
				System.out.print(c);
			}
			System.out.println();
		}
		System.out.println();
	}
}
