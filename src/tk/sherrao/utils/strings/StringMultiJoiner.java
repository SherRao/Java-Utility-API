package tk.sherrao.utils.strings;

import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;

/**
 * {@code StringMultiJoiner} is used to construct a sequence of characters separated
 * by a delimiter and optionally starting with a supplied prefix
 * and ending with an optional supplied suffix.
 * <p>
 * Prior to adding something to the {@code StringMultiJoiner}, its
 * {@code StringMultiJoiner.toString()} method will, by default, return {@code prefix + suffix}.
 * However, if the {@code setEmptyValue} method is called, the {@code emptyValue}
 * supplied will be returned instead. This can be used, for example, when
 * creating a string using set notation to indicate an empty set, i.e.
 * <code>"{}"</code>, where the {@code prefix} is <code>"{"</code>, the
 * {@code suffix} is <code>"}"</code> and nothing has been added to the
 * {@code StringJoiner}.
 *
 * @apiNote
 * <p>The String {@code "[George:Sally:Fred]"} may be constructed as follows:
 *
 * <pre> {@code
 * StringMultiJoiner sj = new StringMultiJoiner(":", "[", "]");
 * sj.add("George").add("Sally").add("Fred");
 * String desiredString = sj.toString();
 * }</pre>
 * <p>
 * A {@code StringMultiJoiner} may be employed to create formatted output from a
 * {@link java.util.stream.Stream} using
 * {@link java.util.stream.Collectors#joining(CharSequence)}. For example:
 *
 * <pre> {@code
 * List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
 * String commaSeparatedNumbers = numbers.stream()
 *     .map(i -> i.toString())
 *     .collect(Collectors.joining(", "));
 * }</pre>
 *
 * @see java.util.stream.Collectors#joining(CharSequence)
 * @see java.util.stream.Collectors#joining(CharSequence, CharSequence, CharSequence)
 * 
 * @author SherR
 *
 */
public class StringMultiJoiner implements CharSequence {
    
    protected StringJoiner sj;
    
    /**
     * Constructs a {@code StringMultiJoiner} with no characters in it, with no
     * {@code prefix} or {@code suffix}, and a copy of the supplied
     * {@code delimiter}.
     * If no characters are added to the {@code StringMultiJoiner} and methods
     * accessing the value of it are invoked, it will not return a
     * {@code prefix} or {@code suffix} (or properties thereof) in the result,
     * unless {@code setEmptyValue} has first been called.
     *
     * @param  delimiter the sequence of characters to be used between each element added to the {@code StringMultiJoiner} value
     * @throws NullPointerException if {@code delimiter} is {@code null}
     *
     * @author SherR
     *
     */
    public StringMultiJoiner( CharSequence delimiter ) {
        this( delimiter, "" );
        
    }
    
    /**
     * Constructs a {@code StringMultiJoiner} with no characters in it using copies
     * of the supplied {@code prefix} and {@code delimiter}.
     * If no characters are added to the {@code StringMultiJoiner} and methods
     * accessing the string value of it are invoked, it will return the
     * {@code prefix} (or properties thereof) in the result, unless
     * {@code setEmptyValue} has first been called.
     *
     * @param  delimiter the sequence of characters to be used between each element added to the {@code StringJoiner}
     * @param  prefix the sequence of characters to be used at the beginning
     * 
     * @throws NullPointerException if {@code prefix} or {@code delimiter} is {@code null}
     * 
     * @author SherR
     * 
     */
    public StringMultiJoiner( CharSequence delimiter, CharSequence prefix ) {
        this( delimiter, prefix, "" );
        
    }
    
    
    /**
     * Constructs a {@code StringMultiJoiner} with no characters in it using copies
     * of the supplied {@code prefix}, {@code delimiter} and {@code suffix}.
     * If no characters are added to the {@code StringMultiJoiner} and methods
     * accessing the string value of it are invoked, it will return the
     * {@code prefix + suffix} (or properties thereof) in the result, unless
     * {@code setEmptyValue} has first been called.
     *
     * @param  delimiter the sequence of characters to be used between each element added to the {@code StringJoiner}
     * @param  prefix the sequence of characters to be used at the beginning
     * @param  suffix the sequence of characters to be used at the end
     * 
     * @throws NullPointerException if {@code prefix}, {@code delimiter}, or {@code suffix} is {@code null}
     * 
     * @author SherR
     * 
     */
    public StringMultiJoiner( CharSequence delimiter, CharSequence prefix, CharSequence suffix ) {
        sj = new StringJoiner( delimiter, prefix, suffix );

    }

