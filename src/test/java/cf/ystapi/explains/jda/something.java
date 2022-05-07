package cf.ystapi.explains.jda;

import cf.ystapi.jda.Handlers.BeforeCommandHandler;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Random;

public class something implements BeforeCommandHandler {
    @Override
    public boolean isSlashCommandCanceled(SlashCommandInteractionEvent event) {
        if(new Random().nextBoolean()){
            event.reply("ㅋㅋ").queue();
            return true;
        }else{
            return false;
        }
    }
}
