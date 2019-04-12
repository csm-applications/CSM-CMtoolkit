package Approaches;

import Modules.PreProcessing.Impl.PreProcessAbstract;
import Model.Abstract;
import Modules.Output.Arff;
import java.util.ArrayList;
import Modules.Input.FilesLoader;
import Util.Paths;

public class BasicArffDataGenerationApproach {

    public static Arff generateMachineLearningData(ArrayList<Abstract> absList, Abstract toClassify) {

        Abstract toAdd = PreProcessAbstract.preProcessSingleAbstract(toClassify);
        ArrayList<Abstract> abs = FilesLoader.loadTrainingData(Paths.PREP_CORPUS_SWT);
        abs.add(toAdd);
        Arff a = new Arff("Dados", "Treinamento", abs);
        return a;
    }
    
    public static Arff generateMachineLearningData(ArrayList<Abstract> absList) {
        ArrayList<Abstract> abs = PreProcessAbstract.preProcessAbstracts(absList);
        Arff a = new Arff("Dados", "Treinamento", abs);
        return a;
    }

}
