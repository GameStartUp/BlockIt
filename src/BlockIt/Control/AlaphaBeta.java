package BlockIt.Control;

import java.util.ArrayList;
import java.util.List;

import BlockIt.Model.Game;
import BlockIt.Model.Node;
import BlockIt.Model.Piece;
import BlockIt.Model.Position;
import BlockIt.Model.Tree;
import BlockIt.Model.Piece.pieceColor;

public class AlaphaBeta {
	public static int MAX_DEPTH;
	
	public AlaphaBeta(int maxDepth){
		MAX_DEPTH=maxDepth;
	}
	
	public Position[][] getNextMove(){
		AlaphaBetaNode node=new AlaphaBetaNode(boardCopy(Game.board));
		alphaBeta(node, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
		for(AlaphaBetaNode child:node.childrenNode){
			if(child.getHeuristic()==node.getHeuristic()){
				return child.board;
			}
		}
		return null;
	}

	public static List<AlaphaBetaNode> generateChildren(AlaphaBetaNode node, int depth){
		List<AlaphaBetaNode> childrenNode=new ArrayList<AlaphaBetaNode>();
		pieceColor color = Game.currentPlayer.getColor();
		pieceColor expandColor = null;
		List<Piece> expandPieces = null;
		if (depth%2==0) {
			expandColor = color;
		} else {
			expandColor = Piece.getOppositeColor(color);
		}
		
		expandPieces = getExpandPiece(expandColor, node.board);
		
		for (Piece piece : expandPieces) {
			for (Move move : piece.getPossibleMove(node.board)) {
				Position[][] newBoard = boardCopy(node.board);
				Move.move(newBoard, move);
				childrenNode.add(new AlaphaBetaNode(newBoard));
			}
		}
		node.setChildrenNode(childrenNode);
		return childrenNode;
	}
	
	//CALL alphaBeta(node, 0, Integer.MIN_VALUE, Integer.MAX_VALUE)
	private static int alphaBeta(AlaphaBetaNode node, int depth, int alpha, int beta){
		List<AlaphaBetaNode> children = generateChildren(node, depth); // generates children. also rates them and applies move to copy of field. 
		
		if (children.size()==0) {
            node.setHeuristic(depth%2==0 ? Integer.MIN_VALUE : Integer.MAX_VALUE);
            return node.getHeuristic();
        } else if (depth >= MAX_DEPTH) {
        	node.setHeuristic(depth%2==1 ? -children.size() : getNumPosMoveByColor(node.board, Piece.getOppositeColor(Game.currentPlayer.getColor())));
        	return node.getHeuristic();
        }

        if (depth%2==0) { // ai tries to maximize the score
            for (AlaphaBetaNode child : children) {
                alpha = Math.max(alpha, alphaBeta(child, depth + 1, alpha, beta));

                if (beta <= alpha) {
                    break; // cutoff
                }
            }
            node.setHeuristic(alpha);
            return alpha;
        } else { // enemy tries to minimize the score
            for (AlaphaBetaNode child : children) {
                beta = Math.min(beta, alphaBeta(child, depth + 1, alpha, beta));
                if (beta <= alpha) {
                    break; // cutoff
                }
            }
            node.setHeuristic(beta);
            return beta;
        }
	}
	
	private static Position[][] boardCopy(Position[][] board) {
		Position[][] bc = new Position[Game.boardSize][Game.boardSize];
		for (int i = 0; i < Game.boardSize; i++) {
			for (int j = 0; j < Game.boardSize; j++) {
				bc[i][j] = board[i][j].copy();
			}
		}
		return bc;
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
	
	private static int getNumPosMoveByColor(Position[][] board, pieceColor color) {
		List<Piece> pieces = getExpandPiece(color, board);
		int num = 0;
		for (Piece piece : pieces) {
			num += piece.getPossibleMove(board).size();
		}
		return num;
	}
}
