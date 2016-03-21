package com.flowfact.coding.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

	private Spiel spiel;
	private int aktiverSpieler;

	@RequestMapping(value = "/dummy", method = RequestMethod.GET)
	public ResponseEntity<String> printDummy() {
		String testResponse = "Der Test war erfolgreich";
		return new ResponseEntity<String>(testResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/spielStarten", method = RequestMethod.GET)
	public ResponseEntity<String> spielStarten() {
		this.spielInitialisieren();
		return new ResponseEntity<String>("Ein neues Spiel wurde gestartet!", HttpStatus.OK);
	}

	public void spielInitialisieren() {
		spiel = new Spiel();
		aktiverSpieler = 1;
		
	}

	@RequestMapping(value = "/getfield", method = RequestMethod.GET)
	public ResponseEntity<Spielfeld> printfield1(int index) {
		Spielfeld returnfield = spiel.getField(index);
		return new ResponseEntity<Spielfeld>(returnfield, HttpStatus.OK);
	}

	@RequestMapping(value = "/schiessen", method = RequestMethod.GET)
	public ResponseEntity<Spielfeld> schiessen(int x, int y) {

		int aktuellerStatus = spiel.getField(aktiverSpieler).getFeldWert(x, y);

		switch (aktuellerStatus) {
		case Status.WASSER:
		case Status.MARKIERT:
			spiel.getField(aktiverSpieler).setFeldWert(x, y, Status.VERFEHLT);
			aktiverSpieler = (aktiverSpieler == 1 ? 2 : 1);
			break;
		case Status.SCHIFF:
			spiel.getField(aktiverSpieler).setFeldWert(x, y, Status.GETROFFEN);
			break;
		default:
			break;
		}

		Spielfeld returnfield = spiel.getField(aktiverSpieler);
		return new ResponseEntity<Spielfeld>(returnfield, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/markieren", method = RequestMethod.GET)
	public ResponseEntity<Spielfeld> markieren(int x, int y) {

		int aktuellerStatus = spiel.getField(aktiverSpieler).getFeldWert(x, y);

		switch (aktuellerStatus) {
		case Status.MARKIERT:
			spiel.getField(aktiverSpieler).setFeldWert(x, y, Status.WASSER);
			break;
		case Status.WASSER:
			spiel.getField(aktiverSpieler).setFeldWert(x, y, Status.MARKIERT);
			break;
		default:
			break;
		}

		Spielfeld returnfield = spiel.getField(aktiverSpieler);
		return new ResponseEntity<Spielfeld>(returnfield, HttpStatus.OK);
	}


	@RequestMapping(name = "getZug", method = RequestMethod.POST, consumes = "application/xml")
	public ResponseEntity<String> printfield(@RequestBody Integer[][] test) {

		String testResponse = "Der Test war erfolgreich";
		this.spielInitialisieren();
		return new ResponseEntity<String>(testResponse, HttpStatus.OK);
	}
}
