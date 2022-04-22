package cf.ystapi.jda.Objects;

import java.util.ArrayList;
import java.util.List;

public class SubCommandGroup {

    String name;
    String description;

    List<SubCommand> subCommands = new ArrayList<>();

    /**
     * SubCommandGroup
     */
    public SubCommandGroup(){}

    /**
     * SubCommandGroup
     * @param name
     */
    public SubCommandGroup(String name){
        this.name = name;
    }

    /**
     * SubCommandGroup
     * @param name
     * @param description
     */
    public SubCommandGroup(String name, String description){
        this.name = name;
        this.description = description;
    }

    /**
     * getName
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * setName
     * @param name
     * @return SubCommandGroup
     */
    public SubCommandGroup setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SubCommandGroup setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    public SubCommandGroup setSubCommands(List<SubCommand> subCommands) {
        this.subCommands = subCommands;
        return this;
    }

    public SubCommandGroup addSubCommands(SubCommand... subCommandz){
        this.subCommands.addAll(subCommands.stream().toList());
        return this;
    }

    public String getAsRaw(){
        String subCommandz = "";
        if(subCommands != null && !subCommands.isEmpty()) {
            for (SubCommand sub : subCommands)
                subCommandz += sub.getRawSubCommandJsonAsString() + ",";
            subCommandz = subCommandz.substring(0, subCommandz.length() - 1);
        }
        return ("{" +
                "\"name\": \"%s\"," +
                "\"description\": \"%s\"," +
                "\"type\": 2" +
                (subCommandz.isEmpty() ? "" : ",\"options\": [%s]".formatted(subCommandz)) +
                "}").formatted(name, description, subCommandz);
    }
}
