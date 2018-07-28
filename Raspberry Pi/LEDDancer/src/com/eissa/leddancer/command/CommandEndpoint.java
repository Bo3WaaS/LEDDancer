
package com.eissa.leddancer.command;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.Session;

@ClientEndpoint
public class CommandEndpoint {
    
    @OnMessage
    public void onMessage(Session session, String message){
        CommandService.setCommand("" + message);
    }

    
}
