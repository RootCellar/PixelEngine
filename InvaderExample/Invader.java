/*
*
*   Darian Marvel - 2/3/2019
*   The mob of the Invader game example, using the PixelEngine
*
*/

import PixelEngine.Game.*;
import PixelEngine.Screen.*;

public class Invader extends Mob
{

    static Team invTeam = new Team();

    int animate = 0;

    double tX = 0;
    double tY = 300;

    public Invader() {
        super();

        team = invTeam;

        size = 15;
        speed = 1;
    }

    public void shoot() {
        Projectile p = new Projectile(this);
        p.x = x;
        p.y = y;

        p.setByRot(90, 3);
        p.setOffset(30);

        p.damage = 10;
        p.damageRange = 5;

        level.add(p);
    }

    public void tick() {
        super.tick();

        if(animate++ >= 25) animate = 0;

        if(x < tX) x+=speed;
        if(x > tX) x-=speed;

        if(y < tY) y+=speed;
        if(y > tY) y-=speed;

        if(Math.abs(x-tX) < speed) {
            tX = (Math.random() * 1000) - 500;

            for(int i=0; i<3; i++) shoot();
        }

        if(Math.abs(y-tY) < speed) {
            tY = (Math.random() * 100) + 200;

            for(int i=0; i<1; i++) shoot();
        }

        HPbar.set( x - 20, y + 15, 40, 10 );
    }

    public void render(PixelCanvas c) {

        //c.drawCircle(x,y,255,0,0,25);

        int SIZE = 10;

        for(int i= -1 * SIZE; i<SIZE + 1; i++) {
            if(animate > 12) c.drawPixel(x+i, y+i, 255, 0, 0);
            else c.drawPixel(x+i, y+i, 255, 255, 255);
        }

        for(int i= -1 * SIZE; i<SIZE + 1; i++) {
            if(animate > 12) c.drawPixel(x+i, y-i, 255, 255, 255);
            else c.drawPixel(x+i, y-i, 255, 0, 0);
        }

        if(animate > 12) c.drawPixel(x, y, 255, 0, 0);
        else c.drawPixel(x, y, 255, 255, 255);

        HPbar.render(c);

    }

}
