package Tests.Functionalities;

import Model.Abstract;
import Modules.PreProcessing.Enum.PreProcessingMethodsSelectionEnum;
import Modules.PreProcessing.Impl.Enum.PartOfSpeechTagSelectionEnum;
import Modules.PreProcessing.Impl.StanfordParserLematization;
import Modules.PreProcessing.Impl.PosTaggerStopwordRemover;
import Modules.Input.FilesLoader;
import Util.Paths;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class ClearAbstractTest {
     ArrayList<Abstract> absList;

    @Before
    public void prepareTest() {
        absList = FilesLoader.loadAbstracts(Paths.ABSTRACTS);
    }

    @Test
    public void testUseStemmer() {
        
        for(Abstract r: this.absList){
            StanfordParserLematization stem = new StanfordParserLematization();
            System.out.println(r.performStemming(stem,PreProcessingMethodsSelectionEnum.PORTER_STEMMER.getMethod()));
            System.out.println("\n");
        }
    }
    
     @Test
    public void testStopwordsRemoval() {
         System.out.println("Stopwords Removal\n\n");
         
         ArrayList<String> tags = new ArrayList<>();
         
         tags.add(PartOfSpeechTagSelectionEnum.Personal_pronoun.getTag());
         tags.add(PartOfSpeechTagSelectionEnum.Noun_plural.getTag());
         
        for(Abstract r: this.absList){
            PosTaggerStopwordRemover p = new PosTaggerStopwordRemover();
            p.setTags(tags);
            System.out.println(r.performStopwordsRemoval(p,PreProcessingMethodsSelectionEnum.STOPWORDS_REMOVAL_BY_POS_TAGGING.getMethod()));
            System.out.println("\n");
        }
    }
 
}
