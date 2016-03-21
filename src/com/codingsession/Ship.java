package com.codingsession;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Ship {

	public int size;

	public Point origin;
	
	public Ship(int size, Point origin){
		this.size = size;
		this.origin = origin;
	}
	
	public boolean checkMouseClick(int x, int y){
		Point p1 = origin;
		
		Point p2 = new Point(origin.x, origin.y);
		p2.translate(Field.FIELD_SIZE * size, Field.FIELD_SIZE);
	
		if(x > p1.x && y > p1.y && x < p2.x && y < p2.y){
			System.out.println(size);
			return true;
		}
		
		return false;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.CYAN);
		
		g.drawImage(BattleGui.images.get("vorne_gedreht"),origin.x, origin.y , null);
		
		for(int i = 0; i < size - 2; i++){
			g.drawImage(BattleGui.images.get("mitte_gedreht"),origin.x + ( i + 1 ) * Field.FIELD_SIZE, origin.y , null);
		}
		
		g.drawImage(BattleGui.images.get("hinten_gedreht"),origin.x + ( size - 1 ) * Field.FIELD_SIZE, origin.y , null);
		
		
	}
	
}
