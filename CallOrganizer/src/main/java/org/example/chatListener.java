package org.example;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class chatListener extends ListenerAdapter {

    public String guild = "1066874960779431976";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("./ids.txt"));
            String line = reader.readLine();

            while (line != null) {
                String args[] = line.split(" ");
                if(event.getAuthor().getId().equalsIgnoreCase(args[0])) {
                    String[] ta = event.getMessage().getContentDisplay().split(" ");
                    if (ta[0].equalsIgnoreCase("+ta")) {
                        postMessage(event,args[1]);
                        break;
                    }
                }else {
                    line = reader.readLine();
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void postMessage(MessageReceivedEvent event, String textChannel){

        List<Message.Attachment> image = event.getMessage().getAttachments();
        if(!image.isEmpty()){
            CompletableFuture<File> future = image.get(0).downloadToFile( "ta.png");
            future.thenAccept(file -> {
                event.getJDA().getGuildById(guild).getTextChannelById(textChannel)
                        .sendMessage(event.getMessage().getContentDisplay()).addFile(new File("ta.png")).queue();
            });
            future.exceptionally(error -> { // handle possible errors
                error.printStackTrace();
                return null;
            });

        }else{
            event.getJDA().getGuildById(guild).getTextChannelById(textChannel)
                    .sendMessage(event.getMessage().getContentDisplay()).queue();
        }

    }


}
