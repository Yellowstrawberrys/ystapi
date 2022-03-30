package cf.ystapi.jda.Handlers;

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
     * Setting Usage of the command
     * **/
    default String[] usage(){return new String[]{};}
    /**
     * Setting Help message of the command
     * **/
    String helpMessages();
    /**
     * Setting Description of the command
     * **/
    default String description(){return "";}
    /**
     * Setting Action when command called.
     * **/
    void onCalled(SlashCommandInteractionEvent event, String subCommand, MessageChannel channel);
}
