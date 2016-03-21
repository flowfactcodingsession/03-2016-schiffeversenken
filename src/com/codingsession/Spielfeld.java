package com.codingsession;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Spielfeld")
public class Spielfeld {

	private int felder[][] = new int[10][10];
	
	public Spielfeld(){
		for(int i = 0; i < 10; i++){
			for(int o = 0; o < 10; o++){
				felder[i][o] = FieldState.EMPTY;
			}
		}
	}
	
	public int getFeldWert(int x, int y){
		return felder[x][y];
	}
	
	public void setFeldWert(int x, int y, int status){
		felder[x][y] = status;
	}
	
	public int[][] getFelder(){
		return felder;
	}
	
}
