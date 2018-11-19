import PixelEngine.Screen.*;

public class MouseHelper
{
  PixelCanvas canvas;
  
  int dx = 0;
  int dy = 0;
  
  double zoom = 1;
  
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
    
    mousePos.translate(dx, dy);
    
    /*
    
    //May or may not work properly
    mousePos.setLocation( mousePos.getX() * zoom, mousePos.getY() * zoom );
    
    */
    
    return mousePos;
    
  }
  
}
