package com.flowfact.coding.controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class schiffeVersenkenGUI {

	//Werden erstellt
	private JFrame frame;
	private JTextField spieler1TextField;
	private JTextField spieler2TextField;
	private JTable gegnerTable;
	private JTable spielerTable;
	private JLabel lblMeinSchlachtfeld;
	private JLabel lblGegnerFeld;
	
	
	//Spieler werden erstellt
	Spieler spieler1 = new Spieler();
	Spieler spieler2 = new Spieler();
	
	//Schiffe werden erstellt
	Schiff[] schiff = new Schiff[8];
	
	//GegnerSpielfeld wird erzeugt
	Object[][] gegnerspielfeld = new Object[][] {
		{null, "A","B", "C", "D", "E", "F", "G", "H", "I", "J"},
		{"1", "", "", "", "", "", "", "", "", "", ""},
		{"2", "", "", "", "", "", "", "", "", "", ""},
		{"3", "", "", "", "", "", "", "", "", "", ""},
		{"4", "", "", "", "", "", "", "", "", "", ""},
		{"5", "", "", "", "", "", "", "", "", "", ""},
		{"6", "", "", "", "", "", "", "", "", "", ""},
		{"7", "", "", "", "", "", "", "", "", "", ""},
		{"8", "", "", "", "", "", "", "", "", "", ""},
		{"9", "", "", "", "", "", "", "", "", "", ""},
		{"10", "", "", "", "", "", "", "", "", "", ""},
	};
	
	//======HAUPTMETHODE===================
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{ 
					schiffeVersenkenGUI window = new schiffeVersenkenGUI();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public schiffeVersenkenGUI() {
		initialize();
	}

	private void initialize() 
	{
		//ALLES WIRD ERSTELLT
		frame = new JFrame("Schiffe Versenken");
		frame.setBounds(100, 100, 1054, 434);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSpielereingabe = new JLabel("Spieler-Eingabe:");
		lblSpielereingabe.setBackground(Color.WHITE);
		lblSpielereingabe.setBounds(54, 13, 96, 16);
		frame.getContentPane().add(lblSpielereingabe);
		
		JLabel lblNewLabel = new JLabel("Spieler 1");
		lblNewLabel.setBounds(12, 47, 57, 21);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSpieler = new JLabel("Spieler 2");
		lblSpieler.setBounds(12, 81, 56, 16);
		frame.getContentPane().add(lblSpieler);
		
		spieler1TextField = new JTextField();
		spieler1TextField.setBounds(78, 46, 116, 22);
		frame.getContentPane().add(spieler1TextField);
		spieler1TextField.setColumns(10);
		
		spieler2TextField = new JTextField();
		spieler2TextField.setBounds(78, 78, 116, 22);
		frame.getContentPane().add(spieler2TextField);
		spieler2TextField.setColumns(10);
		
		
		
		//==========WENN BUTTON GEDRÜCKT WIRD=======================
		JButton btnEintragen = new JButton("Spiel starten!");
		btnEintragen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(("".equals(spieler1TextField.getText()) || "".equals(spieler2TextField.getText())))
				{
					System.out.println("Bitte geben Sie einen Spielernamen ein.");
				}
				else
				{
					spieler1.setName(spieler1TextField.getText());
					spieler2.setName(spieler2TextField.getText());
					gegnerTable.setVisible(true);
					spielerTable.setVisible(true);
					lblGegnerFeld.setVisible(true);
					lblMeinSchlachtfeld.setVisible(true);
					SchiffePlatzieren();
					
				}

			}
		});
		btnEintragen.setBounds(12, 117, 182, 25);
		frame.getContentPane().add(btnEintragen);
		
		
		
		
		
		gegnerTable = new JTable();
		gegnerTable.setCellSelectionEnabled(true);
		gegnerTable.setColumnSelectionAllowed(true);
		gegnerTable.setModel(new DefaultTableModel(
			gegnerspielfeld,
			new String[] {
				" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"
			}
		));
		gegnerTable.setBounds(558, 198, 393, 176);
		frame.getContentPane().add(gegnerTable);
		gegnerTable.setVisible(false);
		
		spielerTable = new JTable();
		spielerTable.setCellSelectionEnabled(true);
		spielerTable.setColumnSelectionAllowed(true);
		spielerTable.setModel(new DefaultTableModel(
			gegnerspielfeld,
			new String[] {
				" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"
			}
		));
		spielerTable.setBounds(40, 198, 393, 176);
		frame.getContentPane().add(spielerTable);
		
		lblMeinSchlachtfeld = new JLabel("Mein Schlachtfeld");
		lblMeinSchlachtfeld.setBounds(148, 169, 109, 16);
		frame.getContentPane().add(lblMeinSchlachtfeld);
		lblMeinSchlachtfeld.setVisible(false);
		
		lblGegnerFeld = new JLabel("Gegnerfeld");
		lblGegnerFeld.setBounds(722, 169, 73, 16);
		frame.getContentPane().add(lblGegnerFeld);
		spielerTable.setVisible(false);
		lblGegnerFeld.setVisible(false);
		
		
		//=======WENN ZELLE ANGEKLICKT WIRD========
		gegnerTable.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = gegnerTable.rowAtPoint(evt.getPoint());
		        int column = gegnerTable.columnAtPoint(evt.getPoint());
		        if (row >= 0 && column >= 0 && "".equals(gegnerTable.getValueAt(row, column))) 
		        {
		        	if(isHit(row, column))
		        	{
			            gegnerspielfeld[column][row] = "X";
			            gegnerTable.setValueAt("X", row, column);		        		
		        	}
		        	else
		        	{
		        		gegnerspielfeld[column][row] = "O";
			            gegnerTable.setValueAt("O", row, column);
		        	}
		        	

		        }
		    }

		//============BACKGROUND CHANGEN==============
		private void setBackground(Color white) {
				// TODO Auto-generated method stub
				
			}
		});
		
		gegnerTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
 
                Component c = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
 
                if ("X".equals(gegnerTable.getValueAt(row,column))) 
                {
                    setBackground(Color.RED);
                } 
                else if ("O".equals(gegnerTable.getValueAt(row, column))){
                    setBackground(Color.BLUE);
                }
                else
                {
                	setBackground(Color.WHITE);
                }
                return this;
            }
        }); 
		
		
	}
	
	public boolean isHit(int row, int column) 
	{
//		Client client = Client.create();
//
//		WebResource webResource = client
//		   .resource("http://192.168.118.215:8080/coding/schiessen?x=" + row + "&y=" + column);
//
//		ClientResponse response = webResource.accept("application/json")
//                   .get(ClientResponse.class);
//		
		
		
		return true;
	}

	
	public void SchiffePlatzieren()
	{
		System.out.println("Bitte platziere ein 5er Schiff.");
		
		schiff[0] = new Schiff(5,0,0);
		
	}

}