# Field_Extractor_Exon
Extracts exon lists from UCSC data tables and parses them to .bed file.

This tool is species agnostic.

Use case for this software: You want to create a custom mask for a reference genome. All exonic regions should be capitalized. All intronic regions should be lower cased.

System requirements for above use case: Java 1.8+, Field_Extractor_Exon, bedtools & bedtools dependencies (Quinlan laboratory at the University of Utah), sed (or any comparable stream-editing utility). You'll probably be using Linux, WSL in Windows, or GNU Bash on MacOS.

How to accomplish with this software - no wrapper yet.
1) Go to the UCSC Table browser, currently located here: https://genome.ucsc.edu/cgi-bin/hgTables
2) Select the appropriate clade, genome, and assembly fields.
3) Specify Genes and Gene Predictions for the group field.
4) Select your track of interest (e.g. Ensembl Genes) - The 'describe table schema' button will display the fields.
*There must be a 'name', 'chrom', 'exonStarts', and 'exonEnds' field. You may elect to insert dummy fields in a custom, tsv file as well. 'exonStarts' and 'exonEnds' is expected to be a csv list.*
5) Download the plain-text output file from the Table Browser.
6) Download or clone Filed_Extractor_Exon from: https://github.com/sfacista/Field_Extractor_Exon/
7) Run Field_Extractor_Exon with Java 8 (1.8) +. At the command line this should looks like:
java -jar /path/to/where/you/put/Field_Extractor_Exon.jar
8) Field_Extractor_Exon will ask you where you put your plain-text file from the Table Browser.
9) You will get an output file in your current working directory. Field_Extractor_Exon software is now done. From here on out, you're using external tools.
*Warning: You have to perform the last step of this process below this point, or your reference genome will have introns capitalized, and exons in lower case.*
10) Remove the header in the output file.
11) Download your reference genome.
12) Use the custom bed file you created with Field_Extractor_Exon in conjunction with bedtools:
bedtools maskfasta [OPTIONS] -fi <input FASTA> -bed <BED/GFF/VCF> -fo <output FASTA>
  e.g.:
  bedtools maskfasta -fi my_reference_genome.fa -bed field_extractor_exon_20019-12-27T191216.257288.bed -fo my_new_custom_masked_reference_genome.fa
*Warning: You have to perform this last step, or your reference genome will have introns capitalized, and exons in lower case.*
 13) Invert the casing of the custom-masked genome using a stream editing utility, or this sed script:
  sed -i 'y/acgntACGNT/ACGNTacgnt/' /path/to/my_new_custom_masked_reference_genome.fa
