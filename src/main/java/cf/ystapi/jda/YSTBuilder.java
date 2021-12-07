package cf.ystapi.jda;

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
    HashMap<String, DiscordRunnable> RunnableCommands = new HashMap<>();
    String prefix = "";
    String OwnerID;
    JDA jda;

    public YSTBuilder(JDA JDAObject){
        jda = JDAObject;
    }

    public YSTBuilder addCommand(CommandHandler commandHandler){
        commands.put(commandHandler.name(), commandHandler);
        return this;
    }

    public YSTBuilder addCommand(String name, DiscordRunnable onCall){
        RunnableCommands.put(name, onCall);
        return this;
    }

    public YSTBuilder setPrefix(String prefix){
        this.prefix = prefix;
        return this;
    }

    public YSTBuilder setOwner(String OwnerID){
        this.OwnerID = OwnerID;
        return this;
    }

    public DiscordBot build(){
        DiscordBot Discordbot = new DiscordBot(jda, commands, RunnableCommands, prefix, OwnerID);
        EventHandler eventHandler = new EventHandler(Discordbot, prefix);
        jda.addEventListener(eventHandler);
        return Discordbot;
    }
}
