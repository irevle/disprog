/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servertcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * One thread per connected client. Reads command lines from the socket and
 * hands them off to FormServer.handleCommand() for the actual business logic.
 * This class does NOT talk to the database directly - it only knows about
 * sockets and text.
 *
 * @author alievar
 */
public class HandleSocket extends Thread {

    FormServer parent;
    Socket client;
    DataOutputStream out;
    BufferedReader in;

    public HandleSocket(FormServer _parent, Socket _client) {
        this.parent = _parent;
        this.client = _client;

        try {
            out = new DataOutputStream(client.getOutputStream());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (Exception Ex) {
            System.out.println(Ex);
        }
    }

    // send one line back to just THIS client (a direct reply, or a broadcast)
    public void sendMsg(String tmp) {
        try {
            out.writeBytes(tmp + "\n");
        } catch (Exception Ex) {
            System.out.println(Ex);
        }
    }

    @Override
    public void run() {
        sendMsg("OK|Connected to Food Reservation Server");
        while (true) {
            try {
                String command = in.readLine();
                if (command == null) {
                    // client disconnected
                    parent.removeClient(this);
                    break;
                }
                System.out.println("Received: " + command);
                parent.handleCommand(this, command);
            } catch (Exception Ex) {
                System.out.println("Error HandleSocket run: " + Ex);
                parent.removeClient(this);
                break;
            }
        }
    }

}
