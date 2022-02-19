package cf.ystapi.jda.Runables;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * DiscordRunnable is just same as Runnable but has event, args, and Channel for Discord.
 * @since Beta 0.0.0.3
 * **/

@FunctionalInterface
public interface DiscordRunnable {
    public abstract void run(MessageReceivedEvent event, String[] args, MessageChannel channel);
}
