package BlockIt.Model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import BlockIt.Control.Move;

public abstract class Piece {
	public static enum pieceColor {
		WHITE, BLACK
	};

	protected Position position;
	protected pieceColor color;
	protected int forward;

	public List<Move> getPossibleMove(Position[][] board) {
		List<Move> moves = getMove(board);

		// clear up
		for (int i=0; i<moves.size(); i++) {
			if (moves.get(i) == null || board[moves.get(i).toY][moves.get(i).toX].getPiece()!= null) {
				moves.remove(i);
				i--;
			}
		}
		return moves;
	}

	public Piece(pieceColor color, Position position) {
		this.color = color;
		this.position = position;
		this.position.setPiece(this);
		if(color==pieceColor.WHITE)
			forward=1;
		else
			forward=-1;
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

	protected List<Move> getLongMoves(Position[][] board,  int x, int y) {
		List<Move> moves = new ArrayList<Move>();
		while (Position.getMovePosition(board, this, x, y) != null
				&& Position.getMovePosition(board, this, x, y).getPiece() == null) {
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

	protected abstract List<Move> getMove(Position[][] board);

	public static pieceColor getOppositeColor(pieceColor color) {
		if (color == pieceColor.WHITE) {
			return pieceColor.BLACK;
		}
		return pieceColor.WHITE;
	}

	public Piece copy(Position position) {
		Piece piece = null;
		try {
			Constructor<? extends Piece> constructor = this.getClass().getDeclaredConstructor(pieceColor.class, Position.class);
			piece = constructor.newInstance(this.getColor(), position);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return piece;
	}
}
