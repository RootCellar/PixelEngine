import PixelEngine.Game.*;
import PixelEngine.Screen.*;

public class SPlayer extends Player
{
    public SPlayer() {
        super();
        
        hp = maxHp = 100;
        
        size = 15;
        speed = 5;
        regen = 0;
    }
    
    public void tick() {
        super.tick();
        
        if(rot > 360) rot -= 360;
        if(rot < 0) rot += 360;
    }
    
    public void render(PixelCanvas c) {
        
        c.drawPolygon(x, y, size, 4, rot, 0, 255, 0);
        
        double[] pos = findPosByAngle(x, y, rot, size);
        c.drawPolygon(x + pos[0], y + pos[1], size, 5, rot, 0, 255, 0);
        
        pos = findPosByAngle(x, y, rot, size);
        c.drawPolygon(x - pos[0], y - pos[1], size, 5, rot, 0, 255, 0);
        
        HPbar.render(c);
    }

}
