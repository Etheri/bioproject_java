import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Start {
    public static void main(String[] args) {

        Map<String, Species> species = new HashMap<String, Species>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

            String parsString = "", tempSpecies = "", tempGeneName = "", tempGeneSeq = "", keySpecie ="";

            while ((parsString = reader.readLine()) != null) {

                if (parsString.startsWith(">")) {

                    keySpecie = parsString.substring(1, parsString.indexOf(':')).trim();


                    if (species.containsKey(keySpecie)) {
                        tempSpecies = keySpecie;
                        tempGeneName = parsString.substring(parsString.indexOf(':')+1).trim();
                    }
                    else {
                        Species s = new Species(keySpecie);
                        species.put(keySpecie, s);

                        tempSpecies = keySpecie;

                        tempGeneName = parsString.substring(parsString.indexOf(':')+1).trim();
                    }
                }
                else {
                    tempGeneSeq = parsString.trim();
                    species.get(tempSpecies).setGene(tempGeneName, tempGeneSeq);
                }
            }

            for (Map.Entry entry: species.entrySet()) {
                System.out.println(entry.getValue());

            }
        }

        catch (FileNotFoundException e) {
            e.getMessage();
            e.printStackTrace();
        } catch (IOException e1) {
            e1.getMessage();
            e1.printStackTrace();
        }



    }
}

