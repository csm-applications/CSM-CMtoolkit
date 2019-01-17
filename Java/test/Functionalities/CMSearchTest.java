package Tests.Functionalities;

import Approaches.CMSearch;
import Model.Concept;
import java.util.HashSet;

public class CMSearchTest {
    
    static int Ckey = 0;
    
    public static void main(String[] args) {
        CMSearch c = new CMSearch();
        c.setRoot("the software is being deployed");
        //Set the first concept
        Concept c1 = new Concept(Ckey++, "Concept Map");
        HashSet<String> c1Synonyms = new HashSet<>();
        c1Synonyms.add("concept maps");
        c1Synonyms.add("concept mapping");
        c1Synonyms.add("conceptual map");
        c1Synonyms.add("conceptual maps");
        
        c1.setSynonyms(c1Synonyms);
        
        //set the second concept
        Concept c2 = new Concept(Ckey++, "Computer Science");
        HashSet<String> c2Synonyms = new HashSet<>();
        c2Synonyms.add("computer science");
        c2Synonyms.add("computer engineering");
        c2Synonyms.add("software engineering");
        c2Synonyms.add("computation");
        
        c2.setSynonyms(c2Synonyms);
        
        HashSet<String> rSynonyms = new HashSet<>();
        rSynonyms.add("used in");
        rSynonyms.add("applied in");
        rSynonyms.add("to support");
        
        c.input(c1,c2,"into a", rSynonyms);
        System.out.println(c.getInput());
        c.doSearch();
    }
}
