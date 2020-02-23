package tk.sherrao.utils.collections;

import java.io.Serializable;
import java.util.Map.Entry;

/**
 * 
 * 
 */
public class DoubleValuePair<K, V1, V2> extends Pair<K, V1> 
        implements Entry<K, V1>, Cloneable, Serializable {
   
    private static final long serialVersionUID = -5836854146596680259L;
  
    /** @serial */
    protected K key;
    
    /** @serial */
    protected V1 value1;
    
    /** @serial*/
    protected V2 value2;
    
    /** @serial */
    protected Pair< V1, V2 > values;
    
    /**
     * 
     * 
     */
    public DoubleValuePair( K key, V1 value1, V2 value2 ) {
        super( key, value1 );
        
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
        this.values = Pair.from( value1, value2 );
        
    }
    
    /**
     * 
     * 
     */
    @Override
    public K getKey() {
        return key; 
        
    }

    /**
     * 
     * 
     */
    @Override
    public V1 getValue() { 
        throw new UnsupportedOperationException(); 
        
    }

    /**
     * 
     * 
     */
    public K setKey( K key ) {
        K old = this.key;
        
        this.key = key;
        return old;
        
    }
    
    /**
     * 
     * 
     */
    @Override
    public V1 setValue( V1 value ) {
        throw new UnsupportedOperationException();
        
    }
    
    /**
     * 
     * 
     */
    public V1 getFirstValue() {
        return value1;
        
    }
    
    /**
     * 
     * 
     */
    public V2 getSecondValue() {
        return value2;
        
    }
    
    /**
     * 
     * 
     */
    public V1 setFirstValue( V1 value ) {
        V1 old = value1;
        this.value1 = value;
        this.values.setKey( value );
        
        return old;
        
    }
    
    /**
     * 
     * 
     */
    public V2 setSecondValue( V2 value ) {
        V2 old = value2;
        this.value2 = value;
        this.values.setValue( value );

        return old;
        
    }
    
    /**
     * 
     * 
     */
    public Pair<V1, V2> values() {
        return values;
        
    }
    
    /**
     * 
     * 
     */
    @Override
    public DoubleValuePair<K, V1, V2> clone() {
        return DoubleValuePair.from( key, value1, value2 );
        
    }
 
    /**
     * Creates a new {@code Pair} with the specified {@code key} and {@code value}. 
     * This method is the same as calling:
     * <pre>{@code new Pair<K, V>( key, value);}</pre>
     * 
     * 
     * @param The key
     * @param <SV> The value 
     * @param key The key 
     * @param value The value
     * 
     * @author SherR
     * 
     */
    public static <SK, SV1, SV2> DoubleValuePair<SK, SV1, SV2> from( SK key, SV1 value1, SV2 value2 ) {
        return new DoubleValuePair<SK, SV1, SV2>( key, value1, value2 );
        
    }
    
}