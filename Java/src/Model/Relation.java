package Model;

import java.util.Set;

public class Relation {
    private int key;
    private Concept from;
    private Concept to;
    private String relationDescription;
    private Set<String> synonyms;

    public Relation(int key, Concept from, Concept to, String relationDescription) {
        this.key = key;
        this.from = from;
        this.to = to;
        this.relationDescription = relationDescription;
    }

    public String toString(){
        return "[RL" + from + "-->" + relationDescription + "-->" + to + "]\n";
    }
    
    public Relation() {
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
    
    public Concept getFrom() {
        return from;
    }

    public void setFrom(Concept from) {
        this.from = from;
    }

    public Concept getTo() {
        return to;
    }

    public void setTo(Concept to) {
        this.to = to;
    }

    public String getRelationDescription() {
        return relationDescription;
    }

    public void setRelationDescription(String relationDescription) {
        this.relationDescription = relationDescription;
    }

    public Set<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(Set<String> synonyms) {
        this.synonyms = synonyms;
    }
}
