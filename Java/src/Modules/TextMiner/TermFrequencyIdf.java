package Modules.TextMiner;

import Model.Abstract;
import Model.Word;
import Util.StringUtils;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class TermFrequencyIdf {

    public ArrayList<Word> getTermFrequencyIdf(ArrayList<ArrayList<String>> docs, String textToParse) {
        
        ArrayList<String> wordsToParse = StringUtils.stringToArrayOfWords(textToParse);
        ArrayList<Word> retorno = new ArrayList<>();
        
        for (String s : wordsToParse) {
            double count = 0;
            retorno.add(new Word(s,tfIdf(wordsToParse, docs, s)));
        }

        LinkedHashSet<Word> temp = new LinkedHashSet<>();
        temp.addAll(retorno);
        retorno.clear();
        retorno.addAll(temp);
        return retorno;
    }

  
    public double tf(ArrayList<String> doc, String term) {
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word)) {
                result++;
            }
        }
        return result / doc.size();
    }

  
    public double idf(ArrayList<ArrayList<String>> docs, String term) {
        double n = 0;
        for (List<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        return Math.log(docs.size() / n);
    }

    
    public double tfIdf(ArrayList<String> doc, ArrayList<ArrayList<String>> docs, String term) {
        return tf(doc, term) * idf(docs, term);

    }
}
