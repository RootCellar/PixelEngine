import PixelEngine.Game.*;

import PixelEngine.Screen.*;

public class InvaderPlayer extends Player{

    int shootTime = 0;

    public InvaderPlayer() {
        super();

        name = "Invader Player";
    }

    //Our own way of ticking
    //(But we're still going to tick like a normal player, using super...)
    public void tick() {
        super.tick();

        if(shootTime > 0) shootTime--;

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

}
