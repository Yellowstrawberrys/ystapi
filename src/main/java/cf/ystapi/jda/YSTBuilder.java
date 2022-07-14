package cf.ystapi.jda;

import cf.ystapi.Logging.Logger;
import cf.ystapi.Logging.LoggingBuilder;
import cf.ystapi.jda.Exceptions.CommandAlreadyExistsException;
import cf.ystapi.jda.Handlers.*;
import cf.ystapi.jda.JDAHandlers.EventHandler;
import cf.ystapi.jda.Objects.CommandData;
import cf.ystapi.jda.Objects.DiscordBot;
import cf.ystapi.jda.Runables.DiscordRunnable;
import cf.ystapi.jda.Runables.SlashRunnable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.*;
import net.dv8tion.jda.api.utils.data.DataObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * How to use:
 *  <p>
 *  YSTBuilder yst = new YSTBuilder(JDABuilder.build());
 *  <p>
 *  yst.setPrefix("!").setOwner("ID").addCommand(new bot());
 *  <p>
 *  DiscordBot dis = yst.build();
 * @since Beta 0.0.0.3
 * **/
public class YSTBuilder {
    HashMap<String, CommandHandler> commands = new HashMap<>();
    HashMap<String, ButtonHandler> buttons = new HashMap<>();
    HashMap<String, ModalHandler> modals = new HashMap<>();
    HashMap<String, DiscordRunnable> RunnableCommands = new HashMap<>();
    HashMap<String, SlashCommandHandler> slashCommands = new HashMap<>();
    HashMap<String, SlashRunnable> slashRunnableCommands = new HashMap<>();
    List<String> helpCommands = new ArrayList<>();
    HelpHandler helpHandler = new DefaultHelpHandler();
    BeforeCommandHandler beforeCommandHandler = new BeforeCommandHandler() {};
    boolean IgnoreCase = false;
    boolean SlashCommandMode = false;
    boolean useFastSlashCommandUpsert = false;
    String prefix = "";
    String testGuildId;
    String OwnerID;
    JDA jda;

    public YSTBuilder(JDA JDAObject){
        jda = JDAObject;
    }

    /**
     * Adding Button to your bot(Button Handler)!
     *
     * @version Beta 0.0.0.5
     * @return YSTBuilder
     * @since Beta 0.0.0.5
     * **/
    public YSTBuilder addButton(ButtonHandler buttonHandler) {
        buttons.put(buttonHandler.id(), buttonHandler);
        return this;
    }

    /**
     * If you turn on this, then YSTDOK and Help commands are only can call in slashCommand.
     *
     * @version Beta 0.0.1.4
     * @return YSTBuilder
     * @since Beta 0.0.1.4
     * **/
    public YSTBuilder SlashCommandMode(boolean isUsing){
        SlashCommandMode = isUsing;
        return this;
    }

    /**
     * IgnoreCase
     *
     * @version Beta 0.0.0.8
     * @return YSTBuilder
     * @since Beta 0.0.0.8
     * **/
    public YSTBuilder IgnoreCase(boolean bool) throws CommandAlreadyExistsException{
        this.IgnoreCase = bool;
        return this;
    }

    /**
     * Adding command to your bot(Command Handler)!
     *
     * @throws CommandAlreadyExistsException
     * @version Beta 0.0.0.4
     * @return YSTBuilder
     * @since Beta 0.0.0.3
     * **/
    public YSTBuilder addCommand(CommandHandler commandHandler) throws CommandAlreadyExistsException{
        if(!doesCommandExits(commandHandler.name()))
            commands.put(commandHandler.name(), commandHandler);
        else
            throw new CommandAlreadyExistsException("This command('"+commandHandler.name()+"') is already exists!");
        return this;
    }

    /**
     * Adding command to your bot(SlashCommand Handler)!
     *
     * @throws CommandAlreadyExistsException
     * @version Beta 0.0.1.9
     * @return YSTBuilder
     * @since Beta 0.0.1.8
     * **/
    public YSTBuilder addCommand(SlashCommandHandler... commandHandler) throws CommandAlreadyExistsException{
        addSlashCommand(commandHandler);
        return this;
    }

