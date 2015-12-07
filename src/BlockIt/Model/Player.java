package BlockIt.Model;

import java.util.List;

import BlockIt.Model.Piece.pieceColor;

public class Player {

	private List<Piece> pieses;
	private pieceColor color;

	public Player(pieceColor color, List<Piece> pieses) {
		this.color = color;
		this.pieses = pieses;
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
