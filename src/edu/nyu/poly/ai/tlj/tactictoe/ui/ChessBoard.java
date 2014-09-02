package edu.nyu.poly.ai.tlj.tactictoe.ui;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import edu.nyu.poly.ai.tlj.tactictoe.TicTacToeGameProperties;
import edu.nyu.poly.ai.tlj.tactictoe.domain.ChessManStatus;
import edu.nyu.poly.ai.tlj.tactictoe.domain.Position;
import edu.nyu.poly.ai.tlj.tactictoe.utils.StringUtil;


/**
 * the chess board of the game. a game consists of one board which consists of many cells
 * @author 
 *
 */
public class ChessBoard {
	//when the text of cell is empty,need a space to reserve space for the text.
	private static final String DEFAULT_CELL_TEXT = " ";

	private JLabel gameMessage;
	private JLabel scoreMessage;
	//store all the cell button
	private JButton[][] boardGrid;
	//store all the cell data model.to record the chessman information
	public Chessman[][] cell;
	//the previous move steps of the game
	private List<Position> moveHistory;
	
	public ChessBoard(int width,int length){
		initializeGameCells(width,length);
		gameMessage = new JLabel(DEFAULT_CELL_TEXT);
		moveHistory = new LinkedList<Position>();
		scoreMessage = new JLabel(DEFAULT_CELL_TEXT);
	}
	//initial or reset the UI board grid and the Data Model Grid cell
	private void initializeGameCells(int width,int length) {
		boardGrid = new JButton[width][length];
		cell = new Chessman[width][length];
		for (int row = 0; row < width; row++){
			for (int col = 0; col < length; col++){
				JButton button = new JButton(DEFAULT_CELL_TEXT);
				boardGrid[row][col] = button;
				this.cell[row][col] = new Chessman(new Position(-1, -1),ChessManStatus.EMPTY,Integer.MIN_VALUE);
			}
		}
	}
	
	public JLabel getScoreMessage() {
		return scoreMessage;
	}

	public void setScoreMessage(String message) {
		this.scoreMessage.setText(message);
	}

	public JLabel getMessage(){
		return gameMessage;
	}

	public void setMessage(String message){
		gameMessage.setText(message);
	}

	public boolean isEmpty() {
		return getNumberOfMove() == 0;
	}

	public boolean isFilled() {
		return getNumberOfMove() == (int)(TicTacToeGameProperties.BOARD_LENGTH_SIZE * TicTacToeGameProperties.BOARD_WIDTH_SIZE);
	}
	
	public int getNumberOfMove(){
		return moveHistory.size();
	}
	
	public List<Position> getMoveHistory(){
		return moveHistory;
	}

	public JButton getGrid(int row, int col) {
		return boardGrid[row][col];
	}

	public boolean isCellVisited(Position position){
		boolean visited = false;
		visited = this.cell[position.getRow()][position.getColumn()].chessManStatus == ChessManStatus.EMPTY?false:true;
		return visited;
	}
	/**
	 * mark the grid to be visited
	 * @param value
	 * @param position
	 */
	public void markGrid(ChessManStatus value, Position position){
		// add visited cell into history list
		moveHistory.add(position);
		JButton grid = boardGrid[position.getRow()][position.getColumn()];
		grid.setText(getPlayerStringNotation(value));
		//mark the player status on the board data cell 
		this.cell[position.getRow()][position.getColumn()].chessManStatus = value;
	}
	private String getPlayerStringNotation(ChessManStatus value) {
		String cleanValue = null;
		if (value == null){
			cleanValue = DEFAULT_CELL_TEXT;
		} else {
			if(value == ChessManStatus.X) {
				cleanValue = "X";
			}else if(value == ChessManStatus.O) {
				cleanValue = "O";
			}else {
				cleanValue = " ";
			}
		}
		return cleanValue;
	}
	/**
	 * 
	 * @param rowNum
	 * @return
	 */
	public Chessman[] getRowValues(int rowNum){
		Chessman[] row = new Chessman[TicTacToeGameProperties.BOARD_LENGTH_SIZE];
		for (int i = 0; i < TicTacToeGameProperties.BOARD_LENGTH_SIZE; i++){
			row[i] = this.cell[rowNum][i];
		}
		return row;
	}

