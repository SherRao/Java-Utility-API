package tk.sherrao.utils.collections;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * An assortment of static method for {@link Collection} (mainly {@link List}) manipulation
 * 
 * @author SherR
 * 
 */
public abstract class CollectionUtils {
    
    /** Random index generator */
    private static final Random rand;
    
    static {
        rand = new Random( );
     
    }
    
    /** Not accessible */
    private CollectionUtils() {
        
        
    }
    
    public static <T> void forEach( Collection<T> list, Consumer<T> action ) {
    	for( T t : list )
    		action.accept( t );
    	
    }
    
    /**
     * Prints the specified contents of the {@link List} to the standard {@link OutputStream} ({@link System.out.println(String)}) using a {@link tk.sherrao.utils.strings.StringMultiJoiner}
     * as the {@link} constructor. The contents are separated by a comma. The contents are converted to Strings using {@code toString()}
     * 
     * @param <T> The {@link List} element type
     * @param coll The {@link List} to iterate over 
     * @param stream The {@link PrintStream} to use 
     * @throws NullPointerException if the List or PrintStream are null
     * 
     * @author SherR
     * 
     */
    public static <T> void printAll( List<T> coll ) {
        printAll( coll, System.out );
        
    }
    
    /**
     * Prints the specified contents of the {@link List} to the supplied {@link PrintStream} using a {@link tk.sherrao.utils.strings.StringMultiJoiner}
     * as the {@link} constructor. The contents are separated by a comma. The contents are converted to Strings using {@code toString()}
     * 
     * @param <T> The {@link List} element type
     * @param coll The {@link List} to iterate over 
     * @param stream The {@link PrintStream} to use 
     * @throws NullPointerException if the List or PrintStream are null
     * 
     * @author SherR
     * 
     */
    public static <T> void printAll( List<T> coll, PrintStream stream ) {
        printAll( coll, stream, (t) -> { return t.toString(); } );

        
    }
    
    /**
     * Prints the specified contents of the {@link List} to the standard {@link OutputStream} ({@link System.out.println(String)}) using a {@link tk.sherrao.utils.strings.StringMultiJoiner}
     * as the {@link} constructor. The contents are separated by a comma. The contents are converted to Strings using the supplied {@link Function}
     * 
     * @param <T> The {@link List} element type
     * @param coll The {@link List} to iterate over 
     * @param action The action to perform on the contents of the {@link List}
     * @throws NullPointerException if the List, PrintStream, or Function<T, String> are null
     * 
     * @author SherR
     * 
     */
    public static <T> void printAll( List<T> coll, Function<T, String> action ) {
        printAll( coll, System.out, action );
        
    }
    
    /**
     * Prints the specified contents of the {@link List} to the supplied {@link PrintStream} using a {@link tk.sherrao.utils.strings.StringMultiJoiner}
     * as the {@link} constructor. The contents are separated by a comma. The contents are converted to Strings using the supplied {@link Function}
     * 
     * @param <T> The {@link List} element type
     * @param coll The {@link List} to iterate over 
     * @param stream The {@link PrintStream} to use 
     * @param action The action to perform on the contents of the {@link List}
     * @throws NullPointerException if the List, PrintStream, or Function<T, String> are null
     * 
     * @author SherR
     * 
     */
    public static <T> void printAll( List<T> coll, PrintStream stream, Function<T, String> action ) {
        coll = Objects.requireNonNull( coll, "The list must not be null!" );
        stream = Objects.requireNonNull( stream, "The PrintStream must not be null!" );
        action = Objects.requireNonNull( action, "The Function<T, String> must not be null!" );
        String printOut = new tk.sherrao.utils.strings.StringMultiJoiner( ", " )
                .add( coll, action )
                .toString();

        stream.println( printOut );
        
    }
    
