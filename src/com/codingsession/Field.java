package com.codingsession;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Field {
	
	public static int FIELD_SIZE = 50;

	public int fieldState;
	
	public Field(){
		this(FieldState.EMPTY);
	}
	
	public Field(int state){
		this.fieldState = state;
	}
	
	public void paint(Graphics g, Point origin){
		
		
		switch(fieldState){
			case FieldState.EMPTY:
				g.setColor(Color.WHITE);
				g.fillRect(origin.x, origin.y, FIELD_SIZE, FIELD_SIZE);
				break;
			case FieldState.MARKED:
				g.setColor(Color.YELLOW);
				g.fillRect(origin.x, origin.y, FIELD_SIZE, FIELD_SIZE);
				break;
			case FieldState.SHIP:
				g.setColor(Color.GRAY);
				g.fillRect(origin.x, origin.y, FIELD_SIZE, FIELD_SIZE);
				break;
			case FieldState.SHIP_HIT:
				g.setColor(Color.RED);
				g.fillRect(origin.x, origin.y, FIELD_SIZE, FIELD_SIZE);
				break;
			case FieldState.WATER_HIT:
				g.setColor(Color.BLUE);
				g.fillRect(origin.x, origin.y, FIELD_SIZE, FIELD_SIZE);
				break;
			case FieldState.LABELED:
				g.drawString("A", origin.x + FIELD_SIZE / 2, origin.y + FIELD_SIZE / 2);
				break;
		}
		
		g.setColor(Color.BLACK);
		g.drawRect(origin.x, origin.y, FIELD_SIZE, FIELD_SIZE);
		
		
	}
	
}
