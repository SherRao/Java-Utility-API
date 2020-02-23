package tk.sherrao.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Random {
    
    protected static ThreadLocalRandom gen;
    static final char[] ALL_CHARS;
    
    static {
        StringBuilder sb = new StringBuilder();
        for( char c = '0'; c <= '9'; ++c )
            sb.append(c);
          
        for (char c = 'a'; c <= 'z'; ++c )
            sb.append(c);
        
        gen = ThreadLocalRandom.current();
        ALL_CHARS = sb.toString().toCharArray();
    
    }
    
    private Random() {

    }

    public static int genInt() {
        return genInt(0);
        
    }
    
    public static int genInt( int min ) {
        return genInt( min, Integer.MAX_VALUE );
        
    }
    
    public static int genInt( int min, int max ) {
        return gen.nextInt( min, max );
        
    }
    
    
    public static long genLong() {
        return genLong(0);
        
    }
    
    public static long genLong( long min ) {
        return genLong( 0, Long.MAX_VALUE );
        
    }
    
    public static long genLong( long min, long max ) {
        return gen.nextLong( min, max );
        
    }
    
    
    public static double genDouble() {
        return genDouble(0);
        
    }
    
    public static double genDouble( double min ) {
        return genDouble( min, Double.MAX_VALUE );
        
    }
    
    public static double genDouble( double min, double max ) {
        return gen.nextDouble( min, max );
        
    }

    
    public static float genFloat() {
        return genFloat(0F);
        
    }
    
    public static float genFloat( float min ) {
        return genFloat( 0F, min );
        
    }
    
    public static float genFloat( float min, float max ) {
        return gen.nextFloat() + (max - min);
        
    }
    
    
    public static short genShort() {
        return genShort( (short) 0 );
        
    }
    
    public static short genShort( short min ) {
        return genShort( min, Short.MAX_VALUE );
        
    }

    public static short genShort( short min, short max ) {
        return (short) gen.ints().sum();
        
    }
    
    
    public static boolean genBool() {
        return gen.nextBoolean();
        
    }
    
    public static String genString() {
        return genString(32);
        
    }
    
    public static String genString( int characterAmount ) {
        StringBuilder sb = new StringBuilder();
        for( int i = 0; i < characterAmount; i++ ) 
            sb.append( ALL_CHARS[ genInt( 0, ALL_CHARS.length ) ] );
        
        return sb.toString();
    }

}