package cf.ystapi.explains.jda.CommandHandlers;

import cf.ystapi.jda.Handlers.SlashCommandHandler;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class slash implements SlashCommandHandler {
    @Override
    public String name() {
        return "sos";
    }

    @Override
    public String helpMessages() {
        return null;
    }

    @Override
    public String description() {
        return "Testing";
    }

    @Override
    public void onCalled(SlashCommandEvent event, String subCommand, MessageChannel channel) {
        channel.sendMessage("Fuck").queue();
    }
}
