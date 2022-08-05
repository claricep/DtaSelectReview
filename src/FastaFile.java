import java.io.*;
import java.util.*;
import java.io.BufferedReader;

//Users/claricepark/data/blindptm/search.xml
//Users/claricepark/data/blindptm/DTASelect-filter.txt
///Users/claricepark/data/blindptm/UniProt_human_reviewed_contaminant_05-23-2020_reversed.fasta

public class FastaFile {

    public static void copyContent(File a, File b)
            throws Exception
    {
        FileInputStream in = new FileInputStream(a);
        FileOutputStream out = new FileOutputStream(b);

        try {
            int n;
            while ((n = in.read()) != -1) {
                out.write(n);
            }
        }
        finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        System.out.println("File Copied");
    }

    public static void main(String[] args) throws Exception { //cannot be main method
        String file = "/users/claricepark/data/blindptm/UniProt_human_contaminant_05-05-2020.fasta";
        String outputPath = file.substring(0, file.lastIndexOf('/')) + File.separator + "shuffled.sequences";

        List<FastaProtein> fastaProteinList = ReadFasta1.getFastaProteinList(file);

        File x = new File(file);
        File y = new File(outputPath);
        copyContent(x, y);

        try(FileWriter fw = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(writer)) {
            writer.write("*");
            for(FastaProtein protein : fastaProteinList){
                writer.write(">" + protein.getDescription());
                writer.write(System.getProperty( "line.separator" ));
                writer.write(protein.getSequence());
                writer.write(System.getProperty( "line.separator" ));

            }writer.close();
        } catch (IOException e) {
            //exception handling left as an exercise for the reader

        }
    }
}
