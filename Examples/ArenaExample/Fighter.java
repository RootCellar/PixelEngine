/*
 *   
 *  Darian Marvel - 2/12/2019
 *  Creating a class for small fighter enemies.
 *   
 */

import PixelEngine.Game.*;
import PixelEngine.Screen.*;
import PixelEngine.Util.*;

public class Fighter extends Mob
{
    public static Randomizer randomizer = new Randomizer();
    public static Team FighterTeam = new Team();
    public static Player player;
    
    //When this class is loaded, ...
    static {
        FighterTeam.name = "Fighter Team";
    }
    
    int reload = 0;

    int randMoveTime = 0;
    int randMoveX = 0;
    int randMoveY = 0;
    
    public Fighter() {
        super();
        
        team = FighterTeam;
        
        hp = maxHp = 50;
        
        size = 20;
        speed = 2;
    }

    public void randomizeSpawn(int spread) {
        int Xp = randomizer.nextInt(0, spread);
        Xp -= spread / 2;

        int Yp = randomizer.nextInt(0, spread);
        Yp -= spread / 2;

        x += Xp;
        y += Yp;
        
    }

    public void shoot() {
        if(reload > 0) return;

        Projectile p = new Projectile(this);
        
        double[] pos = findPosByAngle(x, y, rot, size);

        p.x = x + pos[0];
        p.y = y + pos[1];

        p.setByRot(rot, 10);
        p.setOffset(5);
        
        p.size = (int) (size / 10.0);
        p.damageRange = (int) (size / 10.0);
        p.damage = (int) (size / 5);

        level.add(p);
        reload = 25;
    }

    public void tick() {
        super.tick();
        
        HPbar.set( x - 20, y + 15, 40, 10 );
        
        if(reload-- < 0) reload = 0;
        
        //If we know what the player as and they are alive, do ai...
        if( player != null && player.isAlive ) {
            double angle = Math.atan( ( player.y - y ) / ( player.x - x ) );
            angle = Math.toDegrees(angle);
            if(x < player.x) angle += 180;
            rot = angle + 180;
            shoot();
        }
        
        if(randMoveTime < 1 && randomizer.nextInt(1, 1000) < 10) {
            randMoveTime = randomizer.nextInt(60, 180);

            randMoveX = randomizer.nextInt(0,2) - 1;
            randMoveY = randomizer.nextInt(0,2) - 1;
        }
        else if( randMoveTime > 0) {
            addX(randMoveX * speed);
            addY(randMoveY * speed);
            randMoveTime--;
        }
        
    }

    public void render(PixelCanvas c) {
        //super.render(c);

        //double[] pos = findPosByAngle(x, y, rot, 300);
        //c.drawLine(x, y, x+pos[0], y+pos[1], 255, 255, 255);

        HPbar.render(c);
        
        int SIZE = (int)size;

        c.drawPolygon(x, y, SIZE, 3, rot, 0, 255, 0);
        
        c.drawPolygon(x, y, SIZE, 6, rot, 255, 0, 0);

        double[] pos = findPosByAngle(x, y, rot + 120, SIZE);
        c.drawPolygon(x + pos[0], y + pos[1], SIZE - 5, 3, rot + 180, 255, 255, 255);

        pos = findPosByAngle(x, y, rot + 240, SIZE);
        c.drawPolygon(x + pos[0], y + pos[1], SIZE - 5, 3, rot + 180, 255, 255, 255);
        
        pos = findPosByAngle(x, y, rot, SIZE);
        c.drawPolygon(x + pos[0], y + pos[1], SIZE / 5, 3, rot, 255, 255, 255);

    }
}
