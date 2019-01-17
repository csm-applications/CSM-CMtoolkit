package Modules.PreProcessing.Interfaces;

import java.util.ArrayList;

public interface PosTagger {
    public ArrayList<String> performPosTagging(ArrayList<String> toTag);
}
