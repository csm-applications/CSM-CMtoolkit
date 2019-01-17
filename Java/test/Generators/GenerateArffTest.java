package Tests.Generators;

import Approaches.BasicArffDataGenerationApproach;
import Model.Abstract;
import Modules.Output.Arff;
import Modules.Input.FilesLoader;
import Util.Paths;
import java.util.ArrayList;

public class GenerateArffTest {

    public static void main(String[] args) {

        ArrayList<Abstract> absList = FilesLoader.loadTrainingData(Paths.CORPUS_SWT);

        BasicArffDataGenerationApproach p = new BasicArffDataGenerationApproach();
        Arff a = p.generateMachineLearningData(absList);
        a.exportArfFile();
    }
}
