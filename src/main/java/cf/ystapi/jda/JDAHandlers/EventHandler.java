package cf.ystapi.jda.JDAHandlers;

import cf.ystapi.Logging.Logger;
import cf.ystapi.Logging.LoggingBuilder;
import cf.ystapi.jda.Objects.DiscordBot;
import cf.ystapi.jda.System.ClassData;
import cf.ystapi.jda.System.YSTClassLoader;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;
import jdk.jshell.execution.LocalExecutionControlProvider;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * You don't need to come this class!
 * <p>
 * If you want know about this then,
 * <p>
 * this class is Handling Message Event from jda
 *
 * @version Beta 0.0.2.3
 * @since Beta 0.0.0.3
 * **/
public class EventHandler extends ListenerAdapter {
    DiscordBot Discordbot;
    JShell jShell;
    YSTClassLoader classLoader = new YSTClassLoader();
    long ReadyTime;

    /**
     * You don't need to come here!
     * <p>
     * If you want know about this then,
     * <p>
     * this class is Handling Message Event from jda
     *
     * @version Beta 0.0.0.4
     * @since Beta 0.0.0.3
     * **/
    public EventHandler(DiscordBot Discordbot){
        this.Discordbot = Discordbot;
        JShell.Builder jShellBuilder = JShell.builder();
        jShellBuilder.executionEngine(new LocalExecutionControlProvider(), null);
        this.jShell = jShellBuilder.build();
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        ReadyTime = System.currentTimeMillis();
    }

