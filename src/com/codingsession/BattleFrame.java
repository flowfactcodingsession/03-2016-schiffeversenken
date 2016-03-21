package com.codingsession;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class BattleFrame extends JFrame{

	public Ship[] ships = new Ship[5];
	
	public Ship selectedShip = null;
	
	public BattleFrame(int width, int height){
		this.setSize(width, height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		this.setVisible(true);
		
		ships[0] = new Ship(2, new Point(1200, 100));
		ships[1] = new Ship(2, new Point(1200, 200));
		ships[2] = new Ship(3, new Point(1200, 300));
		ships[3] = new Ship(4, new Point(1200, 400));
		ships[4] = new Ship(5, new Point(1200, 500));
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			public Point oldMousePosition = new Point(0, 0);
			
			@Override
			public void mouseMoved(MouseEvent e) {
				
				if(selectedShip == null)
					return;
				
				selectedShip.origin = new Point(e.getX() - ( Field.FIELD_SIZE / 2 ), e.getY() - ( Field.FIELD_SIZE / 2 ));
				
				int x = oldMousePosition.x - e.getX();
				int y = oldMousePosition.y - e.getY();
				
				Rectangle r1 = new Rectangle(oldMousePosition.x, oldMousePosition.y, selectedShip.size * Field.FIELD_SIZE, Field.FIELD_SIZE); 
				Rectangle r2 = new Rectangle(e.getX(), e.getY(), selectedShip.size * Field.FIELD_SIZE, Field.FIELD_SIZE); 
				
				r1.add(r2);
				r1.translate(-1 * r1.width, -1 * r1.height);
				repaint();//r1.x, r1.y, r1.width / 2, r1.height / 2);
				
				this.oldMousePosition = e.getPoint();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				int mouseAction = 0;
				
				if(e.getButton() == MouseAction.LEFT_MOUSE_CLICK){
					mouseAction = MouseAction.LEFT_MOUSE_CLICK;
				}else if(e.getButton() == MouseAction.RIGHT_MOUSE_CLICK){
					mouseAction = MouseAction.RIGHT_MOUSE_CLICK;
				}else{
					return;
				}
				
				for(int i = 0; i < BattleGui.gameFields.length; i++){
					Point p1 = BattleGui.gameFields[i].origin;
					
					Point p2 = new Point(p1.x, p1.y);
					p2.translate(GameField.FIELD_SIZE * Field.FIELD_SIZE, GameField.FIELD_SIZE * Field.FIELD_SIZE);
				
					if(x > p1.x && y > p1.y && x < p2.x && y < p2.y){
						int fieldX = (int) Math.ceil(x / Field.FIELD_SIZE);
						int fieldY = (int) Math.ceil(y / Field.FIELD_SIZE);
						
						if(i == 0){
							System.out.println("gegnerisches Feld");
							BattleGui.sendMouseClickToServer(fieldX, fieldY, mouseAction);
						}else{
							//if
							
							if(selectedShip != null){
								selectedShip = null;
								
								
								return;
							}
						}
						
					}
				}
				
				for (Ship ship : ships) {
					if(ship.checkMouseClick(x, y)){
						selectedShip = ship;
					}
				}
				
				
			}
		});
	}
	
	public void paint(Graphics g){
		
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		for(int f = 0; f < BattleGui.gameFields.length; f++){
			for(int i = 0; i < GameField.FIELD_SIZE; i++) {
				for(int o = 0; o <  GameField.FIELD_SIZE; o++){
					Point gameFieldOrigin = BattleGui.gameFields[f].origin;
					Point origin = new Point(i * Field.FIELD_SIZE + gameFieldOrigin.x, o * Field.FIELD_SIZE + gameFieldOrigin.y);
					BattleGui.gameFields[f].fields[i][o].paint(g, origin);
				}
			}
		}
		
		
		for(int i = 0; i < ships.length; i++){
			ships[i].paint(g);
		}
		
		
	}
	
	
	
}
