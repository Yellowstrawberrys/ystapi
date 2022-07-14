package cf.ystapi.explains.jda.modal;

import cf.ystapi.jda.Handlers.ModalHandler;
import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.ModalInteraction;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;

import java.util.List;

public class modalhandler implements ModalHandler {
    @Override
    public String id() {
        return "test";
    }

    @Override
    public void onCalled(ModalInteractionEvent event, User user, List<ModalMapping> values, Channel channel) {
        event.reply("""
                TEST FINISHED
                
                Field1 - %s
                Field2 - %s""".formatted(values.get(0).getAsString(), values.get(0).getAsString())).queue();
    }
}
