package com.codingsession;

import java.awt.Point;

public class GameField {
	
	public static final int FIELD_SIZE = 11;
	
	public Field[][] fields = new Field[FIELD_SIZE][FIELD_SIZE];
	
	public Point origin;
	
	public GameField(Point origin){
		
		this.origin = origin;
		
		for(int i = 0; i < FIELD_SIZE; i++) {
			for(int o = 0; o < FIELD_SIZE; o++){
				fields[i][o] = new Field(); //  i % 2 == 0 ? FieldState.EMPTY : FieldState.WATER_HIT
			}
		}
	}
	
}
