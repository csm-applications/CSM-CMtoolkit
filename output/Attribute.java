package Modules.output;

import java.util.ArrayList;

public class Attribute {

    private String nameOfAttribute;
    private ArrayList<String> possibleValues;
    private String primitiveValues;

    public Attribute() {
        nameOfAttribute = "";
        possibleValues = new ArrayList<>();
        primitiveValues = null;
    }

    public Attribute(String nameOfAttribute, ArrayList<String> possibleValues, String primitiveValues) {
        this.nameOfAttribute = nameOfAttribute;
        this.possibleValues = possibleValues;
        this.primitiveValues = primitiveValues;
    }

    public String getNameOfAttribute() {
        return nameOfAttribute;
    }

    public void setNameOfAttribute(String nameOfAttribute) {
        this.nameOfAttribute = nameOfAttribute;
    }

    public ArrayList<String> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(ArrayList<String> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public String getPrimitiveValues() {
        return primitiveValues;
    }

    public void setPrimitiveValues(String primitiveValues) {
        this.primitiveValues = primitiveValues;
    }

}
