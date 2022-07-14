package cf.ystapi.explains.jda.CommandHandlers;

import cf.ystapi.jda.Handlers.SlashCommandHandler;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;

public class modal implements SlashCommandHandler {
    @Override
    public String name() {
        return "modal";
    }

    @Override
    public String description() {
        return "modal test";
    }

    @Override
    public void onCalled(SlashCommandInteractionEvent event, String subCommand, MessageChannel channel) {
        TextInput email = TextInput.create("test", "test", TextInputStyle.SHORT)
                .setPlaceholder("TEST1")
                .setMinLength(10)
                .setMaxLength(100) // or setRequiredRange(10, 100)
                .build();

        TextInput body = TextInput.create("test2", "test2", TextInputStyle.PARAGRAPH)
                .setPlaceholder("TEST2")
                .setMinLength(30)
                .setMaxLength(1000)
                .build();

        Modal modal = Modal.create("test", "test")
                .addActionRows(ActionRow.of(email), ActionRow.of(body))
                .build();
        event.replyModal(modal).queue();
    }
}
