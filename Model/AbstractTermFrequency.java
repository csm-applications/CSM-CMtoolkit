package Model;

import java.util.ArrayList;

public class AbstractTermFrequency {

    private ArrayList<Word> title;

    private ArrayList<Word> context;
    private ArrayList<Word> objectives;
    private ArrayList<Word> methods;
    private ArrayList<Word> results;
    private ArrayList<Word> conclusions;

    @Override
    public String toString() {
        return "Terms Frequency \n" + "\n\ntitle =" + title + ",\ncontext=" + context + ",\nobjectives="
                + objectives + ",\nmethods=" + methods + ",\nresults=" + results + ",\nconclusions=" + conclusions + '}';
    }

    public Word contains(Word toSearch) {
        if (title != null) {
            for (Word e : title) {
                if (e.equals(toSearch)) {
                    return e;
                }
            }
        }
        if (context != null) {
            for (Word e : context) {
                if (e.equals(toSearch)) {
                    return e;
                }
            }
        }
        if (methods != null) {
            for (Word e : methods) {
                if (e.equals(toSearch)) {
                    return e;
                }
            }
        }
        if (objectives != null) {
            for (Word e : objectives) {
                if (e.equals(toSearch)) {
                    return e;
                }
            }
        }
        if (results != null) {
            for (Word e : results) {
                if (e.equals(toSearch)) {
                    return e;
                }
            }
        }
        if (conclusions != null) {
            for (Word e : conclusions) {
                if (e.equals(toSearch)) {
                    return e;
                }
            }
        }
        return null;
    }

    public AbstractTermFrequency() {
    }

    public ArrayList<Word> getTitle() {
        return title;
    }

    public void setTitle(ArrayList<Word> title) {
        this.title = title;
    }

    public ArrayList<Word> getContext() {
        return context;
    }

    public void setContext(ArrayList<Word> context) {
        this.context = context;
    }

    public ArrayList<Word> getObjectives() {
        return objectives;
    }

    public void setObjectives(ArrayList<Word> objectives) {
        this.objectives = objectives;
    }

    public ArrayList<Word> getMethods() {
        return methods;
    }

    public void setMethods(ArrayList<Word> methods) {
        this.methods = methods;
    }

    public ArrayList<Word> getResults() {
        return results;
    }

    public void setResults(ArrayList<Word> results) {
        this.results = results;
    }

    public ArrayList<Word> getConclusions() {
        return conclusions;
    }

    public void setConclusions(ArrayList<Word> conclusions) {
        this.conclusions = conclusions;
    }

}
