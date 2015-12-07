package BlockIt.Model;

import java.util.ArrayList;
import java.util.List;

import BlockIt.Control.Move;

public class Node {
	private Node parentNode;
	private List<Node> childNodes;
	private Move move;

	public Node(Node parentNode, Move move) {
		this.parentNode = parentNode;
		this.setMove(move);
		this.childNodes = new ArrayList<Node>();
		parentNode.addChild(this);
	}

	private void addChild(Node node) {
		childNodes.add(node);
	}

	/**
	 * @return the parentNode
	 */
	public Node getParentNode() {
		return parentNode;
	}

	/**
	 * @return the childNodes
	 */
	public List<Node> getChildNodes() {
		return childNodes;
	}

	/**
	 * @return the move
	 */
	public Move getMove() {
		return move;
	}

	/**
	 * @param move
	 *            the move to set
	 */
	public void setMove(Move move) {
		this.move = move;
	}

	/**
	 * @param parentNode the parentNode to set
	 */
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}
}
