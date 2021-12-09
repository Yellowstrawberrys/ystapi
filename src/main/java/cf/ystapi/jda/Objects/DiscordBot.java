package cf.ystapi.jda.Objects;

import cf.ystapi.jda.DiscordRunnable;
import cf.ystapi.jda.Handlers.ButtonHandler;
import cf.ystapi.jda.Handlers.CommandHandler;
import cf.ystapi.jda.YSTBuilder;
import net.dv8tion.jda.api.JDA;

import java.util.HashMap;

public class DiscordBot {
    public JDA jda;

    public HashMap<String, CommandHandler> commands;
    public HashMap<String, String> Aliases;
    public HashMap<String, DiscordRunnable> RunnableCommands;
    public HashMap<String, ButtonHandler> Buttons;

    public String Owner;
    String prefix;

    public DiscordBot(JDA jda, HashMap<String, CommandHandler> commands, HashMap<String, DiscordRunnable> RunnableCommands, HashMap<String, ButtonHandler> Buttons, String prefix, String OwnerID){
        this.jda = jda;
        this.commands = commands;
        this.RunnableCommands = RunnableCommands;
        this.Aliases = new HashMap<>();
        this.prefix = prefix;
        this.Owner = OwnerID;
        this.Buttons = Buttons;
        new Thread("Init Aliases Thread"){
            @Override
            public void run() {
                for(Object command : commands.keySet().toArray()){
                    if(commands.containsKey(command.toString())){
                        String[] aliases = commands.get(command.toString()).Aliases();
                        for(String a : aliases){
                            if(a != null && !a.isBlank() && !a.isEmpty()){
                                Aliases.put(a, command.toString());
                            }
                        }
                    }
                }
            }
        }.start();
    }

    public DiscordBot addButton(ButtonHandler buttonHandler) {
        Buttons.put(buttonHandler.id(), buttonHandler);
        return this;
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