    /**
     * Adding command to your bot(Discord Runnable)!
     *
     * @throws CommandAlreadyExistsException
     * @version Beta 0.0.0.4
     * @return YSTBuilder
     * @since Beta 0.0.0.3
     * **/
    public YSTBuilder addCommand(String name, DiscordRunnable onCall) throws CommandAlreadyExistsException{
        if(!doesCommandExits(name))
            RunnableCommands.put(name, onCall);
        else
            throw new CommandAlreadyExistsException("This command('"+name+"') is already exists!");
        return this;
    }

    /**
     * Adding Slash command to your bot(SlashCommandHandler)!
     *
     * @throws CommandAlreadyExistsException
     * @version Beta 0.0.1.9
     * @return YSTBuilder
     * @since Beta 0.0.1.4
     * **/
    public YSTBuilder addSlashCommand(SlashCommandHandler... slashCommandHandlers){
        for(SlashCommandHandler slashCommandHandler : slashCommandHandlers){
            if(!doesCommandExits(slashCommandHandler.name()))
                slashCommands.put(slashCommandHandler.name(), slashCommandHandler);
            else
                throw new CommandAlreadyExistsException("This command('"+slashCommandHandler.name()+"') is already exists!");
        }
        return this;
    }

    /**
     * Adding Slash command to your bot(Slash Runnable)!
     *
     * @throws CommandAlreadyExistsException
     * @version Beta 0.0.1.4
     * @return YSTBuilder
     * @since Beta 0.0.1.4
     * **/
    public YSTBuilder addSlashCommand(String name, SlashRunnable slashRunnable){
        if(!doesCommandExits(name))
            slashRunnableCommands.put(name, slashRunnable);
        else
            throw new CommandAlreadyExistsException("This command('"+name+"') is already exists!");
        return this;
    }

    /**
     * Adding Button to your bot(Button Handler) | deprecated!
     *
     * @version Beta 0.0.0.8
     * @return YSTBuilder
     * @since Beta 0.0.0.8
     * @deprecated
     * **/
    @Deprecated
    public YSTBuilder addButton(ButtonHandler... buttonHandlers){
        addButtons(buttonHandlers);
        return this;
    }

    /**
     * Adding Button to your bot(Button Handler)!
     *
     * @version Beta 0.0.2.6
     * @return YSTBuilder
     * @since Beta 0.0.2.6
     * **/
    public YSTBuilder addButtons(ButtonHandler... buttonHandlers) {
        for(ButtonHandler buttonHandler : buttonHandlers)
            buttons.put(buttonHandler.id(), buttonHandler);
        return this;
    }

    /**
     * Adding Button to your bot(Button Handler)!
     *
     * @version Beta 0.0.2.6
     * @return YSTBuilder
     * @since Beta 0.0.2.6
     * **/
    public YSTBuilder addModals(ModalHandler... modalHandlers) {
        for(ModalHandler modalHandler : modalHandlers)
            modals.put(modalHandler.id(), modalHandler);
        return this;
    }

    /**
     * Adding command to your bot(Command Handler)!
     *
     * @throws CommandAlreadyExistsException
     * @version Beta 0.0.0.8
     * @return YSTBuilder
     * @since Beta 0.0.0.8
     * **/
    public YSTBuilder addCommand(CommandHandler... commandHandlers){
        for(CommandHandler commandHandler : commandHandlers)
            commands.put(commandHandler.name(), commandHandler);
        return this;
    }

    /**
     * Setting Prefix for your bot!
     *
     * @return YSTBuilder
     * @since Beta 0.0.0.3
     * **/
    public YSTBuilder setPrefix(String prefix){
        this.prefix = prefix;
        return this;
    }

    private boolean doesCommandExits(String name){
        return commands.containsKey(name) && RunnableCommands.containsKey(name);
    }

    /**
     * Setting Owner for your bot!
     * <p>
     * If you don't set your Discord ID, then you can't use ystdok
     *
     * @return YSTBuilder
     * @since Beta 0.0.0.3
     * **/
    public YSTBuilder setOwner(String OwnerID){
        this.OwnerID = OwnerID;
        return this;
    }

    /**
     * Setting Help Commands to your bot!
     *
     * @version Beta 0.0.0.9
     * @return YSTBuilder
     * @since Beta 0.0.0.9
     * **/
    public YSTBuilder setHelpCommands(String... commands){
        helpCommands.clear();
        helpCommands.addAll(Arrays.asList(commands));
        return this;
    }

