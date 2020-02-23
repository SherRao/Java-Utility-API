package tk.sherrao.utils.collections;

import java.io.Serializable;
import java.util.Map.Entry;

/**
 * 
 * 
 */
public class Pair<K, V> implements Entry<K, V>, 
        Cloneable, Serializable {
   
    private static final long serialVersionUID = -5836854146596680259L;
  
    /** @serial */
    protected K key;
    
    /** @serial */
    protected V value;
    
    /**
     * 
     * 
     */
    public Pair( K key, V value ) {
        this.key = key;
        this.value = value;
        
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
    public V getValue() { 
        return value; 
        
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
    public V setValue( V value ) {
        V old = this.value;

        this.value = value;
        return old;
    
    }
    
    /**
     * 
     * 
     */
    @Override
    public Pair<K, V> clone() {
        return Pair.from( key, value );
        
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
    public static <SK, SV> Pair<SK, SV> from( SK key, SV value ) {
        return new Pair<SK, SV>( key, value );
        
    }
    
}