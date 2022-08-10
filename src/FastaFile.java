import java.io.*;
import java.util.*;

public class FastaFile {

    public static void main(String[] args) throws Exception { //cannot be main method

        Scanner inScanner = new Scanner(System.in);
        System.out.print("Enter file path:");
        String file = inScanner.next();

        String outputPath = file.substring(0, file.lastIndexOf('/')) + File.separator + "shuffled.sequences";
        System.out.println("New file created: " + outputPath);
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
                writer.close();

            }
        } catch (IOException e) {
            System.out.println("error");
        }System.out.println("shuffled");
    }
}