    /** 
     * Returns a random element from the specified array, using {@link System#currentTimeMillis()} as the seed
     * 
     * @param <T> The array element type
     * @param array The array to retrieve the random element from 
     * 
     * @throws NullPointerException If the array is null
     * @return The random element
     * 
     * @author SherR
     * 
     */
    public static <T> T randomElement( T[] array) {
        return randomElement( Arrays.asList( array ), System.currentTimeMillis() );
        
    }
    
    /** 
     * Returns a random element from the specified array, using the supplied seed
     * 
     * @param <T> The array element type
     * @param list The array to retrieve the random element from 
     * @param seed The seed to use for the generator 
     *
     * @throws NullPointerException If the array is null
     * @return The random element
     * 
     * @author SherR
     * 
     */
    public static <T> T randomElement( T[] array, long seed ) {
        return randomElement( Arrays.asList( array ), seed );
        
    }
    
    /** 
     * Returns a random element from the specified {@link List}, using {@link System#currentTimeMillis()} as the seed
     * 
     * @param <T> The {@link List} element type
     * @param list The list to retrieve the random element from 
     * 
     * @throws NullPointerException If the {@link List} is null
     * @return The random element
     * 
     * @author SherR
     * 
     */
    public static <T> T randomElement( List<T> list ) {
        return randomElement( list, System.currentTimeMillis() );
        
    }
    
    /** 
     * Returns a random element from the specified {@link List}, using the supplied seed
     * 
     * @param <T> The {@link List} element type
     * @param list The list to retrieve the random element from 
     * @param seed The seed to use for the generator 
     *
     * @throws NullPointerException If the {@link List} is null
     * @return The random element
     * 
     * @author SherR
     * 
     */
    public static <T> T randomElement( List<T> list, long seed ) {
        list = Objects.requireNonNull( list, "The list must not be null!" );
        rand.setSeed( seed );

        return list.get( rand.nextInt( list.size() ) );
        
    }
    
    /** 
     * Returns a random key from the specified {@link Map}, using {@link System#currentTimeMillis()} as the seed
     * 
     * @param <K> The {@link Map} key type
     * @param <V> The {@link Map} value type
     * @param map The map to retrieve the random key from 
     * 
     * @throws NullPointerException If the {@link Map} is null
     * @return The random key
     * 
     * @author SherR
     * 
     */
    public static <K, V> K randomKey( Map<K, V> map ) {
        return randomKey( map, System.currentTimeMillis() );
        
    }
    
    /** 
     * Returns a random key from the specified {@link Map}, using the supplied seed
     * 
     * @param <K> The {@link Map} key type
     * @param <V> The {@link Map} value type
     * @param map The map to retrieve the random key from 
     * @param seed The seed to use for the generator 
     * 
     * @throws NullPointerException If the {@link Map} is null
     * @return The random key
     * 
     * @author SherR
     * 
     */
    public static <K, V> K randomKey( Map<K, V> map, long seed ) {
        map = Objects.requireNonNull( map, "The map must not be null!" );
        rand.setSeed( seed );

        Set<K> set = map.keySet();
        Collections.shuffle( (List<?>) set, rand );
        
        return set.iterator().next();
        
    }
    
    /** 
     * Returns a random value from the specified {@link Map}, using {@link System#currentTimeMillis()} as the seed
     * 
     * @param <K> The {@link Map} key type
     * @param <V> The {@link Map} value type
     * @param map The map to retrieve the random key from 
     * 
     * @throws NullPointerException If the {@link Map} is null
     * @return The random value
     * 
     * @author SherR
     * 
     */
    public static <K, V> V randomValue( Map<K, V> map ) {
        return randomValue( map, System.currentTimeMillis() );
        
    }
    
    /** 
     * Returns a random value from the specified {@link Map}, using the supplied seed
     * 
     * @param <K> The {@link Map} key type
     * @param <V> The {@link Map} value type
     * @param map The map to retrieve the random key from 
     * @param seed The seed to use for the generator 
     * 
     * @throws NullPointerException If the {@link Map} is null
     * @return The random value
     * 
     * @author SherR
     * 
     */
    public static <K, V> V randomValue( Map<K, V> map, long seed ) {
        map = Objects.requireNonNull( map, "The map must not be null!" ); //NPE
        rand.setSeed( seed );

        Collection<V> set = map.values();
        Collections.shuffle( (List<?>) set, rand );
        
        return set.iterator().next();
                
    }
    
