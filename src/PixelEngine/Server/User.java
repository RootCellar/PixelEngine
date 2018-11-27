package PixelEngine.Server;

import PixelEngine.Network.*;
import PixelEngine.Util.*;

public class User implements SocketUser
{
    public String name = "GUEST";
    
    public SocketHandler socketHandler;
    
    public Room room;
    
    public Registry data = new Registry();
    
    public User(SocketHandler s) {
        socketHandler = s;
        
        s.setUser(this);
    }
    
    public Registry getData() {
        return data;
    }
    
    public boolean isConnected() {
        return socketHandler.isConnected();
    }
    
    public String getName() {
        return name;
    }
    
    public void setRoom(Room r) {
        data.clear();
        if(room != null) room.removeUser(this);
        room = r;
        send("Moving you to a new room...");
    }
    
    public String getAddress() {
        return socketHandler.getAddress();
    }
    
    public SocketHandler getSocketHandler() {
        return socketHandler;
    }
    
    public void send(String s) {
        Message m = new Message( (short) 0, (short) 0);
        
        m.setString(s);
        
        send(m);
    }
    
    public void send(Message m) {
        socketHandler.sendMessage(m);
    }
    
    public void inputBytes(byte[] b) {
        //System.out.println("USER: R");
        if(room != null) {
            room.receive( this, new Message(b) );
        }
    }
    
}