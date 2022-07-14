package cf.ystapi.jda.Handlers;

import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.ModalInteraction;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;

import java.util.List;

public interface ModalHandler {
    /**
     * Setting ID of Button for ButtonHandler to understand.
     * **/
    String id();
    /**
     * Setting action when button pressed.
     * **/
    void onCalled(ModalInteractionEvent event, User user, List<ModalMapping> values, Channel channel);
}
