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
  public static final double GRAVITATIONAL_CONSTANT = 6.67408 * Math.pow(10, -11);

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

  /*
   * Take distance in pixels, masses in kg, return acceleration in pixels
   * this just returns acceleration in pixels, but does not specify direction.
   * maybe there should be a method for that...
  */
  public double calculateGravityAcceleration(double mass1, double mass2, double distance) {
    //Masses are ok, just multiply them
    double answer = mass1 * mass2;

    //Convert distance from pixels to meters, before dividing by distance squared
    distance = pixelsToMeters(distance);
    answer /= Math.pow(distance, 2);

    //Gravitational constant time...
    answer *= GRAVITATIONAL_CONSTANT;

    //The answer is currently meters/second^2, let us convert to pixels/second^2...
    answer = metersToPixels(answer);

    //Time to return
    return answer;

  }

  /*
   *
   *
   *
  */
  public double[] findDirectionalAcceleration(double acceleration, double angle) {

    double[] answers = new double[2];



    return answers;
  }

}
