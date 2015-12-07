package BlockIt.Model;

import java.util.ArrayList;
import java.util.List;

import BlockIt.Control.Move;

public abstract class Piece {
	public static enum pieceColor {
		WHITE, BLACK
	};

	protected Position position;
	protected pieceColor color;

	public List<Move> getPossibleMove(Position[][] board) {
		List<Move> moves = getMove();

		// clear up
		for (Move move : moves) {
			if (move == null || board[move.toY][move.toX] != null) {
				moves.remove(move);
			}
		}
		return moves;
	}

	public Piece(pieceColor color, Position position) {
		this.color = color;
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	/**
	 * @return the color
	 */
	public pieceColor getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(pieceColor color) {
		this.color = color;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	protected List<Move> getLongMoves(int x, int y) {
		List<Move> moves = new ArrayList<Move>();
		while (Position.getMovePosition(this, x, y) != null
				&& Position.getMovePosition(this, x, y).getPiece() == null) {
			moves.add(Move.getMove(this, x, y));
			if (x > 0)
				x++;
			else if (x < 0)
				x--;

			if (y > 0)
				y++;
			else if (y < 0)
				y--;
		}
		return moves;
	}

	protected abstract List<Move> getMove();

	public static pieceColor getOppositeColor(pieceColor color) {
		if (color == pieceColor.WHITE) {
			return pieceColor.BLACK;
		}
		return pieceColor.WHITE;
	}

	public Piece copy() {
		Piece piece = null;
		try {
			piece = Class.forName(this.getClass().getName()).asSubclass(Piece.class).newInstance();
			piece.setColor(this.getColor());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return piece;
	}
}
