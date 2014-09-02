package edu.nyu.poly.ai.tlj.tactictoe.business;
import edu.nyu.poly.ai.tlj.tactictoe.ui.ChessBoard;
import edu.nyu.poly.ai.tlj.tactictoe.utils.WinChecker;
/**
 * Global Game Class, Only one instance in one game,so use singleton design pattern to implement this.
 * Game Class consists of on game board class, WinChecker class to check if winner genereated, four TicTacToePlayer class instance,player1
 * player2 current player and the first player
 * @author 
 *
 */
public class TicTacToeGame {
	public static final String IT_S_A_TIE_MSG = "This game is a tie!";
	private TicTacToePlayer player1;
	private TicTacToePlayer player2;
	private TicTacToePlayer currentPlayer;
	private TicTacToePlayer firstPlayer;
	private ChessBoard board;
	private WinChecker winChecker;
	//record if game is in poogress
	private boolean gameInProgress;
	//record if a winner be generated
	public static boolean isWinned = false;
	//singleton game instance
	private static TicTacToeGame instance;
	private TicTacToeGame(){};
	public static TicTacToeGame getInstance(ChessBoard board, WinChecker checker) {
		if(TicTacToeGame.instance == null){
			instance = new TicTacToeGame();
			instance.board = board;
			instance.winChecker = checker;
		}
		return TicTacToeGame.instance;
	}
	public static TicTacToeGame getInstance() {
		return TicTacToeGame.instance;
	}

	public WinChecker getWinChecker() {
		return winChecker;
	}

	public void setWinChecker(WinChecker winChecker) {
		this.winChecker = winChecker;
	}
	public TicTacToePlayer getFirstPlayer() {
		return this.firstPlayer;
	}
	public void setPlayers(TicTacToePlayer... player) {
		if (player == null || player.length < 2){
			throw new IllegalArgumentException("Must have more than one player.");
		}
		
		player1 = player[0];
		player2 = player[1];

		currentPlayer = player1;
	}
	//restart the game
	public void restart() {
		//clean UI board and the Data Model board
		board.cleanBoard();
		board.cleanCells();
		if (isGameInProgress()){
			currentPlayer = firstPlayer;
		}
		//switch player,take turn to place the chessman on the game board
		changeCurrentPlayer();
		//start the game
		start();
	}
	
	public void start(){
		//set current player to be the first player
		firstPlayer = currentPlayer;
		gameInProgress = true;
		//reset game scores
		updateScores();
		
		newPlayerMove();
	}

	public void newPlayerMove() {
		while (isGameInProgress() && currentPlayer.playerMove(board)){
			processGridClicked();
		}
	}
	
	public ChessBoard getBoard() {
		return this.board;
	}

	public TicTacToePlayer getCurrentPlayer() {
		return currentPlayer;
	}

	public void processGridClicked() {
		if (hasPlayerWon()){
			endGame(currentPlayer.getName() + " won!");
			currentPlayer.win();
			updateScores();
		} else if (isATie()){
			endGame(IT_S_A_TIE_MSG);
		} else {
			changeCurrentPlayer();
			newPlayerMove();
		}
	}

	private void updateScores() {
		board.setScoreMessage(player1.getScoreMessage() + "  |  " + player2.getScoreMessage());
	}

	protected boolean isATie() {
		return board.isFilled();
	}

	private void endGame(String message) {
		board.setMessage(message);
		gameInProgress = false;
	}
	
	public boolean isGameInProgress(){
		return gameInProgress;
	}

	private void changeCurrentPlayer() {
		if (currentPlayer.getplayerStatus() == player1.getplayerStatus()){
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
		
	}

	private boolean hasPlayerWon() {
		
		return this.winChecker.isPlayerWon(currentPlayer.getplayerStatus());
		
	}
}