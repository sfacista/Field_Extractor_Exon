/**
 * @author Salvatore Facista
 * @version 1.0
 * This class handles the import of files.
 * 
 * 
 */
package trackParser;

import java.io.File;

public class ImportFile {

	//Private variables of the class
	private File theFile;
	
	//methods
	
	//Sets a file to use given the user input name.
	public void setFileName(String pathToFile) 
	{
		try 
		{
			File f1 = new File(pathToFile);
			if(f1.exists())
			{
				System.out.println("\n\nFile was found. Processing...\n\n");
				theFile = f1;
			}
			else
			{
				System.out.println("\n\nFile not found!\n\n");
			}
			
			
		}
		catch(Exception FileNotFoundException)
		{
			System.out.println("Encountered missing file.");
			FileNotFoundException.printStackTrace();
		}
	}
	public File returnFile()
	{
		return theFile;
	}
	
}