    /**
     * You don't need to come here!
     * <p>
     * If you want know about this then,
     * <p>
     * this code is handling message
     *
     * @version Beta 0.0.2.3
     * @since Beta 0.0.0.3
     * **/
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if(event.getMessage().getContentRaw().startsWith(Discordbot.prefix)) {
            String command = event.getMessage().getContentRaw().replaceFirst((Discordbot.prefix.equals("$") ? "\\$" : Discordbot.prefix), "");
            String commandName = command.split(" ")[0];
            if (Discordbot.IgnoreCase)
                commandName = commandName.toLowerCase();
            try {
                if(Discordbot.beforeCommandHandler.isCommandCanceled(event)) {
                    if (Discordbot.commands.containsKey(commandName)) {
                        callCommand(event, command, commandName);
                    }
                    if (Discordbot.RunnableCommands.containsKey(commandName))
                        Discordbot.RunnableCommands.get(commandName).run(event, command.split(" "), event.getChannel());
                    if (Discordbot.Aliases.containsKey(commandName)) {
                        Class<?> cls = classLoader.loadClass(Discordbot.commands.get(Discordbot.Aliases.get(commandName)).getClass().getName());
                        Object clazz = cls.getDeclaredConstructor().newInstance();
                        cls.getMethod("onCalled", MessageReceivedEvent.class, String[].class, MessageChannel.class).invoke(clazz, event, command.split(" "), event.getChannel());
                    }
                    if (Discordbot.helpCommands.contains(commandName) && command.split(" ").length > 0)
                        if (command.split(" ").length > 1 && Discordbot.commands.containsKey(command.split(" ")[1])) {
                            Class<?> cls = classLoader.loadClass(Discordbot.helpHandler.getClass().getName());
                            Object clazz = cls.getDeclaredConstructor().newInstance();
                            cls.getMethod("onCalled", String.class, String.class, String[].class, String.class, MessageChannel.class).invoke(clazz, command.split(" ")[1], Discordbot.commands.get(command.split(" ")[1]).helpMessages(), Discordbot.commands.get(command.split(" ")[1]).usage(), commandName, event.getChannel());
                        }
                }
                if (event.getAuthor().getId().equals(Discordbot.Owner)) {
                    if (command.startsWith("ystdok")) {
                        if (command.split(" ").length > 1) {
                            String input = command.replaceFirst(command.split(" ")[0], "").replaceFirst(command.split(" ")[1], "").replaceFirst("\\s\\s", "");
                            switch (command.split(" ")[1]) {
                                case "sh":
                                    new Thread(() -> {
                                        long started = System.currentTimeMillis();
                                        try {

                                            ProcessBuilder builder = new ProcessBuilder(
                                                    System.getProperty("os.name").startsWith("Windows") ? "cmd.exe" : "/bin/bash", System.getProperty("os.name").startsWith("Windows") ? "/c" : "-c", input);

                                            Process p = builder.start();
                                            p.waitFor();
                                            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                                            StringBuilder st = new StringBuilder();
                                            while (reader.ready()) {
                                                st.append(reader.readLine()).append("\n");
                                            }
                                            if (st.length() > 1024) {
                                                event.getChannel().sendMessageEmbeds(new EmbedBuilder().setTitle("YSTDOK")
                                                        .addField("Input", "`" + input + "`", false)
                                                        .addField("Real Time", "`" + (System.currentTimeMillis() - started) + "ms`", false)
                                                        .setFooter("Requested type: sh").build()).queue();
                                                event.getChannel().sendMessage("**OUTPUT(Too many characters):**\n`" + st + "`").queue();
                                            } else {
                                                event.getChannel().sendMessageEmbeds(new EmbedBuilder().setTitle("YSTDOK")
                                                        .addField("Input", "`" + input + "`", false)
                                                        .addField("Output", "`" + st + "`", false)
                                                        .addField("Real Time", "`" + (System.currentTimeMillis() - started) + "ms`", false)
                                                        .setFooter("Requested type: sh").build()).queue();
                                            }
                                        } catch (IOException | InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }).start();
                                    break;
                                case "java":
                                    new Thread(() -> {
                                        long started = System.currentTimeMillis();
                                        List<SnippetEvent> events = jShell.eval(input);
                                        SnippetEvent ev = events.get(events.size() - 1);
                                        String st = ((ev.value() == null || ev.value().isBlank()) ? "N/A" : ev.value());
                                        if (st.length() > 1024) {
                                            event.getChannel().sendMessageEmbeds(new EmbedBuilder().setTitle("YSTDOK")
                                                    .addField("Input", "`" + input + "`", false)
                                                    .addField("Real Time", "`" + (System.currentTimeMillis() - started) + "ms`", false)
                                                    .setFooter("Requested type: Java").build()).queue();
                                            event.getChannel().sendMessage("**OUTPUT(Too many characters):**\n`" + st + "`").queue();
                                        } else {
                                            event.getChannel().sendMessageEmbeds(new EmbedBuilder().setTitle("YSTDOK")
                                                    .addField("Input", "`" + input + "`", false)
                                                    .addField("Output", "`" + st + "`", false)
                                                    .addField("Real Time", "`" + (System.currentTimeMillis() - started) + "ms`", false)
                                                    .setFooter("Requested type: Java").build()).queue();
                                        }
                                    }).start();
                                    break;
                                case "replaceClass":
                                    if (command.split(" ")[2] != null && !command.split(" ")[2].isEmpty() && !command.split(" ")[2].isBlank()) {
                                        new Thread(() -> {
                                            if (!event.getMessage().getAttachments().isEmpty()) {
                                                Message.Attachment attachment = event.getMessage().getAttachments().get(0);
                                                if (Objects.equals(attachment.getFileExtension(), "class")) {
                                                    String path = new File("./Ystdok/ReplacedClass/").getAbsolutePath();
                                                    if (!new File(path).exists())
                                                        new File(path).mkdirs();
                                                    attachment.downloadToFile(path + "/" + attachment.getFileName())
                                                            .thenAccept(file -> {
                                                                ClassData.ReplacedClass.put(command.split(" ")[2], file.getAbsolutePath());
                                                                event.getChannel().sendMessage("Replacing Class has been done, Class is saved in ./Ystdok/ReplacedClass/*.class;").queue();
                                                            });
                                                } else if (Objects.equals(attachment.getFileExtension(), "java")) {
                                                    event.getChannel().sendMessage("We are not supporting direct-java-compile yet, please wait for new release!").queue();
                                                }
                                            }
                                        }).start();
                                    }
                                    break;
                            }
                        } else {
                            RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
                            Runtime.getRuntime().gc();
                            event.getChannel().sendMessage("YST DOK Beta v0.1.5, JDA `"+net.dv8tion.jda.api.JDA.class.getPackage().getImplementationVersion()+"`, `Java " + System.getProperty("java.version") + "` on `" + System.getProperty("os.name") + "`\n" +
                                    "Process started at <t:" + ((System.currentTimeMillis() - rb.getUptime()) / 1000L) + ":R>, bot was ready at <t:" + (ReadyTime / 1000L) + ":R>.\n\n" +
                                    "Using " + String.format("%99.02f", ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576f)).replaceAll(" ", "") + "MB at this process.\n" +
                                    "Running on PID " + rb.getPid() + ".\n\n" +
                                    ((event.getJDA().getAudioManagers().size() > 0) ? voiceChannel(event.getJDA()) : "") +
                                    "This bot is " + event.getJDA().getShardInfo().getShardString() + " and running in " + event.getJDA().getGuilds().size() + " guild(s). " +
                                    "Can see " + event.getJDA().getGuilds().size() + " guild(s) and " + event.getJDA().getUsers().size() + " user(s) in this client.\n" +
                                    "Average websocket latency: " + event.getJDA().getGatewayPing() + "ms").queue();
                        }
                    }
                }
            } catch (NullPointerException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
                err(e.getClass().getName(), e.getMessage());
                e.printStackTrace();
            } catch (InvocationTargetException e){
                classLoader = new YSTClassLoader();
                Throwable actualException = e.getCause();
                actualException.printStackTrace();
                if(Logger.getLoggerByName("System") == null) {
                    new LoggingBuilder().build("System");
                }
                Logger.getLoggerByName("System").error("Error while processing class "+actualException.getClass()+"\nCause: "+actualException.getCause()+"\nMessage: "+actualException.getMessage());
                try {
                    callCommand(event, command, commandName);
                }catch (Exception e1){
                    err(e.getClass().getName(), e.getMessage());
                }
            }
        }
    }

    private void callCommand(@NotNull MessageReceivedEvent event, String command, String commandName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class<?> cls = classLoader.loadClass(Discordbot.commands.get(commandName).getClass().getName());
        Object clazz = cls.getDeclaredConstructor().newInstance();
        if (Discordbot.commands.get(commandName).onlyGuild()) {
            if (event.isFromGuild() || event.isFromThread())
                cls.getMethod("onCalled", MessageReceivedEvent.class, String[].class, MessageChannel.class).invoke(clazz, event, command.split(" "), event.getChannel());
        } else
            cls.getMethod("onCalled", MessageReceivedEvent.class, String[].class, MessageChannel.class).invoke(clazz, event, command.split(" "), event.getChannel());
    }

    public static String voiceChannel(JDA jda){
        return "> Bot has been connected to "+ jda.getAudioManagers().size()+" voice channel(s).\n";
    }

