package com.flowfact.coding.controller;


public class Spiel {

	Spielfeld feld1 = new Spielfeld();
	Spielfeld feld2 = new Spielfeld();

	public Spiel() {
		feld2.setFeldWert(0, 0, Status.MARKIERT);
	}

	public Spielfeld getField(int index) {

		switch (index) {
		case 1:
			return feld1;
		case 2:
			return feld2;
		default:
			return null;
		}

	}
	//
	/*
	 * Invalide Züge: schon mal getroffen, Wert out of border, kein Wert(null)
	 * Schiffe nebeneinander
	 * 
	 */

}
