package com.codingsession;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class BattleGui {

	private static BattleFrame window;

	public static GameField[] gameFields = new GameField[2];

	public static HashMap<String, Image> images = new LinkedHashMap<>();

	public static void main(String[] args) {
		ServerCommunicator.getOwnField();
		
		try {
			images.put("vorne", ImageIO.read(new File("res/Battleship_vorne.png")).getScaledInstance(Field.FIELD_SIZE, Field.FIELD_SIZE, Image.SCALE_SMOOTH));
			images.put("vorne_gedreht", ImageIO.read(new File("res/Battleship_vorne_gedreht.png")).getScaledInstance(Field.FIELD_SIZE, Field.FIELD_SIZE, Image.SCALE_SMOOTH));
			images.put("mitte", ImageIO.read(new File("res/Battleship_mitte.png")).getScaledInstance(Field.FIELD_SIZE, Field.FIELD_SIZE, Image.SCALE_SMOOTH));
			images.put("mitte_gedreht", ImageIO.read(new File("res/Battleship_mitte_gedreht.png")).getScaledInstance(Field.FIELD_SIZE, Field.FIELD_SIZE, Image.SCALE_SMOOTH));
			images.put("hinten", ImageIO.read(new File("res/Battleship_hinten.png")).getScaledInstance(Field.FIELD_SIZE, Field.FIELD_SIZE, Image.SCALE_SMOOTH));
			images.put("hinten_gedreht", ImageIO.read(new File("res/Battleship_hinten_gedreht.png")).getScaledInstance(Field.FIELD_SIZE, Field.FIELD_SIZE, Image.SCALE_SMOOTH));
			images.put("hit", ImageIO.read(new File("res/Battleship_hit.png")).getScaledInstance(Field.FIELD_SIZE, Field.FIELD_SIZE, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Create GameField for player one
		gameFields[0] = new GameField(new Point(35, 50));

		// Create GameField for player two
		gameFields[1] = new GameField(new Point(600, 50));

		window = new BattleFrame(1600, 800);

	}

	public static void sendMouseClickToServer(int fieldX, int fieldY, int mouseAction) {
		System.out.println("X:" + fieldX + " Y:" + fieldY + " - MouseAction: " + mouseAction);

	}

	private static void updateFields() {

	}

}
