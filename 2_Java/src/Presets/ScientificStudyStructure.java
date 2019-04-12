package Presets;

import Model.ConceptMap;

public class ScientificStudyStructure {

    public static ConceptMap getScientificStructure(String paperTitle){
        ConceptMap cMap = new ConceptMap();
        
        cMap.addRoot(paperTitle);
        
        // concepts 1st level
        
        cMap.addConcept("Context");
        cMap.addConcept("Objective");
        cMap.addConcept("Method");
        cMap.addConcept("Result");
        cMap.addConcept("Conclusion");
        
        //concepts 2nd level
        
//        cMap.addConcept("Reseach Area");
//        cMap.addConcept("Gap");
//        cMap.addConcept("Motivation");
//        cMap.addConcept("Specific Objective");
//        cMap.addConcept("Study Design");
//        cMap.addConcept("Type of Study");
//        cMap.addConcept("Participants");
//        cMap.addConcept("Outcome Measure");
        cMap.addConcept("Qualitative");
        cMap.addConcept("Quantitative");
//        cMap.addConcept("Contributions");
//        cMap.addConcept("Limitations");
//        cMap.addConcept("Future Work");
        
        //relations
        
        cMap.addRelation(cMap.getRootNode(),cMap.getConceptByName("Context"), "contains");
        cMap.addRelation(cMap.getRootNode(),cMap.getConceptByName("Objective"), "contains");
        cMap.addRelation(cMap.getRootNode(),cMap.getConceptByName("Method"), "contains");
        cMap.addRelation(cMap.getRootNode(),cMap.getConceptByName("Result"), "contains");
        cMap.addRelation(cMap.getRootNode(),cMap.getConceptByName("Conclusion"), "contains");
        
//        cMap.addRelation(cMap.getConceptByName("Context"),cMap.getConceptByName("Reseach Area"), "is composed by");
//        cMap.addRelation(cMap.getConceptByName("Context"),cMap.getConceptByName("Gap"), "is composed by");
//        cMap.addRelation(cMap.getConceptByName("Context"),cMap.getConceptByName("Motivation"), "is composed by");
//        cMap.addRelation(cMap.getConceptByName("Objective"),cMap.getConceptByName("Specific Objective"), "is classified into");
//        cMap.addRelation(cMap.getConceptByName("Method"),cMap.getConceptByName("Study Design"), "is formed by");
//        cMap.addRelation(cMap.getConceptByName("Method"),cMap.getConceptByName("Type of Study"), "is formed by");
//        cMap.addRelation(cMap.getConceptByName("Method"),cMap.getConceptByName("Participants"), "is formed by");
//        cMap.addRelation(cMap.getConceptByName("Method"),cMap.getConceptByName("Outcome Measure"), "is formed by");
        cMap.addRelation(cMap.getConceptByName("Result"),cMap.getConceptByName("Qualitative"), "are divided into");
        cMap.addRelation(cMap.getConceptByName("Result"),cMap.getConceptByName("Quantitative"), "are divided into");
//        cMap.addRelation(cMap.getConceptByName("Conclusion"),cMap.getConceptByName("Contributions"), "proposes");
//        cMap.addRelation(cMap.getConceptByName("Conclusion"),cMap.getConceptByName("Limitations"), "proposes");
//        cMap.addRelation(cMap.getConceptByName("Conclusion"),cMap.getConceptByName("Future Work"), "proposes");
        
        
        return cMap;
        
    }

}
