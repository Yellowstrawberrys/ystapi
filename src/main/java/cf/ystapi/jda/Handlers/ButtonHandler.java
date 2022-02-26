package cf.ystapi.jda.Handlers;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;


public interface ButtonHandler {
    String id();
    default boolean onlyGuild(){
        return true;
    }
    void onCalled(ButtonInteractionEvent event, User user, Button button, MessageChannel channel);
}
