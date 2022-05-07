package cf.ystapi.jda.Handlers;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface BeforeCommandHandler {
    /**
     * Is Slash Command Canceled
     * **/
    public default boolean isSlashCommandCanceled(SlashCommandInteractionEvent event){
        return false;
    }

    /**
     * Is Command Canceled
     * **/
    public default boolean isCommandCanceled(MessageReceivedEvent event){
        return false;
    }
}
