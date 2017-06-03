package Model;

import Model.Phrase;
import java.util.ArrayList;


public class Text {
    
    
    private ArrayList<Phrase> phrases;

    public Text() {
        phrases = new ArrayList<>();
    }
    
    public void addPhrase(Phrase p){
        phrases.add(p);
    }

    public Text(ArrayList<Phrase> phrases) {
        this.phrases = phrases;
    }

    public ArrayList<Phrase> getPhrases() {
        return phrases;
    }

    public void setPhrases(ArrayList<Phrase> phrases) {
        this.phrases = phrases;
    }
    
    
    
}
