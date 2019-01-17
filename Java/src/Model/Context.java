package Model;

public class Context {
    
    private String context;
    private String researchArea;
    private String Gap;
    private String Motivation;

    public Context(String context, String researchArea, String Gap, String Motivation) {
        this.context = context;
        this.researchArea = researchArea;
        this.Gap = Gap;
        this.Motivation = Motivation;
    }

    public Context(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return context + ", researchArea = " + researchArea + ", Gap = " + Gap + ", Motivation = " + Motivation ;
    }
    
    

    public Context() {
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    public String getGap() {
        return Gap;
    }

    public void setGap(String Gap) {
        this.Gap = Gap;
    }

    public String getMotivation() {
        return Motivation;
    }

    public void setMotivation(String Motivation) {
        this.Motivation = Motivation;
    }
   
    
 
    
}
