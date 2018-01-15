package Tests.Functionalities;

import Model.Text;
import Modules.TextMiner.PosTagger;
import org.junit.Test;

public class PosTaggingTest {

    @Test
    public void PosTaggingTest() {
        PosTagger p = new PosTagger();

        Text text = p.parseText("I am a bread. John is a dog. Katia is a teacher");

        for (int i = 0; i < text.getPhrases().size(); i++) {
            System.out.println("Phrase " + i);
            System.out.println(text.getPhrases().get(i).getOriginalPhrase());
        }

    }
}
