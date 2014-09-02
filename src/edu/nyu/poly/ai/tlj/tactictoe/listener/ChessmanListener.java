package edu.nyu.poly.ai.tlj.tactictoe.listener;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import edu.nyu.poly.ai.tlj.tactictoe.TicTacToeGameProperties;
import edu.nyu.poly.ai.tlj.tactictoe.business.TicTacToeGame;
import edu.nyu.poly.ai.tlj.tactictoe.business.TicTacToePlayer;
import edu.nyu.poly.ai.tlj.tactictoe.domain.Position;
import edu.nyu.poly.ai.tlj.tactictoe.utils.StringUtil;


/**
 * cheesman listener. procees the click event on each board grid.
 * @author 
 *
 */
public class ChessmanListener implements ActionListener{
	
private TicTacToeGame game;
	
	public ChessmanListener(TicTacToeGame game){
		this.game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton button = (JButton) event.getSource();
		if (isNeedToUpdateBoard(button)){
			Position position = getGridPosition(button);
			TicTacToePlayer currentPlayer = game.getCurrentPlayer();
			currentPlayer.playerMove(game.getBoard(), position);
			game.processGridClicked();
		}
	}
	/**
	 * get the position according to the button clicked
	 * @param button   the button clicked by human player
	 * @return
	 */
	private Position getGridPosition(JButton button) {
		Point location = button.getLocation();
		int row = getRowPositionOnBoard(location.getY(), button.getHeight());
		int col = getColPositionOnBoard(location.getX(), button.getWidth());
		Position position = new Position(row, col);
		return position;
	}

	private int getRowPositionOnBoard(double position, int buttonSize) {
		return (int) ((position/buttonSize)%TicTacToeGameProperties.BOARD_WIDTH_SIZE);
	}
	
	private int getColPositionOnBoard(double position, int buttonSize) {
		return (int) ((position/buttonSize)%TicTacToeGameProperties.BOARD_LENGTH_SIZE);
	}

	/**
	 * judge if the grid need to be updated
	 * @param source
	 * @return
	 */
	private boolean isNeedToUpdateBoard(JButton source) {
		return game.isGameInProgress() && isGridEmpty(source);
	}

	private boolean isGridEmpty(JButton source) {
		return StringUtil.isBlank(source.getText());
	}

}
