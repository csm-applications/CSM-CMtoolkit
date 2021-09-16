package Modules.PreProcessing.Impl;

import Model.Abstract;
import Modules.PreProcessing.Enum.PreProcessingMethodsSelectionEnum;
import Modules.PreProcessing.Impl.Enum.PartOfSpeechTagSelectionEnum;
import Modules.PreProcessing.Impl.PorterStemmer;
import Modules.PreProcessing.Impl.PosTaggerStopwordRemover;
import Modules.PreProcessing.Impl.SymbolsRemoveUsingLists;
import Modules.PreProcessing.Interfaces.SymbolsRemover;
import Util.Paths;
import java.util.ArrayList;

public class PreProcessAbstract {

    public static ArrayList<Abstract> preProcessAbstracts(ArrayList<Abstract> absList) {
        //remove symbols
        SymbolsRemover syRm = new SymbolsRemoveUsingLists();

        ArrayList<String> tags = new ArrayList<>();

        tags.add(PartOfSpeechTagSelectionEnum.Adjective.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Adjective_Comparative.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Adjective_superlative.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Adverb.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Adverb_comparative.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Adverb_superlative.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Cardinal_number.getTag());
        //tags.add(PartOfSpeechTagSelectionEnum.Existential_there.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Foreign_word.getTag());
        //tags.add(PartOfSpeechTagSelectionEnum.Interjection.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Modal.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Noun_Singular_or_Mass.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Noun_plural.getTag());
        //tags.add(PartOfSpeechTagSelectionEnum.Possessive_ending.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Predeterminer.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Proper_noun_plural.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Proper_noun_singular.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Proper_noun_singular.getTag());
        //tags.add(PartOfSpeechTagSelectionEnum.Verb_3rd_person_singular_present.getTag());
        //tags.add(PartOfSpeechTagSelectionEnum.Verb_past_participle.getTag());
        //tags.add(PartOfSpeechTagSelectionEnum.Verb_past_tense.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Wh_adverb.getTag());

        //Pre-process -> Perform stopwords removal
        int i = 0;
        System.out.println("\n----------------\nStopwords removing...\n----------------\n");

        ArrayList<Abstract> stopwordsRemoved = new ArrayList<>();
        for (Abstract r : absList) {
            i++;
            try {
                PosTaggerStopwordRemover p = new PosTaggerStopwordRemover();
                p.setTags(tags);
                Abstract a = r.performStopwordsRemoval(p, PreProcessingMethodsSelectionEnum.STOPWORDS_REMOVAL_BY_POS_TAGGING.getMethod());
                stopwordsRemoved.add(a);
                System.out.println("Abstract " + i + "/" + absList.size() + " [Stopwords removed!]");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n----------------\nStopwords removal done!\n---------------\n");

        // remove symbols
        i = 0;
        System.out.println("\n----------------\n Removing Symbols... \n----------------\n");
        ArrayList<Abstract> symbolsCleaned = new ArrayList<>();
        for (Abstract r : stopwordsRemoved) {
            i++;
            try {
                SymbolsRemoveUsingLists p = new SymbolsRemoveUsingLists();
                Abstract a = r.performSymbolsRemoval(p, PreProcessingMethodsSelectionEnum.SYMBOLS_REMOVAL_USING_LIST.getMethod(), Paths.SYMBOLS);
                symbolsCleaned.add(a);
                System.out.println("Abstract " + i + "/" + absList.size() + " [symbol cleaned!]");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n----------------\nSymbols removal done!\n----------------\n");

        // remove symbols
        i = 0;
        System.out.println("\n----------------\n Performing steem... \n----------------\n");
        ArrayList<Abstract> stemmerApplied = new ArrayList<>();
        for (Abstract r : symbolsCleaned) {
            i++;
            try {
                PorterStemmer p = new PorterStemmer();
                Abstract a = r.performStemming(p, PreProcessingMethodsSelectionEnum.PORTER_STEMMER.getMethod());
                stemmerApplied.add(a);
                System.out.println("Abstract = " + i + "/" + absList.size() + " [Stemmed!]");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n----------------\nStemming done!\n----------------\n");

        System.out.println("\n----------------\n treating numbers...\n----------------\n");
        i = 0;
        ArrayList<Abstract> numbersTreated = new ArrayList<>();
        for (Abstract r : stemmerApplied) {
            i++;
            try {
                Abstract a = r.treatNumbers();
                numbersTreated.add(a);
                System.out.println("Abstract = " + i + "/" + absList.size() + " [Numbers treated!]");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n----------------\n numbers treated!\n----------------\n");
        
        return numbersTreated;
    }
    
     public static Abstract preProcessSingleAbstract(Abstract abs) {
        //remove symbols
        SymbolsRemover syRm = new SymbolsRemoveUsingLists();

        ArrayList<String> tags = new ArrayList<>();

        tags.add(PartOfSpeechTagSelectionEnum.Adjective.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Adjective_Comparative.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Adjective_superlative.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Adverb.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Adverb_comparative.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Adverb_superlative.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Cardinal_number.getTag());
        //tags.add(PartOfSpeechTagSelectionEnum.Existential_there.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Foreign_word.getTag());
        //tags.add(PartOfSpeechTagSelectionEnum.Interjection.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Modal.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Noun_Singular_or_Mass.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Noun_plural.getTag());
        //tags.add(PartOfSpeechTagSelectionEnum.Possessive_ending.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Predeterminer.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Proper_noun_plural.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Proper_noun_singular.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Proper_noun_singular.getTag());
        //tags.add(PartOfSpeechTagSelectionEnum.Verb_3rd_person_singular_present.getTag());
        //tags.add(PartOfSpeechTagSelectionEnum.Verb_past_participle.getTag());
        //tags.add(PartOfSpeechTagSelectionEnum.Verb_past_tense.getTag());
        tags.add(PartOfSpeechTagSelectionEnum.Wh_adverb.getTag());

        //Pre-process -> Perform stopwords removal
        int i = 0;
        System.out.println("\n----------------\nStopwords removing...\n----------------\n");

        Abstract stopwordsRemoved = new Abstract();
                PosTaggerStopwordRemover p = new PosTaggerStopwordRemover();
                p.setTags(tags);
                stopwordsRemoved = abs.performStopwordsRemoval(p, PreProcessingMethodsSelectionEnum.STOPWORDS_REMOVAL_BY_POS_TAGGING.getMethod());
             
                
        System.out.println("\n----------------\nStopwords removal done!\n---------------\n");

        // remove symbols
        System.out.println("\n----------------\n Removing Symbols... \n----------------\n");
       Abstract symbolsCleaned;
                SymbolsRemoveUsingLists symRUL = new SymbolsRemoveUsingLists();
                symbolsCleaned= stopwordsRemoved.performSymbolsRemoval(symRUL, PreProcessingMethodsSelectionEnum.SYMBOLS_REMOVAL_USING_LIST.getMethod(), Paths.SYMBOLS);
     
        System.out.println("\n----------------\nSymbols removal done!\n----------------\n");

        // remove symbols
         System.out.println("\n----------------\n Performing steem... \n----------------\n");
        Abstract stemmerApplied = new Abstract();
                PorterStemmer porterStemmer = new PorterStemmer();
                stemmerApplied = symbolsCleaned.performStemming(porterStemmer, PreProcessingMethodsSelectionEnum.PORTER_STEMMER.getMethod());
                
            System.out.println("\n----------------\nStemming done!\n----------------\n");

        System.out.println("\n----------------\n treating numbers...\n----------------\n");
        Abstract numbersTreated = new Abstract();
                numbersTreated = stemmerApplied.treatNumbers();
                
        System.out.println("\n----------------\n numbers treated!\n----------------\n");
        
        return numbersTreated;
    }

}
