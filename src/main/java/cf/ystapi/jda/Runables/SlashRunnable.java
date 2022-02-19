package cf.ystapi.jda.Runables;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

/**
 * SlashRunnable is just same as Runnable but has SlashCommandEvent, args, and Channel for Discord.
 * @since Beta 0.0.1.4
 * **/
@FunctionalInterface
public interface SlashRunnable {
    public abstract void run(SlashCommandEvent event, String subCommand, MessageChannel channel);
}
