package tk.sherrao.utils.strings;

import java.util.concurrent.TimeUnit;

/**
 * {@code StringTime} can be used to construct a {@link String} that represents a date
 * 
 * The formatting can be changed of the date by using {@link StringTime#setFormat(String)}
 * The formatting codes are as follows: 
 *    <li> <b>[d]</b>: The days of the current {@code StringTime} value </li>
 *    <li> <b>[h]</b>: The hours of the current {@code StringTime} value</li>
 *    <li> <b>[m]</b>: The minutes of the current {@code StringTime} value</li>
 *    <li> <b>[s]</b>: The seconds of the current {@code StringTime} value</li>
 *    <li> <b>[ms]</b>: The milliseconds of the current {@code StringTime} value</li>
 *    <li> <b>[mrs]</b>: The microseconds of the current {@code StringTime} value</li>
 *    <li> <b>[ns]</b>: The nanoseconds of the current {@code StringTime} value</li> 
 *    
 * These formatting codes can be used to construct a time-format as this:
 *   <pre> 
 *   {@code
 *   StringTime stringTime = new StringTime( System.currentTimeMillis() );
 *   stringTime.setFormat( "Days: [d] Time: [h]:[m]:[s]" );
 *   System.out.println( stringTime.toString() ); 
 *   } 
 *   </pre>
 * Which would output something along the lines of:
 *   <li>Days: 12 Time: 12:54:23</li>
 *   
 * @author SherR 
 * 
 */
public class StringTime implements CharSequence {
    
    protected long milliseconds;
    protected String format;

    protected int days, hours, minutes, seconds, millis, micros, nanos;

    /** 
     * Constructs a new {@code StringTime} object with the {@link System#currentTimeMillis()} as it's time
     * 
     * @author SherRao
     * 
     */
    public StringTime() {
        this( System.currentTimeMillis() );
        
    }
    
    /**
     * Constructs a new {@code StringTime} object with the time specified and the default format: 
     * <pre>{@code [d]:[h]:[m]:[s] ([ms]) ([mrs]) ([ns]) }</pre> 
     * 
     * @param milliseconds The milliseconds that should be used for the date, same as {@link StringTime#setMillis(long)}
     *
     * @author SherR
     * 
     */
    public StringTime( long milliseconds ) {
        this( milliseconds, null );
        
    }
    
    /**
     * Constructs a new {@code StringTime} object with the time specified and format
     * 
     * @param milliseconds The milliseconds that should be used for the date, same as {@link StringTime#setMillis(long)} 
     * @param format The formatting used to output the time to a {@link String}, same as {@link StringTime#setFormat(String)}
     * If the supplied format is {@code null}, then the default format is used:
     * <pre>{@code [d]:[h]:[m]:[s] ([ms]) ([mrs]) ([ns]) }</pre> 
     * 
     * @author SherR
     * 
     */
    public StringTime( long milliseconds, String format ) {
        this.milliseconds = milliseconds;
        if( format == null )
            this.format = "[d]:[h]:[m]:[s] ([ms]) ([mrs]) ([ns])";

        else
            this.format = format;

    }
    
    /**
     * Converts the supplied date to a string using the format specified, or the default format is none was set
     * 
     * @return time The formatted date (using the supplied format) to a {@link String}
     * 
     * @author SherR
     * @see StringTime#setFormat(String)
     * @see StringTime#setMillis(long)
     * 
     */
    @Override
    public String toString() { 
        String time = format;
        long milliseconds = this.milliseconds;
        if( time.contains( "[d]" )) {
            days = (int) TimeUnit.MILLISECONDS.toDays( milliseconds );
            milliseconds -= TimeUnit.DAYS.toMillis( days );
            
            time = time.replace( "[d]", String.valueOf( days ) );
        } 
        
        if( time.contains( "h" )) {
            hours = (int) TimeUnit.MILLISECONDS.toHours( milliseconds );
            milliseconds -= TimeUnit.HOURS.toMillis( hours );
            if( time.contains( "[hh]" ) )
                time = time.replace( "[hh]", String.valueOf( hours > 12 ? 24 - hours : hours ) );
                
            else if( time.contains( "[h]" ) ) 
                time = time.replace( "[h]", String.valueOf( hours ) );
        }
        
        if( time.contains( "[m]" )) {
            minutes = (int) TimeUnit.MILLISECONDS.toMinutes( milliseconds );
            milliseconds -= TimeUnit.MINUTES.toMillis( minutes );
            
            time = time.replace( "[m]", String.valueOf( minutes ) );
        }
        
        if( time.contains( "[s]" ) ) {
            seconds = (int) TimeUnit.MILLISECONDS.toSeconds( milliseconds );
            milliseconds -= TimeUnit.SECONDS.toMillis( seconds );
            
            time = time.replace( "[s]", String.valueOf( seconds ) );
        }

        if( time.contains( "[ms]" ) ) {
            millis = (int) TimeUnit.MILLISECONDS.toMillis( milliseconds );
            milliseconds -= millis;
            
            time = time.replace( "[ms]", String.valueOf( millis ) );
        }
        
        if( time.contains( "[mrs]" ) ) {
            micros = (int) TimeUnit.MILLISECONDS.toMicros( milliseconds );
            milliseconds -= TimeUnit.MICROSECONDS.toMillis( micros );
            
            time = time.replace( "[mrs]", String.valueOf( micros ) );
        }
        
        if( time.contains( "[ns]" ) ) {
            nanos = (int) TimeUnit.MILLISECONDS.toNanos( milliseconds );
            milliseconds -= TimeUnit.NANOSECONDS.toMillis( nanos );
            
            time = time.replace( "[ns]", String.valueOf( nanos ) );
        }
        
        return time;
    }        
    
    
    /**
     * Sets the milliseconds that should be converted to a {@link String}
     *
     * @param The milliseconds that should be converted
     * 
     * @author SherR
     * 
     */
    public StringTime setMillis( long milliseconds ) { 
        this.milliseconds = milliseconds; 
        
        return this;
        
    }
    
    /**
     * Sets the format that should be used for conversion to a {@link String}
     *
     * @param The format The format that should be used for conversion
     * 
     * @author SherR
     * 
     */
    public StringTime setFormat( String format ) { 
        this.format = format; 
       
        return this;
        
    }
    
    /**
     * Fetches the current milliseconds being used by this {@code StringTime}
     * @return millis Current milliseconds being used
     * 
     * @author SherR
     * 
     */
    public long getMillis() { return millis; }
    
    /**
     * Fetches the current format being used by this {@code StringTime}
     * @return format Current format being used
     * 
     * @author SherR
     * 
     */
    public String getFormat() { return format; }
  
    /**
     * Takes the duration of time from the current time
     * Uses {@link System#currentTimeMillis()}
     * 
     * @author SherR
     * 
     */
    public static long takeTimeFromNow( long amountToTake ) {
        return System.currentTimeMillis() - amountToTake;
        
    }

    @Override
    public int length() {
        return toString().length();
    
    }

    @Override
    public char charAt( int index ) {
        return toString().charAt( index );
    
    }

    @Override
    public CharSequence subSequence( int start, int end ) {
        return toString().substring( start, end );
        
    }
    
}