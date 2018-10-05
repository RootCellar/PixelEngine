package PixelEngine.Util;

import java.util.Random;

public class Randomizer
{
    static Random rand = new Random();
    
    static Random randSeeds = new Random();
    
    public static void reSeed() {
        rand.setSeed( randSeeds.nextLong() );
    }
    
    public static boolean nextBoolean() {
        return rand.nextBoolean();
    }
    
    public static int nextInt(int min, int max) {
        if(min < 0 || max < 0) return -1;
        if(min >= max) return -1;
        
        return min + rand.nextInt( ( max - min ) + 1 );
    }
    
    public static double nextDouble(double max) {
        return rand.nextDouble() * max;
    }
    
    public static double nextDouble(double min, double max) {
        if(min < 0 || max < 0) return -1.0;
        if(min >= max) return -1.0;
        
        return min + nextDouble( ( max - min ) );
    }
}