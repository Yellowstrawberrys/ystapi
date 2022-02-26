package cf.ystapi.explains.jda.ButtonHandlers;

import cf.ystapi.jda.Handlers.ButtonHandler;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class Test implements ButtonHandler {
    @Override
    public String id() {
        return "one";
    }

    @Override
    public void onCalled(ButtonInteractionEvent event, User user, Button button, MessageChannel channel) {
        event.deferEdit().queue();
        if(user.getId().equals("719932404877230140")){
            event.getMessage().editMessage("Owner Clicked!").queue();
        }
    }
}
