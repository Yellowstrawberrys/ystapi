package cf.ystapi.jda;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@FunctionalInterface
public interface DiscordRunnable {
    public abstract void run(MessageReceivedEvent event, String[] args, MessageChannel channel);
}
