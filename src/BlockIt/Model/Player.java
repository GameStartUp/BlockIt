package BlockIt.Model;

import java.util.ArrayList;
import java.util.List;

import BlockIt.Model.Piece.pieceColor;

public class Player {

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
