package edu.nyu.poly.ai.tlj.tactictoe.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.nyu.poly.ai.tlj.tactictoe.business.TicTacToeGame;
/**
 * change player oder and start new game listener
 * @author 
 *
 */
public class ChangePlayOderListener implements ActionListener{
	private TicTacToeGame game;
	
	public ChangePlayOderListener(TicTacToeGame game){
		this.game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		//restart the game
		game.restart();
	}
}

