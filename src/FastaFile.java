import java.io.*;
import java.util.*;

//Users/claricepark/data/blindptm/search.xml
//Users/claricepark/data/blindptm/DTASelect-filter.txt
///Users/claricepark/data/blindptm/UniProt_human_reviewed_contaminant_05-23-2020_reversed.fasta

public class FastaFile {

    public static void main(String[] args) throws Exception { //cannot be main method
        String file = "/users/claricepark/data/blindptm/UniProt_human_contaminant_05-05-2020.fasta";
        String outputPath = file.substring(0, file.lastIndexOf('/')) + File.separator + "shuffled.sequences";
        File outputFile = new File(outputPath);
        if(outputFile.exists())
            outputFile.delete();

        List<FastaProtein> fastaProteinList = ReadFasta1.getFastaProteinList(file);

        try(FileWriter fw = new FileWriter(outputPath, true);
            BufferedWriter writer = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(writer)){
            for(FastaProtein protein : fastaProteinList){
                writer.write(">" + protein.getDescription());
                writer.write("\n");
                writer.write(protein.getSequence());
                writer.write("\n");

                List<String> characters = Arrays.asList(protein.getSequence().split(""));
                Collections.shuffle(characters);
                String afterShuffle = "";
                for (String character : characters)
                {
                    afterShuffle += character;
                }

                writer.write(">Reversed_" + protein.getDescription());
                writer.write("\n");
                writer.write(afterShuffle);
                writer.write("\n");

            }writer.close();
        } catch (IOException e) {

        }System.out.println("shuffled");
    }
}
