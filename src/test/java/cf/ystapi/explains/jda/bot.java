package cf.ystapi.explains.jda;

import cf.ystapi.Logging.Logger;
import cf.ystapi.explains.jda.ButtonHandlers.Test;
import cf.ystapi.explains.jda.CommandHandlers.*;
import cf.ystapi.explains.jda.modal.modalhandler;
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
        builder.addCommand(new first()).addCommand(new second()).addCommand("run", (event, args1, channel) -> channel.sendMessage("Test Fin").queue())
                .setPrefix("$").setOwner("719932404877230140").addCommand("audio", (event, args1, channel) -> event.getJDA().getDirectAudioController().connect(event.getMember().getVoiceState().getChannel()))
                .addSlashCommand(new slash(), new slash2())
                .setHelpCommands("d").addSlashCommand(new slash(), new modal())
                .setBeforeCommandHandler(new something())
                .addModals(new modalhandler());
//        builder.setTestGuild("937349241112756224");
        builder.addButton(new Test());
//        builder.useFastSlashCommandUpsert(true);
        new Logger().err("ww\nww");
        discordBot = builder.build();
    }
}
