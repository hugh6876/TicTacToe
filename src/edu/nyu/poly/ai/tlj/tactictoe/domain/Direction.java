package edu.nyu.poly.ai.tlj.tactictoe.domain;
/**
 * move direction of this game 
 * @author 
 *
 */
public class Direction {
	//four direction used to find the consecutive same chessman in a line
	//to avoid counting repeatly, only calculate four directions:up,upright,right,downright
	public static Position[] directions = new Position[]{new Position(-1, 0),new Position(-1, 1),
	new Position(0, 1),new Position(1, 1)};
	
	
	//eight direction used to judge if a chessman is alone,in other words,on consecutive chessman
	//new Position(0, -1),new Position(-1, -1),left,leftUp,up
	public static Position[] EightDire = new Position[]{new Position(-1, 0),new Position(-1, 1),
	new Position(0, 1),new Position(1, 1),new Position(1,0),new Position(1,-1),new Position(0,-1),new Position(-1, -1)};
}
