package Model;

import Modules.PreProcessing.Enum.PreProcessingMethodsSelectionEnum;
import Modules.PreProcessing.Impl.PorterStemmer;
import Modules.PreProcessing.Impl.StanfordParserLematization;
import Modules.PreProcessing.Impl.PosTaggerStopwordRemover;
import Modules.PreProcessing.Impl.SymbolsRemoveUsingLists;
import Modules.PreProcessing.Interfaces.Stemmer;
import Modules.PreProcessing.Interfaces.StopwordsRemover;
import Modules.PreProcessing.Interfaces.SymbolsRemover;
import Modules.PreProcessing.Enum.PartOfStructuredAbstractEnum;
import Modules.PreProcessing.Impl.NumberHandler;
import Modules.TextMiner.TermFrequency;
import Modules.TextMiner.TermFrequencyIdf;
import Util.StringUtils;
import java.util.ArrayList;

public class Abstract {

    private String title;

    private Context context;
    private Objective objective;
    private Method method;
    private Result result;
    private Conclusion conclusion;

    private String rawAbstract;

    public Abstract(String title, Context context, Objective objectives, Method methods, Result results, Conclusion conclusions) {
        this.title = title;
        this.context = context;
        this.objective = objectives;
        this.method = methods;
        this.result = results;
        this.conclusion = conclusions;
    }

    public Abstract() {
        this.context = new Context();
        this.method = new Method();
        this.objective = new Objective();
        this.result = new Result();
        this.conclusion = new Conclusion();
    }

    public Abstract(String absTxt) {
        this.context = new Context();
        this.method = new Method();
        this.objective = new Objective();
        this.result = new Result();
        this.conclusion = new Conclusion();

        Abstract parsed = parseAbstract(absTxt);
        context = parsed.getContext();
        method = parsed.getMethods();
        objective = parsed.getObjectives();
        result = parsed.getResults();
        conclusion = parsed.getConclusions();
    }

    public Abstract parseAbstract(String absTxt) {
        Abstract toReturn = new Abstract();
        
        //Verify Tags
        isValidAbstract(absTxt);

//        read the header
        if (absTxt.contains("<header>") && absTxt.contains("</header>")) {
            if (absTxt.contains("<TypeOfResult>") && absTxt.contains("</TypeOfResult>")) {
                Result t = new Result();
                t.setTypeOfResult(absTxt.substring(
                        absTxt.indexOf("<TypeOfResult>") + 14, 
                        absTxt.indexOf("</TypeOfResult>")));
                toReturn.setResults(t);
            }
        }
        //Read the abstract
        if (absTxt.toLowerCase().contains("<title>")) {
            toReturn.setTitle(absTxt.substring(
                    absTxt.toLowerCase().indexOf("<title>") + 7, 
                    absTxt.toLowerCase().indexOf("</title>")));
        }
        if (absTxt.toLowerCase().contains("<context>")) {
            toReturn.setContext(new Context(absTxt.substring(
                    absTxt.toLowerCase().indexOf("<context>") + 9, 
                    absTxt.toLowerCase().indexOf("</context>"))));

        }
        if (absTxt.toLowerCase().contains("<objectives>")) {
            toReturn.setObjectives(new Objective(
                    absTxt.substring(absTxt.toLowerCase().indexOf("<objectives>") + 12, 
                            absTxt.toLowerCase().indexOf("</objectives>"))));
        }
        if (absTxt.toLowerCase().contains("<methods>")) {
            toReturn.setMethods(new Method(absTxt.substring(
                    absTxt.toLowerCase().indexOf("<methods>") + 9, 
                    absTxt.toLowerCase().indexOf("</methods>"))));
        }
        if (absTxt.toLowerCase().contains("<results>")) {
            String rawResults = absTxt.substring(
                    absTxt.toLowerCase().indexOf("<results>") + 9, 
                    absTxt.toLowerCase().indexOf("</results>"));
            
            rawResults = rawResults.replaceAll("<result>", "");
            rawResults = rawResults.replaceAll("<TypeOfResult>Qualitative</TypeOfResult>", "");
            rawResults = rawResults.replaceAll("<TypeOfResult>Quantitative</TypeOfResult>", "");
            toReturn.setResults(new Result(rawResults,""));
        }
        if (absTxt.toLowerCase().contains("<conclusions>")) {
            toReturn.setConclusions(new Conclusion(absTxt.substring(
                    absTxt.toLowerCase().indexOf("<conclusions>") + 13, 
                    absTxt.toLowerCase().indexOf("</conclusions>"))));
        }
        return toReturn;
    }

