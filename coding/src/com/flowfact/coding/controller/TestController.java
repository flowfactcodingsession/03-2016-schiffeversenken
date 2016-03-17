package com.flowfact.coding.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

	@RequestMapping(name = "dummy", method = RequestMethod.GET)
	public ResponseEntity<String> printDummy() {
		String testResponse = "Der Test war erfolgreich";
		return new ResponseEntity<String>(testResponse, HttpStatus.OK);
	}

}
