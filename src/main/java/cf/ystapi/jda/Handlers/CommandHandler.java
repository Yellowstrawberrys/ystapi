package cf.ystapi.jda.Handlers;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface CommandHandler {
    String name();
    default String[] Aliases(){
        return new String[]{""};
    }
    default boolean onlyGuild(){
        return true;
    }
    String helpMessages();
    void onCalled(MessageReceivedEvent event, String[] args, MessageChannel channel);
}
