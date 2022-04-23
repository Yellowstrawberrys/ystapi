package cf.ystapi.jda.Objects;

public class Choice {
    String name;
    String value;

    public Choice(){}

    public Choice(String name, String value){
        this.name = name;
        this.value = value;
    }

    /**
     * Get Name
     *
     * @return String
     * */
    public String getName() {
        return name;
    }

    /**
     * Set Name
     *
     * @return Choice
     * */
    public Choice setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get Value
     *
     * @return String
     * */
    public String getValue() {
        return value;
    }

    /**
     * Set Value
     *
     * @return Choice
     * */
    public Choice setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Get As Raw
     *
     * @return String
     * */
    public String getAsRaw(){
        return ("{" +
                "\"name\": \"%s\"," +
                "\"value\": \"%s\"" +
                "}").formatted(name, value);
    }
}
