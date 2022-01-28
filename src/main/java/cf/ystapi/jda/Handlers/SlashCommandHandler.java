package cf.ystapi.jda.Handlers;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public interface SlashCommandHandler {
    String name();
    default String[] usage(){return new String[]{};}
    String helpMessages();
    void onCalled(SlashCommandEvent event, String[] args, MessageChannel channel);
}
