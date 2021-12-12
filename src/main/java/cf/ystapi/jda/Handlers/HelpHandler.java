package cf.ystapi.jda.Handlers;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.awt.*;

public interface HelpHandler {
    public default void onCalled(String commandName, String Description, String usage, String usedCommand, MessageChannel usedChannel){
        EmbedBuilder em = new EmbedBuilder().setTitle("Help - INFO").setColor(Color.YELLOW);
        em.addField("Command Name:", "`"+commandName+"`", false).addField("Description:",Description,false).setFooter("Used Command: "+usedCommand);
        if(usage != null && !usage.isBlank() && !usage.isEmpty())
            em.addField("Usage","`"+usage+"`", false);
        usedChannel.sendMessageEmbeds(em.build()).queue();
    }
}