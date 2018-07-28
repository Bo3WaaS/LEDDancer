package com.eissa.leddancer.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.eissa.leddancer.endpoint.CommandEndpoint;

@Service
public class DefaultLEDService implements LEDService {

	@Override
	public boolean openRed() {
		try {
			CommandEndpoint.piSession.getBasicRemote().sendText("openRed");
			return true;
		} catch (IOException | NullPointerException e) {
			return false;
		}
	}

	@Override
	public boolean closeRed() {
		try {
			CommandEndpoint.piSession.getBasicRemote().sendText("closeRed");
			return true;
		} catch (IOException | NullPointerException e) {
			return false;
		}
	}

	@Override
	public boolean openGreen() {
		try {
			CommandEndpoint.piSession.getBasicRemote().sendText("openGreen");
			return true;
		} catch (IOException | NullPointerException e) {
			return false;
		}
	}

	@Override
	public boolean closeGreen() {
		try {
			CommandEndpoint.piSession.getBasicRemote().sendText("closeGreen");
			return true;
		} catch (IOException | NullPointerException e) {
			return false;
		}
	}

	@Override
	public boolean openBlue() {
		try {
			CommandEndpoint.piSession.getBasicRemote().sendText("openBlue");
			return true;
		} catch (IOException | NullPointerException e) {
			return false;
		}
	}

	@Override
	public boolean closeBlue() {
		try {
			CommandEndpoint.piSession.getBasicRemote().sendText("closeBlue");
			return true;
		} catch (IOException | NullPointerException e) {
			return false;
		}
	}

	@Override
	public boolean dance() {
		try {
			CommandEndpoint.piSession.getBasicRemote().sendText("dance");
			return true;
		} catch (IOException | NullPointerException e) {
			return false;
		}
	}

	@Override
	public boolean openYellow() {
		try {
			CommandEndpoint.piSession.getBasicRemote().sendText("openYellow");
			return true;
		} catch (IOException | NullPointerException e) {
			return false;
		}
	}

	@Override
	public boolean closeYellow() {
		try {
			CommandEndpoint.piSession.getBasicRemote().sendText("closeYellow");
			return true;
		} catch (IOException | NullPointerException e) {
			return false;
		}
	}

}
