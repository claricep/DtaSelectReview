import java.io.*;
import java.util.*;

//Users/claricepark/data/blindptm/search.xml
//Users/claricepark/data/blindptm/DTASelect-filter.txt
///Users/claricepark/data/blindptm/UniProt_human_reviewed_contaminant_05-23-2020_reversed.fasta

public class FastaFile {

    public static void main(String[] args) throws Exception { //cannot be main method

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file path:");
        String fastaPathInput = sc.next();
        String inputFastaFile = fastaPathInput;
        System.out.println("You entered: " + inputFastaFile);

        //String file = "/Users/claricepark/data/shaboo/UniProt_HumEnz_SARS2_Schief_RBD_v202_v_1_contaminant_08-07-2022_reversed_cleaned.fasta";
        String outputPath = inputFastaFile.substring(0, inputFastaFile.lastIndexOf('/')) + File.separator + "shuffled.sequences";
        File outputFile = new File(outputPath);
        if(outputFile.exists())
            outputFile.delete();

        List<FastaProtein> fastaProteinList = ReadFasta1.getFastaProteinList(inputFastaFile);

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

        }System.out.println('\n' + "New file created: " + outputPath);
    }
}