package cf.ystapi.jda.Handlers;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

/**
 * <p>ButtonHandler</p>
 * - handles button
 * **/
public interface ButtonHandler {
    /**
     * Setting ID of Button for ButtonHandler to understand.
     * **/
    String id();
    /**
     * Setting is only for the guilds
     * **/
    default boolean onlyGuild(){
        return true;
    }
    /**
     * Setting action when button pressed.
     * **/
    void onCalled(ButtonInteractionEvent event, User user, Button button, MessageChannel channel);
}
