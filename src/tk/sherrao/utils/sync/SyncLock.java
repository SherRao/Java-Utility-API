package tk.sherrao.utils.sync;

public class SyncLock {
    
    private final class Lock {}
    private final Object lock = new Lock();
    
    public SyncLock() {
    
    }
    
    public final void performSync( Synchronizable.NilaryConsumer action ) {
        synchronized( lock ) { action.accept(); }
        
    }
    
    public final <T> void performSync( Synchronizable.Consumer<T> action, T input ) {
        synchronized( lock ) { action.accept( input ); }
        
    }
    
    public final <T1, T2> void performSync( Synchronizable.BiConsumer<T1, T2> action, T1 input1, T2 input2 ) {
        synchronized( lock ) { action.accept( input1, input2 ); }
        
    }
 
    public final <T, R> R performSync( Synchronizable.Function<T, R> action, T input ) {
        synchronized( lock ) { return action.accept( input ); }
        
    }
    
    public final <T1, T2, R> R performSync( Synchronizable.BiFunction<T1, T2, R> action, T1 input1, T2 input2 ) {
        synchronized( lock ) { return action.accept( input1, input2 ); }
        
    }
    
}