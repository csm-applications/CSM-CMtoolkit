package Approaches;

import Model.Abstract;
import Model.Concept;
import Model.ConceptMap;
import Model.Relation;
import Modules.Input.BibTexFileLoader;
import Modules.TextMiner.ExtractTriples;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CMSearch {

    static int Rkey = 0;

    private ConceptMap input = new ConceptMap();

    public void input(Concept concept1, Concept concept2, String relation, Set<String> relationSynonyms) {
        Relation add = new Relation(Rkey++, concept1, concept2, relation);
        add.setSynonyms(relationSynonyms);
        input.addConcept(concept1);
        input.addConcept(concept2);
        input.addRelation(add);
    }

    public void doSearch() {
        List<Abstract> database = BibTexFileLoader.loadAbstracts("test/Resources/BibTexFiles/acm-Abs.bib");
        List<RelevanceLevel> evaluatedList = calculateRelevanceLevel(database);
        Collections.sort(evaluatedList);
        for (RelevanceLevel a : evaluatedList) {
            System.out.println("-----------------------------------");
            System.out.println("Relevance: " + a.getPointsOfRelevance());
            System.out.println("title: " + a.getAbstractEvaluated().getTitle());
            System.out.println("abstract: " + a.getAbstractEvaluated().getRawAbstract());
            System.out.println("-------------------------------\n");
        }
    }

    /*
        if you find in the title or the abstract one of the concepts of the input (cumulative) → +1
        if you find in the title or abstract the concepts of the input in the exact order → +3
        if you find in the mined relations at least one word of the input → +5
        if you find in the mined relations the full sentence of the input → +10
        
     */
    private List<RelevanceLevel> calculateRelevanceLevel(List<Abstract> abstracts) {
        //initialize the array with the abstracts
        ArrayList<RelevanceLevel> toReturn = new ArrayList<>();
        for (Abstract a : abstracts) {
            toReturn.add(new RelevanceLevel(0, a));
        }

        //if you find in the title or the abstract one of the concepts of the input (cumulative) → +1
        for (RelevanceLevel r : toReturn) {
            for (Concept c : input.getConcepts()) {
                for (String synonym : c.getSynonyms()) {
                    if (r.getAbstractEvaluated().getTitle().toLowerCase().contains(synonym.toLowerCase())
                            || r.getAbstractEvaluated().getRawAbstract().toLowerCase().contains(synonym.toLowerCase())) {
                        r.setPointsOfRelevance(r.getPointsOfRelevance() + 1);
                        System.out.println("More 1 points " + c);
                    }
                }
                if (r.getAbstractEvaluated().getTitle().toLowerCase().contains(c.getConcept().toLowerCase())
                        || r.getAbstractEvaluated().getRawAbstract().toLowerCase().contains(c.getConcept().toLowerCase())) {
                    r.setPointsOfRelevance(r.getPointsOfRelevance() + 1);
                    System.out.println("More 1 points " + c);
                }
            }
        }

        //if you find in the title or abstract the concepts of the input in the exact order → +3
        ArrayList<Relation> rel = input.getRelations();
        Relation relation = rel.get(0);
        String relationToSearch = relation.getFrom().getConcept() + " "
                + relation.getRelationDescription() + " "
                + relation.getTo().getConcept();
        relationToSearch = relationToSearch.toLowerCase();

        for (RelevanceLevel r : toReturn) {
            if (r.getAbstractEvaluated().getTitle().toLowerCase().contains(relationToSearch)
                    || r.getAbstractEvaluated().getRawAbstract().toLowerCase().contains(relationToSearch)) {
                r.setPointsOfRelevance(r.getPointsOfRelevance() + 3);
                System.out.println("More 3 points " + relationToSearch);
            }
        }

        //if you find in the mined relations at least one word of the input → +5
        for (RelevanceLevel r : toReturn) {
            List<Relation> relationsMined = ExtractTriples.extractTriples(r.getAbstractEvaluated().getRawAbstract());
            for (Relation c : relationsMined) {
                String abs = r.getAbstractEvaluated().getRawAbstract();
                String from = c.getFrom().getConcept();
                String to = c.getTo().getConcept();
                String relDescription = c.getRelationDescription();
                String fullRelation = from + " " + relDescription + " " + to;
                if (abs.toLowerCase().contains("concept map") && abs.toLowerCase().contains("computer science")
                        || r.getAbstractEvaluated().getTitle().toLowerCase().contains("concept map")
                        && abs.toLowerCase().contains("computer science")) {
                    System.out.println("\nRelation of input:" + relationToSearch);
                    System.out.println("title = " + r.getAbstractEvaluated().getTitle());
                    //System.out.println("abs = " + r.getAbstractEvaluated().getRawAbstract());
                    System.out.println("Relation Searched: " + fullRelation);
                }

                //if you find in the mined relations the full sentence of the input → +10
                if (fullRelation.contains(relationToSearch)) {
                    r.setPointsOfRelevance(r.getPointsOfRelevance() + 10);
                    System.out.println("more 10 points" + c);
                    break;
                }

                boolean fromContainsInputElement = false;
                for (String syn : relation.getFrom().getSynonyms()) {
                    if (relationToSearch.contains(syn)) {
                        fromContainsInputElement = true;
                    }
                }

                boolean toContainsInputElement = false;
                for (String syn : relation.getTo().getSynonyms()) {
                    if (relationToSearch.contains(syn)) {
                        toContainsInputElement = true;
                    }
                }

                boolean relationContainsInputElement = false;
                for (String syn : relation.getSynonyms()) {
                    if (relationToSearch.contains(syn)) {
                        relationContainsInputElement = true;
                    }
                }

                if (fromContainsInputElement && toContainsInputElement && relationContainsInputElement) {
                    r.setPointsOfRelevance(r.getPointsOfRelevance() + 5);
                    System.out.println("More 5 points" + c);
                    break;
                }
            }
        }

        return toReturn;
    }

    class RelevanceLevel implements Comparable<RelevanceLevel> {

        private int pointsOfRelevance;
        private Abstract abstractEvaluated;

        @Override
        public int compareTo(RelevanceLevel t) {
            if (pointsOfRelevance == t.getPointsOfRelevance()) {
                return 0;
            } else if (pointsOfRelevance > t.getPointsOfRelevance()) {
                return 1;
            } else {
                return -1;
            }
        }

        public RelevanceLevel(int pointsOfRelevance, Abstract abstractEvaluated) {
            this.pointsOfRelevance = pointsOfRelevance;
            this.abstractEvaluated = abstractEvaluated;
        }

        public RelevanceLevel() {
        }

        public int getPointsOfRelevance() {
            return pointsOfRelevance;
        }

        public void setPointsOfRelevance(int pointsOfRelevance) {
            this.pointsOfRelevance = pointsOfRelevance;
        }

        public Abstract getAbstractEvaluated() {
            return abstractEvaluated;
        }

        public void setAbstractEvaluated(Abstract abstractEvaluated) {
            this.abstractEvaluated = abstractEvaluated;
        }

    }

    public ConceptMap getInput() {
        return input;
    }

    public void setRoot(String title) {
        input.addRoot(title);
    }

}
