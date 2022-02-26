package cf.ystapi.jda.Runables;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * SlashRunnable is just same as Runnable but has SlashCommandEvent, args, and Channel for Discord.
 * @version Beta 0.0.1.5
 * @since Beta 0.0.1.4
 * **/
@FunctionalInterface
public interface SlashRunnable {
    public abstract void run(SlashCommandInteractionEvent event, String subCommand, MessageChannel channel);
}
