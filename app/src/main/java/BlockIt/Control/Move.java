package BlockIt.Control;

import BlockIt.Model.Game;
import BlockIt.Model.Piece;
import BlockIt.Model.Position;

public class Move {

	public int fromX, fromY, toX, toY;

	public Move(int fromX, int fromY, int toX, int toY) {
		super();
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
	}

	public static Move getMove(Piece piece, int x, int y) {
		x += piece.getPosition().x;
		y += piece.getPosition().y;
		if (check(x, y)) {
			return new Move(piece.getPosition().x, piece.getPosition().y, x, y);
		} else {
			return null;
		}
	}

	private static boolean check(int x, int y) {
		if (x >= 0 && x < Game.boardSize && y >= 0 && y < Game.boardSize)
			return true;
		else
			return false;
	}

	public static boolean move(Position[][] board, Move move) {
		Piece piece = board[move.fromY][move.fromX].getPiece();
		if (piece == null)
			return false;

		board[move.fromY][move.fromX].setPiece(null);
		board[move.toY][move.toX].setPiece(piece);
		piece.setPosition(board[move.toY][move.toX]);
		if(board==Game.board){
			Game.record.add(move);
		}
		//Position.printBoard(board);
		return true;
	}
	
	public static boolean dismove(Position[][] board, Move move) {
		Piece piece = board[move.toY][move.toX].getPiece();
		if (piece == null)
			return false;

		board[move.toY][move.toX].setPiece(null);
		board[move.fromY][move.fromX].setPiece(piece);
		piece.setPosition(board[move.fromY][move.fromX]);
		//Position.printBoard(board);
		return true;
	}

	public boolean equals(Move m) {
		return this.fromX == m.fromX && this.fromY == m.fromY && this.toX == m.toX && this.toY == m.toY;
	}
}
