
package Model;


public class Method {
    
    private String method;
    private String studyDesign;
    private String typeOfStudy;
    private String participants;
    private String outcomeMesure;

    public Method(String method, String studyDesign, String typeOfStudy, String participants, String outcomeMesure) {
        this.method = method;
        this.studyDesign = studyDesign;
        this.typeOfStudy = typeOfStudy;
        this.participants = participants;
        this.outcomeMesure = outcomeMesure;
    }

    public Method(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return method + ", studyDesign = " + studyDesign + ", typeOfStudy = " + typeOfStudy + ", participants = " + participants + ", outcomeMesure = " + outcomeMesure + '}';
    }

    
    public Method() {
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStudyDesign() {
        return studyDesign;
    }

    public void setStudyDesign(String studyDesign) {
        this.studyDesign = studyDesign;
    }

    public String getTypeOfStudy() {
        return typeOfStudy;
    }

    public void setTypeOfStudy(String typeOfStudy) {
        this.typeOfStudy = typeOfStudy;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getOutcomeMesure() {
        return outcomeMesure;
    }

    public void setOutcomeMesure(String outcomeMesure) {
        this.outcomeMesure = outcomeMesure;
    }
    
    
    
}
