/*
 * Copyright 2021, Kye Matzen, http://kyematzen.com
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
 * THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.kyematzen.themebot.listener;

import com.kyematzen.themebot.ThemeBot;
import com.kyematzen.themebot.service.utilities.AESUtil;
import com.kyematzen.themebot.service.utilities.EmbedUtil;
import com.kyematzen.themebot.service.utilities.SaltUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang3.Validate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GuildMessageReceivedListener extends ListenerAdapter {

    private final ThemeBot bot;

    public GuildMessageReceivedListener(ThemeBot bot) {
        this.bot = bot;
    }

    /**
     * In this event, we determine whether or not our core command is being executed.
     *
     * @param event An event containing information about a {@link Message Message} that was sent in a channel.
     */
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        Message message = event.getMessage();

        String messageStr = message.getContentDisplay();

        TextChannel textChannel = event.getChannel();

        if (!textChannel.getName().equals("theme")) return; // If text channel is not theme-test, don't continue.

        User author = event.getAuthor();

        if (author.isBot()) return; // If user is a bot, don't continue.
        if (!message.isFromType(ChannelType.TEXT)) return; // If message isn't from a text channel, don't continue.
        if (message.isWebhookMessage()) return; // If message is a web hook, don't continue.

        Member member = event.getMember(); // Guild member that sent the message, contains specific guild information about user.

        Validate.notNull(member, "member cannot be null!");

        // Check if the member sending a message has administrator.
        if (!member.hasPermission(Permission.ADMINISTRATOR)) return;

        Guild guild = event.getGuild(); // Guild that message was sent in.
        Member selfMember = guild.getSelfMember(); // Bot member that is in server.

        // Checks if bot is administrator.
        if (!selfMember.hasPermission(Permission.ADMINISTRATOR)) return;

        // If message doesn't start with !theme, check if it is a theme file, else return.
        if (!messageStr.startsWith("!theme")) {
            // Grab attachments if any if the message isn't a command.
            List<Message.Attachment> attachmentList = message.getAttachments();

            // Checks if there are any attachments, if not don't continue.
            if (attachmentList.size() == 0) {
                return;
            }

            // Grab first attachment only.
            Message.Attachment attachment = attachmentList.get(0);

            // If attachment is not a theme, don't continue.
            if (attachment.isImage() || attachment.isSpoiler() || attachment.isVideo()) {
                return;
            }

            if (attachment.getFileExtension() != null && attachment.getFileExtension().equals("txt")) { // If attachment file extension is null, or extension is not a text document, don't continue.
                long serverID = event.getGuild().getIdLong();

                if (bot.getServerCache().hasServer(serverID)) {
                    message.delete().queue();

                    EmbedBuilder embedBuilder = EmbedUtil.getEmbedBuilder();

                    embedBuilder.addField("Server", "A theme is already importing, please cancel current to import.", false);
                    textChannel.sendMessage(embedBuilder.build()).queue();
                    return;
                }

                String temporaryName = null;
                boolean renamed = false;

                try {
                    File file = attachment.downloadToFile().get();

                    // Create a temporary random name.
                    temporaryName = SaltUtil.getRandomSalt();

                    renamed = file.renameTo(new File("temporary-themes/" + temporaryName + ".txt"));
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

                if (renamed) {
                    List<String> decryptedList = AESUtil.decryptList(bot, readFileLines(new File("temporary-themes/" + temporaryName + ".txt")), "$($&*@#*$");
                    boolean valid = !decryptedList.isEmpty();

                    EmbedBuilder embedBuilder = EmbedUtil.getEmbedBuilder();

                    // Deletes the file
                    message.delete().queue();

                    if (!valid) {
                        embedBuilder.addField("Server", "Failed to import uploaded file.", false);
                        embedBuilder.addField("How to import?", "Make sure that the file came from our application before attempting to upload.", false);
                        textChannel.sendMessage(embedBuilder.build()).queue();
                        return;
                    }

                    // Lets user know that the bot has begun to register their file.
                    embedBuilder.addField("Server", "Please wait while we begin theme process...", false);

                    // Creates local message id that is used to update about the server information.
                    textChannel.sendMessage(embedBuilder.build()).queue();

                    // Implement locking channel

                    bot.getServerCache().createServer(serverID, textChannel, decryptedList);
                }
            }

            return;
        }

        String[] arguments = messageStr.split(" "); // Split message by spaces allowing arguments.

        bot.getCoreCommand().onCommand(author, textChannel, guild, selfMember, arguments);
    }

    /**
     * Reads all data from file and returns list encrypted data if possible.
     *
     * @param file Temporary file name that was generated
     * @return encrypted list data from file.
     */
    private List<String> readFileLines(File file) {
        Validate.notNull(file, "File cannot be null!");

        List<String> stringList = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                // process the line.
                stringList.add(line);
            }
            // line is not visible here.
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return stringList;
    }
}