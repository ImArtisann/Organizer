package org.example;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class chatListener extends ListenerAdapter {


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //Change ID to lukes this is mine
        if(event.getAuthor().getId().equals("895670016165433355")){
            String[] ta = event.getMessage().getContentDisplay().split(" ");
            if(ta[0].equalsIgnoreCase("+ta")){
                List<Message.Attachment> image = event.getMessage().getAttachments();
                if(!image.isEmpty()){
                    String messageID = event.getMessageId();
                    CompletableFuture<File> future = image.get(0).downloadToFile( "ta.png");
                    future.thenAccept(file -> {
                        event.getJDA().getGuildById("1066874960779431976").getTextChannelById("1073397470421319772")
                                .sendMessage(event.getMessage().getContentDisplay()).addFile(new File("ta.png")).queue();
                    });
                    future.exceptionally(error -> { // handle possible errors
                        error.printStackTrace();
                        return null;
                    });

                }else{
                    event.getJDA().getGuildById("1066874960779431976").getTextChannelById("1073397470421319772")
                            .sendMessage(event.getMessage().getContentDisplay()).queue();
                }
            }
        }else if(event.getAuthor().getId().equals("174739297939750912")){ //Lieutenant
            String[] ta = event.getMessage().getContentDisplay().split(" ");
            if(ta[0].equalsIgnoreCase("+ta")){
                List<Message.Attachment> image = event.getMessage().getAttachments();
                if(!image.isEmpty()){
                    String messageID = event.getMessageId();
                    CompletableFuture<File> future = image.get(0).downloadToFile("taa.png");
                    future.thenAccept(file -> {
                        event.getJDA().getGuildById("1066874960779431976").getTextChannelById("1074142744399847504")
                                .sendMessage(event.getMessage().getContentDisplay()).addFile(new File("taa.png")).queue();
                    });
                    future.exceptionally(error -> { // handle possible errors
                        error.printStackTrace();
                        return null;
                    });

                }else{
                    event.getJDA().getGuildById("1066874960779431976").getTextChannelById("1074142744399847504")
                            .sendMessage(event.getMessage().getContentDisplay()).queue();
                }
            }
        }
    }


}
