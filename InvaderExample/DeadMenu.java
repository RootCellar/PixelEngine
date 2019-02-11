/*
*
*   Uh-oh! The player has died!
*
*/

import PixelEngine.Screen.*;
import PixelEngine.Input.*;
import PixelEngine.Game.*;

import java.awt.Graphics;
import java.awt.Color;

public class DeadMenu extends Menu implements PixelCanvasUser
{

    String[] choices = {
                        "" ,
                        "Reset",
                       };

    int choice = 0;

    public DeadMenu(PixelEngine e) {
        super(e);
    }

    public void tick() {
        InputListener in = engine.getInput();

        if(in.space.wasDown()) {
            
            if(choice == 1) {
                engine.reset();
            }

        }

        doSelect();
    }

    public void doSelect() {
        InputListener in = engine.getInput();

        if(in.down.wasDown()) choice++;
        if(in.up.wasDown()) choice--;

        if(choice<0) choice = 0;
        if(choice>choices.length-1) choice = choices.length - 1;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);

        g.drawString("You have died!", 50, 80);

        showChoices(g);
    }

    public void showChoices(Graphics g) {
        for(int i=0; i<choices.length; i++) {
            if( i == choice) g.drawString("> "+choices[i], 50, (i*20)+100);
            else g.drawString(choices[i], 50, (i*20)+100);
        }
    }
}
