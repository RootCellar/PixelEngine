/*
 *
 * Implementing a method of using some physics in the PixelEngine
 * The intent is to cover gravity at the least, but possibly
 * kinetic energy (engines in vehicles) and collisions if those
 * are ever improved.
 *
 * All forces are in newtons, all masses are in kg, and all distance values
 * are in meters
 *
*/

package PixelEngine.Game;

public class Physics
{
  //Final Variables
  public static final String prefix = "[Physics]";
  public static final double WATTS_PER_HORSEPOWER = 746;

  //Variables
  private double pixelsPerMeter = 10;

  public double getPixelsPerMeter() {
    return pixelsPerMeter;
  }

  public double pixelsToMeters(double distance) {
    return distance / pixelsPerMeter;
  }

  public double metersToPixels(double distance) {
    return distance * pixelsPerMeter;
  }

  //F=ma calculate acceleration: F/m = a
  public static double calculateAcceleration(double force, double mass) {
    return force / mass;
  }

  public static double horsepowerToWatts(double horsepower) {
    return horsepower * WATTS_PER_HORSEPOWER;
  }



}
