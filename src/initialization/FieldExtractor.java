package initialization;

import java.util.Scanner;

import trackParser.*;

public class FieldExtractor {

	//vars we need
	static String userInput;
	
	public static void main(String[] args) {
		
		setParams();
		ReadAndParse nRAP = new ReadAndParse();
		nRAP.readIn(userInput);
		System.out.println("You should now have a file in your working directory");
			
		

	}
	
	public static void setParams()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("This program will look for fields named \"name\", \"contig\", \"exonStarts\", and \"exonEnds\".\nIt will then extract the exons, and parse them to a new .bed file. \n\n");
		System.out.println("Enter the full path for Ensembl Table file you want to parse: ");
		
		String refFile = keyboard.nextLine();
		
		keyboard.close();
		
		userInput = refFile;
	}

}
