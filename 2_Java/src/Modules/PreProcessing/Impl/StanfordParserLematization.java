package Modules.PreProcessing.Impl;

import Modules.PreProcessing.Interfaces.Stemmer;
import Util.StringUtils;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class StanfordParserLematization implements Stemmer {

    public static StanfordCoreNLP pipeline;

    public StanfordParserLematization() {

        if (pipeline == null) {
            Properties props;
            props = new Properties();
            props.put("annotators", "tokenize, ssplit, pos, lemma");
            this.pipeline = new StanfordCoreNLP(props);
        }
    }

    @Override
    public String performStemming(String words) {
        List<String> lemmas = new LinkedList<>();

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(words);

        // run all Annotators on this text
        this.pipeline.annotate(document);

        // Iterate over all of the sentences found
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            // Iterate over all tokens in a sentence
            for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
                // Retrieve and add the lemma for each word into the list of lemmas
                lemmas.add(token.get(LemmaAnnotation.class));
            }
        }

        return StringUtils.arrayOfWordsToString(new ArrayList<>(lemmas));

    }

}
