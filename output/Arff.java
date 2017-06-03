package Modules.output;

import Model.Abstract;
import Model.AbstractTermFrequency;
import Model.Word;
import Modules.PreProcessing.Enum.PrimitiveWekaDataTypesEnum;
import Modules.TextMiner.BagOfWords;
import Modules.TextMiner.TermFrequency;
import Modules.TextMiner.TermFrequencyDocument;
import Modules.TextMiner.TermFrequencyIdf;
import Util.StringUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Arff {

    String path;
    String relation;
    ArrayList<Attribute> attributes = new ArrayList<>();
    ArrayList<Data> data;

    public Arff() {
        path = "";
        relation = "";
        attributes = new ArrayList<>();
        data = new ArrayList<>();
    }

    public Arff(String path, String relation, ArrayList<Abstract> toExport) {
        this.path = path;
        this.relation = relation;
        //create a new bag
        BagOfWords bag = new BagOfWords();

        //Convert to term frequency object
        ArrayList<AbstractTermFrequency> tfabs = new ArrayList<>();
        for (Abstract a : toExport) {
            tfabs.add(a.applyTermFrequencyIdf(toExport));
        }

        //Populate the bag of words        
        for (AbstractTermFrequency tf : tfabs) {
//            if (tf.getTitle() != null) {
//                for (Word a : tf.getTitle()) {
//                    bag.addWordToBag(a.getWord());
//                }
//
//            }
//            if (tf.getContext() != null) {
//                for (Word a : tf.getContext()) {
//                    bag.addWordToBag(a.getWord());
//                }
//            }
//            if (tf.getMethods() != null) {
//                for (Word a : tf.getMethods()) {
//                    bag.addWordToBag(a.getWord());
//                }
//            }
//            if (tf.getObjectives() != null) {
//                for (Word a : tf.getObjectives()) {
//                    bag.addWordToBag(a.getWord());
//                }
//            }
            if (tf.getResults() != null) {

                for (Word a : tf.getResults()) {
                    bag.addWordToBag(a.getWord());
                }
            }
//            if (tf.getConclusions() != null) {
//                for (Word a : tf.getConclusions()) {
//                    bag.addWordToBag(a.getWord());
//                }
//            }
        }

        this.attributes.add(new Attribute("Title", null, PrimitiveWekaDataTypesEnum.string.getType()));
        this.attributes.addAll(bag.convertToAttributesList());
        this.attributes.add(new Attribute("TypeOfResults", new ArrayList<>(Arrays.asList("Quantitative", "Qualitative")), null));

        data = new ArrayList<>();

        for (int i = 0; i < toExport.size(); i++) {
            Data d = new Data();
            for (Attribute a : attributes) {
                Word result = tfabs.get(i).contains(new Word(a.getNameOfAttribute(), 0));
                if (result != null) {
                    d.addDouble(result);
                } else if (a.getNameOfAttribute().equals("TypeOfResults")) {
                    if (toExport.get(i).getResults().getTypeOfResult() == null) {
                        System.out.println(toExport.get(i));
                    }
                    d.addString(toExport.get(i).getResults().getTypeOfResult());
                } else if (a.getNameOfAttribute().equals("Title")) {
                    d.addString("'" + toExport.get(i).getTitle() + "'");
                } else {
                    d.addZero();
                }
            }
            this.data.add(d);
        }
        System.out.println("Arff data generated!");
    }

    public Arff(String path, ArrayList<String> toExport) {
        this.path = path;
        this.relation = "words";
        //create a new bag
        BagOfWords bag = new BagOfWords();

        //Convert to term frequency object
        ArrayList<TermFrequencyDocument> tfabs = new ArrayList<>();
        TermFrequencyIdf tf = new TermFrequencyIdf();
        
        ArrayList<ArrayList<String>> toProcess = new ArrayList<>();
        for(String s: toExport){
            toProcess.add(StringUtils.stringToArrayOfWords(s));
        }
        for (String a : toExport) {
            tfabs.add(new TermFrequencyDocument(new HashSet<>(tf.getTermFrequencyIdf(toProcess, a))));
        }

        for (TermFrequencyDocument f : tfabs) {
            for (Word a : f.getWords()) {
                bag.addWordToBag(a.getWord());
            }
        }

         this.attributes.addAll(bag.convertToAttributesList());
        
        data = new ArrayList<>();

        for (int i = 0; i < toExport.size(); i++) {
            Data d = new Data();
            for (Attribute a : attributes) {
                Word result = tfabs.get(i).contains(new Word(a.getNameOfAttribute(), 0));
                if (result != null) {
                    d.addDouble(result);
                } else {
                    d.addZero();
                }
            }
            this.data.add(d);
        }
        System.out.println("Arff data generated!");
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public void exportArfFile() {
        Path file = Paths.get(path + ".arff");
        ArrayList<String> toWrite = new ArrayList<>();

        toWrite.add("@relation " + relation + "\n\n ");

        if (attributes != null && !attributes.isEmpty()) {
            for (Attribute a : attributes) {
                if (a.getPrimitiveValues() != null && a.getPrimitiveValues().equals(PrimitiveWekaDataTypesEnum.string.getType())) {
                    toWrite.add("@attribute " + a.getNameOfAttribute() + " " + a.getPrimitiveValues());
                } else if (a.getPrimitiveValues() != null && a.getPrimitiveValues().equals(PrimitiveWekaDataTypesEnum.numeric.getType())) {
                    toWrite.add("@attribute " + a.getNameOfAttribute() + " " + a.getPrimitiveValues());
                } else if ((a.getPossibleValues() == null || a.getPossibleValues().isEmpty()) && a.getPrimitiveValues() == null) {
                    toWrite.add("@attribute " + a.getNameOfAttribute());
                } else {
                    String add = "@attribute " + a.getNameOfAttribute() + "{";
                    for (int i = 0; i < a.getPossibleValues().size(); i++) {
                        if (a.getPossibleValues().size() - 1 == i) {
                            add += a.getPossibleValues().get(i);
                        } else {
                            add += a.getPossibleValues().get(i) + ",";
                        }

                    }
                    add += "}";
                    toWrite.add(add);
                }
            }
        }

        toWrite.add("\n\n@data");

        if (data != null && !data.isEmpty()) {
            for (Data d : data) {
                String add = "";
                for (int i = 0; i < d.getItems().size(); i++) {
                    if (d.getItems().size() - 1 == i) {
                        add += d.getItems().get(i);
                    } else {
                        add += d.getItems().get(i) + ",";
                    }
                }
                toWrite.add(add);
            }

        }
        try {
            Files.write(file, toWrite, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            Logger.getLogger(Arff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
