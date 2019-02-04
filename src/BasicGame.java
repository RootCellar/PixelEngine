/*
*
*   Darian Marvel
*   2/1/2019
*   Making a simple shooter game as an example of how the PixelEngine can be used.
*   Compared to other games that could be made, this one is relatively simple.
*
*/

//PixelEngine imports
import PixelEngine.Screen.*;
import PixelEngine.Game.*;
import PixelEngine.Input.*;
import PixelEngine.Util.*;

//Java Library imports
import java.awt.Graphics;


//The class
public class BasicGame extends Game
{
    //Just can't escape this method...
    public static void main(String[] args) {
        new BasicGame();
    }

    //Variables/Objects for the game
    PixelBar bar = new PixelBar();

    public BasicGame() {
        super();

        //Use debug log
        engine.debug("Starting engine...");

        //Tell the engine to start the main loop
        engine.start();
    }

    public void revivePlayer() {
        player.x = 0;
        player.y = 700;

        player.revive();
    }

    //Called before the engine starts the main loop
    //Used to set up the game
    public void setup() {
        player = new InvaderPlayer(); //We'll use our special kind of player

        //Spawn position
        player.x = 0;
        player.y = 700;

        //Put out player into the level, the engine does not do this automatically
        engine.getLevel().add(player);

        for(int i = 0; i<10; i++) {
            Invader inv = new Invader();
            inv.x = 0;
            inv.y = 0;
            engine.getLevel().add(inv);
        }
    }

    public int getInvaderCount() {
        int count = 0;
        for(Entity e : engine.getLevel().entities) {
            if(e instanceof Invader) count++;
        }

        return count;
    }

    //Simulate the game
    //This method is part of the main loop
    public void tick() {
        //Grab the inputlistener, make it easier to look at key input
        //Could be done in constructor, with a global object, but...nah.
        InputListener in = engine.getInput();

        if(getInvaderCount() < 10) {
            Invader inv = new Invader();
            inv.x = 0;
            inv.y = 0;
            engine.getLevel().add(inv);
        }

        //Let's keep the player where we want them to be
        if( player.x > 500 ) player.x = 500;
        if( player.x < -500 ) player.x = -500;

        //Uh-oh...the player died!?!?!?
        if( player.isAlive == false ) {
            revivePlayer();
        }

        //Handle controls

        if(in.up.down) {
            //player.subY( player.speed );
        }

        if(in.down.down) {
            //player.addY( player.speed );
        }

        if(in.left.down) {
            player.subX( player.speed );
        }

        if(in.right.down) {
            player.addX( player.speed );
        }

        if(in.v.wasDown()) {
            engine.setFps(10000); //Yes, changing the preferred fps is THIS EASY...
            engine.debug("User wants turbo mode!");
        }

        if(in.y.wasDown()) {
            engine.setFps(100);
            engine.debug("User wants normal mode");
        }

        if(in.u.wasDown()) {
            engine.getScreen().ZOOM /= 2; //Changing the zoom is THIS EASY...
        }

        if(in.i.wasDown()) {
            engine.getScreen().ZOOM *= 2;
        }

        if(in.e.wasDown()) {
            engine.getScreen().ZOOM = 1;
        }

        if(in.p.wasDown()) {
            for(double i = 0; i<90; i++) {
                Projectile p = new Projectile(player);

                p.setByRot(i * 4, 3);

                engine.getLevel().add(p);
            }
        }

        if(in.space.down) {

            /* Let's find a better method...
            Projectile pProj = new Projectile(player);
            pProj.x = player.x;
            pProj.y = player.y;
            pProj.setByRot(270, 10);
            pProj.setOffset(30);
            pProj.damage = 15;
            engine.getLevel().add(pProj);
            */

            ( (InvaderPlayer) player ).shoot();
        }

        engine.getLevel().tick(); //Tick the level, which includes the entities, mobs, and projectiles
    }

    //Draw everything to the screen
    //Called during main loop
    public void render() {

        if( engine.getScreen().ZOOM < 0.125 ) engine.getScreen().ZOOM = 0.125;

        if( engine.getScreen().ZOOM > 8 ) engine.getScreen().ZOOM = 8;

        PixelCanvas c = engine.getScreen();

        Level level = engine.getLevel();

        c.clear(); //Clear the screen, make it all black
        //engine.getScreen().randomize();

        c.setCenter( 0, 500 ); //Set the center of the screen

        //c.setCenter( player.x, player.y );

        engine.renderBorder(); //Render the level border, and the dots

        engine.renderEntities(); //Render mobs, projectiles, and items

        /*
        c.drawCircle(player.x, player.y, 255, 255, 255, player.size);
        c.drawCircle(player.x, player.y, 255, 255, 255, player.size * 20);

        c.drawLine(player.x, player.y, 0, 0, 255, 255, 255);

        c.drawLine( player.x, player.y, -1 * level.xBound, -1 * level.yBound, 255, 0, 0, 100 );
        c.drawLine( player.x, player.y, level.xBound, -1 * level.yBound, 0, 255, 0, 100 );
        c.drawLine( player.x, player.y, -1 * level.xBound, level.yBound, 0, 0, 255, 100 );
        c.drawLine( player.x, player.y, level.xBound, level.yBound, 255, 255, 0, 100 );

        c.drawLine( player.x, player.y, 0, -1 * level.yBound, 255, 0, 255, 100 );
        c.drawLine( player.x, player.y, 0, level.yBound, 0, 255, 255, 100 );
        */

        c.render(); //Take the pixels, push them to the screen
    }

    //Draw words and such to the screen
    //Called during main loop
    public void draw(Graphics g) {
        g.drawString("Time: " + System.nanoTime(), 50, 60);
        g.drawString("ZOOM: " + engine.getScreen().ZOOM, 50, 80);
    }
}
