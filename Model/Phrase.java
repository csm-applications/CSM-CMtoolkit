package Model;

import edu.stanford.nlp.trees.Tree;
import java.util.ArrayList;

public class Phrase {
    
    private Tree originalPhrase;
    private ArrayList<Tree> WordsExtracted;

    public Phrase(Tree originalPhrase, ArrayList<Tree> WordsExtracted) {
        this.originalPhrase = originalPhrase;
        this.WordsExtracted = WordsExtracted;
    }

    @Override
    public String toString() {
        return "Phrase{" + "originalPhrase=" + originalPhrase + ", WordsExtracted=" + WordsExtracted + '}';
    }

    public Phrase() {
    }

    public Tree getOriginalPhrase() {
        return originalPhrase;
    }

    public void setOriginalPhrase(Tree originalPhrase) {
        this.originalPhrase = originalPhrase;
    }

    public ArrayList<Tree> getWordsExtracted() {
        return WordsExtracted;
    }

    public void setWordsExtracted(ArrayList<Tree> WordsExtracted) {
        this.WordsExtracted = WordsExtracted;
    }

    
    

    
}
