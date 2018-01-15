package Tests.Generators;

import Approaches.BasicCmExtractionApproach;
import Model.Abstract;
import Modules.Input.FilesLoader;
import Util.Paths;
import java.util.ArrayList;

public class BasicCmExtractionApproachTest {
    public static void main(String[] args) {
        ArrayList<Abstract> abslist= FilesLoader.loadTrainingData(Paths.PREP_CORPUS_SWT);
        Abstract main = FilesLoader.loadSingleAbstract("C:\\Users\\USER\\Dropbox\\2016_01 Orientação Vinicius dos Santos\\05_Código\\JavaTextMiner\\test\\resources\\test\\1_teste.txt");
        System.out.println(BasicCmExtractionApproach.generateConceptMap(abslist, main));
    }
}
