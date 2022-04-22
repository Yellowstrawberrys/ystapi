package cf.ystapi.jda.Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandData {
    List<String> subCommands = new ArrayList<>();
    List<String> subCommandGroups = new ArrayList<>();
    List<String> options = new ArrayList<>();

    /**
     * Add SubCommands
     * @return CommandData
     * */
    public CommandData addSubCommands(SubCommand... subCommandz){
        for(SubCommand sub : subCommandz)
            subCommands.add(sub.getRawSubCommandJsonAsString());
        return this;
    }

    /**
     * Add SubCommandGroups
     * @return CommandData
     * */
    public CommandData addSubCommandGroups(SubCommandGroup... subCommandGroupz){
        for(SubCommandGroup sub : subCommandGroupz)
            subCommandGroups.add(sub.getAsRaw());
        return this;
    }

    /**
     * Add Options
     * @return CommandData
     * */
    public CommandData addOptions(Option... optionz){
        for(Option opt : optionz)
            options.add(opt.getAsRaw());
        return this;
    }

    /**
     * Get As Raw
     * @return HashMap
     * */
    public HashMap<String, List<String>> getAsRaw(){
        HashMap<String, List<String>> raws = new HashMap<>();
        raws.put("SubCommands", subCommands);
        raws.put("SubCommandGroups", subCommandGroups);
        raws.put("Options", options);
        return raws;
    }
}
