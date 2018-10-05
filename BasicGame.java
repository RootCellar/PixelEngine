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
        
        engine.getLevel().tick();
    }
    
    public void render() {
        engine.getScreen().clear();
        //engine.getScreen().randomize();
        
        engine.getScreen().setCenter( player.x, player.y );
        
        engine.renderEntities();
        engine.renderBorder();
        
        engine.getScreen().render();
    }
    
    public void draw(Graphics g) {
        g.drawString("Time: " + System.nanoTime(), 50, 60);
    }
}