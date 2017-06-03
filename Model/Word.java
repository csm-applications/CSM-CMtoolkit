package Model;

import java.text.DecimalFormat;
import java.util.Objects;

public class Word implements Comparable<Word> {

    private String word;
    private double frequency;

    public Word(String word, double frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "[" + "w = " + word + ", f = " + df.format(frequency) + ']';
    }

    @Override
    public int compareTo(Word o) {
        if (this.frequency > o.getFrequency()) {
            return -1;
        }
        if (this.frequency < o.getFrequency()) {
            return 1;
        }

        return 0;

    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Word other = (Word) obj;
        if (!other.getWord().equals(this.word)) {
            return false;
        }
        return true;
    }
}
