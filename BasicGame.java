/*
 * This class contains a basic game that just puts the player in an empty level and allows them to move
 * Feel free to copy this for your own game!
 * Note: It is also a good idea to read this to understand the basic things that are required to make a working game
*/

import PixelEngine.Screen.*;
import PixelEngine.Game.*;
import PixelEngine.Input.*;

import java.awt.Graphics;

public class BasicGame extends Game
{
    public static void main(String[] args) {
        new BasicGame();
    }
    
    public BasicGame() {
        super();
        
        engine.debug("Starting engine...");
        
        engine.start();
    }
    
    public void tick() {
        InputListener in = engine.getInput();
        
        if(in.up.down) {
            player.subY( player.speed );
        }
        
        if(in.down.down) {
            player.addY( player.speed );
        }
        
        if(in.left.down) {
            player.subX( player.speed );
        }
        
        if(in.right.down) {
            player.addX( player.speed );
        }
        
        if(in.v.wasDown()) {
            engine.setFps(1000);
            engine.debug("User wants turbo mode!");
        }
        
        if(in.y.wasDown()) {
            engine.setFps(100);
            engine.debug("User wants normal mode");
        }
        
        if(in.u.wasDown()) {
            engine.getScreen().ZOOM /= 2;
        }
        
        if(in.i.wasDown()) {
            engine.getScreen().ZOOM *= 2;
        }
        
        if(in.e.wasDown()) {
            engine.getScreen().ZOOM = 1;
        }
        
        engine.getLevel().tick();
    }
    
    public void render() {
        
        if( engine.getScreen().ZOOM < 0.125 ) engine.getScreen().ZOOM = 0.125;
        
        if( engine.getScreen().ZOOM > 8 ) engine.getScreen().ZOOM = 8;
        
        engine.getScreen().clear();
        //engine.getScreen().randomize();
        
        engine.getScreen().setCenter( player.x, player.y );
        
        engine.renderEntities();
        engine.renderBorder();
        
        engine.getScreen().render();
    }
    
    public void draw(Graphics g) {
        g.drawString("Time: " + System.nanoTime(), 50, 60);
        g.drawString("ZOOM: " + engine.getScreen().ZOOM, 50, 80);
    }
}
