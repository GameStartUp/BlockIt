package BlockIt.Control;

import BlockIt.Model.Position;

public class Node {
	Position[][] board;
	int heuristic = Integer.MIN_VALUE;
	public Node(Position[][] board) {
		this.board = board;
	}

	/**
	 * @return the heuristic
	 */
	public int getHeuristic() {
		return heuristic;
	}

	/**
	 * @param heuristic the heuristic to set
	 */
	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}
}
