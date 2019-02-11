/*
*
*   Darian Marvel - 2/1/19
*   Making a special player class just for the invader game
*
*/

import PixelEngine.Game.*;
import PixelEngine.Screen.*;

public class InvaderPlayer extends Player {

    int shootTime = 0;

    int score = 0;

    public InvaderPlayer() {
        super();

        name = "Invader Player";
        regen = 0.001;
    }

    public void damage(double a) {
        hp-=a;
        damageTime = 400;
        checkHp();

        score -= 100;
    }

    public void killed(Mob m) {
        if(m instanceof Invader) score += 50;
        if(m instanceof InvaderBoss) score += 1000;
    }

    //Our own way of ticking
    //(But we're still going to tick like a normal player, using super...)
    public void tick() {
        super.tick();

        if(shootTime > 0) shootTime--;
        damageTime = 0;

        if(score < 0) score = 0;

    }

    //Shoot a projectile
    public void shoot() {
        if(shootTime > 0) return;
        Projectile pProj = new Projectile(this); //Create projectile

        //Make sure that it starts where we are
        //Weird things happen if this isn't done...
        pProj.x = x;
        pProj.y = y;

        //270 degrees is actually upwards, it's weird...
        //(Upside down unit circle)
        //Moves 10 pixels per tick
        pProj.setByRot(270, 10);

        //Allows us to make the angle vary by x degrees, 100% accuracy is boring
        pProj.setOffset(10);

        pProj.damage = 15;

        //Put it in the level
        level.add(pProj);

        shootTime = 5;
    }

    public void render(PixelCanvas c) {

        c.drawPolygon(x, y, 10, 3, -90, 255, 255, 255);

        double[] pos = findPosByAngle(x, y, 30, 10);
        c.drawPolygon(x + pos[0], y + pos[1], 7, 3, 90, 255, 255, 255);

        pos = findPosByAngle(x, y, 150, 10);
        c.drawPolygon(x + pos[0], y + pos[1], 7, 3, 90, 255, 255, 255);

        HPbar.render(c);
    }

}
