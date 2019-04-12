package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXParser;

public class BibtexParser {

    public static BibTeXDatabase getDatabase(String pathToFiles) {
        try {
            BibTeXParser parser = new BibTeXParser();
            BibTeXDatabase database = parse(parser, pathToFiles);
            return database;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    static private BibTeXDatabase parse(BibTeXParser parser, String path) throws Exception {
        File initialFile = new File(path);
        InputStream is = new FileInputStream(initialFile);
        try {
            Reader reader = new InputStreamReader(is, "US-ASCII");

            try {
                return parser.parse(reader);
            } finally {
                reader.close();
            }
        } finally {
            is.close();
        }
    }

}
