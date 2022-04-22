package cf.ystapi.jda.Handlers;

import cf.ystapi.jda.Objects.CommandData;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * Slash Command Handler
 * <p>
 * registering Command can take longer than 1 hours
 * **/
public interface SlashCommandHandler {
    /**
     * Setting Name of the command
     * **/
    String name();
    /**
     * Setting Description of the command
     * **/
    String description();
    /**
     * Setting Usage of the command
     * **/
    default String[] usage(){return new String[]{};}
    /**
     * Setting Help message of the command
     * **/
    default String helpMessages(){return null;}
    /**
     * Setting Options
     * */
    default CommandData commandData(){
        return null;
    }
    /**
     * Setting Action when command called.
     * **/
    void onCalled(SlashCommandInteractionEvent event, String subCommand, MessageChannel channel);
}
