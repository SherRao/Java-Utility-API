package tk.sherrao.utils.sync;

public abstract class Synchronizable {

    @FunctionalInterface
    public static interface NilaryConsumer {
        
        void accept();
        
    }

    @FunctionalInterface
    public static interface Consumer<T> {
        
        void accept( T t );
        
    }
    
    @FunctionalInterface
    public static interface BiConsumer<T1, T2> {
        
        void accept( T1 t1, T2 t2 );
        
    }
    
    @FunctionalInterface
    public static interface Function<T, R> {
        
        R accept( T t );
        
    }
    
    @FunctionalInterface
    public static interface BiFunction<T1, T2, R> {
        
        R accept( T1 t1, T2 t2 );
        
    }

}