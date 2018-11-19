import PixelEngine.Screen.*;

public class MouseHelper
{
  PixelCanvas canvas;
  
  double dx = 0;
  double dy = 0;
  
  double zoom = 1;
  
  double wasX = 0;
  double wasY = 0;
  
  public MouseHelper(PixelCanvas c) {
     canvas = c;
  }
  
  public void setCenter(int x, int y) {
    dx = x;
    dy = y;
  }
  
  public void setZoom(double z) {
    zoom = z; 
  }
  
  public Point getPoint() {
    Point mousePos = canvas.getMousePosition();
    
    if(mousePos == null) {
      mousePos = new Point(wasX, wasY);
    }
    
    wasX = mousePos.getX();
    wasY = mousePos.getY();
    
    mousePos.translate(dx, dy);
    
    /*
    
    //May or may not work properly
    mousePos.setLocation( mousePos.getX() * zoom, mousePos.getY() * zoom );
    
    */
    
    return mousePos;
    
  }
  
}
