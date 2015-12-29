package BlockIt.Model;

import BlockIt.Model.Piece.pieceColor;

public class Player extends Object{

	private pieceColor color;

	public Player(pieceColor color) {
		this.color = color;
	}

	/**
	 * @return the color
	 */
	public pieceColor getColor() {
		return color;
	}
}
