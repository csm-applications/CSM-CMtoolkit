package Modules.TextMiner;

import Modules.PreProcessing.Enum.PrimitiveWekaDataTypesEnum;
import Modules.output.Attribute;
import java.util.ArrayList;
import java.util.HashSet;


public class BagOfWords {
    private HashSet<String> words;

    
    public void addWordsToBag(ArrayList<String> toAdd){
        words.addAll(toAdd);
    }
    
    public void addWordToBag(String toAdd){
        words.add(toAdd.toLowerCase());
    }
    
    public ArrayList<Attribute> convertToAttributesList(){
        ArrayList<Attribute> lstAtributes = new ArrayList<>();
        for (String a : words){
            lstAtributes.add(new Attribute(a, new ArrayList<>(), PrimitiveWekaDataTypesEnum.numeric.getType()));
        }
        return lstAtributes;
    }
    
    public BagOfWords() {
        words = new HashSet<>();
    }

    public HashSet<String> getWords() {
        return words;
    }

    public void setWords(HashSet<String> words) {
        this.words = words;
    }
    
    
}
