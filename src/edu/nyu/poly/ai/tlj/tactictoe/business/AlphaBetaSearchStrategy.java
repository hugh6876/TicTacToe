package edu.nyu.poly.ai.tlj.tactictoe.business;

import java.util.ArrayList;
import java.util.List;

import edu.nyu.poly.ai.tlj.tactictoe.TicTacToeGameProperties;
import edu.nyu.poly.ai.tlj.tactictoe.domain.ChessManStatus;
import edu.nyu.poly.ai.tlj.tactictoe.domain.Direction;
import edu.nyu.poly.ai.tlj.tactictoe.domain.Position;
import edu.nyu.poly.ai.tlj.tactictoe.ui.ChessBoard;
import edu.nyu.poly.ai.tlj.tactictoe.ui.Chessman;
import edu.nyu.poly.ai.tlj.tactictoe.utils.WinChecker;
/**
 * AlphaBetaSearchStrategy use alfa beta search algorithm to find the next move for coumputer player
 * @author 
 *
 */
public class AlphaBetaSearchStrategy implements Strategy {
	private ChessBoard board;
	private WinChecker winChecker = new WinChecker();
	
	public AlphaBetaSearchStrategy(ChessBoard board) {
		this.board = board;
		this.winChecker.setBoard(board);
	}
	/**
	 * get the next position to move for computer player
	 */
	@Override
	public Position getNextMovePosition(ChessManStatus player) {
		// depth, max player turn, alpha, beta
		Chessman chessman = alfaBetaSearch(TicTacToeGameProperties.GAME_DIFFICULTY, player, Integer.MIN_VALUE, Integer.MAX_VALUE);
		
		return chessman.getPosition();

	}

	/**
	 * recursive to get the best next move position at level of depth for max player or min player
	 * @param depth    search depth for the next few moves for the players
	 * @param player
	 * @param alpha
	 * @param beta
	 * @return the next best position in the board to move
	 */
	private Chessman alfaBetaSearch(int depth, ChessManStatus player, int alpha, int beta) {
		// Generate possible next moves in a list of Position.
		List<Position> nextMoves = generateNextMoves();
		// Computer is maximizing; while Human is minimizing
		int score = 0;
		int bestRow = -1;
		int bestCol = -1;
		if (nextMoves.isEmpty() || depth == 0) {
			// calculate score when game is over or depth==0 according to the position situation of the board at depth 
			score = getScore(player);//this is the evaluation function
			return new Chessman(score);
		} else {
			for (Position move : nextMoves) {
				this.board.cell[move.getRow()][move.getColumn()].chessManStatus = player;
				if (player == ChessManStatus.X) { 
					score = alfaBetaSearch(depth - 1, ChessManStatus.O, alpha, beta).score;
					if (score > alpha) {
						alpha = score;
						bestRow = move.getRow();
						bestCol = move.getColumn();
					}
				} else { // O is human player, that is minimizing player
					score = alfaBetaSearch(depth - 1,ChessManStatus.X , alpha, beta).score;
					if (score < beta) {
						beta = score;
						bestRow = move.getRow();
						bestCol = move.getColumn(); 
					}
				}
				// clear the visited mark
				this.board.cell[move.getRow()][move.getColumn()].chessManStatus = ChessManStatus.EMPTY;
				// cut-off
				if (alpha >= beta)
					break;
			}
			return new Chessman(new Position(bestRow, bestCol),player==ChessManStatus.X?alpha:beta);
		}
	}

	/**
	 * Find all valid next moves. Return List of moves in int[2] of {row, col}
	 * or empty list if gameover
	 */

	private List<Position> generateNextMoves() {
		List<Position> nextMoves = new ArrayList<Position>(); // allocate List

		// If gameover, i.e., no next move
		if (this.winChecker.isPlayerWon(ChessManStatus.O) || this.winChecker.isPlayerWon(ChessManStatus.X)) {
			return nextMoves; // return empty list
		}

		// Search for empty cells and add to the List
		Chessman curChessman = null;
		for (int row = 0; row < TicTacToeGameProperties.BOARD_WIDTH_SIZE; ++row) {
			for (int col = 0; col < TicTacToeGameProperties.BOARD_LENGTH_SIZE; ++col) {
				curChessman = this.board.cell[row][col];
				if (curChessman.chessManStatus == ChessManStatus.EMPTY) {
					nextMoves.add(new Position(row,col));
				}
			}
		}
		return nextMoves;
	}


