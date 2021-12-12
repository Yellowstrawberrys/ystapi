package cf.ystapi.jda.Objects;

import cf.ystapi.jda.DiscordRunnable;
import cf.ystapi.jda.Handlers.ButtonHandler;
import cf.ystapi.jda.Handlers.CommandHandler;
import cf.ystapi.jda.Handlers.HelpHandler;
import net.dv8tion.jda.api.JDA;

import java.util.HashMap;
import java.util.List;

public class DiscordBot {
    public JDA jda;

    public HashMap<String, CommandHandler> commands;
    public HashMap<String, String> Aliases;
    public HashMap<String, DiscordRunnable> RunnableCommands;
    public HashMap<String, ButtonHandler> Buttons;

    public List<String> helpCommands;
    public HelpHandler helpHandler;

    public String Owner;
    public boolean IgnoreCase;
    public String prefix;

    public DiscordBot(JDA jda, HashMap<String, CommandHandler> commands, HashMap<String, DiscordRunnable> RunnableCommands, HashMap<String, ButtonHandler> Buttons, HelpHandler helpHandler, List<String> helpCommands, String prefix, String OwnerID, boolean IgnoreCase){
        this.jda = jda;
        this.commands = commands;
        this.RunnableCommands = RunnableCommands;
        this.Aliases = new HashMap<>();
        this.prefix = prefix;
        this.Owner = OwnerID;
        this.Buttons = Buttons;
        this.IgnoreCase = IgnoreCase;
        this.helpCommands = helpCommands;
        this.helpHandler = helpHandler;
        Thread t = new Thread("LowerCase"){
            @Override
            public void run() {
                while (true){
                    if(IgnoreCase){
                        for(String command : commands.keySet()){
                            if(!command.equals(command.toLowerCase())){
                                CommandHandler commandHandler = commands.get(command);
                                commands.remove(command);
                                commands.put(command.toLowerCase(), commandHandler);
                            }
                        }
                        for(String aliases : Aliases.keySet()){
                            if(!aliases.equals(aliases.toLowerCase())){
                                String command = Aliases.get(aliases);
                                Aliases.remove(aliases);
                                Aliases.put(aliases.toLowerCase(), command);
                            }
                        }
                        for(String command : RunnableCommands.keySet()){
                            if(!command.equals(command.toLowerCase())){
                                DiscordRunnable discordRunnable = RunnableCommands.get(command);
                                RunnableCommands.remove(command);
                                RunnableCommands.put(command.toLowerCase(), discordRunnable);
                            }
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.setPriority(1);
        t.start();
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

    public DiscordBot addButton(ButtonHandler... buttonHandlers) {
        for(ButtonHandler buttonHandler : buttonHandlers)
            Buttons.put(buttonHandler.id(), buttonHandler);
        return this;
    }

    public DiscordBot addCommand(CommandHandler... commandHandlers){
        for(CommandHandler commandHandler : commandHandlers)
            commands.put(commandHandler.name(), commandHandler);
        return this;
    }

    public DiscordBot addCommand(String name, DiscordRunnable onCall){
        RunnableCommands.put(name, onCall);
        return this;
    }

    public DiscordBot replaceCommand(String name, DiscordRunnable onCall){
        if(RunnableCommands.containsKey(name)){
            RunnableCommands.replace(name, onCall);
        }else if(commands.containsKey(name)){
            commands.remove(name);
            RunnableCommands.put(name, onCall);
        }else
            System.out.println("That command isn't exists!");
        return this;
    }

    public DiscordBot removeCommand(String name){
        if(RunnableCommands.containsKey(name))
            RunnableCommands.remove(name);
        else if(commands.containsKey(name))
            commands.remove(name);
        else
            System.out.println("That command isn't exists!");
        return this;
    }

    public DiscordBot setPrefix(String prefix){
        this.prefix = prefix;
        return this;
    }
}
