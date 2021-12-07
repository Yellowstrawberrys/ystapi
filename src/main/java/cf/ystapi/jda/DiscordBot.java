package cf.ystapi.jda;

import net.dv8tion.jda.api.JDA;

import java.util.HashMap;

public class DiscordBot {
    public JDA jda;

    public HashMap<String, CommandHandler> commands;
    public HashMap<String, DiscordRunnable> RunnableCommands;

    public String Owner;
    String prefix;

    public DiscordBot(JDA jda, HashMap<String, CommandHandler> commands, HashMap<String, DiscordRunnable> RunnableCommands, String prefix, String OwnerID){
        this.jda = jda;
        this.commands = commands;
        this.RunnableCommands = RunnableCommands;
        this.prefix = prefix;
        this.Owner = OwnerID;
    }

    public DiscordBot addCommand(CommandHandler commandHandler){
        commands.put(commandHandler.name(), commandHandler);
        return this;
    }

    public DiscordBot addCommand(String name, DiscordRunnable onCall){
        RunnableCommands.put(name, onCall);
        return this;
    }

    public DiscordBot setPrefix(String prefix){
        this.prefix = prefix;
        return this;
    }
}
