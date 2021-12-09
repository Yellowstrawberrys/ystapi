package cf.ystapi.jda;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.interactions.components.Button;

public interface ButtonHandler {
    String id();
    default boolean onlyGuild(){
        return true;
    }
    void onCalled(ButtonClickEvent event, User user, Button button, MessageChannel channel);
}
