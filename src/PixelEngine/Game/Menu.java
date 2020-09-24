/*
*
*   Helpful little class that can be extended to make menus
*   Options menus, info menus, shop menus, etc...
*   This class can be copied and renamed, it makes a decent template
*
*/

package PixelEngine.Game;

import PixelEngine.Screen.*;
import PixelEngine.Input.*;

import java.awt.*;

public class Menu implements PixelCanvasUser
{
    public PixelEngine engine;

    String[] choices = {
                        "Start Game" ,
                        "About",
                       };

    int choice = 0;

    public Menu(PixelEngine e) {
        engine = e;
    }

    public void tick() {
        InputListener in = engine.getInput();

        if(in.space.wasDown()) {
            if(choice == 0) engine.setMenu( null );
            if(choice == 1) engine.setMenu( new EngineAboutMenu(engine) );
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


    //TODO: Some kind of animation?

    public void draw(Graphics g) {

        g.drawString("Welcome!", 50, 80);

        showChoices(g);
    }

    public void showChoices(Graphics g) {
        for(int i=0; i<choices.length; i++) {
            if( i == choice) g.drawString("> "+choices[i], 50, (i*20)+100);
            else g.drawString(choices[i], 50, (i*20)+100);
        }
    }
}
