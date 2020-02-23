package tk.sherrao.utils.configuration;

public interface Serializable<T> {

	Object serialise();
	
	T deserialise();
	
}