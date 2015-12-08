package BlockIt.Model;

import java.util.ArrayList;
import java.util.List;

import BlockIt.Model.Piece.pieceColor;

public class Player {

	private List<Piece> pieses;
	private pieceColor color;

	public Player(pieceColor color) {
		this.color = color;
		pieses = new ArrayList<Piece>();
	}

	/**
	 * @return the pieses
	 */
	public List<Piece> getPieses() {
		return pieses;
	}

	/**
	 * @return the color
	 */
	public pieceColor getColor() {
		return color;
	}
}