//    public static long getUsingMemory(){
//        long Metaspace;
//        for (MemoryPoolMXBean memoryMXBean : ManagementFactory.getMemoryPoolMXBeans()) {
//            if ("Metaspace".equals(memoryMXBean.getName())) {
//                Metaspace = memoryMXBean.getUsage().getUsed();
//            }
//        }
//        return Runtime.getRuntime().totalMemory() + Metaspace + (Thread.getAllStackTraces())
//    }


    /**
     * You don't need to come here!
     * <p>
     * If you want know about this then,
     * <p>
     * this code is handling slashCommands
     * <p>
     * <strong>Slash Command takes minimum 1min to register</strong>
     *
     * @version Beta 0.0.2.3
     * @since Beta 0.0.1.4
     * **/
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(Discordbot.beforeCommandHandler.isSlashCommandCanceled(event)) {
            String command = event.getName();
            if (Discordbot.IgnoreCase)
                command = command.toLowerCase();
            try {
                if (Discordbot.slashCommands.containsKey(command)) {
                    Class<?> cls = classLoader.loadClass(Discordbot.slashCommands.get(command).getClass().getName());
                    Object clazz = cls.getDeclaredConstructor().newInstance();
                    cls.getMethod("onCalled", SlashCommandInteractionEvent.class, String.class, MessageChannel.class).invoke(clazz, event, event.getSubcommandName(), event.getChannel());
                }
                if (Discordbot.slashRunnableCommands.containsKey(command))
                    Discordbot.slashRunnableCommands.get(command).run(event, event.getSubcommandName(), event.getChannel());
//            if (Discordbot.Aliases.containsKey(command)) {
//                Class cls = classLoader.loadClass(Discordbot.slashCommands.get(Discordbot.Aliases.get(commandName)).getClass().getName());
//                Object clazz = cls.newInstance();
//                cls.getMethod("onCalled", SlashCommandEvent.class, String[].class, MessageChannel.class).invoke(clazz, event, command, event.getChannel());
//            }
                if (Discordbot.helpCommands.contains(command) && event.getSubcommandName() != null && Discordbot.isSlashMode)
                    if (Discordbot.slashCommands.containsKey(command)) {
                        Class<?> cls = classLoader.loadClass(Discordbot.helpHandler.getClass().getName());
                        Object clazz = cls.getDeclaredConstructor().newInstance();
                        cls.getMethod("onCalled", String.class, String.class, String[].class, String.class, MessageChannel.class).invoke(clazz, Discordbot.slashCommands.get(command).name(), Discordbot.slashCommands.get(command).helpMessages(), Discordbot.slashCommands.get(command).usage(), command, event.getChannel());
                    }

            } catch (InvocationTargetException e) {
                classLoader = new YSTClassLoader();
                try {
                    Class<?> cls = classLoader.loadClass(Discordbot.slashCommands.get(command).getClass().getName());
                    Object clazz = cls.getDeclaredConstructor().newInstance();
                    cls.getMethod("onCalled", SlashCommandInteractionEvent.class, String.class, MessageChannel.class).invoke(clazz, event, event.getSubcommandName(), event.getChannel());
                } catch (Exception e1) {
                    String er = e.getCause().getClass().getName() + ": " + e.getCause().getMessage() + "\n";
                    for (StackTraceElement l : e.getCause().getStackTrace())
                        er += "   " + l.toString() + "\n";
                    Logger.getLoggerByName("System").err(er);
                }
            } catch (Exception e) {
                err(e.getClass().getName(), e.getMessage());
            }
        }
    }

    /**
     *
     * @param cause
     * @param message
     */
    private void err(String cause, String message){
        if(Logger.getLoggerByName("System") == null) {
            new LoggingBuilder().build("System");
        }
        Logger.getLoggerByName("System").err("YST API ERROR\n" +
                " - Please report this error to Github\n" +
                "\n" +
                "Message: "+message+"\n" +
                "Cause: "+cause);
    }

    /**
     * You don't need to come here!
     * <p>
     * If you want know about this then,
     * <p>
     * this code is handling buttons
     *
     * @version Beta 0.0.1.6
     * @since Beta 0.0.0.5
     * **/
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        try {
            if (Discordbot.Buttons.containsKey(event.getButton().getId())) {
                Class<?> cls = new YSTClassLoader().loadClass(Discordbot.Buttons.get(event.getButton().getId()).getClass().getName());
                Object clazz = cls.getDeclaredConstructor().newInstance();
                cls.getMethod("onCalled", ButtonInteractionEvent.class, User.class, Button.class, MessageChannel.class).invoke(clazz, event, event.getUser(), event.getButton(), event.getChannel());
            }
        } catch (InvocationTargetException e){
            classLoader = new YSTClassLoader();
            try {
                Class<?> cls = new YSTClassLoader().loadClass(Discordbot.Buttons.get(event.getButton().getId()).getClass().getName());
                Object clazz = cls.getDeclaredConstructor().newInstance();
                cls.getMethod("onCalled", ButtonInteractionEvent.class, User.class, Button.class, MessageChannel.class).invoke(clazz, event, event.getUser(), event.getButton(), event.getChannel());
            }catch (Exception e1){
                err(e.getClass().getName(), e.getMessage());
            }
        }catch (Exception e){err(e.getClass().getName(), e.getMessage());}
    }
}
