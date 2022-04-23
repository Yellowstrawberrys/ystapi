package cf.ystapi.jda.Objects;

import cf.ystapi.jda.Enums.OptionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Option {
    String name;
    String description = "N/A";
    OptionType optionType = OptionType.STRING;
    boolean required = false;
    List<Choice> choices = new ArrayList<>();

    public Option(String name){
        this.name = name;
    }

    public Option(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Option(String name, String description, OptionType optionType){
        this.name = name;
        this.description = description;
        this.optionType = optionType;
    }

    /**
     * Get Name
     * @return String
     * */
    public String getName() {
        return name;
    }

    /**
     * Set Name
     * @return Option
     * */
    public Option setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get Description
     * @return String
     * */
    public String getDescription() {
        return description;
    }

    /**
     * Set Description
     * @return Option
     * */
    public Option setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get isRequired
     * @return boolean
     * */
    public boolean isRequired() {
        return required;
    }

    /**
     * Set isRequired
     *
     * <strong>Default Value is false</strong>
     * @return Option
     * */
    public Option setRequired(boolean required) {
        this.required = required;
        return this;
    }

    /**
     * Get Choices
     * @return List
     * */
    public List<Choice> getChoices() {
        return choices;
    }

    /**
     * Set Choices
     * @return Option
     * */
    public Option setChoices(List<Choice> choices) {
        this.choices = choices;
        return this;
    }

    /**
     * Get OptionType
     * @return OptionType
     * */
    public OptionType getOptionType() {
        return optionType;
    }

    /**
     * Set OptionType
     * @return Option
     * */
    public Option setOptionType(OptionType optionType) {
        this.optionType = optionType;
        return this;
    }

    /**
     * Add Choices
     * @return Option
     * */
    public Option addChoices(Choice... choicez){
        choices.addAll(Arrays.asList(choicez));
        return this;
    }

    /**
     * Remove Choices
     * @return Option
     * */
    public Option removeChoices(Choice... choicez){
        choices.removeAll(Arrays.stream(choicez).toList());
        return this;
    }

    /**
     * Get as raw
     * @return String
     * */
    public String getAsRaw(){
        String choicez = "";
        if(choices != null && !choices.isEmpty()) {
            for (Choice choice : choices)
                choicez += choice.getAsRaw() + ",";
            choicez = choicez.substring(0, choicez.length() - 1);
        }
        return ("{" +
                "\"name\": \"%s\"," +
                "\"description\": \"%s\"," +
                "\"type\": %s," +
                "\"required\": %s" +
                (choicez.isEmpty() ? "" : ",\"choices\": [%s]".formatted(choicez)) +
                "}").formatted(name, description, optionType.getId(), required);
    }
}
