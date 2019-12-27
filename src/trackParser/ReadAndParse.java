package trackParser;

/**
 * @author Salvatore Facista
 * @version 1.0
 * This class handles the import of .bed files.
 * It will use the BedFileObjects to point to specific areas in the .fa (FASTA) reference genome.
 * 
 */

import java.util.ArrayList;
import java.io.*;
import java.time.LocalDateTime;
import trackParser.ImportFile;
import trackParser.RegionObject;


public class ReadAndParse {

	//private variables
	//private ArrayList<RegionObject> aR;
	private ArrayList<ExonObject>eR = new ArrayList<ExonObject>();
	private int indexEnsemblTranscriptID;
	private int indexContig;
	private int indexSense;
	private int indexExonStarts;
	private int indexExonEnds;
	
	
	//methods
	
	//The readIn method relies on the parseIt method below. It assigns the read-in file to the private RegionObject
	public void readIn(String someFile)
	{
		try 
		{
			//Variables used within this method only
			String firstLine = null;
			ArrayList<RegionObject> tempAR = new ArrayList<RegionObject>();
						
			//import a file
			ImportFile f1 = new ImportFile();
			f1.setFileName(someFile);
			FileReader f1a = new FileReader(f1.returnFile());
			//stream the file
			BufferedReader in0 = new BufferedReader(f1a);
			firstLine = in0.readLine();
			//characterize / parse the file based on first line properties
			this.parseIt(firstLine);
			firstLine = in0.readLine();
			while (firstLine != null)
			{
				String[] strArray = firstLine.split("\t");
				RegionObject vnishh = new RegionObject(strArray[indexEnsemblTranscriptID], strArray[indexContig], strArray[indexSense].charAt(0), strArray[indexExonStarts], strArray[indexExonEnds]);
				
				/*/BEGIN TS CODE//
				System.out.println(strArray[indexEnsemblTranscriptID] + " is the EnsembleTranscriptID.\n" + strArray[indexContig] + " is the indexContig.\n"  + strArray[indexSense].charAt(0) + " is the sense.\n"  + strArray[indexExonStarts] + " is the ExonStarts.\n"  + strArray[indexExonEnds] + " is the ExonEnds.\n"  + "...all being dumped into the new RegionObject");
				//END TS CODE/*/
				tempAR.add(vnishh);
				/*/Begin TS code//
				System.out.println("Added the item to the temp array.");
				System.out.println(vnishh);
				//END TS CODE/*/
				firstLine = in0.readLine();
			}
			
			for (RegionObject r: tempAR)
				{
					parseFurther(r);
				}
			
			in0.close();
			
			//For all in tempAR second level parse
			/*/Begin TS Code//
			System.out.println("If all the above worked, now remove the TS code and print out all of eR");
			System.out.println(eR.size() + " is the size of the eR array ");
			for(ExonObject e: eR)
			{
				System.out.println(e.getContig());
				System.out.println(e.getexonStart());
				System.out.println(e.getexonEnd());
			}
			System.out.println("If all the above worked, eR was printed");
			System.out.println(eR.size() + " is the size of the eR array");
			//END TS CODE/*/
			makeFileBed(eR);
		} 
		catch (IOException e) 
		{
			System.out.println("Something went wrong when attempting to read in the file using readIn method of ReadAndParse Class.");
			e.printStackTrace();
			
		}
		
	}
	
