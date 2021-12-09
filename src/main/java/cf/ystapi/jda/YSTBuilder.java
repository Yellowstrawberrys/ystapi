package cf.ystapi.jda;

import cf.ystapi.jda.Exceptions.CommandAlreadyExistsException;
import cf.ystapi.jda.JDAHandlers.EventHandler;
import net.dv8tion.jda.api.JDA;

import java.util.HashMap;
/**
 * How to use:
 *  <p>
 *  YSTBuilder yst = new YSTBuilder(JDABuilder.build());
 *  <p>
 *  yst.setPrefix("!").setOwner("ID").addCommand(new bot());
 *  <p>
 *  DiscordBot dis = yst.build();
 *
 * @since Beta 0.0.0.3
 * **/
public class YSTBuilder {
    HashMap<String, CommandHandler> commands = new HashMap<>();
    HashMap<String, ButtonHandler> buttons = new HashMap<>();
    HashMap<String, DiscordRunnable> RunnableCommands = new HashMap<>();
    String prefix = "";
    String OwnerID;
    JDA jda;

    public YSTBuilder(JDA JDAObject){
        jda = JDAObject;
    }

    /**
     * Adding Button to your bot(Button Handler)!
     *
     * @version Beta 0.0.0.5
     * @return YSTBuilder
     * @since Beta 0.0.0.5
     * **/
    public YSTBuilder addButton(ButtonHandler buttonHandler) {
        buttons.put(buttonHandler.id(), buttonHandler);
        return this;
    }

    /**
     * Adding command to your bot(Command Handler)!
     *
     * @throws CommandAlreadyExistsException
     * @version Beta 0.0.0.4
     * @return YSTBuilder
     * @since Beta 0.0.0.3
     * **/
    public YSTBuilder addCommand(CommandHandler commandHandler) throws CommandAlreadyExistsException{
        if(!doesCommandExits(commandHandler.name()))
            commands.put(commandHandler.name(), commandHandler);
        else
            throw new CommandAlreadyExistsException("This command('"+commandHandler.name()+"') is already exists!");
        return this;
    }

    /**
     * Adding command to your bot(Discord Runnable)!
     *
     * @throws CommandAlreadyExistsException
     * @version Beta 0.0.0.4
     * @return YSTBuilder
     * @since Beta 0.0.0.3
     * **/
    public YSTBuilder addCommand(String name, DiscordRunnable onCall) throws CommandAlreadyExistsException{
        if(!doesCommandExits(name))
            RunnableCommands.put(name, onCall);
        else
            throw new CommandAlreadyExistsException("This command('"+name+"') is already exists!");
        return this;
    }

    /**
     * Setting Prefix for your bot!
     *
     * @return YSTBuilder
     * @since Beta 0.0.0.3
     * **/
    public YSTBuilder setPrefix(String prefix){
        this.prefix = prefix;
        return this;
    }

    private boolean doesCommandExits(String name){
        return commands.containsKey(name) && RunnableCommands.containsKey(name);
    }

    /**
     * Setting Owner for your bot!
     * <p>
     * If you don't set your Discord ID, then you can't use ystdok
     *
     * @return YSTBuilder
     * @since Beta 0.0.0.3
     * **/
    public YSTBuilder setOwner(String OwnerID){
        this.OwnerID = OwnerID;
        return this;
    }

    /**
     * To start your bot, you need this!
     *
     * @return DiscordBot
     * @since Beta 0.0.0.3
     * **/
    public DiscordBot build(){
        DiscordBot Discordbot = new DiscordBot(jda, commands, RunnableCommands, buttons, prefix, OwnerID);
        EventHandler eventHandler = new EventHandler(Discordbot, prefix);
        jda.addEventListener(eventHandler);
        return Discordbot;
    }
}
