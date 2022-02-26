package cf.ystapi.jda.Handlers;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * Slash Command Handler
 * <p>
 * registering Command can take longer than 1 hours
 * **/
public interface SlashCommandHandler {
    String name();
    default String[] usage(){return new String[]{};}
    String helpMessages();
    default String description(){return "";}
    void onCalled(SlashCommandInteractionEvent event, String subCommand, MessageChannel channel);
}
