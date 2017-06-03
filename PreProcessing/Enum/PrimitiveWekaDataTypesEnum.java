package Modules.PreProcessing.Enum;


public enum PrimitiveWekaDataTypesEnum {

    numeric("numeric"),
    string("string");

    private String type;

    PrimitiveWekaDataTypesEnum(String tag) {
        this.type = tag;
    }

    public String getType() {
        return type;
    }
}
