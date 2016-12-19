package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteIntoFile {

	String filename;
	String text;
	public WriteIntoFile(){
		
	}
		
	public WriteIntoFile(String filename, String text){
		this.filename = filename;
		this.text = text;
	}
	
	
	public void filexample() {
		try {

			String content = text;

			File file = new File(filename);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void makeAFile() throws IOException{
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter( new FileWriter( this.filename));
		    writer.write( this.text);

		}
		catch ( IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
	}
	
}
