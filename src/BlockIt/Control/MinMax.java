package BlockIt.Control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import BlockIt.Model.Game;
import BlockIt.Model.Node;
import BlockIt.Model.Piece.pieceColor;
import BlockIt.Model.Position;
import BlockIt.Model.Tree;

public class MinMax {
	public static Move bestMove(){
		return minMaxChild(Tree.head, 0).node.getMove();
	}
	
	private static NodeAssist minMaxChild(Node node, int deep){
		if(node.getChildNodes().size()!=0){
			NodeAssist childNode=null;
			List<NodeAssist> naList=new ArrayList<NodeAssist>();
			
			for(Node n:node.getChildNodes()){
				naList.add(minMaxChild(n, deep++));
			}
			Collections.sort(naList);
			
			if(deep%2==0){
				childNode=naList.get(naList.size()-1);
			}else{
				childNode=naList.get(0);
			}
			childNode.setNode(node);
			return childNode;
		}else{
			List<Move> moves=new ArrayList<Move>();
			Node currentNode=node;
			while(currentNode.getParentNode()!=null){
				moves.add(currentNode.getMove());
				currentNode=currentNode.getParentNode();
			}
			Position[][] boardCopy=Tree.boardCopy(Game.board);
			while(moves.size()>0)
				Move.move(boardCopy, moves.remove(moves.size()-1));
			
			int heuristic=Tree.getNumPosMoveByColor(boardCopy, pieceColor.WHITE)
					+Tree.getNumPosMoveByColor(boardCopy, pieceColor.BLACK);
			return new NodeAssist(node, heuristic);
		}
	}
	
	private static class NodeAssist implements Comparable<NodeAssist>{
		Node node;
		int heuristic;
		
		private NodeAssist(Node node, int heuristic) {
			this.node = node;
			this.heuristic = heuristic;
		}

		@Override
		public int compareTo(NodeAssist o) {
			return this.heuristic-o.heuristic;
		}

		/**
		 * @param node the node to set
		 */
		public void setNode(Node node) {
			this.node = node;
		}
	}
}
