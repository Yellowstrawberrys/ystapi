package cf.ystapi.explains.jda;

import cf.ystapi.explains.jda.CommandHandlers.first;
import cf.ystapi.jda.DiscordBot;
import cf.ystapi.jda.YSTBuilder;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class bot {
    public static DiscordBot discordBot;
    public static void main(String[] args) throws LoginException {
        JDABuilder jdaBuilder = JDABuilder.createDefault("ODMyMDA4MDkwMDQ3MjE3NzM1.YHdh6g.AThfgntFDjBKIanOhyBvjX45V98");
        YSTBuilder builder = new YSTBuilder(jdaBuilder.build());
        builder.addCommand(new first()).addCommand("run", (event, args1, channel) -> {
            channel.sendMessage("Test Fin").queue();
        })
                .setPrefix("!").setOwner("719932404877230140");

        discordBot = builder.build();
    }
}
