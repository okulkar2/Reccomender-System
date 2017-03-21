package recommender;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FileProcessor {
	private InputStream inputStream;
	private BufferedReader bufferReader;
	private BufferedWriter bufferWriter;
	private OutputStream outputStream;
	
	/**
	 * Constructor of FileProcessor used to initialize inputStream 
	 * and instantiate Buffered Reader class with that input Stream.
	 * 	
	 * @param inputStream
	 *            InputStream to read.
	 */
	public FileProcessor(InputStream inputStreamIn,OutputStream outputStreamIn) {
		
		inputStream=inputStreamIn;
		bufferReader=new BufferedReader(new InputStreamReader(inputStream));
		
		outputStream = outputStreamIn;
		bufferWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
	}
	
	/**
	 * This function reads one line from a file and returns it.
	 * 
	 * @return String Represents a single line read from file.
	 */
	public String readLineFromFile(){
		
		String line = null;
		try {
			line=bufferReader.readLine();
		} catch (IOException e) {
			System.err.println("An error occurred while reading input file !!");
			System.exit(1);
		}
		return line;
	}
	
	/**
	 * This function writes one line to the file.
	 * 
	 */
	public void writeLineToFile(String stringIn){
		
		if(stringIn != null){
			try {
				bufferWriter.write(stringIn);
				bufferWriter.newLine();
				bufferWriter.flush();
			}catch (IOException e) {
				System.err.println("An error occurred while writing to file !!");
				System.exit(1);
			}
		}
	}
	
	/**
	 * Closes the input stream.
	 */
	public void closeStream() {
		
		try {
			
			if(inputStream!=null)
				inputStream.close();
			
			if(outputStream!=null)
				outputStream.close();
			
		} catch (IOException e) {
			System.err.println("An exception occurred while closing file !!");
		}
	}
}
