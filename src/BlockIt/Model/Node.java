package BlockIt.Model;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private Node parentNode;
	private List<Node> childNodes;
	private Position from, to;
	
	public Node(Node parentNode, Position from, Position to) {
		this.parentNode = parentNode;
		this.from = from;
		this.to = to;
		this.childNodes = new ArrayList<Node>();
		parentNode.addChild(this);
	}
	
	public void addChild(Node node){
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
	 * @return the from
	 */
	public Position getFrom() {
		return from;
	}

	/**
	 * @return the to
	 */
	public Position getTo() {
		return to;
	}
}
