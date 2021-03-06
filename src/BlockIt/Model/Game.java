package BlockIt.Model;

import java.util.ArrayList;
import java.util.List;

import BlockIt.Control.AlaphaBeta;
import BlockIt.Control.Move;
import BlockIt.Model.Piece.pieceColor;

public class Game {
	public static Position[][] board;
	public static int boardSize;
	public static List<Player> players;
	public static Player currentPlayer;
	public static List<Move> record;

	public Game(int size, List<Piece> pieces) {
		Game.players= new ArrayList<Player>();
		Game.players.add(new Player(pieceColor.WHITE));
		Game.players.add(new Player(pieceColor.BLACK));
		Game.boardSize = size;
		Game.record=new ArrayList<Move>();
		board = new Position[size][size];
		for (Piece piece:pieces){
			int x = piece.getPosition().x;
			int y = piece.getPosition().y;
			board[y][x]=piece.getPosition();
		}
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				if(board[y][x]==null)
					board[y][x] = new Position(x, y);
			}
		}
		currentPlayer = players.get(0);
	}

	public static void nextPlayer() {
		int i = players.indexOf(currentPlayer) + 1;
		currentPlayer = players.get(i % players.size());
	}
	
	public static boolean gameover(){
		return AlaphaBeta.getNumPosMoveByColor(board, currentPlayer.getColor())==0;
	}
}