    public ArrayList<Abstract> getTrainingData(String absTxt) {
        ArrayList<Abstract> absToReturn = new ArrayList<>();

        isValidAbstract(absTxt);

        //Read the abstract
        if (absTxt.toLowerCase().contains("<title>")) {
            title = absTxt.substring(absTxt.toLowerCase().indexOf("<title>") + 7, absTxt.toLowerCase().indexOf("</title>"));
        }
        if (absTxt.toLowerCase().contains("<context>")) {
            context.setContext(absTxt.substring(absTxt.toLowerCase().indexOf("<context>") + 9, absTxt.toLowerCase().indexOf("</context>")));

        }
        if (absTxt.toLowerCase().contains("<objectives>")) {
            objective.setObjective(absTxt.substring(absTxt.toLowerCase().indexOf("<objectives>") + 12, absTxt.toLowerCase().indexOf("</objectives>")));
        }
        if (absTxt.toLowerCase().contains("<methods>")) {

            method.setMethod(absTxt.substring(absTxt.toLowerCase().indexOf("<methods>") + 9, absTxt.toLowerCase().indexOf("</methods>")));
        }
        if (absTxt.toLowerCase().contains("<results>")) {

            String rawAbstract = absTxt.substring(absTxt.toLowerCase().indexOf("<results>") + 9, absTxt.toLowerCase().indexOf("</results>"));
            while (rawAbstract.contains("<result>")) {
                try {
                    Abstract absToAdd = new Abstract();
                    String firstResult = rawAbstract.substring(rawAbstract.toLowerCase().indexOf("<result>") + 8, rawAbstract.toLowerCase().indexOf("</result>"));
                    String typeOfResult = firstResult.substring(firstResult.indexOf("<TypeOfResult>") + 14, firstResult.indexOf("</TypeOfResult>"));
                    firstResult = firstResult.replace("<TypeOfResult>" + typeOfResult + "</TypeOfResult>", "");
                    absToAdd.setResults(new Result(firstResult, typeOfResult));
                    absToAdd.setTitle(title);
                    absToReturn.add(absToAdd);
                    rawAbstract = rawAbstract.substring(rawAbstract.indexOf("</result>") + 9);
                } catch (Exception e) {
                    System.out.println("The error is in this abstract:" + rawAbstract);
                    e.printStackTrace();
                }

            }
        }
        if (absTxt.toLowerCase().contains("<conclusions>")) {

            conclusion.setConclusion(absTxt.substring(absTxt.toLowerCase().indexOf("<conclusions>") + 13, absTxt.toLowerCase().indexOf("</conclusions>")));
        }

        return absToReturn;
    }

