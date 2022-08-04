import java.io.*;
import java.util.*;
import java.io.BufferedReader;

//Users/claricepark/data/blindptm/search.xml
//Users/claricepark/data/blindptm/DTASelect-filter.txt
///Users/claricepark/data/blindptm/UniProt_human_reviewed_contaminant_05-23-2020_reversed.fasta

public class FastaFile {

    public static void main(String[] args) throws Exception { //cannot be main method
        String file = "/users/claricepark/data/blindptm/UniProt_human_reviewed_contaminant_05-23-2020_reversed.fasta";
        String outputPath = file.substring(0, file.lastIndexOf('/')) + File.separator + "shuffled.sequences";

        List<FastaProtein> fastaProteinList = ReadFasta1.getFastaProteinList(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for(FastaProtein protein : fastaProteinList){
                writer.write("*>" + protein.getDescription());
                writer.write(System.getProperty( "line.separator" ));
                writer.write(protein.getSequence());
                writer.write(System.getProperty( "line.separator" ));

        }
        writer.close();
    }
}
