/*
 * This was just an attempt to support collisions. This can be ignored.
*/

import java.awt.*;
import java.util.ArrayList;

public class CollisionBox
{
  ArrayList<Point> collisionPoints = new ArrayList<Point>();
  
  public double top = 0;
  publc double bottom = 0;
  public double farLeft = 0;
  public double farRight = 0;
  
  public void addPoint(Point p) {
    collisionPoints.add(p);
    
    if(p.getX() < farLeft) farLeft = p.getX();
    if(p.getX() > farRight) farRight = p.getX();
    if(p.getY() < top) top = p.getY();
    if(p.getY() > bottom) bottom = p.getY();
    
  }
  
  public Rectangle getRectangle() {
    return new Rectangle( top, farLeft, farRight - farLeft, bottom - top );
  }
  
}
