package com.eissa.leddancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eissa.leddancer.constant.Constant;
import com.eissa.leddancer.service.LEDService;

@RestController("/command")
public class Controller {
	
	@Autowired
	private LEDService ledService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<String> command(@RequestParam("name") String name){
		
		switch(name){
		case Constant.OPEN_RED:
			if(ledService.openRed()){
				return new ResponseEntity<String>("Red LEDs Opened", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Error occurs while opening Red LEDs!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		case Constant.CLOSE_RED:
			if(ledService.closeRed()){
				return new ResponseEntity<String>("Red LEDs Closed", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Error occurs while closing Red LEDs!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		case Constant.OPEN_GREEN:
			if(ledService.openGreen()){
				return new ResponseEntity<String>("Green LEDs Opened", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Error occurs while opening Green LEDs!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		case Constant.CLOSE_GREEN:
			if(ledService.closeGreen()){
				return new ResponseEntity<String>("Green LEDs Closed", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Error occurs while closing Green LEDs!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		case Constant.OPEN_BLUE:
			if(ledService.openBlue()){
				return new ResponseEntity<String>("Blue LEDs Opened", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Error occurs while opening Blue LEDs!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		case Constant.CLOSE_BLUE:
			if(ledService.closeBlue()){
				return new ResponseEntity<String>("Blue LEDs Closed", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Error occurs while closing Blue LEDs!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		case Constant.OPEN_YELLOW:
			if(ledService.openYellow()){
				return new ResponseEntity<String>("Yellow LEDs Opened", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Error occurs while opening Yellow LEDs!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		case Constant.CLOSE_YELLOW:
			if(ledService.closeYellow()){
				return new ResponseEntity<String>("Yellow LEDs Closed", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Error occurs while closing Yellow LEDs!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		case "dance":
			if(ledService.dance()){
				return new ResponseEntity<String>("Danced Successfully", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Error occurs while LEDs are trying to dance!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}

}