	/**
	 * evaluation function
	 * @param player
	 * @return the evaluation value
	 */
	private int getScore(ChessManStatus player) {
		int score = 0;
		Chessman curChessman;
		//visit all grid to get number of consecutive X or O in a line , and calculate the score
		for(int i=0;i<TicTacToeGameProperties.BOARD_WIDTH_SIZE;i++){
			for(int j=0;j<TicTacToeGameProperties.BOARD_LENGTH_SIZE;j++){
				curChessman = TicTacToeGame.getInstance().getBoard().cell[i][j]; 
				if(curChessman.getChessManStatus() != ChessManStatus.EMPTY ) {
					//                              depth,direction,status and current postion
					score = score + evaluationFunction(1,0,curChessman.chessManStatus,i,j);
					TicTacToeGame.isWinned = false;
				}
			}
		}

		return score;
	}
	/**
	 * The heuristic evaluation function for the current board
	 * @param depth
	 * @param direction
	 * @param player
	 * @param curRow
	 * @param curCol
	 * @return
	 */
	private int evaluationFunction(int depth,int direction,ChessManStatus player,int curRow,int curCol)  {
		int score = 0;
		//depth ==  4 means there are four consecutive X or O in one row, and add 20 to Max and minus 20 to Min
		if(depth == 4){
			TicTacToeGame.isWinned = true;
			if(player == TicTacToeGame.getInstance().getFirstPlayer().getplayerStatus()) {
				score += 20;
			}else if (player != ChessManStatus.EMPTY) {
				score += -20;
			}
			return score;
		}
		//judge if this node is alone,no other same node adjacent to it. if yes calculate and return.if not
		//recurse to the next node in the same direction
		if(depth ==1  ){
			boolean isCellAlone = true;
			Chessman curChessman = null,nextChessman = null;
			curChessman = TicTacToeGame.getInstance().getBoard().cell[curRow][curCol];
			//check each node to see if it is alone
			for(int k=0;k<Direction.EightDire.length;k++) {
				int nextRow = curRow + Direction.EightDire[k].getRow();
				int nextCol = curCol + Direction.EightDire[k].getColumn();
				
				if(   (nextRow>=0 && nextRow<TicTacToeGameProperties.BOARD_WIDTH_SIZE) && 
						(nextCol>=0 && nextCol<TicTacToeGameProperties.BOARD_LENGTH_SIZE) ){
					
					nextChessman = TicTacToeGame.getInstance().getBoard().cell[nextRow][nextCol];
					if(nextChessman.chessManStatus==curChessman.chessManStatus){
						isCellAlone = false;
					}
				}
			}
			if(isCellAlone == true) {
				if(player == TicTacToeGame.getInstance().getFirstPlayer().getplayerStatus()) {
					score += 3;
				}else if (player != ChessManStatus.EMPTY) {
					score += -3;
				}
				return score;
			}
		}
		//this node have same mark adjacent to it
		for (int k = 0; k < Direction.directions.length; k++) {
			int nextRow = curRow + Direction.directions[k].getRow();
			int nextCol = curCol + Direction.directions[k].getColumn();
			if ((nextRow >= 0 && nextRow < TicTacToeGameProperties.BOARD_WIDTH_SIZE)
					&& (nextCol >= 0 && nextCol < TicTacToeGameProperties.BOARD_LENGTH_SIZE)
					&& TicTacToeGame.getInstance().getBoard().cell[nextRow][nextCol]
							.getChessManStatus() == player)
				if (  (depth >=2 &&direction == k)   || (depth<=1) ){
					score = evaluationFunction(depth + 1, k, player, nextRow,
							nextCol);
				}

		}
		//process 2 consecutive same node
		if(depth == 2 && TicTacToeGame.isWinned == false){
			if(player == TicTacToeGame.getInstance().getFirstPlayer().getplayerStatus()) {
				score += 8;
			}else if (player != ChessManStatus.EMPTY) {
				score += -8;
			}
		}
		//process 3 consecutive same node
		if(depth == 3 && TicTacToeGame.isWinned == false ){
			if(player == TicTacToeGame.getInstance().getFirstPlayer().getplayerStatus()) {
				score += 15;
			}else if (player != ChessManStatus.EMPTY) {
				score += -15;
			}
			
		}
		return score;
	}
	
	private void printBorad() {
		for(int i=0;i<TicTacToeGameProperties.BOARD_WIDTH_SIZE;i++){
			for(int j=0;j<TicTacToeGameProperties.BOARD_LENGTH_SIZE;j++){
				if(TicTacToeGame.getInstance().getBoard().cell[i][j].getChessManStatus() == ChessManStatus.EMPTY){
					System.out.print("-");
				}else {
					System.out.print(TicTacToeGame.getInstance().getBoard().cell[i][j].getChessManStatus());
				}
				
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}
}
