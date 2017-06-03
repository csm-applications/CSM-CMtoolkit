package Modules.PreProcessing.Enum;

public enum PreProcessingMethodsSelectionEnum {

    PORTER_STEMMER(1),
    STANFORD_LEMMA_STEMMER(2),
    STOPWORDS_REMOVAL_BY_POS_TAGGING(3),
    SYMBOLS_REMOVAL_USING_LIST(4);

    private int method;

    PreProcessingMethodsSelectionEnum(int method) {
        this.method = method;
    }

    public int getMethod() {
        return method;
    }

}
