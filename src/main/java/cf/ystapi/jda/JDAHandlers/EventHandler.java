package cf.ystapi.jda.JDAHandlers;

import cf.ystapi.jda.DiscordBot;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class EventHandler extends ListenerAdapter {
    DiscordBot Discordbot;
    String prefix;
    JShell jShell;
    public EventHandler(DiscordBot Discordbot, String prefix){
        this.Discordbot = Discordbot;
        this.prefix = prefix;
        JShell.Builder jShellBuilder = JShell.builder();
        this.jShell = jShellBuilder.build();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String command = event.getMessage().getContentRaw().replaceFirst(prefix, "");
        try{
            if(Discordbot.commands.containsKey(command.split(" ")[0]))
                Discordbot.commands.get(command.split(" ")[0]).onCalled(event, command.split(" "), event.getChannel());
            if(Discordbot.RunnableCommands.containsKey(command.split(" ")[0]))
                Discordbot.RunnableCommands.get(command.split(" ")[0]).run(event, command.split(" "), event.getChannel());
            if(event.getAuthor().getId().equals(Discordbot.Owner)){
                if(command.startsWith("ystdok")){
                    if(command.split(" ").length > 1){
                        System.out.println(command.split(" ")[1]);
                        String input = command.replaceFirst(command.split(" ")[0], "").replaceFirst(command.split(" ")[1], "").replaceFirst("  ", "");
                        if(command.split(" ")[1].equals("sh")){
                            System.out.println(input);
                            new Thread(() -> {
                                long started = System.currentTimeMillis();
                                try {

                                    ProcessBuilder builder = new ProcessBuilder(
                                            System.getProperty("os.name").startsWith("Windows") ? "cmd.exe" : "/bin/bash", System.getProperty("os.name").startsWith("Windows") ? "/c" : "-c", input);

                                    Process p = builder.start();
                                    p.waitFor();
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                                    StringBuilder st = new StringBuilder("");
                                    while (reader.ready()) {
                                        st.append(reader.readLine()).append("\n");
                                    }
                                    event.getChannel().sendMessageEmbeds(new EmbedBuilder().setTitle("YSTDOK")
                                            .addField("Input", "`"+input+"`", false)
                                            .addField("Output", "`"+st+"`", false)
                                            .addField("Real Time", "`"+(System.currentTimeMillis()-started)+"ms`", false)
                                            .setFooter("Requested type: sh").build()).queue();
                                } catch (IOException | InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }).start();
                        }else if(command.split(" ")[1].equals("java")){
                            new Thread(() -> {
                                long started = System.currentTimeMillis();
                                List<SnippetEvent> events = jShell.eval(input);
                                SnippetEvent ev = events.get(events.size()-1);
                                event.getChannel().sendMessageEmbeds(new EmbedBuilder().setTitle("YSTDOK")
                                        .addField("Input", "`"+input+"`", false)
                                        .addField("Output(JShell Output)", "`"+ ((ev.value() == null || ev.value().isBlank()) ? "N/A" : ev.value()) +"`", false)
                                        .addField("Real Time", "`"+(System.currentTimeMillis()-started)+"ms`", false)
                                        .setFooter("Requested type: Java").build()).queue();
                            }).start();
                        }
                    }else{
                        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
                        String timestamp = String.valueOf((System.currentTimeMillis()-rb.getUptime())/1000L);
                        event.getChannel().sendMessage("YST DOK Beta v0.1.2, JDA `5.0.0-alpha.1`, `Java "+System.getProperty("java.version")+"` on `"+System.getProperty("os.name")+"`\n" +
                                "Process started at <t:"+timestamp+":R>, bot was ready at <t:"+timestamp+":R>.\n\n" +
                                "Using "+String.format("%99.02f", ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1048576f)).replaceAll(" ", "")+"MB at this process.\n" +
                                "Running on PID "+rb.getPid()+".\n\n" +
                                "This bot is " + event.getJDA().getShardInfo()+" and running in "+event.getJDA().getGuilds().size()+" guild(s). " +
                                "Can see "+event.getJDA().getGuilds().size()+" guild(s) and "+event.getJDA().getUsers().size()+" user(s) in this client.\n" +
                                "Average websocket latency: "+event.getJDA().getGatewayPing()+"ms").queue();
                    }
                }
            }
        }catch (NullPointerException e){

        }
    }
}
