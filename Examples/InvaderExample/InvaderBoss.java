/*
*
*   Darian Marvel - 2/5/2019
*   Boss Example
*   An example of a boss, and how polygons can be put together
*   to make something somewhat interesting.
*
*/

import PixelEngine.Game.*;
import PixelEngine.Screen.*;

public class InvaderBoss extends Mob
{

    double tX = 0;
    double tY = 300;
    
    public InvaderBoss() {
        hp = maxHp = 3000;
        regen = 0;

        size = 100;

        team = Invader.invTeam;
    }
    
    public void shoot() {
        Projectile p = new Projectile(this);
        p.x = x;
        p.y = y;

        p.setByRot(90, 3);
        p.setOffset(30);

        p.damage = 30;
        p.damageRange = 10;
        p.size = 10;

        level.add(p);
    }
    
    public void shoot2() {
        Projectile p = new Projectile(this);
        p.x = x;
        p.y = y;

        p.setByRot(90, 6);
        p.setOffset(80);

        p.damage = 5;
        p.damageRange = 5;
        p.size = 5;

        level.add(p);
    }

    public void tick() {
        super.tick();

        rot+=2;
        if(rot >= 720) rot = 0;
        
        if(x < tX) x+=speed;
        if(x > tX) x-=speed;

        if(y < tY) y+=speed;
        if(y > tY) y-=speed;

        if(Math.abs(x-tX) < speed) {
            tX = (Math.random() * 1000) - 500;

            for(int i=0; i<10; i++) shoot2();
        }

        if(Math.abs(y-tY) < speed) {
            tY = (Math.random() * 100) + 100;

            for(int i=0; i<1; i++) shoot();
        }

        HPbar.set( x - 40, y + 15, 80, 20 );
    }

    public void render(PixelCanvas c) {

        //Base pentagon
        c.drawPolygon(x, y, 100, 5, rot, 255, 0, 0);

        //Green Square
        c.drawPolygon(x, y, 80, 4, rot * 2 * -1, 0, 255, 0);

        //Blue Triangle
        c.drawPolygon(x, y, 50, 3, rot/2, 0, 0, 255);

        //For-loop draw the triangles on the edges
        for(int i=0; i<5; i++) {
            double[] pos = findPosByAngle(x, y, rot + (i * 72), 150);
            c.drawPolygon(pos[0] + x, pos[1] + y, 30, 3, rot + (i * 72), 255, 255, 255);
        }
        
        //Draw reverse-direction triangles
        for(int i=0; i<5; i++) {
            double[] pos = findPosByAngle(x, y, -rot + (i * 72), size);
            c.drawPolygon(pos[0] + x, pos[1] + y, 30, 3, -rot + (i * 72), 255, 255, 255);
        }


        //As always, draw the life bar
        HPbar.render(c);
    }
}