	//The parseIt method looks for select headers in the first line
	public void parseIt(String headString)
	{
		String[] labels = headString.split("\t");
		int numberItems = labels.length;
		for (int i = 0; i < numberItems; i++)
		{
			/*/Begin TS Code
			System.out.println("The labels index i is: " + i +". And the string is: " + labels[i]);
			//End TS code/*/
			if(labels[i].equals("name")) 
			{
				indexEnsemblTranscriptID = i;
				/*/Begin TS Code
				System.out.println("i is: " + i +". And assigned to: " + "indexEnsemblTranscriptID");
				//End TS code/*/
			}
			else if(labels[i].equals("chrom"))
			{
				indexContig = i;
				/*/Begin TS Code
				System.out.println("i is: " + i +". And assigned to: " + "indexContig");
				//End TS code/*/
			}
			else if(labels[i].equals("strand"))
			{
				indexSense = i;
				/*/Begin TS Code//
				System.out.println("i is: " + i +". And assigned to: " + "indexSense");
				//End TS code/*/

			}
			else if(labels[i].equals("exonStarts"))
			{
				indexExonStarts = i;
				/*/Begin TS Code
				System.out.println("i is: " + i +". And assigned to: " + "indexExonStarts");
				//End TS code/*/

			}
			else if(labels[i].equals("exonEnds"))
			{
				indexExonEnds = i;
				/*/Begin TS Code
				System.out.println("i is: " + i +". And assigned to: " + "indexExonEnds");
				//End TS code/*/

			}
			else
			{
				/*/Begin TS Code
				System.out.println("i is: " + i +". And went unassigned");
				//End TS code/*/

			}
			
		}
	}
	
	
	public void parseFurther(RegionObject whatEver)
	{
		try
		{
			//private variable
			ArrayList<ExonObject> tempER = new ArrayList<ExonObject>();
			
			String[] sts = whatEver.exonStarts.split(",");
			int lenSts = sts.length;
			/*/BEGIN TS CODE//
			System.out.println("The first exonStarts has " + lenSts + " exons.");
			//END TS CODE/*/
			String[] eds = whatEver.exonEnds.split(",");
			int lenEds = eds.length;
			/*/BEGIN TS CODE//
			System.out.println("The first exonEnds has " + lenSts + " exons.");
			//End TS CODE/*/
			if (lenSts == lenEds)
			{
				for (int i = 0; i < lenSts; i++)
				{
					ExonObject newExonObject = new ExonObject(whatEver.contig, sts[i], eds[i]);
					/*/Begin TS Code//
					System.out.println(whatEver.contig + " " + whatEver.exonStarts + " " + whatEver.exonEnds + " " + "Is the current Region info");
					System.out.println(newExonObject.contig + " " + newExonObject.exonStart + " " + newExonObject.exonEnd + " " + "Is the Exon info");
					System.out.println("Adding Exon Object to master Array");
					//END TS Code/*/
					tempER.add(newExonObject);
					/*/Begin TS Code//
					System.out.println(tempER.size() + " is the tempER size inside the loop");
					//End TS code/*/
				}
				/*/Begin TS code//
				System.out.println(tempER.size() + " is the size of the tempER array");
				for(ExonObject e: tempER)
				{
					System.out.println(e.getContig());
					System.out.println(e.getexonStart());
					System.out.println(e.getexonEnd());
				}
				System.out.println("If all the above worked, tempER was printed");
				System.out.println(tempER.size() + " is the size of the tempER array");
				//END ts Code/*/
				eR.addAll(tempER);
			}
			else
			{
				System.out.println("There is a exon missing in " + whatEver.ensemblTranscriptID + " " + whatEver.contig + " ENS T ID _ Contig");
			}
		}
		catch (Exception e)
		{
			
		}
	}
	
	public static void makeFileBed(ArrayList<ExonObject> exons01)
	{
		PrintWriter outStream = null;
		try
		{
			LocalDateTime dateTime = LocalDateTime.now();
			outStream = new PrintWriter(new FileOutputStream("field_extractor_exon_" + dateTime + ".bed"));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("A file could not be generated for output.");
			e.printStackTrace();
		}
		
		try 
		{
			outStream.println("Contig_or_Chromosome" + "\t" + "Start_Index" + "\t" + "End_Index");
			for (ExonObject e : exons01)
			{
				outStream.println(
										e.getContig() + "\t" 
										+ e.getexonStart() + "\t" 
										+ e.getexonEnd() + "\t" 
										);
			}
			
			outStream.close();
			System.out.println("A file was generated.");
			System.out.println("There are " + exons01.size() + " content lines in your file excluding the header");
			System.out.println("File Extractor (Exon) version 1.0 - Salvatore Facista");
		}
		catch(Exception e)
		{
			System.out.println("The output stream could not be written to the file.");
			e.printStackTrace();
		}
	}
}
