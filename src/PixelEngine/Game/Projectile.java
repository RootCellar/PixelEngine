package PixelEngine.Game;

import PixelEngine.Screen.*;
import PixelEngine.Network.*;

import java.util.ArrayList;
public class Projectile extends Entity
{
    public double velocX = 0;
    public double velocY = 0;
    public int maxTime = 1000;

    public int time = 0;
    public int damage = 0;
    public int damageRange = 10;
    public int size = 2;

    public double rot = 0;
    public double speed = 0;

    public Mob shooter;
    public Team team;

    public Projectile(Mob m) {
        shooter = m;
        team = shooter.team;
    }
    
    public Message getSpawnMessage() {
        return new Message( (short) MessageTypes.getId("PROJ_SPAWN"), id);
    }
    
    public Message getDespawnMessage() {
        return new Message( (short) MessageTypes.getId("PROJ_REMOVE"), id);
    }

    //TODO: add support for missiles (find angle to target, rotate at given speed, ....)

    public void setOffset(double acc) {
        double offset = (Math.round( Math.random() * ( acc * 2 ) ) - acc );
        setByRot(rot + offset, speed);
    }

    public void setByRot(double r, double s) {
        speed = s;
        rot = r;
        velocX = Math.cos( Math.toRadians(rot) ) * speed;
        velocY = Math.sin( Math.toRadians(rot) ) * speed;
    }

    public void hit(Mob target) {

        //if(team==null || target.team==null) target.damage( damage, this, shooter);
        if(!target.team.equals(team)) {
            target.damage( damage, shooter );
            remove();
        }

    }

    public void remove() {
        level.remove(this);
    }

    public void tick() {
        time++;
        if(time>=maxTime) {
            remove();
        }
        x+=velocX;
        y+=velocY;

        hitInRange();
    }

    //TODO: Add support for mutliple types of collision detection.
    public void hitInRange() {
        ArrayList<Mob> entities = level.getInRange( x, y, damageRange);

        for(int i=0; i<entities.size(); i++) {
            Mob m = entities.get(i);
            if(!team.equals(m.team)) hit(m);
        }

    }

    public void render(PixelCanvas c) {
        for(int i=0-size; i<=size; i++) {
            for(int k=0-size; k<=size; k++) {
                c.drawPixel(x+i, y+k, 0, 255, 0);
            }
        }
        //game.drawPixel(x, y, 0, 255, 0);
    }
}
