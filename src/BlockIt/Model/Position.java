package BlockIt.Model;

public class Position extends Object{

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

	public static Position getMovePosition(Position[][] board, Piece piece, int x, int y) {
		return getPosition(board, piece.getPosition().x + x, piece.getPosition().y + y);
	}

	public static Position getPosition(Position[][] board, int x, int y) {
		if (x >= 0 && x < Game.boardSize && y >= 0 && y < Game.boardSize)
			return board[y][x];
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
					switch(board[i][j].getPiece().getClass().getSimpleName().charAt(2)){
					case 'w':
						c = 'p';
						break;
					case 'o':
						c = 'r';
						break;
					case 'i':
						c = 'k';
						break;
					case 's':
						c = 'b';
						break;
					case 'e':
						c = 'q';
						break;
					case 'n':
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
