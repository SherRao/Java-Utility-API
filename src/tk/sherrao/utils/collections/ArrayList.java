package tk.sherrao.utils.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;

import tk.sherrao.utils.strings.StringMultiJoiner;

public class ArrayList<E> extends java.util.ArrayList<E> {

	private static final long serialVersionUID = 131077166120165443L;

	public ArrayList() {
		super();
		
	}
	
	public ArrayList( Collection<? extends E> c ) {
		super( c );
		
	}
	
	public ArrayList( int initialCapacity ) {
		super( initialCapacity );
		
	}
	
	public E random() {
		return random( System.currentTimeMillis() );
		
	}
	
	public E random( long seed ) {
		return CollectionUtils.randomElement( this, seed );
		
	} 
	
	public void shuffle() {
		shuffle( System.currentTimeMillis() );
		
	}
	
	public void shuffle( long seed ) {
		Collections.shuffle( this, new java.util.Random( seed ) );
		
	}
	
	public ArrayList<E> filter( Predicate<E> condition ) { 
		ArrayList<E> list = this;
		super.clear();
		super.addAll( CollectionUtils.filter( list, condition ) );
		return list;
		
	}
	
	@Override
	public String toString() {
		return new StringMultiJoiner( ", ", "[", "]" )
				.add( this )
				.toString();
		
	}
	
}