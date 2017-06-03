package Modules.Classifiers;

import java.io.BufferedReader;
import java.io.FileReader;
import weka.classifiers.Classifier;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.Remove;

public class ClassifyUsingWeka {

    public static void classifyUsingJ48() {
        try {

            J48 j48tree = new J48();
            j48tree.setUnpruned(true);
            Instances train = new Instances(
                    new BufferedReader(
                            new FileReader("C:\\Users\\USER\\Dropbox\\2016_01 Orientação Vinicius dos Santos\\05_Código\\CMGen\\test\\resources\\train.arff")));
           
            int lastIndexTrain = train.numAttributes() - 1;

            train.setClassIndex(lastIndexTrain);

            Instances test = new Instances(new BufferedReader(
                    new FileReader("C:\\Users\\USER\\Dropbox\\2016_01 Orientação Vinicius dos Santos\\05_Código\\CMGen\\test\\resources\\test.arff")));
            Instances labels = test;
              
            int lastIndexTest = test.numAttributes() - 1;
            test.setClassIndex(lastIndexTest);

            // filter
            Remove rm = new Remove();
            rm.setAttributeIndices("1");  // remove 1st attribute
            // classifier
            FilteredClassifier fc = new FilteredClassifier();
            fc.setFilter(rm);
            
            fc.setClassifier(j48tree);

            fc.buildClassifier(train);

            for (int i = 0; i < test.numInstances(); i++) {
              
                double index = fc.classifyInstance(test.instance(i));
                String className = test.attribute(lastIndexTest).value((int) index);
                System.out.println(className + " - Study: " + test.attribute("Title").value(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
