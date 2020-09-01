import PixelEngine.Game.*;
import PixelEngine.Screen.*;
import PixelEngine.Util.*;

public class SMob extends Mob
{
    static Team sTeam = new Team();
    static Randomizer randomizer = new Randomizer();

    int randMoveTime = 0;
    int randMoveX = 0;
    int randMoveY = 0;

    int reload = 150;

    public SMob() {
        super();

        team = sTeam;
        
        hp = maxHp = 50;
        
        size = 15;
        speed = 5;

        rot = randomizer.nextDouble(0, 360);
    }

    public void shoot() {
        if(reload > 1) return;

        Projectile p = new Projectile(this);
        p.x = x;
        p.y = y;

        p.setByRot(rot, 5);
        p.setOffset(30);

        p.damage = 10;

        level.add(p);
        reload = 25;
    }
    
    public void tick() {
        super.tick();

        if(reload-- < 0) reload = 0;
        shoot();

        rot++;
        
        if(rot > 360) rot -= 360;
        if(rot < 0) rot += 360;

        if(randMoveTime < 1 && randomizer.nextInt(1, 100) < 3) {
            randMoveX = randomizer.nextInt(0, 2) - 1;
            randMoveY = randomizer.nextInt(0, 2) - 1;
            randMoveTime = randomizer.nextInt(60, 180);
        }
        else if(randMoveTime > 0) {
            addX(randMoveX * speed);
            addY(randMoveY * speed);
            randMoveTime--;
        }

        HPbar.set(x - 20, y + 15, 40, 15);

    }
    
    public void render(PixelCanvas c) {
        
        c.drawPolygon(x, y, size, 3, rot, 0, 255, 0);
        
        double[] pos = findPosByAngle(x, y, rot, size);
        c.drawPolygon(x + pos[0], y + pos[1], size, 3, rot, 0, 255, 0);
        
        pos = findPosByAngle(x, y, rot, size);
        c.drawPolygon(x - pos[0], y - pos[1], size, 3, rot, 0, 255, 0);
        
        HPbar.render(c);
    }

}
