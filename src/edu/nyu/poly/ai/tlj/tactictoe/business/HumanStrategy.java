package edu.nyu.poly.ai.tlj.tactictoe.business;

import edu.nyu.poly.ai.tlj.tactictoe.domain.ChessManStatus;
import edu.nyu.poly.ai.tlj.tactictoe.domain.Position;
/**
 * human move strategy is null means human player will place chessman on his/her own thought
 * @author 
 *
 */
public class HumanStrategy implements Strategy {

	@Override
	public Position getNextMovePosition(ChessManStatus player) {
		return null;
	}

}
