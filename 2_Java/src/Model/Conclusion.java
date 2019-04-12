
package Model;

public class Conclusion {
    
    private String conclusion;
    private String contributions;
    private String limitations;
    private String futureWork;

    public Conclusion(String conclusion, String contributions, String limitations, String futureWork) {
        this.conclusion = conclusion;
        this.contributions = contributions;
        this.limitations = limitations;
        this.futureWork = futureWork;
    }

    public Conclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    
    
    public Conclusion() {
    }

    @Override
    public String toString() {
        return  conclusion + ", contributions = " + contributions + ", limitations=" + limitations + ", futureWork=" + futureWork + '}';
    }
    
    

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getContributions() {
        return contributions;
    }

    public void setContributions(String contributions) {
        this.contributions = contributions;
    }

    public String getLimitations() {
        return limitations;
    }

    public void setLimitations(String limitations) {
        this.limitations = limitations;
    }

    public String getFutureWork() {
        return futureWork;
    }

    public void setFutureWork(String futureWork) {
        this.futureWork = futureWork;
    }
    
    
    
    
}