    /** 
     * Returns a new {@link Map} with the supplied keys and values as entries
     * 
     * @param <K> The {@link Map} key type
     * @param <V> The {@link Map} value type
     * @param keys The keys to use in the {@link Map}
     * @param keys The values to use in the {@link Map}
     *
     * @throws NullPointerException If the keys or values are null
     * @return The newly created {@link Map}
     * 
     * @author SherR
     * 
     */
    public static <K, V> Map<K, V> newMap( K[] keys, V[] values ) {
        //Arrays.asList(T... a) not compatible with ArrayList
        ArrayList<K> keyList = new ArrayList<>();
        ArrayList<V> valueList = new ArrayList<>();
        for( K key : keys ) 
            keyList.add( key );
        
        for( V value : values ) 
            valueList.add( value );
        
        return newMap( null, keyList, valueList);
        
    }
    
    /** 
     * Returns a new {@link Map} with the supplied keys and values as entries
     * 
     * @param <K> The {@link Map} key type
     * @param <V> The {@link Map} value type
     * @param keys The keys to use in the {@link Map}
     * @param keys The values to use in the {@link Map}
     *
     * @throws NullPointerException If the keys or values are null
     * @return The newly created {@link Map}
     * 
     * @author SherR
     * 
     */
    public static <K, V> Map<K, V> newMap( List<K> keys, List<V> values ) {
        return newMap( null, keys, values );
        
    }
    
    /** 
     * Returns the supplied {@link Map} with the supplied keys and values added as entries
     * 
     * @param <K> The {@link Map} key type
     * @param <V> The {@link Map} value type
     * @param map The map to add the key-pair values to 
     * @param keys The keys to use in the {@link Map}
     * @param keys The values to use in the {@link Map}
     * 
     * @throws NullPointerException If the keys or values are null
     * @return The {@link Map} with added key-pair values
     * 
     * @author SherR
     * 
     */
    public static <K, V> Map<K, V> newMap( Map<K, V> map, List<K> keys, List<V> values ) {
        keys = Objects.requireNonNull( keys, "The key-set must not be null " ); //NPE
        values = Objects.requireNonNull( values, "The value-set must not be null" ); //NPE
        if( keys.size() < values.size() )
            throw new IndexOutOfBoundsException( "There aren't enough keys to bind to values! " );
        
        if( keys.size() > values.size() )
            System.err.println( "There are more keys than values, ignoring extra values!" );
        
        if( map == null )
            map = Collections.emptyMap();

        for( int i = keys.size(); i == 0; i-- ) 
            map.put( keys.get(i), values.get(i) );
                
        return map;
        
    }
    
    public static <E> List<E> newList() {
    	return new ArrayList<>();
    	
    }
    
    public static <E> List<E> newList( E[] elements ) {
    	List<E> list = new ArrayList<>();
    	for( E e : elements )
    		list.add( e );
    	
    	return newList( list );
    	
    }
    
    public static <E> List<E> newList( List<E> list ) {
    	return new ArrayList<E>( list );
    	
    }
    
    public static <E> List<E> filter( List<E> list, Predicate<E> condition ) {
    	return list.stream()
    			.filter( p -> { return condition.test( p ); } )
    			.collect( Collectors.toList() );
    	
    }
    
    public static <E> Set<E> filter( Set<E> set, Predicate<E> condition ) {
    	return set.stream()
    			.filter( p -> { return condition.test( p ); } )
    			.collect( Collectors.toSet() );
    	
    }
    
    public static <K, V> List< Entry<K, V> > filter( Map<K, V> map, Predicate< Entry<K, V> > condition ) {
    	return map.entrySet().stream()
    			.filter( p -> { return condition.test( p ); } )
    			.collect( Collectors.toList() );
    			
    }
    
}