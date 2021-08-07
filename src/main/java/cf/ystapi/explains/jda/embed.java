package cf.ystapi.explains.jda;

import cf.ystapi.jda.Embed;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;
import org.jetbrains.annotations.NotNull;

public class embed extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(event.getMessage().getContentRaw().equals("!test")){

            //Create Embed
            EmbedBuilder e = new EmbedBuilder().setTitle("Test")
                    .addField("TEST","TEST",false);

            //Get Text Channel
            TextChannel t = event.getTextChannel();

            //Create Buttons
            Button b1 = Button.primary("Hi", "Click Me");
            Button b2 = Button.primary("Hi1", "Click Me!");

            //Send Message
            event.getChannel().sendMessage(e.build()).queue(
                    //AddButton
                    message -> Embed.addButton(
                        //Set Text
                        t,
                        //Set Message Id
                        message.getId(),
                        "Select:",
                        //set Buttons
                        b1,
                        b2
                    ));
        }
    }
}