    /**
     * Setting Help Command Handler to your bot!
     *
     * @version Beta 0.0.0.9
     * @return YSTBuilder
     * @since Beta 0.0.0.9
     * **/
    public YSTBuilder setHelpHandler(HelpHandler helpHandler){
        this.helpHandler = helpHandler;
        return this;
    }

    /**
     * Setting BeforeCommandHandler Command Handler to your bot!
     *
     * @version Beta 0.0.2.3
     * @return YSTBuilder
     * @since Beta 0.0.2.3
     * **/
    public YSTBuilder setBeforeCommandHandler(BeforeCommandHandler beforeCommandHandler){
        this.beforeCommandHandler = beforeCommandHandler;
        return this;
    }

    /**
     * Set Test Guild for SlashCommands
     *
     * @param guildId
     * @return YSTBuilder
     * @since Beta 0.0.2.2
     */
    public YSTBuilder setTestGuild(String guildId){
        this.testGuildId = guildId;
        return this;
    }

    /**
     * Set useFastSlashCommandUpsert<br/>
     *<br/>
     * <strong>WE DON'T RECOMMEND TO USE THIS OPTION</strong><br/>
     * <strong>This might cause more resources/loading in startup.</strong>
     * @param isUsing
     * @return YSTBuilder
     */
    public YSTBuilder useFastSlashCommandUpsert(boolean isUsing){
        this.useFastSlashCommandUpsert = isUsing;
        return this;
    }

    /**
     * To start your bot, you need this!
     *
     * @version Beta 0.0.2.6
     * @return DiscordBot
     * @since Beta 0.0.0.3
     * **/
    public DiscordBot build(){
        if(Logger.getLoggerByName("System") == null)
            new LoggingBuilder().build("System");
        Logger.getLoggerByName("System").info("Starting to load JDA Commands");
        DiscordBot Discordbot = new DiscordBot(jda, commands, RunnableCommands, buttons, modals, slashCommands, slashRunnableCommands, helpHandler, beforeCommandHandler, helpCommands, prefix, OwnerID, IgnoreCase);
        EventHandler eventHandler = new EventHandler(Discordbot);
        jda.addEventListener(eventHandler);
        new Thread(() -> {
            try {
                if (testGuildId != null && !(testGuildId.isBlank() && testGuildId.isEmpty())) {
                    for (SlashCommandHandler slashCommandHandler : slashCommands.values())
                        jda.awaitReady().getGuildById(testGuildId).upsertCommand(slashCommandDataMaker(slashCommandHandler)).queue();
                }
                if (useFastSlashCommandUpsert) {
                    for (Guild g : jda.awaitReady().getGuilds()) {
                        for (SlashCommandHandler slashCommandHandler : slashCommands.values()) {
                            g.upsertCommand(slashCommandDataMaker(slashCommandHandler)).queue();
                        }
                    }
                } else {
                    for (SlashCommandHandler slashCommandHandler : slashCommands.values())
                        jda.awaitReady().upsertCommand(slashCommandDataMaker(slashCommandHandler)).queue();
                }
                for (String command : slashRunnableCommands.keySet())
                    jda.awaitReady().upsertCommand(command, "N/A").queue();
                Logger.getLoggerByName("System").info("Finish loading JDA Commands!");
            }catch (Exception e){}
        }).start();
        return Discordbot;
    }

    public SlashCommandData slashCommandDataMaker(SlashCommandHandler slashCommandHandler){
        SlashCommandData sl = Commands.slash(slashCommandHandler.name(), (!(slashCommandHandler.description() == null && slashCommandHandler.description().isEmpty() && slashCommandHandler.description().isBlank()) ? slashCommandHandler.description() : "N/A"));
        CommandData commandData = slashCommandHandler.commandData();
        if(commandData != null) {
            for (String st : commandData.getAsRaw().get("SubCommands"))
                sl.addSubcommands(SubcommandData.fromData(DataObject.fromJson(st)));
            for (String st : commandData.getAsRaw().get("SubCommandGroups"))
                sl.addSubcommandGroups(SubcommandGroupData.fromData(DataObject.fromJson(st)));
            for (String st : commandData.getAsRaw().get("Options"))
                sl.addOptions(OptionData.fromData(DataObject.fromJson(st)));
        }
        return sl;
    }
}
