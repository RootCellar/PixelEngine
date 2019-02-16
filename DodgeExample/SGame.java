/*
 * 
 * Darian Marvel - 2/13/2019
 * Making an entire game in ONE day
 * ...Here we go!
 * 
 */

import PixelEngine.Screen.*;
import PixelEngine.Game.*;
import PixelEngine.Input.*;

import java.awt.Graphics;
import java.awt.Point;

public class SGame extends Game
{
    public static void main(String[] args) {
        new SGame();
    }

    //Constants

    //Objects
    MouseHelper mh;

    //Variables
    double mouseX = 0;
    double mouseY = 0;

    int playerDeadTime = 0;

    public SGame() {
        super();

        mh = new MouseHelper( engine.getScreen() );

        engine.debug("Starting engine...");

        engine.start();
    }

    public void setup() {
        engine.getLevel().clear();
        
        engine.getLevel().xBound = 2000;
        engine.getLevel().yBound = 2000;

        player = new SPlayer();

        engine.getLevel().add(player);
    
    }

    public int getMobCount() {
        int count = 0;
        for(Entity e : engine.getLevel().entities) {
            if(e instanceof SMob) count++;
        }
        return count;
    }

    public void tick() {
        InputListener in = engine.getInput();

        Point mp = mh.getPoint();

        mouseX = mp.getX();
        mouseY = mp.getY();

        if(getMobCount() < 30) engine.getLevel().add(new SMob());

        if(player.isAlive == false) playerDeadTime++;
        if(playerDeadTime >= 120) {
            setup();
            playerDeadTime = 0;
        }

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
        
        if(in.aleft.down) player.rot -= 5;
        if(in.aright.down) player.rot += 5;

        if(in.v.wasDown()) {
            engine.setFps(1000);
            engine.debug("User wants turbo mode!");
        }

        if(in.y.wasDown()) {
            engine.setFps(100);
            engine.debug("User wants normal mode");
        }

        ///*
        if(in.u.wasDown()) {
            engine.getScreen().ZOOM /= 2;
        }

        if(in.i.wasDown()) {
            engine.getScreen().ZOOM *= 2;
        }

        if(in.e.wasDown()) {
            engine.getScreen().ZOOM = 1;
        }
        //*/

        engine.getLevel().tick();
    }

    public void render() {

        if( engine.getScreen().ZOOM < 0.125 ) engine.getScreen().ZOOM = 0.125;

        if( engine.getScreen().ZOOM > 8 ) engine.getScreen().ZOOM = 8;

        engine.getScreen().clear();
        //engine.getScreen().randomize();

        engine.getScreen().setCenter( player.x, player.y );

        engine.getScreen().drawCircle(mouseX, mouseY, 255, 255, 255, 10);

        engine.renderEntities();
        engine.renderBorder();

        engine.getScreen().render();
    }

    public void draw(Graphics g) {
        g.drawString("Time: " + System.nanoTime(), 50, 60);
        g.drawString("ZOOM: " + engine.getScreen().ZOOM, 50, 80);

        g.drawString("ANGLE: " + player.rot, 50, 100);
    }
}
