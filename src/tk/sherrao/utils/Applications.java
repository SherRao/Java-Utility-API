package tk.sherrao.utils;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Applications {

	public static boolean execute( File file ) {
		try {
			String app = file.getName();
			if( !isProcessRunning( app ) ) {
				Desktop.getDesktop().open( file );
				return true;
				
			} else return false;
			
		} catch ( IOException e ) {
			e.printStackTrace(); 
			return false;
			
		}
	}
	
	public static boolean isProcessRunning( String task ) {
		try {
			return toString( new ProcessBuilder( "tasklist.exe" ).start().getInputStream() )
					.contains( task );
		
		} catch ( IOException e ) { 
			e.printStackTrace(); 
			return false;
			
		} 
	}
	
	private static final String toString( InputStream stream ) 
			throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while( (length = stream.read( buffer)) != -1 ) {
			result.write( buffer, 0, length );
			
		}
		
		return result.toString( StandardCharsets.UTF_8.name() );
		
	}
	
}
