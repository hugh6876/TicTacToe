package edu.nyu.poly.ai.tlj.tactictoe.listener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.nyu.poly.ai.tlj.tactictoe.TicTacToeGameProperties;


/**
 * game difficulty change listener
 * @author 
 *
 */
public class GameDifficultyChangeListener implements ChangeListener{

	@Override
	public void stateChanged(ChangeEvent e) {
		  JSlider source = (JSlider)e.getSource();
		    if (!source.getValueIsAdjusting()) {
		        int difficuty = (int)source.getValue();
		        TicTacToeGameProperties.GAME_DIFFICULTY = difficuty;
		    }
	}
}
