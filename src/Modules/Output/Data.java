package Modules.Output;

import Model.Word;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class Data {

    private ArrayList<String> items;

    public Data() {
        items = new ArrayList<>();
    }
    
    public void addString(String toAdd){
        this.items.add(toAdd);
    }

    public void addDouble(Word toAdd) {
        DecimalFormat df = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.US));
        this.items.add(String.valueOf(df.format(toAdd.getFrequency())));
    }
    
    public void addInteger(Integer toAdd){
        this.items.add(String.valueOf(toAdd));
    }
    
    public void addAll(ArrayList<String> toAdd){
        this.items.addAll(toAdd);
    }


    public void addZero() {
        this.items.add("0");
    }

    public Data(ArrayList<String> items) {
        this.items = items;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

}
