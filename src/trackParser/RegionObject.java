/**
 * @author Salvatore Facista
 * @version 1.0
 * This class stores track region information. Usually this comes from a custom table.
 * Only specific track information is stored
 * 
 */

package trackParser;

public class RegionObject {
	
	//Private variables
	String ensemblTranscriptID;
	String contig;		//aka chromosomeName
	char sense;			//plus or minus only
	String exonStarts;	//string representing exon start positions starting at the lowest long
	String exonEnds;	//string representing exon end positions starting at the lowest long
	
	//constructor
	public RegionObject()
	{
		ensemblTranscriptID = null;
		contig = null;
		sense = '0';
		exonStarts = null;
		exonEnds = null;
		
	}
	
	public RegionObject(String iEnsemblTransciptID, String iContig, char iSense, String iExonStarts, String iExonEnds)
	{
		ensemblTranscriptID = iEnsemblTransciptID;
		contig = iContig;
		sense = iSense;
		exonStarts = iExonStarts;
		exonEnds = iExonEnds;
		
	}
	
	//Set methods
	public void setEnsemblTranscriptID(String newEnsemblTranscriptID) {ensemblTranscriptID = newEnsemblTranscriptID;}
	public void setContig (String newContig) {contig = newContig;}
	public void setSense (char newSense) {sense = newSense;}
	public void setExonStarts (String newExonStarts) {exonStarts = newExonStarts;}
	public void setExonEnds (String newExonEnds) {exonEnds = newExonEnds;}
	
	//Get methods
	public String getEnsemblTranscriptID(){return ensemblTranscriptID;}
	public String getContig(){return contig;}
	public char getSense(){return sense ;}
	public String getExonStarts(){return exonStarts;}
	public String getExonEnds(){return exonEnds;}
}
