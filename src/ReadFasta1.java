import java.io.FileReader;
import java.util.*;
import java.io.BufferedReader;
import java.util.Collections;
import java.util.Random;

public class ReadFasta1 {


    public static List<FastaProtein> getFastaProteinList(String fastaFile) throws Exception {

        List<FastaProtein> proteins = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(fastaFile));
        StringBuffer sb = new StringBuffer();

        String eachLine = null;
        eachLine = br.readLine();
        while (true) {

            String defLine = eachLine.substring(1);
            while(   ((eachLine = br.readLine()) != null) && !eachLine.startsWith(">") ) {
                if(eachLine.equals(""))
                    continue;
                sb.append(eachLine);
            }
            FastaProtein protein = new FastaProtein();
            protein.setDescription(defLine);
            protein.setSequence(sb.toString());

            proteins.add(protein);
            sb.delete(0, sb.length());

            if(eachLine == null)
                break;
        }

        br.close();
        return proteins;
    }
}
