package cf.ystapi.explains.jda;

import cf.ystapi.explains.jda.ButtonHandlers.Test;
import cf.ystapi.explains.jda.CommandHandlers.first;
import cf.ystapi.explains.jda.CommandHandlers.slash;
import cf.ystapi.jda.Objects.DiscordBot;
import cf.ystapi.jda.YSTBuilder;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class bot {
    public static DiscordBot discordBot;

    @org.junit.jupiter.api.Test
    public static void main(String[] args) throws LoginException {
        JDABuilder jdaBuilder = JDABuilder.createDefault(args[0]);
        YSTBuilder builder = new YSTBuilder(jdaBuilder.build());
        builder.addCommand(new first()).addCommand("run", (event, args1, channel) -> channel.sendMessage("Test Fin").queue())
                .setPrefix("!").setOwner("719932404877230140").addCommand("audio", (event, args1, channel) -> event.getJDA().getDirectAudioController().connect(event.getMember().getVoiceState().getChannel()))
                .setHelpCommands("d").addSlashCommand(new slash());
        builder.addButton(new Test());

        discordBot = builder.build();
    }
}
