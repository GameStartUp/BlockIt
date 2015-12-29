package BlockIt.Control;

import java.util.ArrayList;
import java.util.List;

import BlockIt.Model.Game;
import BlockIt.Model.Piece;
import BlockIt.Model.Position;
import BlockIt.Model.Piece.pieceColor;

public class AlaphaBeta {
	private static int MAX_DEPTH;
	private static List<Node> childrenNodes;
	
	public static void setMaxDepth(int maxDepth){
		MAX_DEPTH=maxDepth;
	}
	
	public static Move getNextMove(){
		Node node=new Node(boardCopy(Game.board));
		Node bestChild=null;
		alphaBeta(node, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
		int fromX=0, fromY=0, toX=0, toY=0;
		for(Node child: childrenNodes){
			if(child.heuristic==node.heuristic)
				bestChild=child;
		}
		for(int y=0; y<Game.boardSize; y++){
			for(int x=0; x<Game.boardSize; x++){
				if(node.board[y][x].getPiece()!=null 
						&& bestChild.board[y][x].getPiece()==null){
					fromX=x;
					fromY=y;
				}else if(node.board[y][x].getPiece()==null 
						&& bestChild.board[y][x].getPiece()!=null){
					toX=x;
					toY=y;
				}
			}
		}
		return new Move(fromX, fromY, toX, toY);
	}

	private static List<Node> generateChildren(Node node, int depth){
		List<Node> childrenNode=new ArrayList<Node>();
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
				childrenNode.add(new Node(newBoard));
			}
		}
		return childrenNode;
	}
	
	//CALL alphaBeta(node, 0, Integer.MIN_VALUE, Integer.MAX_VALUE)
	private static int alphaBeta(Node node, int depth, int alpha, int beta){
		List<Node> children = generateChildren(node, depth); // generates children. also rates them and applies move to copy of field. 
		if(depth==0)
			childrenNodes=children;
		
		if (children.size()==0) {
            node.setHeuristic(depth%2==0 ? Integer.MIN_VALUE : Integer.MAX_VALUE);
            return node.getHeuristic();
        } else if (depth >= MAX_DEPTH) {
        	node.setHeuristic(depth%2==1 ? -children.size() : -getNumPosMoveByColor(node.board, Piece.getOppositeColor(Game.currentPlayer.getColor())));
        	return node.getHeuristic();
        }

        if (depth%2==0) { // ai tries to maximize the score
            for (Node child : children) {
                alpha = Math.max(alpha, alphaBeta(child, depth + 1, alpha, beta));

                if (beta <= alpha) {
                    break; // cutoff
                }
            }
            node.setHeuristic(alpha);
            return alpha;
        } else { // enemy tries to minimize the score
            for (Node child : children) {
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
	
	public static int getNumPosMoveByColor(Position[][] board, pieceColor color) {
		List<Piece> pieces = getExpandPiece(color, board);
		int num = 0;
		for (Piece piece : pieces) {
			num += piece.getPossibleMove(board).size();
		}
		return num;
	}
}
