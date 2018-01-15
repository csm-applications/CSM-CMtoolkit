package Approaches;

import Model.Abstract;
import Model.ConceptMap;
import Model.Relation;
import Modules.Classifiers.ClassifyUsingWeka;
import Modules.Tools.SelectTrippleByLength;
import Modules.Tools.SentenceSplitter;
import Modules.Tools.TripleExtractor;
import Presets.ScientificStudyStructure;
import java.util.ArrayList;

public class BasicCmExtractionApproach {

    public static ConceptMap generateConceptMap(ArrayList<Abstract> corpus, Abstract main) {
        ConceptMap cResult = new ConceptMap();
        ArrayList<String> sentences = SentenceSplitter.getSentences(main.getResults().getResults());

        System.out.println("\n--------------------applying IE------------------\n");
        //Get basic structure 
        cResult = ScientificStudyStructure.getScientificStructure(main.getTitle());

        String openIE = "";
        for (int i = 0; i < sentences.size(); i++) {
            openIE += " " + sentences.get(i);
        }
        ArrayList<Relation> toAdd = SelectTrippleByLength.selectBestTriple(TripleExtractor.extractRelationTriple(openIE));
        
        Abstract classified = ClassifyUsingWeka.classifyUsingJ48(corpus, main);
        
        if (toAdd != null && !toAdd.isEmpty()) {
            for (Relation add : toAdd) {
                cResult.addConcept(add.getFrom());
                cResult.addRelation(cResult.getConceptByName(main.getResults().getTypeOfResult()),
                        cResult.getConceptByName(add.getFrom().getConcept()),
                        "like");
                cResult.addConcept(add.getTo());
                cResult.addRelation(add);
            }
        }
        return cResult;
    }

}
