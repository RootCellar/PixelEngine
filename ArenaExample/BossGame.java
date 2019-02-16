/*
 *
 *  Darian Marvel - 2/10/2019
 *  Making a game using PixelEngine v0.5
 *  Testing out some other things that the PixelEngine can do
 *   
 */

import PixelEngine.Screen.*;
import PixelEngine.Game.*;
import PixelEngine.Input.*;

import java.awt.Graphics;
import java.awt.Point;

public class BossGame extends Game
{
    public static void main(String[] args) {
        new BossGame();
    }

    //Constants

    //Objects
    MouseHelper mh;

    //Variables
    double mouseX = 0;
    double mouseY = 0;

    int playerDeadTime = 0;
    long timePl = 0;

    public BossGame() {
        super();

        mh = new MouseHelper( engine.getScreen() );

        engine.debug("Starting engine...");

        engine.start();
    }

    public void setup() {
        engine.getLevel().clear();
        
        engine.getLevel().xBound = 2000;
        engine.getLevel().yBound = 2000;

        player = new BossPlayer();

        Fighter.player = player;

        engine.getLevel().add(player);

        /*
        for(int k=0; k<5; k++) {
            for(int i=0; i< 5; i++) {
                Fighter f = new Fighter();

                f.x = 50 + i * 100;
                f.y = 50 + k * 100;

                engine.getLevel().add(f);
            }
        }
        */
        
        timePl = 0;
    
    }

    public int getFighterCount() {
        int count = 0;
        for(Entity e : engine.getLevel().entities) {
            if(e instanceof Fighter) count++;
        }
        return count;
    }

    public void tick() {
        InputListener in = engine.getInput();

        Point mp = mh.getPoint();

        mouseX = mp.getX();
        mouseY = mp.getY();

        timePl++;

        if(player.isAlive == false) playerDeadTime++;
        if(playerDeadTime >= 120) {
            setup();
            playerDeadTime = 0;
        }

        if(getFighterCount() < 3 + (timePl / 1500) ) {
            Fighter f = new Fighter();
            f.randomizeSpawn( (int) engine.getLevel().xBound );
            engine.getLevel().add(f);
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

        if(in.space.down) ( (BossPlayer) player ).shoot();

        if( mouseX - player.x != 0) {
            double angle = Math.atan( ( mouseY - player.y ) / ( mouseX - player.x ) );
            angle = Math.toDegrees(angle);
            if(mouseX < player.x) angle += 180;
            player.rot = angle;
        }

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
