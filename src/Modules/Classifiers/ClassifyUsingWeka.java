package Modules.Classifiers;

import Approaches.BasicArffDataGenerationApproach;
import Model.Abstract;
import Model.Result;
import Modules.Output.Arff;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import weka.classifiers.Evaluation;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class ClassifyUsingWeka {

    public static Abstract classifyUsingJ48(ArrayList<Abstract> trainingCorpus, Abstract toClassify) {

        String instanceClassified = "";
        try {
            J48 j48tree = new J48();

            //merge all

            Arff file = BasicArffDataGenerationApproach.generateMachineLearningData(trainingCorpus, toClassify);

            Instances train = file.exportWekaInstance();
            Instances test = file.exportWekaInstance();

            //remove the instances where the "typeOfStudy" attribute is equals "?"
            for (int i = test.numInstances() - 1; i >= 0; i--) {
                if (test.instance(i).value(test.numAttributes() - 1) != -1) {
                    test.delete(i);
                }
            }

            //remove the instances where the "typeOfStudy" attribute is equals "?"
            for (int i = train.numInstances() - 1; i >= 0; i--) {
                if (train.instance(i).value(train.numAttributes() - 1) == -1) {
                    train.delete(i);
                }
            }

            //get the index of the class
            int lastIndex = train.numAttributes() - 1;
            //set the class for training
            train.setClassIndex(lastIndex);

            test.setClassIndex(lastIndex);

            // filter to remove the string attribute
            Remove rm = new Remove();
            rm.setAttributeIndices("1");
            FilteredClassifier fc = new FilteredClassifier();
            fc.setFilter(rm);

            //set the classifier
            fc.setClassifier(j48tree);
            //traininig the classifier
            fc.buildClassifier(train);

            //Avaluate model
            Instances toAvaluate = Filter.useFilter(train, rm);
            Evaluation eval = new Evaluation(toAvaluate);
            Random rand = new Random(1);  // using seed = 1
            String[] options = new String[]{"-C", "0.25", "-M", "2"};
            eval.crossValidateModel(j48tree, toAvaluate, 5, rand);
            System.out.println("\n------------------Classifier statistics------------------\n");
            System.out.println(eval.toSummaryString());
            System.out.println(eval.toClassDetailsString());
            System.out.println("\n------------------End of avaluation------------------\n");

            //use the trained classifier to classify the others
            for (int i = 0; i < test.numInstances(); i++) {

                double index = fc.classifyInstance(test.instance(i));

                instanceClassified = Arrays.asList("Quantitative", "Qualitative").get((int) index);
                //System.out.println(instanceClassified + " - Study: " + test.attribute("Title").value(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Abstract toReturn = toClassify;
        toReturn.setResults(new Result(toClassify.getResults().getResults(), instanceClassified));

        return toReturn;
    }
}
