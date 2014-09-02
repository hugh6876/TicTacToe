package edu.nyu.poly.ai.tlj.tactictoe.business;

import edu.nyu.poly.ai.tlj.tactictoe.domain.ChessManStatus;
import edu.nyu.poly.ai.tlj.tactictoe.domain.Position;
import edu.nyu.poly.ai.tlj.tactictoe.ui.ChessBoard;
import edu.nyu.poly.ai.tlj.tactictoe.utils.StringUtil;
/**
 * game player
 * @author 
 *
 */
public class TicTacToePlayer {
	private final String name;
	//play strategy. in this case, there are two strategy:human and alfa beta search strategy
	private Strategy strategy;
	private int score;
	//the play status,human player or computer player
	private ChessManStatus playerStatus;
	//construct function
	public TicTacToePlayer(String name, ChessManStatus value) {
		if (value == null){
			new IllegalArgumentException("Value cannot be null.");
		}
		this.playerStatus = value;
		this.name = StringUtil.getNonNullString(name);
	}
	
	public String getName(){
		return name;
	}
	
	public ChessManStatus getValue(){
		return playerStatus;
	}

	public void setStrategy(Strategy strategy){
		this.strategy = strategy;
	}
	
	/**
	 * @param chessBoard
	 * @return true if the player moved, false otherwise
	 */
	public boolean playerMove(ChessBoard board) {
		Position position =  strategy.getNextMovePosition(TicTacToeGame.getInstance().getCurrentPlayer().playerStatus); 
		return visitBoard(board, position);
	}
	/**
	 * human player move
	 * @param board
	 * @param position
	 * @return
	 */
	public boolean playerMove(ChessBoard board, Position position){
		return visitBoard(board, position);
	}
	/**
	 * marked the cell of the board be visited with the player status
	 * @param board
	 * @param position
	 * @return true if cell marked successfully
	 */
	private boolean visitBoard(ChessBoard board, Position position) {
		boolean moved = false;
		if(position == null) {
			return moved;
		}
		else if (false == board.isCellVisited(position) ){
			board.markGrid(getValue(), position);
			moved = true;
		}
		return moved;
	}

	public void win() {
		score++;
	}
	
	public int getScore(){
		return score;
	}
	
	public String getScoreMessage() {
		String msg = "";
		if (this.playerStatus == ChessManStatus.O) {
			msg = name + " [ O ] : " + score; 
		} else if(this.playerStatus == ChessManStatus.X) {
			msg = name + " [ X ] : " + score;
		}
		return msg; 
	}

	public ChessManStatus getplayerStatus() {
		return playerStatus;
	}
	public void setPlayerStatus(ChessManStatus playerStatus) {
		this.playerStatus = playerStatus;
	}	

}
