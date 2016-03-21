package com.flowfact.coding.controller;

public class Spieler 
{
	String spielername;
	public int verbleibendeSchiffe;
	static final int MAX_SCHIFFE = 8;
	public int besiegteSchiffe;

	public int currentProgress() 
	{
		verbleibendeSchiffe = MAX_SCHIFFE - besiegteSchiffe;
	return verbleibendeSchiffe;
	
	}
	
	public String getName()
	{
		return spielername;
	}
	
	public void setName(String spielername)
	{
		this.spielername = spielername;
	}
	
	public boolean checkforGameOver()
	{ 
		if (verbleibendeSchiffe <= 0) {
			System.out.println("Game over!");
			return true;
		}
		return false;
	}
}
