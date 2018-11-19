package PixelEngine.Screen;

import java.awt.*;

public class StatusInfo
{
  
  int dx = 0;
  int dy = 0;
  String message = "{NOT SET}";
  
  int r = 255;
  int g = 255;
  int b = 255;

  public void setMessage(String s) {
    message = s;
  }
  
  public void setPos(int x, int y) {
    dx = x;
    dy = y;
  }
  
  public void setColor(int red, int blue, int green) {
    r = red;
    b = blue;
    g = green;
  }

  public void draw(Graphics g) {
    g.drawString(message, dx, dy, r, g, b);
  }
  
}
