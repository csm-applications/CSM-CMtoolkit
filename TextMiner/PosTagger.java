package Modules.TextMiner;

import Model.Text;
import Model.Phrase;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.trees.Tree;
import java.util.ArrayList;
import java.util.List;


public class PosTagger {
    
    private Text text;

    
    public PosTagger(String textToConvert){
        text = parseText(textToConvert);
    }

    public PosTagger() {
    }
    
    public Text parseText(String textToConvert) {
        Text text = new Text();
        Phrase phrase = new Phrase();
        Document doc = new Document(textToConvert);
        for (Sentence sent : doc.sentences()) {
            Tree t = sent.parse();
            phrase.setOriginalPhrase(t);
            text.addPhrase(phrase);
        }
        return text;

    }



    private ArrayList<Tree> extractNouns(Tree node) {
        
        ArrayList<Tree> nounList = new ArrayList<>();
        
        for (Tree subtree : node) {
            if (subtree.label().value().equals("NN")) {
                nounList.add(subtree);
            }
        }
        
        return nounList;
    }
    
    private ArrayList<Tree> extractVerbs(Tree node) {
        
        ArrayList<Tree> verbsList = new ArrayList<>();
        
        for (Tree subtree : node) {
            if (subtree.label().value().equals("VB")) {
                verbsList.add(subtree);
            }
        }
        
        return verbsList;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    
}
