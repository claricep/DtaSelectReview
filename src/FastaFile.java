import java.io.*;
import java.util.*;
import java.io.BufferedReader;

//Users/claricepark/data/blindptm/search.xml
//Users/claricepark/data/blindptm/DTASelect-filter.txt
///Users/claricepark/data/blindptm/UniProt_human_reviewed_contaminant_05-23-2020_reversed.fasta

public class FastaFile {
    public static final String file = "/users/claricepark/data/blindptm/shuffled.fasta";
    public static void main(String[] args) throws Exception { //cannot be main method

        List<dta.FastaProtein> fastaProteinList = ReadFasta1.getFastaProteinList(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        Hashtable<String, dta.FastaProtein> fastaHt = new Hashtable<>();
        for(dta.FastaProtein fp:fastaProteinList) {
            // String desc = fp.getDescription().split(" ")[0];
            String key = "";
            if(fp.getDescription().length()>=21)
                key = fp.getDescription().substring(0,21);
            else
                key = fp.getDescription();

            fastaHt.put(key, fp);
        }

        Set<String> fastaProteinSet = fastaHt.keySet();

        for(dta.FastaProtein protein : fastaProteinList){

            if(fastaProteinSet.contains(protein)) {
                dta.FastaProtein fp = fastaHt.get(protein);
                //System.out.println("Output file successfully created");
//                System.out.println(fp.getDescription());
//                System.out.println(fp.getSequence());
                List<String> aaList = Arrays.asList("ACDE".split(""));
//                List<String> aaList = Arrays.asList(fp.getSequence().split(""));
                Collections.shuffle(aaList);
                writer.write(">" + fp.getDescription());
                writer.write(System.getProperty( "line.separator" ));
                writer.write("aa");
                writer.write(System.getProperty( "line.separator" ));
            }
        }

        writer.close();
    }
}
