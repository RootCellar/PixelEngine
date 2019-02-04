/*
*
*   What games made using the PixelEngine should look like
*   Must be extended to make a game
*
*/

package PixelEngine.Game;

import PixelEngine.Screen.*;

import java.awt.*;

public class Game implements PixelCanvasUser
{

    public PixelEngine engine;

    public Player player;

    public Game() {
        engine = new PixelEngine(this);

        player = engine.getPlayer();
    }

    public void setup() {

    }

    public void tick() {

    }

    public void render() {

    }

    public void draw(Graphics g) {

    }
}
