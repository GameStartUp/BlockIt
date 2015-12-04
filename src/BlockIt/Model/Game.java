package BlockIt.Model;

public class Game {
	public static Position[][] board;
	public static int boardSize;
	
	public Game(int size){
		this.boardSize=size;
		board = new Position[size][size];
		for(int i=0; i<size; i++){
			for(int j=0; i<size; j++){
				board[i][j]=new Position(j, i);
			}
		}
	}
}
