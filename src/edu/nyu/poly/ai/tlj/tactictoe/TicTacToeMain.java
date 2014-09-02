package edu.nyu.poly.ai.tlj.tactictoe;


import edu.nyu.poly.ai.tlj.tactictoe.business.AlphaBetaSearchStrategy;
import edu.nyu.poly.ai.tlj.tactictoe.business.HumanStrategy;
import edu.nyu.poly.ai.tlj.tactictoe.business.TicTacToeGame;
import edu.nyu.poly.ai.tlj.tactictoe.business.TicTacToePlayer;
import edu.nyu.poly.ai.tlj.tactictoe.domain.ChessManStatus;
import edu.nyu.poly.ai.tlj.tactictoe.ui.ChessBoard;
import edu.nyu.poly.ai.tlj.tactictoe.ui.TicTacToeGUI;
import edu.nyu.poly.ai.tlj.tactictoe.utils.WinChecker;



public class TicTacToeMain {
	public static void main(String[] args){
		ChessBoard chessBoard = new ChessBoard(TicTacToeGameProperties.BOARD_WIDTH_SIZE,TicTacToeGameProperties.BOARD_LENGTH_SIZE);
		WinChecker checker = new WinChecker();

		
		TicTacToeGame game = TicTacToeGame.getInstance(chessBoard, checker);

    	TicTacToePlayer computerPlayer = new TicTacToePlayer("Computer", ChessManStatus.X);
    	computerPlayer.setStrategy(new AlphaBetaSearchStrategy(game.getBoard()));
    	
    	TicTacToePlayer humanPlayer = new TicTacToePlayer("Human", ChessManStatus.O);
    	humanPlayer.setStrategy(new HumanStrategy());

		
		TicTacToeGUI gui = new TicTacToeGUI();
		gui.createGUI(game);
		gui.displayGUI();
		
		
		game.setPlayers(computerPlayer, humanPlayer);
		game.start();
	}

}
