package Model;


    public class Result {
     
    private String results;
    private String typeOfResult;

    @Override
    public String toString() {
        return  results + ", typeOfResult = " + typeOfResult;
    }

    public Result() {
    }
    
    public Result(String results, String typeOfResult) {
        this.results = results;
        this.typeOfResult = typeOfResult;
    }
    
    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getTypeOfResult() {
        return typeOfResult;
    }

    public void setTypeOfResult(String typeOfResult) {
        this.typeOfResult = typeOfResult;
    }
    
    
}
