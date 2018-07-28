
package com.eissa.leddancer.command;


public class CommandService {
    
    private static String command = "";

    public static String getCommand() {
        return command;
    }

    protected static void setCommand(String command) {
        synchronized(CommandService.class){
            CommandService.command = command;
        }
    }
    
    public static void clearCommand(){
        CommandService.setCommand("");
    }
    
}
