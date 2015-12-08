package BlockIt.Control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import BlockIt.Model.Game;
import BlockIt.Model.Node;
import BlockIt.Model.Piece;
import BlockIt.Model.Piece.pieceColor;
import BlockIt.Model.Position;
import BlockIt.Model.Tree;

public class MinMax {
	public static Move bestMove(){
		NodeAssist na=minMaxChild(Tree.head, 0);
		return na.node.get(na.node.size()-2).getMove();
	}
	
	private static NodeAssist minMaxChild(Node node, int deep){
		NodeAssist nodeAssist=null;
		if(node.getChildNodes().size()!=0){
			List<NodeAssist> naList=new ArrayList<NodeAssist>();
			int nextDeep=deep+1;
			for(Node n:node.getChildNodes()){
				naList.add(minMaxChild(n, nextDeep));
			}
			Collections.sort(naList);
			
			if(deep%2==0){
				nodeAssist=naList.get(naList.size()-1);
			}else{
				nodeAssist=naList.get(0);
			}
			nodeAssist.addNode(node);
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
			int numMoveSelf=getNumPosMoveByColor(boardCopy, Game.currentPlayer.getColor());
			int numMoveEnemy=getNumPosMoveByColor(boardCopy, Piece.getOppositeColor(Game.currentPlayer.getColor()));
			int heuristic=0;
			if(numMoveSelf==0 && deep%2==0){
				heuristic=Integer.MIN_VALUE;
			}else if(numMoveEnemy==0 && deep%2==1){
				heuristic=Integer.MAX_VALUE;
			}else if(numMoveSelf==0){
				heuristic=Integer.MIN_VALUE/2;
			}else if(numMoveEnemy==0){
				heuristic=Integer.MAX_VALUE/2;
			}else{
				heuristic=numMoveSelf-numMoveEnemy;
			}
			return new NodeAssist(node, heuristic);
		}
		return nodeAssist;
	}
	
	public static int getNumPosMoveByColor(Position[][] board, pieceColor color) {
		List<Piece> pieces = Tree.getExpandPiece(color, board);
		int num = 0;
		for (Piece piece : pieces) {
			num += piece.getPossibleMove(board).size();
		}
		return num;
	}
	
	private static class NodeAssist implements Comparable<NodeAssist>{
		List<Node> node;
		int heuristic;
		
		private NodeAssist(Node node, int heuristic) {
			this.node = new ArrayList<Node>();
			this.node.add(node);
			this.heuristic = heuristic;
		}

		@Override
		public int compareTo(NodeAssist o) {
			return this.heuristic-o.heuristic;
		}

		/**
		 * @param node the node to set
		 */
		public void addNode(Node n) {
			node.add(n);
		}
	}
}
