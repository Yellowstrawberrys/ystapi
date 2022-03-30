package cf.ystapi.jda.Handlers;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface CommandHandler {
    /**
     * Setting Name of the command
     * **/
    String name();
    /**
     * Setting Aliases of the command
     * **/
    default String[] Aliases(){
        return new String[]{""};
    }
    /**
     * Setting is only for the guilds
     * **/
    default boolean onlyGuild(){
        return true;
    }
    /**
     * Setting usage of the command
     * **/
    default String[] usage(){return new String[]{};}
    /**
     * Setting help message of the command
     * **/
    String helpMessages();
    /**
     * Setting action that will be done when command called.
     * **/
    void onCalled(MessageReceivedEvent event, String[] args, MessageChannel channel);
}
