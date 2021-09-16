
package Modules.Tools;

import Model.Relation;
import java.util.ArrayList;

public class SelectTrippleByLength {
    
    public static ArrayList<Relation> selectBestTriple(ArrayList<Relation> toAnalyse) {
        if (toAnalyse != null && toAnalyse.size() > 0) {
            ArrayList<Relation> auxilary = new ArrayList<>();
            ArrayList<Relation> toReturn = new ArrayList<>();

            for (Relation r : toAnalyse) {
                auxilary.clear();
                String subjectLemmaGloss = r.getFrom().getConcept();
                auxilary.add(r);

                if (!containsSubjectLemmaGloss(toReturn, r.getFrom().getConcept())) {
                    for (Relation agroup : toAnalyse) {
                        if(agroup.getFrom().getConcept().equals(subjectLemmaGloss)){
                            auxilary.add(agroup);
                        }
                    }
                    toReturn.add(getBiggestRelationLemmaGloss(auxilary));
                }
            }

            return toReturn;
        }
        return null;
    }

    private static boolean containsSubjectLemmaGloss(ArrayList<Relation> toAnalyse, String subjectLemmaGloss) {
        for (Relation r : toAnalyse) {
            if (r.getFrom().getConcept().equals(subjectLemmaGloss)) {
                return true;
            }
        }
        return false;
    }
    
    private static Relation getBiggestRelationLemmaGloss(ArrayList<Relation> toSelect){
        Relation toReturn = null;
        for(Relation r : toSelect){
            if(toReturn == null){
                toReturn = r;
            }else if(toReturn.getTo().getConcept().length() < r.getTo().getConcept().length()){
                toReturn = r;
            }
        }
        return toReturn;
    }

}
