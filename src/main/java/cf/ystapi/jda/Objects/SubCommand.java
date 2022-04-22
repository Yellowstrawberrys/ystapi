package cf.ystapi.jda.Objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubCommand {
    String name;
    String description;
    List<Option> opts = new ArrayList<>();

    /**
     *
     * @param name
     */
    public SubCommand(String name){
        this.name = name;
        this.description = "N/A";
    }

    /**
     *
     * @param name
     * @param description
     */
    public SubCommand(String name, String description){
        this.name = name;
        this.description = description;
    }

    /**
     * Add Options
     * @return SubCommand
     * */
    public SubCommand addOptions(Option... optionz){
        opts.addAll(Arrays.stream(optionz).toList());
        return this;
    }

    /**
     * Get AS Raw
     * @return String
     * */
    public String getRawSubCommandJsonAsString(){
        String options = "";
        if(opts != null && !opts.isEmpty()) {
            for (Option opt : opts)
                options += opt.getAsRaw() + ",";
            options = options.substring(0, options.length() - 1);
        }
        return ("{" +
                "\"name\": \"%s\"," +
                "\"type\": 1," +
                "\"description\": \"%s\"" +
                (options.isEmpty() ? "" : ",\"options\": [%s]".formatted(options)) +
                "}").formatted(name, description);
    }
}
