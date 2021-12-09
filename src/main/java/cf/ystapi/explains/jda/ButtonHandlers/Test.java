package cf.ystapi.explains.jda.ButtonHandlers;

import cf.ystapi.jda.Handlers.ButtonHandler;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.interactions.components.Button;

public class Test implements ButtonHandler {
    @Override
    public String id() {
        return "one";
    }

    @Override
    public void onCalled(ButtonClickEvent event, User user, Button button, MessageChannel channel) {
        event.deferEdit().queue();
        if(user.getId().equals("719932404877230140")){
            event.getMessage().editMessage("Owner Clicked!").queue();
        }
    }
}
