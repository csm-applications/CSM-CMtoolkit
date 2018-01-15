package Model;

import java.util.Set;


public class Concept {
    private Integer key;
    private String concept;
    private Set<String> synonyms;
    
    public String toString(){
        return "([CP" + key + "] " + concept + ")";
    }

    public Concept() {
    }

    public Concept(Integer key, String concept) {
        this.key = key;
        this.concept = concept;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Set<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(Set<String> synonyms) {
        this.synonyms = synonyms;
    }
}
