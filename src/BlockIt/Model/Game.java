package BlockIt.Model;

import java.util.ArrayList;
import java.util.List;

public class Game {
	public static Position[][] board;
	public static int boardSize;
	public static List<Player> players;
	public static Player currentPlayer;

	public Game(int size) {
		this.boardSize = size;
		board = new Position[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; i < size; j++) {
				board[i][j] = new Position(j, i);
			}
		}
		players = new ArrayList<Player>();
	}

	public void nextPlayer() {
		int i = players.indexOf(currentPlayer) + 1;
		currentPlayer = players.get(i % players.size());
	}
}
