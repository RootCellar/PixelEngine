package PixelEngine.Game;

import PixelEngine.Server.*;
import PixelEngine.Game.*;
import PixelEngine.Screen.*;
import PixelEngine.Network.*;

public class Player extends Mob
{
    public User user;
    
    public int kills = 0;

    public double dx = 0;
    public double dy = 0;

    public Player() {
        super();
        name = "Player";
        maxHp = 100;
        hp = maxHp;
        regen = 0.005;
        team = new Team();
        speed = 2;
        size = 5;
        y = 500;
    }

    public void damage(double a) {
        hp-=a;
        damageTime = 400;
        checkHp();
    }

    public void damage(double a, Mob m) {
        damage(a);
        if(hp<=0) die(m);
    }

    public void die(Mob m) {
        m.killed(this);
        if(isAlive) {
            die();
        }
    }

    public void die() {
        isAlive=false;
        if(level != null) level.remove(this);
    }

    public void killed(Mob m) {
        kills++;
    }

    public void render(PixelCanvas c) {
        c.drawSquare((int)x-3, (int)y-3, (int)x+3, (int)y+3, 255, 255, 255);

        HPbar.render(c);
    }

    public void tick() {
        super.tick();

        HPbar.set( x - 20, y + 15, 40, 10 );

        dx = x;
        dy = y;

    }
}
