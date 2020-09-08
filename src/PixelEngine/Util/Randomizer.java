/*
 *
 * This class can be used to assist with random number generation.
 * It even supports automatic reseeding, to change the seed each time something is generated
 * to spice up the random values that are obtained. Caution though, that functionality
 * is slower than just grabbing random numbers.
 *
*/

package PixelEngine.Util;

import java.util.Random;

public class Randomizer
{
    static Random rand = new Random();
    
    static Random randSeeds = new Random();
    
    static boolean AUTO_RESEED = true;
    
    public static void reSeed() {
        rand.setSeed( randSeeds.nextLong() );
    }
    
    public static double nextGaussian(double mean, double dev) {
        return ( rand.nextGaussian() * dev ) + mean;
    }
    
    public static boolean nextBoolean() {
        if(AUTO_RESEED) reSeed();
        
        return rand.nextBoolean();
    }
    
    public static int nextInt(int min, int max) {
        if(AUTO_RESEED) reSeed();
        
        //if(min < 0 || max < 0) return -1; //Not right...
        if(min >= max) {
            int c = Math.min(max, min);
            max = Math.max(max, min);
            min = c;
        }
        
        return min + rand.nextInt( ( max - min ) + 1 );
    }
    
    public static double nextDouble(double max) {
        if(AUTO_RESEED) reSeed();
        
        return rand.nextDouble() * max;
    }
    
    public static double nextDouble(double min, double max) {
        if(AUTO_RESEED) reSeed();
        
        //if(min < 0 || max < 0) return -1.0; //That's not right...
        if(min >= max) {
            double c = Math.min(min, max);
            max = Math.max(min,max);
            min = c;
        }
        
        return min + nextDouble( ( max - min ) );
    }
}
