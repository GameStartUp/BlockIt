package BlockIt.Control;

import java.util.List;

import BlockIt.Model.Position;

public class AlaphaBetaNode {
	Position[][] board;
	List<AlaphaBetaNode> childrenNode;
	int heuristic;
	public AlaphaBetaNode(Position[][] board) {
		this.board = board;
	}
	
	/**
	 * @return the childrenNode
	 */
	public List<AlaphaBetaNode> getChildrenNode() {
		return childrenNode;
	}
	/**
	 * @param childrenNode the childrenNode to set
	 */
	public void setChildrenNode(List<AlaphaBetaNode> childrenNode) {
		this.childrenNode = childrenNode;
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
