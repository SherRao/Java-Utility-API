package tk.sherrao.utils.collections;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

/**
 * An implementation of a {@link Map} that maps two values for each key.
 * A {@code DoubleValueMap} is thread-safe and can be used concurrently, meaning more than one thread can access a {@code DoubleValueMap} at the same 
 * time, without any inconsistencies.
 * The underlying keys, values, entry-set, and value-set all use {@link ArrayList} for storing and loading objects
 * 
 * @author SherR
 * 
 */
public class DoubleValueMap<K, V1, V2> extends AbstractMap< K, Entry<V1, V2>> 
        implements Map<K, Entry<V1, V2>>, Serializable, Cloneable {
    
    private static final long serialVersionUID = -8984612005981640588L;

    /** Async Lock */
    protected final Object lock = new Object();
    
    /** @serial */
    protected Map<K, Entry<V1, V2>> entries;
    
    /** 
     * Constructs an empty {@code DoubleValueMap} with a default capacity of 16
     * 
     * @author SherR
     *  
     */
    public DoubleValueMap() {
        this(16);
        
    }
    
    /** 
     * Constructs an empty {@code DoubleValueMap} with the supplied capacity
     * 
     * @author SherR
     *  
     */
    public DoubleValueMap( int initialCapacity ) {
        this( initialCapacity, null );
        
    }
    
    /**
     * Constructs a {@code DoubleValueMap} with the supplied map's keys and values already present, and an
     * initial capacity of the {@code supplied map's size + 16}
     *
     * Uses {@link DoubleValueMap#merge(DoubleValueMap)}
     *
     * @author SherR
     * 
     */
    public DoubleValueMap( DoubleValueMap<K, V1, V2> map ) {
        this( map.size() + 16, map );
    }
    
    /**
     * Constructs a {@code DoubleValueMap} with the supplied map's keys and values already present, and an
     * initial capacity of the {@code supplied map's size + supplied initial capacity}
     *
     * Uses {@link DoubleValueMap#merge(DoubleValueMap)}
     *
     * @author SherR
     * 
     */
    public DoubleValueMap( int initialCapacity, DoubleValueMap<K, V1, V2> map ) {
        this.entries = new HashMap<>( map.size() + initialCapacity );
        if( map != null && !map.isEmpty() )
            merge( map );
        
    }
    
    /** 
     * 
     * @author SherR
     * 
     */
    @Override
    public void clear() {
        synchronized( lock ) {
            entries.clear();
            
        }
    }
    
    /** 
     * 
     * @author SherR
     * 
     */
    @Override
    public int size() {
        synchronized( lock ) { 
        	return entries.size(); 
        	
        }
    }

    /** 
     * 
     * @author SherR
     * 
     */
    @Override
    public boolean isEmpty() {
        synchronized( lock ) { 
        	return entries.isEmpty(); 
        
        }
    }
    
    /** 
     * 
     * @author SherR
     * 
     */
    @Override
    public int hashCode() {
        synchronized( lock ) { 
            return Objects.hashCode( entries );
        
        }
    }
    
    /** 
     * 
     * 
     */
    @Override
    public Set< Entry<K, Entry<V1, V2>> > entrySet() {
    	return entries.entrySet();
    	
    }
    
    /** 
     * 
     * 
     */
    @Override
    public boolean containsKey( Object key ) {
    	return entries.containsKey( key );
    	
    }
    
    /** 
     *
     * @deprecated Unsupported Operation 
     * @throws UnsupportedOperationException Use {@link DoubleValueMap#hasFirstValue(Object)} or {@link DoubleValueMap#hasSecondValue(Object)}
     * 
     */
    @Override
    public boolean containsValue( Object value ) {
        throw new UnsupportedOperationException( "Use DoubleValueMap.hasFirstValue(Object) or DoubleValueMap.hasSecondValue(Object)" );

    }
    
    /** 
     * 
     * 
     */
    @Override
    public Entry<V1, V2> get( Object key ) {
    	try {
    		return entries.get( key );
    	
    	} catch( NullPointerException | ClassCastException e ) {
    		return null;
    		
    	}
    }
    
    /** 
     * 
     * 
     */
    @Override
    public Entry<V1, V2> remove( Object key ) {
        throw new UnsupportedOperationException( "Use DoubleValueMap.delete(Object)" );

    }
    
    /** 
     *
     * @deprecated Unsupported Operation 
     * @throws UnsupportedOperationException Use {@link DoubleValueMap#merge(DoubleValueMap)} 
     * 
     */
    @Override
    public void putAll( Map<? extends K, ? extends Entry<V1, V2>> m ) {
        throw new UnsupportedOperationException( "Use DoubleValueMap.merge(DoubleValueMap)" );
    
    }
    
    /** 
     * 
     * 
     */
    @Override
    public Set<K> keySet() {
    	return entries.keySet();
   
    }
    
    /** 
     * 
     * 
     */
    @Override
    public Collection<Entry<V1, V2>> values() {
    	return entries.values();

    }
    
    /**
     * 
     * 
     */
    public void place( K key, V1 firstValue, V2 secondValue ) {
        add( key, firstValue, secondValue );
        
    }
    
    /**
     * 
     *
     */
    public void add( K key, V1 firstValue, V2 secondValue ) {
        synchronized( lock ) { 
            
        }
    }
    
    /**
     * 
     * 
     */
    public void delete( K key ) {
        synchronized( lock ) {
            
        }
    }
    
    /**
     * 
     * 
     */
    public void merge( DoubleValueMap<K, V1, V2> mergeWith ) {
        mergeWith = Objects.requireNonNull( mergeWith, "The DoubleValueMap must not be null!" );
        
    }
    
    /**
     * 
     * 
     */
    public V1 getFirstValue( K key ) { 
    	return null;

    }
    
    /**
     * 
     * 
     */
    public V2 getSecondValue( K key ) { 
    	return null;
    
    }

    /**
     * 
     * 
     */
    public Collection<K> keys() { 
    	return null;
        
    }
    
    /**
     * 
     * 
     */
    public Collection<V1> firstValues() { 
    	return null;
        
    }
    
    /**
     * 
     * 
     */
    public Collection<V2> secondValues() { 
    	return null;
    
    }

    /**
     * 
     * 
     */
    public Collection< Entry< K, Entry<V1, V2> > > entries() { 
    	return null;
    
    }
    
    /**
     * 
     * 
     */
    public boolean hasKey( K key ) {
    	return false;
        
    }
    
    /**
     * 
     * 
     */
    public boolean hasFirstValue( V1 value ) { 
    	return false;
        
    }
    
    /**
     * 
     * 
     */
    public boolean hasSecondValue( V2 value ) { 
    	return false;
        
    }
    
}