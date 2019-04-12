package Tests.Generators;

import Approaches.BasicArffDataGenerationApproach;
import Model.Abstract;
import Modules.Output.Arff;
import Modules.Input.FilesLoader;
import Util.Paths;
import java.util.ArrayList;
import java.util.Random;
import org.junit.Test;
import weka.classifiers.Evaluation;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class ConceptMapGeneratorTest {

    @Test
    public void testAvaluationByCommandLine() {

        J48 j48tree = new J48();

        ArrayList<Abstract> absList;

        absList = FilesLoader.loadAbstracts(Paths.ABSTRACTS);
        Arff file = BasicArffDataGenerationApproach.generateMachineLearningData(absList);

        Instances train = file.exportWekaInstance();
        int lastIndex = train.numAttributes() - 1;

        train.setClassIndex(lastIndex);

        // filter to remove the string attribute
        Remove rm = new Remove();
        rm.setAttributeIndices("1");
        FilteredClassifier fc = new FilteredClassifier();
        fc.setFilter(rm);

        //set the classifier
        fc.setClassifier(j48tree);
        //traininig the classifier

        try {

            fc.buildClassifier(train);
            Instances toAvaluate = Filter.useFilter(train, rm);
            //System.out.println(toAvaluate);
            Evaluation eval = new Evaluation(toAvaluate);
            Random rand = new Random(1);  // using seed = 1
            String[] options = new String[]{"-C", "0.25", "-M", "2"};
            j48tree.setOptions(options);
            eval.crossValidateModel(j48tree, toAvaluate,10 , rand);
            System.out.println("\n------------------Classifier statistics------------------\n");;
            System.out.println(eval.toSummaryString("\nResult\n",true));
            System.out.println(eval.toSummaryString());
            System.out.println(eval.toClassDetailsString());
            System.out.println("\n------------------End of avaluation------------------\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
