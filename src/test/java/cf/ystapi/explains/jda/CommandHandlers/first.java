package cf.ystapi.explains.jda.CommandHandlers;

import cf.ystapi.jda.Handlers.CommandHandler;
import cf.ystapi.util.Img.Captcha;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class first implements CommandHandler {
    @Override
    public String name() {
        return "test";
    }

    @Override
    public String helpMessages() {
        return "This Command is Testing Command!";
    }

    @Override
    public String[] usage() {
        return new String[]{"!test"};
    }

    @Override
    public void onCalled(MessageReceivedEvent event, String[] args, MessageChannel channel) {
        channel.sendMessage("Hello World!").setActionRows(ActionRow.of(Button.primary("one","WOW!"))).addFile(new Captcha().export(new Captcha().generateCaptchaText(5), 100, 100), "ystCaptcha.png").queue();
    }
}
