
package com.eissa.leddancer.core;

import com.eissa.leddancer.command.CommandEndpoint;
import com.eissa.leddancer.command.CommandService;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.DeploymentException;
import org.glassfish.tyrus.client.ClientManager;


public class LEDDancer {

    private static final String SERVER_URL = "ws://serverIp:8080/LEDDancer/command/endpoint";
    private static GpioPinDigitalOutput redPin1;
    private static GpioPinDigitalOutput redPin2;
    private static GpioPinDigitalOutput greenPin1;
    private static GpioPinDigitalOutput greenPin2;
    private static GpioPinDigitalOutput bluePin1;
    private static GpioPinDigitalOutput bluePin2;
    private static GpioPinDigitalOutput yellowPin1;
    private static GpioPinDigitalOutput yellowPin2;

    public static void main(String[] args) {

        System.out.println("Application Started :)");
        initialize();
        
        try {
            connectToServer();
        } catch (InterruptedException ex) {
            Logger.getLogger(LEDDancer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("An error occured, the program will be stopped");
            return;
        }

        while (true) {
            switch (CommandService.getCommand()) {
                case Constant.OPEN_RED:
                    openRedPin();
                    System.out.println("Red LEDs opened");
                    CommandService.clearCommand();
                    break;
                case Constant.CLOSE_RED:
                    closeRedPin();
                    System.out.println("Red LEDs closed");
                    CommandService.clearCommand();
                    break;
                case Constant.OPEN_GREEN:
                    openGreenPin();
                    System.out.println("Green LEDs opened");
                    CommandService.clearCommand();
                    break;
                case Constant.CLOSE_GREEN:
                    closeGreenPin();
                    System.out.println("Green LEDs closed");
                    CommandService.clearCommand();
                    break;
                case Constant.OPEN_BLUE:
                    openBluePin();
                    System.out.println("Blue LEDs opened");
                    CommandService.clearCommand();
                    break;
                case Constant.CLOSE_BLUE:
                    closeBluePin();
                    System.out.println("Blue LEDs closed");
                    CommandService.clearCommand();
                    break;
                case Constant.OPEN_YELLOW:
                    openYellowPin();
                    System.out.println("Yellow LEDs opened");
                    CommandService.clearCommand();
                    break;
                case Constant.CLOSE_YELLOW:
                    closeYellowPin();
                    System.out.println("Yellow LEDs closed");
                    CommandService.clearCommand();
                    break;
                case "dance":
                    System.out.println("Dancing");
                    dance();
                    break;
            }
        }

    }

    private static void initialize() {
        System.out.println("Initializing LEDs");
        GpioController gpio = GpioFactory.getInstance();
        yellowPin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, PinState.LOW);
        yellowPin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, PinState.LOW);
        greenPin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, PinState.LOW);
        greenPin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, PinState.LOW);
        bluePin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, PinState.LOW);
        bluePin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, PinState.LOW);
        redPin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, PinState.LOW);
        redPin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, PinState.LOW);
        System.out.println("LEDs Initialization done");
    }

    private static void dance() {
        try {
            flash1();
            flash1();
            flash2();
        } catch (Exception ex) {
            Logger.getLogger(LEDDancer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void flash1() throws InterruptedException {
        openRedPin();
        Thread.sleep(150);
        closeRedPin();

        openBluePin();
        Thread.sleep(150);
        closeBluePin();

        openGreenPin();
        Thread.sleep(150);
        closeGreenPin();

        openYellowPin();
        Thread.sleep(150);
        closeYellowPin();
    }

    private static void flash2() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            openBluePin();
            openGreenPin();
            openRedPin();
            openYellowPin();

            Thread.sleep(90);

            closeBluePin();
            closeGreenPin();
            closeRedPin();
            closeYellowPin();

            Thread.sleep(90);
        }
    }

    private static void openYellowPin() {
        yellowPin1.setState(PinState.HIGH);
        yellowPin2.setState(PinState.HIGH);
    }

    private static void closeYellowPin() {
        yellowPin1.setState(PinState.LOW);
        yellowPin2.setState(PinState.LOW);
    }

    private static void openGreenPin() {
        greenPin1.setState(PinState.HIGH);
        greenPin2.setState(PinState.HIGH);
    }

    private static void closeGreenPin() {
        greenPin1.setState(PinState.LOW);
        greenPin2.setState(PinState.LOW);
    }

    private static void openBluePin() {
        bluePin1.setState(PinState.HIGH);
        bluePin2.setState(PinState.HIGH);
    }

    private static void closeBluePin() {
        bluePin1.setState(PinState.LOW);
        bluePin2.setState(PinState.LOW);
    }

    private static void openRedPin() {
        redPin1.setState(PinState.HIGH);
        redPin2.setState(PinState.HIGH);
    }

    private static void closeRedPin() {
        redPin1.setState(PinState.LOW);
        redPin2.setState(PinState.LOW);
    }

    private static void connectToServer() throws InterruptedException {
        System.out.println("Connecting to server");
        ClientManager client = ClientManager.createClient();
        boolean connected = false;
        while (!connected) {
            try {
                client.connectToServer(CommandEndpoint.class, URI.create(SERVER_URL));
                connected = true;
                System.out.println("Connection to server done");
            } catch (IOException | DeploymentException e) {
                System.out.println("Could not connect to server, we will try again after a while");
                Thread.sleep(10000);
            }
        }
    }

}
