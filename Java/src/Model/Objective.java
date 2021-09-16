
package Model;

public class Objective {
    
    private String objective;
    private String specificObjetive;

    public Objective(String objective, String specificObjetive) {
        this.objective = objective;
        this.specificObjetive = specificObjetive;
    }

    public Objective(String objective) {
        this.objective = objective;
    }

    @Override
    public String toString() {
        return objective + ", specificObjetive = " + specificObjetive + '}';
    }
    
    

    public Objective() {
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getSpecificObjetive() {
        return specificObjetive;
    }

    public void setSpecificObjetive(String specificObjetive) {
        this.specificObjetive = specificObjetive;
    }
    
    
    
}
