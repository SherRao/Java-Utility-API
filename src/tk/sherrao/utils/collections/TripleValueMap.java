package tk.sherrao.utils.collections;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import tk.sherrao.utils.collections.Pair;

/**
 * An implementation of a {@link Map} that maps three values for each key.
 * A {@code TripleValueMap} is thread-safe and can be used concurrently, meaning more than one thread can access a {@code TripleValueMap} at the same 
 * time, without any inconsistencies.
 * The underlying keys, values, entry-set, and value-set all use {@link ArrayList} for storing and loading objects
 * 
 * @author SherR
 * 
 */
public class TripleValueMap<K, V1, V2, V3> extends AbstractMap<K, V1> 
        implements Map<K, V1>, Serializable, Cloneable {
    
    private static final long serialVersionUID = -8984612005981640588L;

    /** Async Lock */
    protected final Object lock = new Object();
    
    /** @serial */
    protected List<K> keys;

    /** @serial */
    protected List<V1> values1;
    
    /** @serial */
    protected List<V2> values2;
    
    /** @serial */
    protected List<V3> values3;
    
    /** @serial */
    protected List< Entry<V1, V2> > values;
    
    /** @serial */
    protected List< Entry< K, Entry<V1, V2> >> entries;
    
    /** 
     * Constructs an empty {@code TripleValueMap} with a default capacity of 16
     * 
     * @author SherR
     *  
     */
    public TripleValueMap() {
        this(16);
        
    }
    
    /** 
     * Constructs an empty {@code TripleValueMap} with the supplied capacity
     * 
     * @author SherR
     *  
     */
    public TripleValueMap( int initialCapacity ) {
        this( initialCapacity, null );
        
    }
    
    /**
     * Constructs a {@code TripleValueMap} with the supplied map's keys and values already present, and an
     * initial capacity of the {@code supplied map's size + 16}
     *
     * Uses {@link TripleValueMap#merge(TripleValueMap)}
     *
     * @author SherR
     * 
     */
    public TripleValueMap( TripleValueMap<K, V1, V2, V3> map ) {
        this( map.size() + 16, map );
    }
    
    /**
     * Constructs a {@code TripleValueMap} with the supplied map's keys and values already present, and an
     * initial capacity of the {@code supplied map's size + supplied initial capacity}
     *
     * Uses {@link TripleValueMap#merge(TripleValueMap)}
     *
     * @author SherR
     * 
     */
    public TripleValueMap( int initialCapacity, TripleValueMap<K, V1, V2, V3> map ) {
        this.keys = new ArrayList<>( map.size() + initialCapacity );
        this.values1 = new ArrayList<>( map.size() + initialCapacity );
        this.values2 = new ArrayList<>(  map.size() + initialCapacity );
        this.values3 = new ArrayList<>(  map.size() + initialCapacity );
        this.values = new ArrayList<>(  map.size() + initialCapacity );
        this.entries = new ArrayList<>( map.size() + initialCapacity );
        
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
            keys.clear();
            values1.clear();
            values2.clear();
            values.clear();
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
        synchronized( lock ) { return keys.size(); }
   
    }

    /** 
     * 
     * @author SherR
     * 
     */
    @Override
    public boolean isEmpty() {
        synchronized( lock ) { return keys.isEmpty(); }
    
    }
    
    /** 
     * 
     * @author SherR
     * 
     */
    @Override
    public int hashCode() {
        synchronized( lock ) { 
            return Objects.hashCode( keys ) ^ Objects.hashCode( values1 ) ^ Objects.hashCode( values2 ) 
                    ^ Objects.hash( values3 ) ^ Objects.hashCode( entries ) ^ Objects.hashCode( values );
        
        }
    }
    
    /** 
     * 
     * @deprecated Unsupported Operation 
     * @throws UnsupportedOperationException Use {@link TripleValueMap#entries()}
     * 
     */
    @Override
    public Set< Entry<K, V1> > entrySet() {
        throw new UnsupportedOperationException( "Use TripleValueMap.entries()" );
    
    }
    
    /** 
     * 
     * @deprecated Unsupported Operation 
     * @throws UnsupportedOperationException Use {@link TripleValueMap#hasKey(Object)}
     * 
     */
    @Override
    public boolean containsKey( Object key ) {
        throw new UnsupportedOperationException( "Use TripleValueMap.hasKey(Object)" );
    
    }
    
    /** 
     *
     * @deprecated Unsupported Operation 
     * @throws UnsupportedOperationException Use {@link TripleValueMap#hasFirstValue(Object)} or {@link TripleValueMap#hasSecondValue(Object)}
     * 
     */
    @Override
    public boolean containsValue( Object value ) {
        throw new UnsupportedOperationException( "Use TripleValueMap.hasFirstValue(Object) or TripleValueMap.hasSecondValue(Object)" );

    }
    
    /** 
     * 
     * @deprecated Unsupported Operation 
     * @throws UnsupportedOperationException Use {@link TripleValueMap#getFirstValue(Object)} or {@link TripleValueMap#getSecondValue(Object)}
     * 
     */
    @Override
    public V1 get( Object key ) {
        throw new UnsupportedOperationException( "Use TripleValueMap.getFirstValue(Object) or TripleValueMap.getSecondValue(Object)" );
    
    }
    
    /** 
     * 
     * @deprecated Unsupported Operation 
     * @throws UnsupportedOperationException Use {@link TripleValueMap#add(Object, Object, Object} or {@link TripleValueMap#place(Object, Object, Object} 
     * 
     */
    @Override
    public V1 put( K key, V1 value ) {
        throw new UnsupportedOperationException( "Use TripleValueMap.add(Object, Object, Object) or TripleValueMap.place(Object, Object, Object)" );
    
    }
    
    /** 
     * 
     * @deprecated Unsupported Operation 
     * @throws UnsupportedOperationException Use {@link TripleValueMap#delete(Object)}
     * 
     */
    @Override
    public V1 remove( Object key ) {
        throw new UnsupportedOperationException( "Use TripleValueMap.delete(Object)" );

    }
    
    /** 
     *
     * @deprecated Unsupported Operation 
     * @throws UnsupportedOperationException Use {@link TripleValueMap#merge(TripleValueMap)} 
     * 
     */
    @Override
    public void putAll( Map<? extends K, ? extends V1> m ) {
        throw new UnsupportedOperationException( "Use TripleValueMap.merge(TripleValueMap)" );
    
    }
    
    /** 
     * 
     * @deprecated Unsupported Operation 
     * @throws UnsupportedOperationException Use {@link TripleValueMap#keys()}
     * 
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException( "Use TripleValueMap.keys()" );
   
    }
    
    /** 
     * 
     * @deprecated Unsupported Operation 
     * @throws UnsupportedOperationException Use {@link TripleValueMap#firstValues()} or {@link TripleValueMap#secondValues()}
     * 
     */
    @Override
    public Collection<V1> values() {
        throw new UnsupportedOperationException( "Use TripleValueMap.firstValues() or TripleValueMap.secondValues()" );

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
            keys.add( key );
            values1.add( firstValue );
            values2.add( secondValue );

            Pair<V1, V2> value = Pair.from( firstValue, secondValue );
            values.add( value );
            entries.add( Pair.from( key, value ) );
            
        }
    }
    
    /**
     * 
     * 
     */
    public void delete( K key ) {
        synchronized( lock ) {
            int index = keys.indexOf(key);
            keys.remove( index );
            values1.remove( index );
            values2.remove( index );
            
        }
    }
    
    /**
     * 
     * 
     */
    public void merge( TripleValueMap<K, V1, V2, V3> mergeWith ) {
        mergeWith = Objects.requireNonNull( mergeWith, "The TripleValueMap must not be null!" );

        keys.addAll( mergeWith.keys );
        values1.addAll( mergeWith.values1 );
        values2.addAll( mergeWith.values2 );
        values3.addAll( mergeWith.values3 );    
        
        values.addAll( mergeWith.values );
        entries.addAll( mergeWith.entries );
        
    }
    
    /**
     * 
     * 
     */
    public V1 getFirstValue( K key ) { 
        synchronized( lock ) {
            V1 value = null;
            if( hasKey( key ) )
                value = values1.get( keys.indexOf( key ) );
        
            return value;
        
        }
    }
    
    /**
     * 
     * 
     */
    public V2 getSecondValue( K key ) { 
        synchronized( lock ) {
            V2 value = null;
            if( hasKey( key ) )
                value = values2.get( keys.indexOf( key ) );
        
            return value;
        
        }
    }
    
    /**
     * 
     * 
     */
    public V3 getThirdValue( K key ) { 
        synchronized( lock ) {
            V3 value = null;
            if( hasKey( key ) )
                value = values3.get( keys.indexOf( key ) );
        
            return value;
        
        }
    }

    /**
     * 
     * 
     */
    public Collection<K> keys() { 
        synchronized( lock ) { return keys; }
        
    }
    
    /**
     * 
     * 
     */
    public Collection<V1> firstValues() { 
        synchronized( lock ) { return values1; }
        
    }
    
    /**
     * 
     * 
     */
    public Collection<V2> secondValues() { 
        synchronized( lock ) { return values2; }
    
    }
    
    /**
     * 
     * 
     */
    public Collection<V3> thirdValues() { 
        synchronized( lock ) { return values3; }
    
    }

    /**
     * 
     * 
     */
    public Collection< Entry< K, Entry<V1, V2> > > entries() { 
        synchronized( lock ) { return entries; }
    
    }
    
    /**
     * 
     * 
     */
    public boolean hasKey( K key ) {
        synchronized( lock ) { return keys.contains( key ); }
        
    }
    
    /**
     * 
     * 
     */
    public boolean hasFirstValue( V1 value ) { 
        synchronized( lock ) { return values1.contains( value ); }
        
    }
    
    /**
     * 
     * 
     */
    public boolean hasSecondValue( V2 value ) { 
        synchronized( lock ) { return values2.contains( value ); }
        
    }
    
    /**
     * 
     * 
     */
    public boolean hasThirdValue( V3 value ) { 
        synchronized( lock ) { return values3.contains( value ); }
        
    }
    
}