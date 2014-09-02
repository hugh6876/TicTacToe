package edu.nyu.poly.ai.tlj.tactictoe.ui;

import edu.nyu.poly.ai.tlj.tactictoe.domain.ChessManStatus;
import edu.nyu.poly.ai.tlj.tactictoe.domain.Position;
/**
 * chessman. a game board consists of many chessman, in this game, there are 4X5=20 cells.
 * @author 
 *
 */
public class Chessman {
	//the position of chessman
	private Position position;
	public int score;
	//indicate the player of this chessman. X(computer),O(human) or EMPTY
	public ChessManStatus chessManStatus;
	
	public Chessman() {
		
	}
	public Chessman(int score){
		this.score = score;
	}
	public Chessman(Position position){
		this.position = position;
	}
	public Chessman(Position position,int score){
		this.position = position;
		this.score = score;
	}
	public Chessman(Position position,ChessManStatus status,int score){
		this.position = position;
		this.chessManStatus = status;
		this.score = score;
	}
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ChessManStatus getChessManStatus() {
		return chessManStatus;
	}

	public void setChessManStatus(ChessManStatus chessManStatus) {
		this.chessManStatus = chessManStatus;
	}

	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}

}