    /**
     * Adds copies of the given {@code CharSequence} values as the next
     * elements of the {@code StringMultiJoiner} value. If {@code newElements} is
     * {@code null}, then {@code "null"} is added.
     *
     * @param  newElements The elements to add
     * @return This {@code StringMultiJoiner} 
     * 
     * @author SherR
     * 
     */
    public StringMultiJoiner add( CharSequence... newElements ) {
        for( CharSequence cs : newElements )
            add( cs );
        
        return this;
        
    }
    
    /**
     * Adds a copy of the given {@code CharSequence} value as the next
     * element of the {@code StringMultiJoiner} value. If {@code newElement} is
     * {@code null}, then {@code "null"} is added.
     *
     * @param  newElement The element to add
     * @return This {@code StringMultiJoiner} 
     * 
     * @author SherR
     * 
     */
    public StringMultiJoiner add( CharSequence newElement ) {
        sj.add( newElement );
        return this;
    
    }
    
    /**
     * Adds copies of the given {@code Number} values as the next
     * elements of the {@code StringMultiJoiner} value. If {@code numbers} is
     * {@code null}, then {@code "null"} is added.
     *
     * @param  numbers The elements to add
     * @return This {@code StringMultiJoiner} 
     * 
     * @author SherR
     * 
     */
    public StringMultiJoiner add( Number... numbers ) {
        for( Number n : numbers )
            add( n );
        
        return this;

    }
    
    /**
     * Adds a copy of the given {@code Number} value as the next
     * element of the {@code StringMultiJoiner} value. If {@code number} is
     * {@code null}, then {@code "null"} is added.
     *
     * @param  number The element to add
     * @return This {@code StringMultiJoiner} 
     * 
     * @author SherR
     * 
     */
    public StringMultiJoiner add( Number number ) {
        if( number instanceof Double ) 
            add( Double.toString( number.doubleValue() ));
             
        else if( number instanceof Long )
            add( Long.toString( number.longValue() ));
            
        else if( number instanceof Byte ) 
            add( Byte.toString( number.byteValue() ));

        else if( number instanceof Float )
            add( Float.toString( number.floatValue() ));
        
        else if( number instanceof Integer ) 
            add( Integer.toString( number.intValue() ));
        
        else if( number instanceof Short ) 
            add( Short.toString( number.shortValue() ));
        
        else 
            throw new IllegalArgumentException( "Argument has to be a number!" );
        
        return this;
    
    }
    
    /**
     * Adds a list of {@link Objects} that get turned into strings via a supplied {@link Function}
     *
     * @param list The list that the {@link Function} gets applied to
     * @param function The action to perform on each element to be transformed to a {@link String}
     * 
     * @return This {@code StringMultiJoiner} 
     * 
     * @author SherR
     * 
     */
    public <T> StringMultiJoiner add( List<T> list, Function<T, String> function ) {
        for( T t : list )
            add( function.apply(t) );
        
        return this;

    }
    
    /**
     * Adds a list of {@link Objects} that get turned into strings via {@link Object#toString()}
     *
     * @param list The list that gets transformed to a list of strings
     * @return This {@code StringMultiJoiner} 
     * 
     * @author SherR
     * 
     */
    public <T> StringMultiJoiner add( List<T> list ) {
        add( list, (t) -> { return t.toString(); } );
       
        return this;

    }
    
