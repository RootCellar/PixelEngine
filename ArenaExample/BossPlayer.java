/*
 *
 *  Darian Marvel - 2/10/2019
 *  Boss player class
 *
 */

import PixelEngine.Game.*;
import PixelEngine.Screen.*;

public class BossPlayer extends Player
{
    int reload = 0;
    
    public BossPlayer() {
        super();
        
        hp = maxHp = 5000;
        
        size = 50;
        speed = 4;
    }

    public void shoot() {
        if(reload > 0) return;

        Projectile p = new Projectile(this);
        
        double[] pos = findPosByAngle(x, y, rot, size);

        p.x = x + pos[0];
        p.y = y + pos[1];

        p.setByRot(rot, 15);
        p.setOffset(5);
        
        p.size = (int) (size / 10.0);
        p.damageRange = (int) (size / 10.0);
        p.damage = (int) (size / 5);

        level.add(p);
        reload = 5;
    }

    public void tick() {
        super.tick();
        
        HPbar.set( x - 40, y + 20, 80, 15 );
        
        if(reload-- < 0) reload = 0;
    }

    public void render(PixelCanvas c) {
        //super.render(c);

        //double[] pos = findPosByAngle(x, y, rot, 300);
        //c.drawLine(x, y, x+pos[0], y+pos[1], 255, 255, 255);

        HPbar.render(c);
        
        int SIZE = (int)size;

        c.drawPolygon(x, y, SIZE, 3, rot, 255, 255, 255);
        
        c.drawPolygon(x, y, SIZE, 6, rot, 255, 0, 0);

        double[] pos = findPosByAngle(x, y, rot + 120, SIZE);
        c.drawPolygon(x + pos[0], y + pos[1], SIZE - 5, 3, rot + 180, 255, 255, 255);

        pos = findPosByAngle(x, y, rot + 240, SIZE);
        c.drawPolygon(x + pos[0], y + pos[1], SIZE - 5, 3, rot + 180, 255, 255, 255);
        
        pos = findPosByAngle(x, y, rot, SIZE);
        c.drawPolygon(x + pos[0], y + pos[1], SIZE / 5, 3, rot, 255, 255, 255);

    }
}
