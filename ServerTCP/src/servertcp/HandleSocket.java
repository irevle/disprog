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
 *
 * @author mohammadfaridnaufal
 */
public class HandleSocket extends Thread{
    
    FormServer parent;
    Socket client;
    DataOutputStream out;
    BufferedReader in;
    
    public HandleSocket(FormServer _parent, Socket _client)
    {
        this.parent = _parent;
        this.client = _client;
        
        try
        {
            out = new DataOutputStream(client.getOutputStream());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
    }
    
    public void sendChat(String tmp)
    {
        try
        {
            out.writeBytes(tmp + "\n");
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
    }
    
    @Override
    public void run()
    {
        sendChat("Welcome to this Group Chat");
        parent.broadCastJoin(this, "");
        while(true)
        {
            try
            {
                String msg = in.readLine();
                System.out.println("masuk");
                if(msg.contains(";-joins"))
                {
                    parent.broadCastJoin(this, msg);
                }
                else
                {
                    parent.showChat(msg);
                }
            }
            catch(Exception Ex)
            {
                System.out.println(Ex);
            } 
        }
    }
            
}
