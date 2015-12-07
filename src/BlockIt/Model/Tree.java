package BlockIt.Model;

import java.util.ArrayList;
import java.util.List;

import BlockIt.Control.Move;
import BlockIt.Model.Piece.pieceColor;

public class Tree {
	public static Node head;
	private static int maxDeep;

	public Tree(int maxDeep) {
		Tree.maxDeep = maxDeep;
		head = new Node(null, null);
	}

	public static void build(Move move1, Move move2) {
		Node parentNode=head;
		int i=0;
		while(parentNode!=null&&i<2){
			parentNode=head.getParentNode();
			i++;
		}
		if(i==2){
			parentNode.setParentNode(null);
		}
		
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
		expand(head, 0, boardCopy(Game.board));
	}

	private static void expand(Node node, int currentDeep, Position[][] board) {
		pieceColor color = Game.currentPlayer.getColor();
		pieceColor expandColor = null;
		if (currentDeep%2==0) {
			expandColor = color;
		} else {
			expandColor = Piece.getOppositeColor(color);
		}
		
		if (currentDeep < maxDeep && getNumPosMoveByColor(board, expandColor) > 0) {
			List<Piece> pieces = getExpandPiece(expandColor, board);
			// produce children node
			if (node.getChildNodes().size() == 0) {
				for (Piece piece : pieces) {
					for (Move move : piece.getPossibleMove(board)) {
						new Node(node, move);
					}
				}
			}
			// expand children node
			for (Node n : node.getChildNodes()) {
				Position[][] newBoard = boardCopy(board);
				Move.move(newBoard, n.getMove());
				expand(n, currentDeep++, newBoard);
			}
		}
	}

	public static int getNumPosMoveByColor(Position[][] board, pieceColor color) {
		List<Piece> pieces = getExpandPiece(color, board);
		int num = 0;
		for (Piece piece : pieces) {
			num += piece.getPossibleMove(board).size();
		}
		return num;
	}

	public static Position[][] boardCopy(Position[][] board) {
		Position[][] boardCopy = new Position[Game.boardSize][Game.boardSize];
		for (int i = 0; i < Game.boardSize; i++) {
			for (int j = 0; j < Game.boardSize; j++) {
				boardCopy[i][j] = board[i][j].copy();
			}
		}
		return boardCopy;
	}

	private static List<Piece> getExpandPiece(pieceColor color, Position[][] board) {
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
