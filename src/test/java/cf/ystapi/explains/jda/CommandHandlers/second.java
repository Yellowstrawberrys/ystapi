package cf.ystapi.explains.jda.CommandHandlers;

import cf.ystapi.jda.Handlers.CommandHandler;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class second implements CommandHandler {
    @Override
    public String name() {
        return "f";
    }

    @Override
    public String helpMessages() {
        return null;
    }

    @Override
    public void onCalled(MessageReceivedEvent event, String[] args, MessageChannel channel) {
        System.out.println("Fuck");
        event.getChannel().sendMessage("hi").queue();
    }
}
