package Modules.TextMiner;

import Model.Word;
import Util.StringUtils;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class TermFrequency {
    
    public ArrayList<Word> getTermFrequency(String textToParse) {
        ArrayList<String> words = StringUtils.stringToArrayOfWords(textToParse);
        ArrayList<Word> retorno = new ArrayList<>();
        for (String s : words) {
            double count = 0;
            for (String i : words) {
                if (s.equals(i)) {
                    count++;
                }
            }
            if (!s.isEmpty()) {
                double a = count/words.size();
                retorno.add(new Word(s,count/words.size()));
            }
        }

        LinkedHashSet<Word> temp = new LinkedHashSet<>();
        temp.addAll(retorno);
        retorno.clear();
        retorno.addAll(temp);
        return retorno;
    }
}
