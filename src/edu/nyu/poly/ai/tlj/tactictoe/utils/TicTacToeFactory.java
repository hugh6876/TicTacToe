package edu.nyu.poly.ai.tlj.tactictoe.utils;

import edu.nyu.poly.ai.tlj.tactictoe.business.TicTacToePlayer;
import edu.nyu.poly.ai.tlj.tactictoe.domain.ChessManStatus;
/**
 * to generate different player, for now there are only two:computer and one human.
 * this can be extended to multiple players
 */
public class TicTacToeFactory {

	public static TicTacToePlayer getComputerPlayer(ChessManStatus value) {
		TicTacToePlayer player = new TicTacToePlayer("Computer", value);
		return player;
	}

	public static TicTacToePlayer getHumanPlayer(String name, ChessManStatus value) {
		TicTacToePlayer player = new TicTacToePlayer(name, value);
		return player;
	}
}
