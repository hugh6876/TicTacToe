package edu.nyu.poly.ai.tlj.tactictoe.domain;
/**
 * to record the position of the chessman
 * @author 
 *
 */
public class Position {
	//row position of chessman in the game board
	private final int row;
	//col position of chessman in the game board
	private final int column;
	
	public Position(int row, int column) {
//		super();
//		if (row < 0 || column < 0){
//			throw new IllegalArgumentException("Position cannot be less than 0");
//		}
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}

	
	
	public String toString(){
		return "[" + row + ',' + column + ']';
	}
}
