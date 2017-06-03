package Modules.TextMiner;

import Model.Word;
import java.util.ArrayList;
import java.util.HashSet;

public class TermFrequencyDocument {

    private HashSet<Word> words;

    public Word contains(Word toSearch) {
        if (words != null && words.size() > 1) {
            for (Word e : words) {
                if (e.equals(toSearch)) {
                    return e;
                }
            }
        }
        return null;
    }

    public TermFrequencyDocument(HashSet<Word> words) {
        this.words = words;
    }

    public HashSet<Word> getWords() {
        return words;
    }

    public void setWords(HashSet<Word> words) {
        this.words = words;
    }

}
