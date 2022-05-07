package cf.ystapi.explains.jda.CommandHandlers;

import cf.ystapi.jda.Enums.OptionType;
import cf.ystapi.jda.Handlers.SlashCommandHandler;
import cf.ystapi.jda.Objects.CommandData;
import cf.ystapi.jda.Objects.Option;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;


public class slash implements SlashCommandHandler {
    @Override
    public String name() {
        return "sos";
    }

    @Override
    public String helpMessages() {
        return null;
    }

    @Override
    public String description() {
        return "Testing";
    }

    @Override
    public CommandData commandData() {
        return new CommandData().addOptions(new Option("fuck", "FUCK?").setOptionType(OptionType.MENTIONABLE));
    }

    @Override
    public void onCalled(SlashCommandInteractionEvent event, String subCommand, MessageChannel channel) {
//        if(event.getSubcommandGroup() != null && event.getSubcommandGroup().equals("vegetable")){
//            if(event.getSubcommandName() != null && event.getSubcommandName().equals("lol"))
//                event.reply(event.getOption("realKuKu").getAsUser().getAsMention()+" ㄹㅇㅋㅋ").queue();
//        }
//        if(event.getSubcommandName() != null && event.getSubcommandName().equals("carrot")){
//            if (event.getOption("shake").getAsBoolean())
//                event.reply("Thanks!").queue();
//            else
//                event.reply("No Thanks!").queue();
//        }else
        if(event.getOption("fuck") != null)
            event.reply("FUCK YOU "+event.getOption("fuck").getAsTextChannel().getAsMention()).queue();
        else
            event.reply("FUCK").queue();
    }
}
