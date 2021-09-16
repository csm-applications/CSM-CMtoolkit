package Modules.PreProcessing.Impl;

import Exceptions.NoTagsAvaliableException;
import Model.Phrase;
import Modules.PreProcessing.Interfaces.StopwordsRemover;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.trees.Tree;
import java.util.ArrayList;

public class PosTaggerStopwordRemover implements StopwordsRemover {

    private ArrayList<String> tags;

    public PosTaggerStopwordRemover() {
        this.tags = new ArrayList<>();
    }

    public PosTaggerStopwordRemover(ArrayList<String> tags) {
        this.tags = tags;
    }

    @Override
    public String removeStopwords(String contentToRemoveStopwords) {
        if (tags == null || tags.isEmpty()) {
            throw new NoTagsAvaliableException();
        }

        String result = "";
        Phrase phrase = new Phrase();
        Document doc = new Document(contentToRemoveStopwords);
        for (Sentence sent : doc.sentences()) {
            Tree t = sent.parse();
            phrase.setOriginalPhrase(t);
            phrase.setWordsExtracted(extractWords(t, tags));
            //System.out.println(phrase);
            for (Tree word : phrase.getWordsExtracted()) {
                int ini = word.toString().indexOf(" ") + 1;
                int fin = word.toString().indexOf(")");
                if (ini > 0 && fin > 0) {
                    //REMOVE WORDS WITH 2 CHARACTERS OR LESS - pROBABLY NOT USEFUL
                    if ((word.toString().substring(ini, fin) != null 
                            && word.toString().substring(ini, fin).length() > 2) || word.toString().substring(ini, fin).matches(".*\\d.*")) {
                        result += word.toString().substring(ini, fin) + " ";
                        //System.out.println(word.toString());
                    }
//                    }else{
//                        System.out.println("Word removed: " + word.toString().substring(ini,fin));
//                    }
                }
            }
        }
        return result;
    }

    private ArrayList<Tree> extractWords(Tree node, ArrayList<String> tags) {

        ArrayList<Tree> stopwordsList = new ArrayList<>();

        for (Tree subtree : node) {
            if ((tags).contains(subtree.label().value())) {
                stopwordsList.add(subtree);
            }
        }

        return stopwordsList;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

}
