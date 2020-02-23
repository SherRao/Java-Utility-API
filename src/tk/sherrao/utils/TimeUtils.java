package tk.sherrao.utils;


public abstract class TimeUtils {
    
    private TimeUtils() {
    }

    public static boolean isTimedOut( long started, long timeout ) {
        return ( started <= System.currentTimeMillis() - timeout );
        
    }
    
    public static long nanos() {
        return System.nanoTime();
        
    }
    
    public static long millis() {
        return System.currentTimeMillis();
        
    }
    
    public static long seconds() {
        return millis() / 1000L;
        
    }
    
    public static long minutes() {
        return seconds() / 60L;
        
    }
    
    public static long hours() {
        return minutes() / 60L;
        
    }
    
    public static long days() {
        return hours() / 24L;
        
    }
    
    public static long weeks() {
        return days() / 7;
        
    }
    
    public static long months() {
        throw new UnsupportedOperationException();
        
    }
    
    public static long years() {
        throw new UnsupportedOperationException();
        
    }
    
    public static String time() { 
        throw new UnsupportedOperationException();
        
    }
    
}