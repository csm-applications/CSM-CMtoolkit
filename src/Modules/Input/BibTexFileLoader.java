package Modules.Input;

import Model.Abstract;
import Util.BibtexParser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

public class BibTexFileLoader {

    public static List<Abstract> loadAbstracts(String pathToFiles) {
        BibTeXDatabase database = BibtexParser.getDatabase(pathToFiles);
        Map<Key, BibTeXEntry> entries = database.getEntries();
        Iterator<Map.Entry<Key, BibTeXEntry>> itr = entries.entrySet().iterator();

        List<Abstract> toReturn = new ArrayList<>();
        while (itr.hasNext()) {
            Map.Entry<Key, BibTeXEntry> entry = itr.next();
            Abstract c = new Abstract();
            c.setTitle(entry.getValue().getField(new Key("title")).toUserString());
            c.setRawAbstract(entry.getValue().getField(new Key("abstract")).toUserString());
            toReturn.add(c);
        }
        return toReturn;
    }
}
