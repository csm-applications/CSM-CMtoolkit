package Tests.Functionalities;

import Modules.Tools.SentenceSplitter;
import java.util.ArrayList;
import org.junit.Test;

public class SentenceSplitterTest {
    
    @Test
    public void sentenceSplittingTest(){
        ArrayList<String> result = SentenceSplitter.getSentences("hello, its me. I am a Dog. I have a cat and its name is Oswaldo");
        
        for(int i = 0 ; i < result.size(); i++){
            System.out.println(i + " frase:" + result.get(i));
        }
    }
}
