package Modules.PreProcessing.Impl;

import Exceptions.InvalidPathException;
import Modules.PreProcessing.Interfaces.SymbolsRemover;
import Util.LoadFiles;
import Util.Paths;
import Util.StringUtils;
import java.util.ArrayList;

public class SymbolsRemoveUsingLists implements SymbolsRemover{
    
    private String pathToSymbolsList;

    @Override
    public String removeSymbols(String words) {
        if(pathToSymbolsList == null || pathToSymbolsList.isEmpty()){
            throw new InvalidPathException();
        }
        ArrayList<String> symbols = LoadFiles.loadSingleFile(pathToSymbolsList);
        ArrayList<String> retorno;
        
        symbols.toString();
        
        
        for(String sy: symbols){
                if(words.contains(sy)) 
                      words = words.replace(sy, "");
        }

        retorno = StringUtils.stringToArrayOfWords(words);
        ArrayList<String> r = new ArrayList<>();
        
        
        for(String a: retorno){
            if(a.length() > 1){
                r.add(a);
            }
        }
        
        return StringUtils.arrayOfWordsToString(r);
    }

    public String getPathToSymbolsList() {
        return pathToSymbolsList;
    }

    public void setPathToSymbolsList(String pathToSymbolsList) {
        this.pathToSymbolsList = pathToSymbolsList;
    }
    
    
}