    private boolean isValidAbstract(String absTxt) {
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
                return false;
            }
            return true;
        }
        return false;
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
        if (objective.getObjective() != null) {
            r.setObjectives(new Objective(syRm.removeSymbols(objective.getObjective())));
        }
        if (method.getMethod() != null) {
            r.setMethods(new Method(syRm.removeSymbols(method.getMethod())));
        }
        if (result.getResults() != null) {
            r.setResults(new Result(syRm.removeSymbols(result.getResults()), result.getTypeOfResult()));
        }
        if (conclusion.getConclusion() != null) {
            r.setConclusions(new Conclusion(syRm.removeSymbols(conclusion.getConclusion())));
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
        if (objective.getObjective() != null) {
            r.setObjectives(new Objective(stopwordsRemover.removeStopwords(objective.getObjective())));
        }
        if (method.getMethod() != null) {
            r.setMethods(new Method(stopwordsRemover.removeStopwords(method.getMethod())));
        }
        if (result.getResults() != null) {
            r.setResults(new Result(stopwordsRemover.removeStopwords(result.getResults()), result.getTypeOfResult()));
        }
        if (conclusion.getConclusion() != null) {
            r.setConclusions(new Conclusion(stopwordsRemover.removeStopwords(conclusion.getConclusion())));
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
        if (objective.getObjective() != null) {
            r.setObjectives(new Objective(stemmer.performStemming(objective.getObjective())));
        }
        if (method.getMethod() != null) {
            r.setMethods(new Method(stemmer.performStemming(method.getMethod())));
        }
        if (result.getResults() != null) {
            r.setResults(new Result(stemmer.performStemming(result.getResults()), result.getTypeOfResult()));
        }
        if (conclusion.getConclusion() != null) {
            r.setConclusions(new Conclusion(stemmer.performStemming(conclusion.getConclusion())));
        }
        return r;
    }

    public Abstract treatNumbers() {
        Abstract r = new Abstract();

        NumberHandler numHandler = new NumberHandler();

        if (title != null) {
            r.setTitle(numHandler.handleNumber(title));
        }
        if (context.getContext() != null) {
            r.setContext(new Context(numHandler.handleNumber(context.getContext())));
        }
        if (objective.getObjective() != null) {
            r.setObjectives(new Objective(numHandler.handleNumber(objective.getObjective())));
        }
        if (method.getMethod() != null) {
            r.setMethods(new Method(numHandler.handleNumber(method.getMethod())));
        }
        if (result.getResults() != null) {
            r.setResults(new Result(numHandler.handleNumber(result.getResults()), result.getTypeOfResult()));
        }
        if (conclusion.getConclusion() != null) {
            r.setConclusions(new Conclusion(numHandler.handleNumber(conclusion.getConclusion())));
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
        if (objective.getObjective() != null) {
            r.setObjectives(tf.getTermFrequency(objective.getObjective()));
        }
        if (method.getMethod() != null) {
            r.setMethods(tf.getTermFrequency(method.getMethod()));
        }
        if (result.getResults() != null) {
            r.setResults(tf.getTermFrequency(result.getResults()));
        }
        if (conclusion.getConclusion() != null) {
            r.setConclusions(tf.getTermFrequency(conclusion.getConclusion()));
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
        if (objective.getObjective() != null) {
            r.setObjectives(tfidf.getTermFrequencyIdf(StringUtils.convertAbstractsToArray(abs, PartOfStructuredAbstractEnum.objectives.getTag()), objective.getObjective()));
        }
        if (method.getMethod() != null) {
            r.setMethods(tfidf.getTermFrequencyIdf(StringUtils.convertAbstractsToArray(abs, PartOfStructuredAbstractEnum.methods.getTag()), method.getMethod()));
        }
        if (result.getResults() != null) {
            r.setResults(tfidf.getTermFrequencyIdf(StringUtils.convertAbstractsToArray(abs, PartOfStructuredAbstractEnum.results.getTag()), result.getResults()));
        }
        if (conclusion.getConclusion() != null) {
            r.setConclusions(tfidf.getTermFrequencyIdf(StringUtils.convertAbstractsToArray(abs, PartOfStructuredAbstractEnum.conclusions.getTag()), conclusion.getConclusion()));
        }

        return r;
    }

    public String toString() {
        String r = "[Title:" + title + "]";
        r += "\n[Context:" + context + "]";
        r += "\n[Objectives:" + objective + "]";
        r += "\n[Methods:" + method + "]";
        r += "\n[Results:" + result + "]";
        r += "\n[Conclusions:" + conclusion + "]";

        return r;
    }

    public String parseToStructuredAbstract() {
        String r = "<title>" + title + "</title>";
        r += "\n<context>" + context.getContext() + "</context>";
        r += "\n<objectives>" + objective.getObjective() + "</objectives>";
        r += "\n<methods>:" + method.getMethod() + "</methods>";
        r += "\n<results>" + "<result>" + result.getResults() + "<TypeOfResult>" + result.getTypeOfResult() + "</TypeOfResult>" + "</result>" + "</results>";
        r += "\n<conclusions>" + conclusion.getConclusion() + "</conclusions>";

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
        return objective;
    }

    public void setObjectives(Objective objectives) {
        this.objective = objectives;
    }

    public Method getMethods() {
        return method;
    }

    public void setMethods(Method methods) {
        this.method = methods;
    }

    public Result getResults() {
        return result;
    }

    public void setResults(Result results) {
        this.result = results;
    }

    public Conclusion getConclusions() {
        return conclusion;
    }

    public void setConclusions(Conclusion conclusions) {
        this.conclusion = conclusions;
    }

    public String getRawAbstract() {
        return rawAbstract;
    }
    
    public void setRawAbstract(String rawAbstract) {
        this.rawAbstract = rawAbstract;
    }

    
}
