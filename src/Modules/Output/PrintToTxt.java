package Modules.Output;

import Model.Abstract;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintToTxt {

    public static void exportToTxtFile(String path, ArrayList<Abstract> toWrite) {
        ArrayList<String> write = new ArrayList<>();
        for (Abstract a : toWrite) {
            write.add("\n\n" + a.toString());
        }
        Path file = Paths.get(path + ".txt");
        try {
            Files.write(file, write, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            Logger.getLogger(PrintToTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void exportIndividualTxtFiles(String path, ArrayList<Abstract> toWrite) {
        try {
            for (int i = 0; i < toWrite.size(); i++) {
                Path dst = Paths.get("EngSw" + i + ".txt");
                BufferedWriter writer = Files.newBufferedWriter(dst, StandardCharsets.UTF_8);
                writer.write(toWrite.get(i).parseToStructuredAbstract());
                writer.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(PrintToTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
