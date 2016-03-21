package com.flowfact.coding.controller;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Spielfeld")
public class Spielfeld {

	@XmlElement(name = "felder")
	private int felder[][] = new int[10][10];

	public Spielfeld() {
		for (int j = 0; j < 10; j++) {
			for (int i = 0; i < 10; i++) {
				felder[i][j] = Status.WASSER;
			}
		}
	}
	
	public int getFeldWert(int x, int y) {
		return felder[x][y];
	}

	public void setFeldWert(int x, int y, int status) {
		felder[x][y] = status;
	}
	
	public int[][] getFelder() {
		return felder;
	}
}
