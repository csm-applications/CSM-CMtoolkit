package Tests.Functionalities;

import Model.Abstract;
import Model.Result;
import Modules.Classifiers.ClassifyUsingWeka;
import Modules.Input.FilesLoader;
import Util.Paths;
import java.util.ArrayList;
import org.junit.Test;

public class ClassifyUsingWekaTest {

    @Test
    public void testJ48() {

        ArrayList<Abstract> absList;

        absList = FilesLoader.loadTrainingData(Paths.CORPUS_SWT);

        Abstract toClassify= FilesLoader.loadSingleAbstract("C:\\Users\\USER\\Dropbox\\2016_01 Orientação Vinicius dos Santos\\05_Código\\JavaTextMiner\\test\\resources\\test\\1_teste.txt");
        
        toClassify.setTitle("The Use of Visual Text Mining to Support the Study Selection Activity in Systematic Literature Reviews: A Replication Study");
        toClassify.setResults(new Result("The results of the replication confirmed that studies are more rapidly selected using VTM. We observed that the level of experience in researching has a direct relationship with the effectiveness.","?"));
        Abstract returned = ClassifyUsingWeka.classifyUsingJ48(absList,toClassify);
        System.out.println(returned);
    }
}
