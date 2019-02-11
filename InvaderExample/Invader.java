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

    //Put all Invaders on the same team
    static Team invTeam = new Team();

    //Statically set up said team
    static {
        invTeam.name = "Invaders";
        invTeam.g = 0;
        invTeam.b = 0;
    }

    int animate = 0;

    double tX = 0;
    double tY = 300;

    int rC = 255;
    int gC = 0;
    int bC = 0;

    int lvl = 0;

    public Invader(int l) {
        super();

        lvl = l;

        hp = maxHp = 30;

        if(lvl>1) {
            rC = 0;
            if(lvl == 2) gC = 255;
            if(lvl == 3) bC = 255;
            if(lvl == 4) {
                rC = 255;
                gC = 255;
                bC = 255;
            }

            hp = ( maxHp*=lvl * 2 );
        }

        team = invTeam;

        name = "Invader";

        size = 15;
        speed = 1;
    }

    public void setLevel(Level l) {
        super.setLevel(l);

        invTeam.setLevel(l);
    }

    public void shoot() {
        Projectile p = new Projectile(this);
        p.x = x;
        p.y = y;

        p.setByRot(90, 2 );
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

            //for(int i=0; i<3; i++) shoot();
        }

        if(Math.abs(y-tY) < speed) {
            tY = (Math.random() * 100) + 200;

            for(int i=0; i<1; i++) shoot();
        }

        if(y < 90) {
            if( ( Math.random() * 190 ) < 3) {
                shoot();
            }
        }

        HPbar.set( x - 20, y + 15, 40, 10 );
    }

    public void render(PixelCanvas c) {

        //c.drawCircle(x,y,255,0,0,25);

        int SIZE = 10;

        c.drawPolygon(x, y, SIZE, 3, 90, rC, gC, bC);

        if(animate > 12) c.drawPolygon(x, y-SIZE, SIZE, 3, 90, 255, 0, 0);
        else c.drawPolygon(x, y-SIZE, SIZE, 3, 90, 255, 255, 255);

        /*
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
        */

        HPbar.render(c);

    }

}