	public Chessman[] getColumnValues(int colNum){
		Chessman[] col = new Chessman[TicTacToeGameProperties.BOARD_WIDTH_SIZE];
		for (int i = 0; i < TicTacToeGameProperties.BOARD_WIDTH_SIZE; i++){
			col[i] = this.cell[i][colNum];
		}
		return col;
	}
	public Chessman[] getLeftUpLineValues(int i) {
		Chessman[] cols = null;
		if(i==0){
			cols = new Chessman[3];
			int colPos = 0;
			int rowPos = 1;
			for(int j=0;j<3;j++){
				cols[j] = this.cell[rowPos][colPos];
				colPos++;rowPos++;
			}
			
		}else if( i==1){
			cols = new Chessman[4];
			int colPos = 0;
			int rowPos = 0;
			for(int j=0;j<4;j++){
				cols[j] = this.cell[rowPos][colPos];
				colPos++;rowPos++;
			}
		}else if( i==2){
			cols = new Chessman[4];
			int colPos = 1;
			int rowPos = 0;
			for(int j=0;j<4;j++){
				cols[j] = this.cell[rowPos][colPos];
				colPos++;rowPos++;
			}
		}else if( i==3){
			cols = new Chessman[3];
			int colPos = 2;
			int rowPos = 0;
			for(int j=0;j<3;j++){
				cols[j] = this.cell[rowPos][colPos];
				colPos++;rowPos++;
			}
	}
	return cols;
	}
	public Chessman[] getRightUpLineValues(int i) {
		Chessman[] cols = null;
		if(i==0){
			cols = new Chessman[3];
			int colPos = 0;
			int rowPos = 2;
			for(int j=0;j<3;j++){
				cols[j] = this.cell[rowPos][colPos];
				colPos++;rowPos--;
			}
			
		}else if( i==1){
			cols = new Chessman[4];
			int colPos = 0;
			int rowPos = 3;
			for(int j=0;j<4;j++){
				cols[j] = this.cell[rowPos][colPos];
				colPos++;rowPos--;
			}
		}else if( i==2){
			cols = new Chessman[4];
			int colPos = 1;
			int rowPos = 3;
			for(int j=0;j<4;j++){
				cols[j] = this.cell[rowPos][colPos];
				colPos++;rowPos--;
			}
		}else if( i==3){
			cols = new Chessman[3];
			int colPos = 2;
			int rowPos = 3;
			for(int j=0;j<3;j++){
				cols[j] = this.cell[rowPos][colPos];
				colPos++;rowPos--;
			}
	}
	return cols;
	}
	public void cleanBoard(){
		moveHistory = new LinkedList<Position>();
		setMessage(DEFAULT_CELL_TEXT);
		clearBoardGridValues();
	}
	public void cleanCells(){
		for (int row = 0; row < TicTacToeGameProperties.BOARD_WIDTH_SIZE; row++){
			for (int col = 0; col < TicTacToeGameProperties.BOARD_LENGTH_SIZE; col++){
				this.cell[row][col].chessManStatus = ChessManStatus.EMPTY;
				this.cell[row][col].score = 0;
			}
		}
		
	}
	private void clearBoardGridValues() {
		for (int col = 0; col < TicTacToeGameProperties.BOARD_LENGTH_SIZE; col++){
			for (int row = 0; row < TicTacToeGameProperties.BOARD_WIDTH_SIZE; row++){
				boardGrid[row][col].setText(DEFAULT_CELL_TEXT);
			}
		}
	}
	
	public void printBoard(PrintStream printStream){

		for (int row = 0; row < TicTacToeGameProperties.BOARD_WIDTH_SIZE; row++){
			for (int col = 0; col < TicTacToeGameProperties.BOARD_LENGTH_SIZE; col++){
				Chessman squareValue = cell[row][col];
				String value = getPlayerStringNotation(squareValue.getChessManStatus());
				printStream.print('['+ value +']');
			}
			printStream.print('\n');
		} 
	}
}