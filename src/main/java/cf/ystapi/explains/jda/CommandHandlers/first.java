package cf.ystapi.explains.jda.CommandHandlers;

import cf.ystapi.jda.Handlers.CommandHandler;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;

public class first implements CommandHandler {
    @Override
    public String name() {
        return "test";
    }

    @Override
    public String helpMessages() {
        return "This Command is Testing Command!";
    }

    @Override
    public String usage() {
        return "!test";
    }

    @Override
    public void onCalled(MessageReceivedEvent event, String[] args, MessageChannel channel) {
        channel.sendMessage("Hello World!").setActionRows(ActionRow.of(Button.primary("one","WOW!"))).queue();
    }
}
