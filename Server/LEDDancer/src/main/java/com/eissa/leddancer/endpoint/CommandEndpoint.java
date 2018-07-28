package com.eissa.leddancer.endpoint;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/command/endpoint")
public class CommandEndpoint {
	
	public static Session piSession = null;

	@OnOpen
	public void onOpen(Session session){
		piSession = session;
	}
	
	@OnClose
	public void onClose(Session session){
		piSession = null;
	}
	
	@OnError
	public void onError(Throwable t){
		
	}

}
