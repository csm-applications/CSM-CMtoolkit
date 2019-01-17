package Tests.Functionalities;

import Util.BibtexParser;
import java.util.Iterator;
import java.util.Map;
import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

public class BibtexParserTest {
    
    public static void main(String[] args) {
        BibTeXDatabase database = BibtexParser.getDatabase("test/Resources/BibTexFiles/bibtex.bib");
        Map<Key, BibTeXEntry> entries = database.getEntries();
        Iterator<Map.Entry<Key, BibTeXEntry>> itr = entries.entrySet().iterator();

        while (itr.hasNext()) {
            Map.Entry<Key, BibTeXEntry> entry = itr.next();
            System.out.println("Key = " + entry.getKey()
                    + ", Value = " + entry.getValue().getField(new Key("abstract")).toUserString());
        }
    }

}
