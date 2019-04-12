
package Tests.Functionalities;

import Modules.PreProcessing.Impl.StanfordParserLematization;
import org.junit.Test;

public class StanfordLemmaStemmingTest {
    @Test
    public void testStemm(){
        String txt = "Although its use is required in the U.S. Navy, 1679 is approved for use by all other agencies within the DoD. MIL-STD-1679, which is becoming more and more an integral ";
        
        StanfordParserLematization control = new StanfordParserLematization();
        
        System.out.println(control.performStemming(txt));
    }
}
