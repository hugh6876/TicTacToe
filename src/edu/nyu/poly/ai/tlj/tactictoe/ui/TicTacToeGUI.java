package edu.nyu.poly.ai.tlj.tactictoe.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import edu.nyu.poly.ai.tlj.tactictoe.TicTacToeGameProperties;
import edu.nyu.poly.ai.tlj.tactictoe.business.TicTacToeGame;
import edu.nyu.poly.ai.tlj.tactictoe.listener.ChangePlayOderListener;
import edu.nyu.poly.ai.tlj.tactictoe.listener.ChessmanListener;
import edu.nyu.poly.ai.tlj.tactictoe.listener.GameDifficultyChangeListener;
/**
 * GUI class, to create the graphic user interface of TicTacToe game
 * @author lijun
 *
 */
public class TicTacToeGUI {
	//the size of the outter frame
	private final static int FRAME_SIZE_LEN = 450;
	private final static int FRAME_SIZE_WIDTH = 650;

	private final static String FONT = "Arial";
	//the color of the oueer frame
	private final static Color BLUE = new Color(0, 172, 193);
	private final static Color BROWN = new Color(120, 100, 100);
	private final static Color ORANGE = new Color(222, 80, 4);

	private JFrame frame;

	public void displayGUI() {
		frame.setVisible(true);
	}
	/** 
	 * start to create the UI of game
	 * @param game
	 */
	public void createGUI(TicTacToeGame game) {
		frame = createFrame();
		Container content = frame.getContentPane();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		content.add(getTitle());
		content.add(getGameMessage(game.getBoard()));
		content.add(getGridPanel(game));
		content.add(getDifficultyMessagePanel());
		content.add(getDifficultySlider());
		content.add(getScorePanel(game.getBoard()));
		content.add(getNewGamePanel(game));
		frame.setVisible(true);
	}
	/**
	 * create the start new game and change play oder button
	 * @param game
	 * @return
	 */
	private Component getNewGamePanel(TicTacToeGame game) {
		JButton button = new JButton("Changer Player Oder");
		button.addActionListener(new ChangePlayOderListener(game));
		button.setBackground(BROWN);
		setCommonComponentProperty(button, 30, Color.WHITE, Font.BOLD);
		return button;
	}
	private Component getDifficultyMessagePanel() {
		JLabel message = new JLabel("Difficulty:");//.getScoreMessage();
		message.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
				Color.gray));
		setCommonComponentProperty(message, 20, ORANGE, Font.PLAIN);
		return message;
	}
	private JFrame createFrame() {
		JFrame frame = new JFrame(TicTacToeGameProperties.GAME_NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_SIZE_LEN, FRAME_SIZE_WIDTH);
		return frame;
	}

	private Component getTitle() {
		JLabel title = new JLabel(TicTacToeGameProperties.GAME_NAME);
		title.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BLUE));
		setCommonComponentProperty(title, 50, BROWN, Font.BOLD);
		return title;
	}

	private Component getGameMessage(ChessBoard board) {
		JLabel gameMessage = board.getMessage();
		gameMessage.setBorder(BorderFactory.createEmptyBorder());
		setCommonComponentProperty(gameMessage, 20, ORANGE, Font.PLAIN);
		return gameMessage;
	}

	private JPanel getGridPanel(TicTacToeGame game) {
		ChessBoard board = game.getBoard();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(TicTacToeGameProperties.BOARD_WIDTH_SIZE,
				TicTacToeGameProperties.BOARD_LENGTH_SIZE));
		for (int row = 0; row < TicTacToeGameProperties.BOARD_WIDTH_SIZE; row++) {
			for (int col = 0; col < TicTacToeGameProperties.BOARD_LENGTH_SIZE; col++) {
				JButton square = board.getGrid(row, col);
				square.addActionListener(new ChessmanListener(game));
				square.setContentAreaFilled(false);
				square.setBorder(BorderFactory.createEtchedBorder());
				setCommonComponentProperty(square, 50, BLUE, Font.BOLD);

				panel.add(square);
			}
		}
		return panel;
	}
	private Component getDifficultySlider() {
		JSlider gameDifficulty = new JSlider(JSlider.HORIZONTAL,
                1, 6, 2);

		//Turn on labels at major tick marks.
		gameDifficulty.setMajorTickSpacing(1);
		gameDifficulty.setMinorTickSpacing(1);
		gameDifficulty.setPaintTicks(true);
		gameDifficulty.setPaintLabels(true);
	
//		Hashtable labelTable = new Hashtable();
//		labelTable.put( new Integer( 0 ), new JLabel("Difficulty:") );
//		gameDifficulty.setLabelTable(labelTable);

		Font font = new Font("Serif", Font.ITALIC, 15);
		gameDifficulty.setFont(font);
		
		gameDifficulty.addChangeListener(new GameDifficultyChangeListener());
		return gameDifficulty;
	}
	private Component getScorePanel(ChessBoard board) {
		JLabel message = board.getScoreMessage();
		message.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
				Color.gray));
		setCommonComponentProperty(message, 20, ORANGE, Font.PLAIN);
		return message;
	}
	

	private void setCommonComponentProperty(JComponent component, int fontSize,
			Color fontColor, int fontWeight) {
		component.setFont(new Font(FONT, Font.PLAIN, fontSize));
		component.setForeground(fontColor);
		component.setAlignmentX(Component.CENTER_ALIGNMENT);
		component.setFocusable(false);
	}

}
