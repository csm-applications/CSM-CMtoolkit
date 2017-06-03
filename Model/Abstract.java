package Model;

import Exceptions.InvalidTagInsideFileException;
import Modules.PreProcessing.Enum.PreProcessingMethodsSelectionEnum;
import Modules.PreProcessing.Impl.PorterStemmer;
import Modules.PreProcessing.Impl.StanfordParserLematization;
import Modules.PreProcessing.Impl.PosTaggerStopwordRemover;
import Modules.PreProcessing.Impl.SymbolsRemoveUsingLists;
import Modules.PreProcessing.Interfaces.Stemmer;
import Modules.PreProcessing.Interfaces.StopwordsRemover;
import Modules.PreProcessing.Interfaces.SymbolsRemover;
import Modules.PreProcessing.Enum.PartOfStructuredAbstractEnum;
import Modules.TextMiner.TermFrequency;
import Modules.TextMiner.TermFrequencyIdf;
import Util.StringUtils;
import java.util.ArrayList;

public class Abstract {

    private String title;

    private Context context;
    private Objective objectives;
    private Method methods;
    private Results results;
    private Conclusion conclusions;

    public Abstract(String title, Context context, Objective objectives, Method methods, Results results, Conclusion conclusions) {
        this.title = title;
        this.context = context;
        this.objectives = objectives;
        this.methods = methods;
        this.results = results;
        this.conclusions = conclusions;
    }

    public Abstract() {
        this.context = new Context();
        this.methods = new Method();
        this.objectives = new Objective();
        this.results = new Results();
        this.conclusions = new Conclusion();
    }

    public Abstract(String absTxt) {
        this.context = new Context();
        this.methods = new Method();
        this.objectives = new Objective();
        this.results = new Results();
        this.conclusions = new Conclusion();

        //Verify Tags
        if ((absTxt.contains("<title>") && !absTxt.contains("</title>"))
                || (absTxt.contains("<context>") && !absTxt.contains("</context>"))
                || (absTxt.contains("<objectives>") && !absTxt.contains("</objectives>"))
                || (absTxt.contains("<methods>") && !absTxt.contains("</methods>"))
                || (absTxt.contains("<results>") && !absTxt.contains("</results>"))
                || (absTxt.contains("<conclusions>") && !absTxt.contains("</conclusions>"))) {

            String title = null;
            try {
                title = absTxt.substring(absTxt.indexOf("<title>") + 7, absTxt.indexOf("</title>"));
            } catch (Exception e) {
                title = "The <title> tag could not be found in this summary \n\n RawText:" + absTxt;
                throw new InvalidTagInsideFileException(title);
            }
            
            throw new InvalidTagInsideFileException(title);
        }

        //read the header
        if (absTxt.contains("<header>") && absTxt.contains("</header>")) {
            if (absTxt.contains("<TypeOfResult>") && absTxt.contains("</TypeOfResult>")) {
                results.setTypeOfResult(absTxt.substring(absTxt.indexOf("<TypeOfResult>") + 14, absTxt.indexOf("</TypeOfResult>")));
            }
        }

        //Read the abstract
        if (absTxt.toLowerCase().contains("<title>")) {
            title = absTxt.substring(absTxt.toLowerCase().indexOf("<title>") + 7, absTxt.toLowerCase().indexOf("</title>"));
        }
        if (absTxt.toLowerCase().contains("<context>")) {
            context.setContext(absTxt.substring(absTxt.toLowerCase().indexOf("<context>") + 9, absTxt.toLowerCase().indexOf("</context>")));

        }
        if (absTxt.toLowerCase().contains("<objectives>")) {
            objectives.setObjective(absTxt.substring(absTxt.toLowerCase().indexOf("<objectives>") + 12, absTxt.toLowerCase().indexOf("</objectives>")));
        }
        if (absTxt.toLowerCase().contains("<methods>")) {

            methods.setMethod(absTxt.substring(absTxt.toLowerCase().indexOf("<methods>") + 9, absTxt.toLowerCase().indexOf("</methods>")));
        }
        if (absTxt.toLowerCase().contains("<results>")) {

            results.setResults(absTxt.substring(absTxt.toLowerCase().indexOf("<results>") + 9, absTxt.toLowerCase().indexOf("</results>")));
        }
        if (absTxt.toLowerCase().contains("<conclusions>")) {

            conclusions.setConclusion(absTxt.substring(absTxt.toLowerCase().indexOf("<conclusions>") + 13, absTxt.toLowerCase().indexOf("</conclusions>")));
        }
    }

    public Abstract performSymbolsRemoval(SymbolsRemover symbolsRemover, int methodUsed, String pathToSymbolsList) {
        Abstract r = new Abstract();
        
        SymbolsRemoveUsingLists syRm = new SymbolsRemoveUsingLists();
        syRm.setPathToSymbolsList(pathToSymbolsList);
        
        
        if (title != null) {
            r.setTitle(syRm.removeSymbols(title));
        }
        if (context.getContext() != null) {
            r.setContext(new Context(syRm.removeSymbols(context.getContext())));
        }
        if (objectives.getObjective() != null) {
            r.setObjectives(new Objective(syRm.removeSymbols(objectives.getObjective())));
        }
        if (methods.getMethod() != null) {
            r.setMethods(new Method(syRm.removeSymbols(methods.getMethod())));
        }
        if (results.getResults() != null) {
            r.setResults(new Results(syRm.removeSymbols(results.getResults()), results.getTypeOfResult()));
        }
        if (conclusions.getConclusion() != null) {
            r.setConclusions(new Conclusion(syRm.removeSymbols(conclusions.getConclusion())));
        }

        return r;
    }

