package Model;

import Exceptions.CannotAddRelationException;
import java.util.ArrayList;

public class ConceptMap {

    private Concept rootNode;
    private ArrayList<Concept> concepts;
    private ArrayList<Relation> relations;

    public Integer getNumberOfConcepts() {
        if (concepts != null) {
            return concepts.size();
        }
        return 0;
    }

    public int getNumberOfRelations() {
        if (relations != null) {
            return relations.size();
        }
        return 0;
    }

    public String getConceptsAsJson() {
        String rConcept = "[";

        rConcept += "{" + "key :" + rootNode.getKey() + ", " + "text: \"" + rootNode.getConcept() + "\"},";

        for (Concept c : concepts) {
            rConcept += "{" + "key: " + c.getKey() + ", " + "text: \"" + c.getConcept() + "\"},";
        }

        rConcept += "]";

        return rConcept;
    }

    public String getRelationsAsJson() {
        String rRelations = "[";
        for (Relation r : relations) {
            rRelations += "{" + "from: " + r.getFrom().getKey() + ", " + "to: " + r.getTo().getKey() + ", " + "text: \"" + r.getRelationDescription() + "\"},";
        }

        rRelations += "]";

        return rRelations;
    }

    public Concept getConceptById(int toFind) {
        for (Concept c : concepts) {
            if (c.getKey() == toFind) {
                return c;
            }
        }
        return null;
    }

    public Concept getConceptByName(String toFind) {
        if (toFind != null && !toFind.equals("") && concepts != null && concepts.size() > 1) {
            for (Concept c : concepts) {
                if (c.getConcept().toLowerCase().equals(toFind.toLowerCase())) {
                    return c;
                }
            }
            if (rootNode.getConcept().toLowerCase().equals(toFind.toLowerCase())) {
                return rootNode;
            }
        }
        return null;
    }

    public Relation getRelationByName(String toFind) {
        if (toFind != null) {
            for (Relation r : relations) {
                if (r.getRelationDescription().contains(toFind)) {
                    return r;
                }
            }
        }
        return null;
    }

    public String toString() {
        String toPrint = "";

        toPrint += "<root>" + rootNode.getConcept() + "</root>\n";

        for (Concept c : concepts) {
            toPrint += "\t" + c.toString() + "\n";
            for (Relation r : relations) {
                if(r.getFrom().getConcept().equals(c.getConcept())){
                    toPrint += "\t\t" + r.getRelationDescription() + " → " + r.getTo() + "\n";
                }
            }
        }

        return toPrint;
    }

    public void addRoot(String paperTitle) {
        rootNode = new Concept(1, paperTitle);
    }

    public void addConcept(String concept) {
        concepts.add(new Concept(getLastConceptKey() + 1, concept));
    }

    public void addConcept(int key, String concept) {
        if (getConceptById(key) == null && getConceptByName(concept) != null) {
            concepts.add(new Concept(key, concept));
        } else {
            System.out.println("Id Already exists in concept map");
        }
    }

    public void addAllConcepts(ArrayList<String> concepts) {
        for (String s : concepts) {
            this.concepts.add(new Concept(getLastConceptKey() + 1, s));
        }
    }

    public void addRelation(Concept from, Concept to, String relationDescription) {
        if (concepts != null && concepts.size() > 0 && from != null && to != null && from.getConcept() != null) {
            relations.add(new Relation(getLastRelationKey() + 1, getConceptByName(from.getConcept()), getConceptByName(to.getConcept()), relationDescription));
        } else {
            System.out.println("This relationship already Exists");
        }
    }

    public void addConcept(Concept toAdd) {
        if (getConceptByName(toAdd.getConcept()) == null) {
            toAdd.setKey(getLastConceptKey() + 1);
            concepts.add(toAdd);
        }
    }

    public void addRelation(Relation toAdd) {
        if (concepts != null && concepts.size() > 0 && toAdd.getFrom().getConcept() != null && getConceptByName(toAdd.getTo().getConcept()) != null) {
            toAdd.setKey(getLastRelationKey() + 1);
            toAdd.setFrom(getConceptByName(toAdd.getFrom().getConcept()));
            toAdd.setTo(getConceptByName(toAdd.getTo().getConcept()));
            relations.add(toAdd);
        } else {
            throw new CannotAddRelationException();
        }
    }

    private int getLastConceptKey() {
        if (concepts != null && concepts.size() > 0) {
            return concepts.get(concepts.size() - 1).getKey();
        } else {
            return 1;
        }
    }

    private int getLastRelationKey() {
        if (relations != null && relations.size() > 0) {
            return relations.get(relations.size() - 1).getKey();
        } else {
            return 1;
        }
    }

    //constructor
    public ConceptMap() {
        rootNode = new Concept();
        concepts = new ArrayList<>();
        relations = new ArrayList<>();
    }

    public Concept getRootNode() {
        return rootNode;
    }

    public ArrayList<Concept> getConcepts() {
        return concepts;
    }

    public ArrayList<Relation> getRelations() {
        return relations;
    }

}
