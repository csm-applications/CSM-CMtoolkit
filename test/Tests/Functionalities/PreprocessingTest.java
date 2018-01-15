package Tests.Functionalities;


import Model.Abstract;
import Modules.PreProcessing.Impl.SymbolsRemoveUsingLists;
import Modules.Input.FilesLoader;
import Util.Paths;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class PreprocessingTest {

    private ArrayList<String> lista = new ArrayList<>();

    public PreprocessingTest() {
    }

        ArrayList<Abstract> absList;

    @Before
    public void prepareTest() {
        absList = FilesLoader.loadAbstracts(Paths.ABSTRACTS);
    }
    
    @Test
    public void testarRemoveSymbols() {
        System.out.println("\n\n ----> Test symbols removal");
        System.out.println("Removing Symbols...");
        System.out.println(new SymbolsRemoveUsingLists().removeSymbols(absList.get(0).getResults().getResults()));
    }

    @Test
    public void testarRemoveStopwords() {
//        System.out.println("\n\n ----> Test stopwords Removal");
//        ArrayList<String> l2 = new ArrayList<>();
//        l2.addAll(lista);
//        System.out.println("Removing symbols...");
//        l2 = new SymbolsRemoveUsingLists().removeSymbols(StringUtils.arrayOfWordsToString(l2));
//        System.out.println("Removing STOPWORDS...");
//        System.out.println(new ListOfWordsStopwordsRemover().removeStopwords(StringUtils.arrayOfWordsToString(l2)));
    }
    
    @Test
    public void makeSteemingPorter() {
//        System.out.println("\n\n ----> Test Stemming Porter");
//        ArrayList<String> l3 = new ArrayList<>();
//        l3.addAll(lista);
//       
//        System.out.println("\nRemoving symbols...");
//        l3 = new SymbolsRemoveUsingLists().removeSymbols(l3);
//        System.out.println("symbols removed!");
//        
//        System.out.println("\nRemoving STOPWORDS...");
//        l3 = new ListOfWordsStopwordsRemover().removeStopwords(StringUtils.arrayOfWordsToString(l3));
//        System.out.println("STOPWORDS Removed!");
//        
//        System.out.println("\nFinal Result:");
//        
//        System.out.println(new PorterStemmer().performStemming(l3));
    }
    
    
}
