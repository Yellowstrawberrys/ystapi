package cf.ystapi.explains.jda.CommandHandlers;

import cf.ystapi.jda.Handlers.SlashCommandHandler;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;


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
    public void onCalled(SlashCommandInteractionEvent event, String subCommand, MessageChannel channel) {
        channel.sendMessage("Fuck").queue();
    }
}
