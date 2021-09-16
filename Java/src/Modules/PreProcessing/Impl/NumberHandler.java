package Modules.PreProcessing.Impl;

import Util.StringUtils;
import java.util.ArrayList;

public class NumberHandler {
    
    public String handleNumber(String toTreat){
        ArrayList<String> words = StringUtils.stringToArrayOfWords(toTreat);
        ArrayList<String> toReturn = new ArrayList<>();
        for(String s : words){
            if(s.matches(".*\\d.*")){
                toReturn.add("number#999");
            }else{
                toReturn.add(s);
            }
        }
        
        return StringUtils.arrayOfWordsToString(toReturn);
    }
    
}
