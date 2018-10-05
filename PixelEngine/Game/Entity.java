package PixelEngine.Game;

import java.util.ArrayList;

import PixelEngine.Screen.*;

public class Entity
{
    public double x;
    public double y;
    public double size = 0;
    public Level level;
    public Team team = new Team();
    public String type = "Entity";
    
    public boolean visible = true;
    public static boolean DEBUG_DRAW = false;
    public static short idPoint = -32768;
    
    public short id = idPoint++;
    
    public void remove() {
        level.remove(this);
    }
    
    public void tick() {
        if(idPoint > 32000) idPoint = -32767;
    }
    
    public void setLevel(Level l) {
        level=l;
    }   
    
    public void render(PixelCanvas c) {
        if(!visible) return;
        
        c.drawPixel(x, y, 128, 128, 128);
    }
}