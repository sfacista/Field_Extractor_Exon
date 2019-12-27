/*
 * This object is generated from a region, and can be output as a line x line.
 */

package trackParser;

public class ExonObject extends RegionObject{
	
	//private variables
	String ensemblTranscriptID;
	String contig;		//aka chromosomeName
	char sense;			//plus or minus only
	String exonStart;	//string representing exon start positions starting at the lowest long
	String exonEnd;	//string representing exon end positions starting at the lowest long

	//Constructor
	public ExonObject()
	{
		ensemblTranscriptID = null;
		contig = null;
		sense = '0';
		exonStart = null;
		exonEnd = null;
	}
	
	public ExonObject(String iEnsemblTransciptID, String iContig, char iSense, String iexonStart, String iexonEnd)
	{
		ensemblTranscriptID = iEnsemblTransciptID;
		contig = iContig;
		sense = iSense;
		exonStart = iexonStart;
		exonEnd = iexonEnd;	
	}
	
	public ExonObject(String iContig, String iexonStart, String iexonEnd)
	{
		contig = iContig;
		exonStart = iexonStart;
		exonEnd = iexonEnd;
	}
	
	//Set methods
	public void setEnsemblTranscriptID(String newEnsemblTranscriptID) {ensemblTranscriptID = newEnsemblTranscriptID;}
	public void setContig (String newContig) {contig = newContig;}
	public void setSense (char newSense) {sense = newSense;}
	public void setexonStart (String newexonStart) {exonStart = newexonStart;}
	public void setexonEnd (String newexonEnd) {exonEnd = newexonEnd;}
	
	//Get methods
	public String getEnsemblTranscriptID(){return ensemblTranscriptID;}
	public String getContig(){return contig;}
	public char getSense(){return sense ;}
	public String getexonStart(){return exonStart;}
	public String getexonEnd(){return exonEnd;}

}
