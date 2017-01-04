import java.util.HashMap;
import java.util.Map;

public class Species {
    private String Name;
    //Key - gene, Value - seq
    private Map<String, String> hashmap = new HashMap<String, String>();

    Species (String name) {
        this.Name = name;
    }

    public void setGene (String _nameOfGene, String _Seq) {
        this.hashmap.put(_nameOfGene,_Seq);

    }

    public String toString() {
        String result = "\033[31;1m"+this.Name+":"+"\033[32;1m"+hashmap.keySet()+"\n";
        for(String person : hashmap.keySet()){
            result+="\033[35;1m"+person+"\n";
            result+="\033[36;1m"+hashmap.get(person)+"\n";
        }
        return result;
    }


}