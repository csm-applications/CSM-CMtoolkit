package Model;


public class Concept {
    private Integer key;
    private String concept;
    
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
    
    
    
}
