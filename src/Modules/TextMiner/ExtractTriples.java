package Modules.TextMiner;

import Model.Concept;
import Model.Relation;
import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class ExtractTriples {

    public static List<Relation> extractTriples(String textToParse) {
        ArrayList<Relation> relation = new ArrayList<>();
        int i = 0;
        
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,depparse,natlog,openie");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Annotate an example document.
        Annotation doc = new Annotation(textToParse);
        pipeline.annotate(doc);

        // Loop over sentences in the document
        for (CoreMap sentence : doc.get(CoreAnnotations.SentencesAnnotation.class)) {
            // Get the OpenIE triples for the sentence
            Collection<RelationTriple> triples = sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);
            // Print the triples
            for (RelationTriple triple : triples) {
                relation.add(new Relation(
                        i++, 
                        new Concept(i++, triple.subjectLemmaGloss()), 
                        new Concept(i++, triple.objectLemmaGloss()), 
                        triple.relationLemmaGloss()));
//                System.out.println(triple.confidence + "\t"
//                        + "(C)" + triple.subjectLemmaGloss() + "\t"
//                        + "(R)" + triple.relationLemmaGloss() + "\t"
//                        + "(C)" + triple.objectLemmaGloss());
            }
        }
        return relation;
    }
}
