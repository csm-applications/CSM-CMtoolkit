package Tests.Functionalities;

import Model.Abstract;
import Modules.Input.FilesLoader;
import Util.Paths;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class TermFrequencyTest {
    
    
    ArrayList<Abstract> absList;

    @Before
    public void prepareTest() {
        absList = FilesLoader.loadAbstracts(Paths.ABSTRACTS);
    }

    @Test
    public void extractTermFrequency(){
        for(Abstract a: absList){
            System.out.println(a.applyTermFrequency());
            System.out.println("----------------------------\n");
        }
    }
}
