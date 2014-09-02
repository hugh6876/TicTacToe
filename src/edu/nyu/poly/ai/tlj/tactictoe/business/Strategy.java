package edu.nyu.poly.ai.tlj.tactictoe.business;

import edu.nyu.poly.ai.tlj.tactictoe.domain.ChessManStatus;
import edu.nyu.poly.ai.tlj.tactictoe.domain.Position;
/**
 * the interface of the game
 * @author 
 *
 */
public interface Strategy {
	public Position getNextMovePosition(ChessManStatus player);
}
