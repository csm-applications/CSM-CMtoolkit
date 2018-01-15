package Util;

import Model.Abstract;
import Modules.PreProcessing.Enum.PartOfStructuredAbstractEnum;
import java.util.ArrayList;

public class StringUtils {

    //AUXILIAR METHODS
    public static ArrayList<String> stringToArrayOfWords(String contentToConvert) {
        ArrayList<String> words = new ArrayList<>();
        if (contentToConvert != null) {
            for (String s : contentToConvert.split(" ")) {
                words.add(s);
            }
        }
        return words;
    }

    public static String arrayOfWordsToString(ArrayList<String> arrayToConvert) {
        String r = "";
        if (arrayToConvert != null) {
            for (String s : arrayToConvert) {
                r += s + " ";
            }
        }
        return r;
    }

    public static ArrayList<ArrayList<String>> convertAbstractsToArray(ArrayList<Abstract> arrayToConvert, String field) {

        ArrayList<ArrayList<String>> retorno = new ArrayList<>();
        for (Abstract a : arrayToConvert) {
            if (PartOfStructuredAbstractEnum.title.getTag().equals(field)) {
                retorno.add(stringToArrayOfWords(a.getTitle()));
            }
            if (PartOfStructuredAbstractEnum.context.getTag().equals(field) && a.getContext().getContext() != null) {
                retorno.add(stringToArrayOfWords(a.getContext().getContext()));
            }
            if (PartOfStructuredAbstractEnum.objectives.getTag().equals(field) && a.getObjectives().getObjective() != null) {
                retorno.add(stringToArrayOfWords(a.getObjectives().getObjective()));
            }
            if (PartOfStructuredAbstractEnum.methods.getTag().equals(field) && a.getObjectives().getObjective() != null) {
                retorno.add(stringToArrayOfWords(a.getMethods().getMethod()));
            }
            if (PartOfStructuredAbstractEnum.results.getTag().equals(field) && a.getResults().getResults() != null) {
                retorno.add(stringToArrayOfWords(a.getResults().getResults()));
            }
            if (PartOfStructuredAbstractEnum.conclusions.getTag().equals(field) && a.getConclusions().getConclusion() != null) {
                retorno.add(stringToArrayOfWords(a.getConclusions().getConclusion()));
            }
        }
        return retorno;
    }
    
    public static void printArrayListOfSentences(ArrayList<String> toPrint) {
        for(int i = 0; i < toPrint.size(); i++){
            System.out.println("Sentence " + i + " - " + toPrint.get(i));
        }
    }
   
    
}
