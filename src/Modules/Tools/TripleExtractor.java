package Modules.Tools;

import Model.Concept;
import Model.Relation;
import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.Triple;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class TripleExtractor {

    public static ArrayList<Relation> extractRelationTriple(String sentenceToAnalyse) {
        ArrayList<Relation> propositions = new ArrayList<>();

        // Create the Stanford CoreNLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,depparse,natlog,openie");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Annotate an example document.
        Annotation doc = new Annotation(sentenceToAnalyse);
        pipeline.annotate(doc);
        
        System.out.println(sentenceToAnalyse);

        // Loop over sentences in the documentLondrina
        for (CoreMap sentence : doc.get(CoreAnnotations.SentencesAnnotation.class)) {
            // Get the OpenIE triples for the sentence
            Collection<RelationTriple> triples = sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);
            // Print the triples
            for (RelationTriple triple : triples) {
                System.out.println(triple.confidence + "\t"
                        + triple.subjectLemmaGloss() + "\t\t\t"
                        + triple.relationLemmaGloss() + "\t\t\t"
                        + triple.objectLemmaGloss());
                Concept from = new Concept(1, triple.subjectLemmaGloss());
                Concept to = new Concept(2, triple.objectLemmaGloss());
                Relation toAdd = new Relation(1, from, to, triple.relationLemmaGloss());
                propositions.add(toAdd);
            }
        }

        return propositions;
    }

}