    public Abstract performStopwordsRemoval(StopwordsRemover stopwordsRemover, int methodUsed) {
        Abstract r = new Abstract();

        if (PreProcessingMethodsSelectionEnum.STOPWORDS_REMOVAL_BY_POS_TAGGING.getMethod() == methodUsed) {
            stopwordsRemover = (PosTaggerStopwordRemover) stopwordsRemover;
        }

        if (title != null) {
            r.setTitle(stopwordsRemover.removeStopwords(title));
        }
        if (context.getContext() != null) {
            r.setContext(new Context(stopwordsRemover.removeStopwords(context.getContext())));
        }
        if (objectives.getObjective() != null) {
            r.setObjectives(new Objective(stopwordsRemover.removeStopwords(objectives.getObjective())));
        }
        if (methods.getMethod() != null) {
            r.setMethods(new Method(stopwordsRemover.removeStopwords(methods.getMethod())));
        }
        if (results.getResults() != null) {
            r.setResults(new Results(stopwordsRemover.removeStopwords(results.getResults()), results.getTypeOfResult()));
        }
        if (conclusions.getConclusion() != null) {
            r.setConclusions(new Conclusion(stopwordsRemover.removeStopwords(conclusions.getConclusion())));
        }

        return r;
    }

    public Abstract performStemming(Stemmer stemmer, int methodUsed) {
        Abstract r = new Abstract();

        if (PreProcessingMethodsSelectionEnum.PORTER_STEMMER.getMethod() == methodUsed) {
            stemmer = (PorterStemmer) stemmer;
        } else if (PreProcessingMethodsSelectionEnum.STANFORD_LEMMA_STEMMER.getMethod() == methodUsed) {
            stemmer = (StanfordParserLematization) stemmer;
        }

        if (title != null) {
            r.setTitle(stemmer.performStemming(title));
        }
        if (context.getContext() != null) {
            r.setContext(new Context(stemmer.performStemming(context.getContext())));
        }
        if (objectives.getObjective() != null) {
            r.setObjectives(new Objective(stemmer.performStemming(objectives.getObjective())));
        }
        if (methods.getMethod() != null) {
            r.setMethods(new Method(stemmer.performStemming(methods.getMethod())));
        }
        if (results.getResults() != null) {
            r.setResults(new Results(stemmer.performStemming(results.getResults()), results.getTypeOfResult()));
        }
        if (conclusions.getConclusion() != null) {
            r.setConclusions(new Conclusion(stemmer.performStemming(conclusions.getConclusion())));
        }
        return r;
    }

    public AbstractTermFrequency applyTermFrequency() {

        AbstractTermFrequency r = new AbstractTermFrequency();
        TermFrequency tf = new TermFrequency();

        if (title != null) {
            r.setTitle(tf.getTermFrequency(title));
        }
        if (context.getContext() != null) {
            r.setContext(tf.getTermFrequency(context.getContext()));
        }
        if (objectives.getObjective() != null) {
            r.setObjectives(tf.getTermFrequency(objectives.getObjective()));
        }
        if (methods.getMethod() != null) {
            r.setMethods(tf.getTermFrequency(methods.getMethod()));
        }
        if (results.getResults() != null) {
            r.setResults(tf.getTermFrequency(results.getResults()));
        }
        if (conclusions.getConclusion() != null) {
            r.setConclusions(tf.getTermFrequency(conclusions.getConclusion()));
        }

        return r;
    }

    public AbstractTermFrequency applyTermFrequencyIdf(ArrayList<Abstract> abs) {

        AbstractTermFrequency r = new AbstractTermFrequency();
        TermFrequencyIdf tfidf = new TermFrequencyIdf();

        if (title != null) {
            r.setTitle(tfidf.getTermFrequencyIdf(StringUtils.convertAbstractsToArray(abs, PartOfStructuredAbstractEnum.title.getTag()), title));
        }
        if (context.getContext() != null) {
            r.setContext(tfidf.getTermFrequencyIdf(StringUtils.convertAbstractsToArray(abs, PartOfStructuredAbstractEnum.context.getTag()), context.getContext()));
        }
        if (objectives.getObjective() != null) {
            r.setObjectives(tfidf.getTermFrequencyIdf(StringUtils.convertAbstractsToArray(abs, PartOfStructuredAbstractEnum.objectives.getTag()), objectives.getObjective()));
        }
        if (methods.getMethod() != null) {
            r.setMethods(tfidf.getTermFrequencyIdf(StringUtils.convertAbstractsToArray(abs, PartOfStructuredAbstractEnum.methods.getTag()), methods.getMethod()));
        }
        if (results.getResults() != null) {
            r.setResults(tfidf.getTermFrequencyIdf(StringUtils.convertAbstractsToArray(abs, PartOfStructuredAbstractEnum.results.getTag()), results.getResults()));
        }
        if (conclusions.getConclusion() != null) {
            r.setConclusions(tfidf.getTermFrequencyIdf(StringUtils.convertAbstractsToArray(abs, PartOfStructuredAbstractEnum.conclusions.getTag()), conclusions.getConclusion()));
        }

        return r;
    }

    public String toString() {
        String r = "[Title:" + title + "]";
        r += "\n[Context:" + context + "]";
        r += "\n[Objectives:" + objectives + "]";
        r += "\n[Methods:" + methods + "]";
        r += "\n[Results:" + results + "]";
        r += "\n[Conclusions:" + conclusions + "]";

        return r;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Objective getObjectives() {
        return objectives;
    }

    public void setObjectives(Objective objectives) {
        this.objectives = objectives;
    }

    public Method getMethods() {
        return methods;
    }

    public void setMethods(Method methods) {
        this.methods = methods;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public Conclusion getConclusions() {
        return conclusions;
    }

    public void setConclusions(Conclusion conclusions) {
        this.conclusions = conclusions;
    }

}
