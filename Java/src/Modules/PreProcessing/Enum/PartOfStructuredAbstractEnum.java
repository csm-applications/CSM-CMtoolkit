package Modules.PreProcessing.Enum;

import Modules.PreProcessing.Enum.*;


public enum PartOfStructuredAbstractEnum {

    title("title"),
    context("context"),
    objectives("objectives"),
    methods("methods"),
    results("results"),
    conclusions("conclusions");

    private String tag;

    PartOfStructuredAbstractEnum(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
