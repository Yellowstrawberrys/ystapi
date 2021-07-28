package cf.ystapi.jda;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;

public class Embed {
    public static void addButton(Message message, String label, Button... buttons){
        message.reply(label).setActionRows(ActionRow.of(buttons)).queue();
    }
    public static void addButton(TextChannel textChannel, String MessageId, String label, Button... buttons){
        Message message = textChannel.getHistory().getMessageById(MessageId);
        assert message != null;
        message.reply(label).setActionRows(ActionRow.of(buttons)).queue();
    }
    public static void addButton(TextChannel textChannel, Long MessageId, String label, Button... buttons){
        Message message = textChannel.getHistory().getMessageById(MessageId);
        assert message != null;
        message.reply(label).setActionRows(ActionRow.of(buttons)).queue();
    }
}
