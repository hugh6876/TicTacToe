package edu.nyu.poly.ai.tlj.tactictoe.utils;


import edu.nyu.poly.ai.tlj.tactictoe.TicTacToeGameProperties;
import edu.nyu.poly.ai.tlj.tactictoe.business.TicTacToeGame;
import edu.nyu.poly.ai.tlj.tactictoe.domain.ChessManStatus;
import edu.nyu.poly.ai.tlj.tactictoe.ui.ChessBoard;
import edu.nyu.poly.ai.tlj.tactictoe.ui.Chessman;


/**
 * utility class to check the win status of the game
 * @author 
 *
 */
public class WinChecker {
	
	private ChessBoard board;
	

	public WinChecker(){
	}
	public WinChecker(ChessBoard board){
		this.board = board;
	}
	public ChessBoard getBoard() {
		return board;
	}
	public void setBoard(ChessBoard board) {
		this.board = board;
	}
	/**
	 * check if there is a player who has win the game
	 * @param chessManStatus
	 * @return
	 */
	public boolean isPlayerWon(ChessManStatus chessManStatus){
		boolean win = false;
		if(winHorizontally(chessManStatus)) {
			win = true;
		} else if (winDiagonally(chessManStatus)){
			win = true;
		} else if (winVertially(chessManStatus)){
			win = true;
		}
		return win;
	}
	/**
	 * 
	 * @param playerValue
	 * @param colValues
	 * @return return true if there is four consecutive marks in a row(row line 45 and 135 degree diagonal line)
	 */
	private boolean isValuesSameAsPlayerValue(ChessManStatus playerValue, Chessman[] colValues){
		boolean win = false;
		for (int i = 0; i <= colValues.length-4; i++){
			win = true;
			for(int j = i; j < colValues.length && j<i+4; j++ ){
				if( colValues[j].getChessManStatus() != playerValue ) {
					win = false;
				}
			}
			if( win == true )
				break;
		}
		return win;
	}

	private boolean winDiagonally(ChessManStatus chessManStatus) {
		boolean win = winLeftUpDiagonal(chessManStatus);
		if (!win){
			win = winRightUpDiagonal(chessManStatus);
		}
		return win;
	}

	private boolean winRightUpDiagonal(ChessManStatus chessManStatus) {
		boolean colWin = false;
		for (int i = 0; i < 4; i++){
			Chessman[] colValues = TicTacToeGame.getInstance().getBoard().getRightUpLineValues(i);//board.getColumnValues(col);
			colWin = isValuesSameAsPlayerValue(chessManStatus, colValues);
			if (colWin){
				break;
			}
		}
		return colWin;
		
	
	}

	private boolean winLeftUpDiagonal(ChessManStatus chessManStatus) {
		boolean colWin = false;
		for (int i = 0; i < 4; i++){
			Chessman[] colValues = TicTacToeGame.getInstance().getBoard().getLeftUpLineValues(i);//board.getColumnValues(col);
			colWin = isValuesSameAsPlayerValue(chessManStatus, colValues);
			if (colWin){
				break;
			}
		}
		return colWin;
	}

	private boolean winVertially(ChessManStatus chessManStatus) {
		boolean colWin = false;
		for (int col = 0; col < TicTacToeGameProperties.BOARD_LENGTH_SIZE; col++){
			Chessman[] colValues = TicTacToeGame.getInstance().getBoard().getColumnValues(col);//board.getColumnValues(col);
			colWin = isValuesSameAsPlayerValue(chessManStatus, colValues);
			if (colWin){
				break;
			}
		}
		
		return colWin;
	}

	private boolean winHorizontally(ChessManStatus chessManStatus) {
		boolean colWin = false;
		for (int row = 0; row < TicTacToeGameProperties.BOARD_WIDTH_SIZE; row++){
			Chessman[] rowValues = TicTacToeGame.getInstance().getBoard().getRowValues(row);//board.getColumnValues(col);
			colWin = isValuesSameAsPlayerValue(chessManStatus, rowValues);
			if (colWin){
				break;
			}
		}
		
		return colWin;
	}
	

}
