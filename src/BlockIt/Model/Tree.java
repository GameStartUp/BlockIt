package BlockIt.Model;

import java.util.ArrayList;
import java.util.List;

import BlockIt.Control.Move;
import BlockIt.Model.Piece.pieceColor;

public class Tree {
	public static Node head;
	private static int maxDeep;
	public static Position[][] boardCopy;
	private static List<Piece> whitePieces;
	private static List<Piece> blackPieces;

	public Tree(int maxDeep) {
		Tree.maxDeep = maxDeep;
		head = new Node(null, null);
		boardCopy = boardCopy(Game.board);
		whitePieces=getExpandPiece(pieceColor.WHITE, boardCopy);
		blackPieces=getExpandPiece(pieceColor.BLACK, boardCopy);
		
		expand(head, 0);
	}

	public static void build(Move move1, Move move2) {
		//clean 3 level ahead parent data
		Node parentNode=head;
		int i=0;
		while(parentNode!=null&&i<2){
			parentNode=head.getParentNode();
			i++;
		}
		if(i==2){
			parentNode.setParentNode(null);
		}
		
		//Go current board node
		for (Node n : head.getChildNodes()) {
			if (move1.equals(n.getMove())) {
				head = n;
				break;
			}
		}
		for (Node n : head.getChildNodes()) {
			if (move2.equals(n.getMove())) {
				head = n;
				break;
			}
		}
		
		//move copy board to current board status;
		Move.move(boardCopy, move1);
		Move.move(boardCopy, move2);
		
		expand(head, 0);
	}

	private static void expand(Node node, int currentDeep) {
		node.setHeuristic(null);
		pieceColor color = Game.currentPlayer.getColor();
		pieceColor expandColor = null;
		List<Piece> expandPieces = null;
		if (currentDeep%2==0) {
			expandColor = color;
		} else {
			expandColor = Piece.getOppositeColor(color);
		}
		
		if(expandColor==pieceColor.WHITE){
			expandPieces = whitePieces;
		}else{
			expandPieces = blackPieces;
		}
		
		int nextDeep=currentDeep+1;
		if (currentDeep < maxDeep) {
			if (node.getChildNodes().size() == 0) {
				// produce children node
				for (Piece piece : expandPieces) {
					for (Move move : piece.getPossibleMove(boardCopy)) {
						//System.out.println("Deep: "+currentDeep);
						Move.move(boardCopy, move);
						expand(new Node(node, move), nextDeep);
						//System.out.println("Deep: "+currentDeep);
						Move.dismove(boardCopy, move);
					}
				}
			} else {
				// expand children node
				for (Node n : node.getChildNodes()) {
					Move.move(boardCopy, n.getMove());
					expand(n, nextDeep);
					Move.dismove(boardCopy, n.getMove());
				}
			}
		}
	}

	public static Position[][] boardCopy(Position[][] board) {
		Position[][] bc = new Position[Game.boardSize][Game.boardSize];
		for (int i = 0; i < Game.boardSize; i++) {
			for (int j = 0; j < Game.boardSize; j++) {
				bc[i][j] = board[i][j].copy();
			}
		}
		return bc;
	}

	public static List<Piece> getExpandPiece(pieceColor color, Position[][] board) {
		List<Piece> pieces = new ArrayList<Piece>();
		for (int i = 0; i < Game.boardSize; i++) {
			for (int j = 0; j < Game.boardSize; j++) {
				Position p = board[i][j];
				if (p.getPiece() != null && p.getPiece().getColor() == color) {
					pieces.add(p.getPiece());
				}
			}
		}
		return pieces;
	}
}