    /**
     * Adds the contents of the given {@code StringMultiJoiner} without prefix and
     * suffix as the next element if it is non-empty. If the given {@code
     * StringMultiJoiner} is empty, the call has no effect.
     *
     * <p>A {@code StringMultiJoiner} is empty if {@link #add(CharSequence) add()}
     * has never been called, and if {@code merge()} has never been called
     * with a non-empty {@code StringMultiJoiner} argument.
     *
     * <p>If the other {@code StringMultiJoiner} is using a different delimiter,
     * then elements from the other {@code StringMultiJoiner} are concatenated with
     * that delimiter and the result is appended to this {@code StringMultiJoiner}
     * as a single element.
     *
     * @param other The {@code StringMultiJoiner} whose contents should be merged into this one
     * 
     * @throws NullPointerException if the other {@code StringMultiJoiner} is null
     * @return This {@code StringMultiJoiner} 
     * 
     * @author SherR
     * 
     */
    public StringMultiJoiner merge( StringMultiJoiner other ) {
        merge( other.toStringJoiner() );
       
        return this;
    
    }
    
    /**
     * Adds the contents of the given {@code StringJoiner} without prefix and
     * suffix as the next element if it is non-empty. If the given {@code
     * StringJoiner} is empty, the call has no effect.
     *
     * <p>A {@code StringJoiner} is empty if {@link #add(CharSequence) add()}
     * has never been called, and if {@code merge()} has never been called
     * with a non-empty {@code StringMultiJoiner} or {@code StringJoiner} argument.
     *
     * <p>If the other {@code StringJoiner} is using a different delimiter,
     * then elements from the other {@code StringJoiner} are concatenated with
     * that delimiter and the result is appended to this {@code StringMultiJoiner}
     * as a single element.
     *
     * @param other The {@code StringJoiner} whose contents should be merged into this one
     * 
     * @throws NullPointerException if the other {@code StringJoiner} is null
     * @return This {@code StringMultiJoiner} 
     * 
     * @author SherR
     *
     */    
    public StringMultiJoiner merge( StringJoiner other ) {
        sj.merge( other );
        return this;

    }
    
    /**
     * Sets the sequence of characters to be used when determining the string
     * representation of this {@code StringMultiJoiner} and no elements have been
     * added yet, that is, when it is empty.  A copy of the {@code emptyValue}
     * parameter is made for this purpose. Note that once an add method has been
     * called, the {@code StringMultiJoiner} is no longer considered empty, even if
     * the element(s) added correspond to the empty {@code String}.
     *
     * @param  emptyValue the characters to return as the value of an empty {@code StringJoiner}
     * 
     * @return This {@code StringMultiJoiner} 
     * @throws NullPointerException when the {@code emptyValue} parameter is {@code null}
     * 
     * @author SherR 
     * 
     */    
    public StringMultiJoiner setEmptyValue( CharSequence emptyValue ) {
        sj.setEmptyValue( emptyValue );
       
        return this;
   
    }
    
    /**
     * Returns the underlying {@link StringJoiner} being used by this {@code StringMulitJoiner}
     *
     * @return the underlying {@link StringJoiner} being used
     * 
     * @author SherR
     * 
     */
    public StringJoiner toStringJoiner() { 
        return sj;
        
    }
    
    /**
     * Returns the length of the {@code String} representation
     * of this {@code StringJoiner}. Note that if
     * no add methods have been called, then the length of the {@code String}
     * representation (either {@code prefix + suffix} or {@code emptyValue})
     * will be returned. The value should be equivalent to
     * {@code toString().length()}.
     *
     * @return the length of the current value of {@code StringJoiner}
     * 
     * @author SherR
     * 
     */
    @Override
    public int length() {
        return sj.length();

    }
    
    /**
     * Returns the current value, consisting of the {@code prefix}, the values
     * added so far separated by the {@code delimiter}, and the {@code suffix},
     * unless no elements have been added in which case, the
     * {@code prefix + suffix} or the {@code emptyValue} characters are returned
     *
     * @return the string representation of this {@code StringJoiner}
     * 
     * @author SherR
     * 
     */
    @Override
    public String toString() {
        return sj.toString();
        
    }

    @Override
    public char charAt( int index ) {
        return sj.toString().charAt( index );
    
    }

    @Override
    public CharSequence subSequence( int start, int end ) {
        return sj.toString().substring( start, end );
        
    }
     
}