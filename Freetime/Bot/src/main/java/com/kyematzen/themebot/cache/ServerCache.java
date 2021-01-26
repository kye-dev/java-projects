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

package com.kyematzen.themebot.cache;

import com.kyematzen.themebot.ThemeBot;
import com.kyematzen.themebot.service.server.Server;
import com.kyematzen.themebot.service.theme.Theme;
import com.kyematzen.themebot.service.utilities.EmbedUtil;
import com.kyematzen.themebot.service.utilities.SaltUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.RestAction;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ServerCache {

    private final ThemeBot bot;
    private final Map<Long, Server> serverCache = new HashMap<>();

    public ServerCache(ThemeBot bot) {
        this.bot = bot;

        List<Long> longList = new ArrayList<>();

        for (Guild guild : bot.getJDA().getGuilds()) {
            longList.add(guild.getIdLong());
        }

        loadServers(longList);
    }

    public void loadServers(List<Long> guildIDs) {
        for (Long guildID : guildIDs) {
            this.bot.getLogger().info("Checking if server " + guildID + " has theme data...");
        }

        this.bot.getLogger().info(this.serverCache.size() + " servers loaded.");
    }

    public boolean hasServer(long serverID) {
        return this.serverCache.containsKey(serverID);
    }

    public void createServer(long serverID, TextChannel textChannel, List<String> data) {
        Server server = new Server(serverID);

        // Create theme object, and load information from file.
        Theme theme = convertData(data, textChannel.getGuild().getIdLong());

        // Set theme after created.
        server.setTheme(theme);

        EmbedBuilder embedBuilder = EmbedUtil.getEmbedBuilder();

        embedBuilder.addField("Server", "Please confirm within 10 seconds to begin installation.", false);

        textChannel.sendMessage(embedBuilder.build()).queueAfter(1, TimeUnit.SECONDS, message -> {
            message.addReaction("U+2705").queue();
        });

        RestAction<List<Message>> message = textChannel.getHistory().retrievePast(1);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Message localMessage = message.complete().get(0);

                // Clears messages in channel.
                List<Message> messages = textChannel.getHistory().retrievePast(5).complete();
                textChannel.deleteMessages(messages).complete();

                if (localMessage != null && localMessage.getReactions().size() > 0) {

                    MessageReaction messageReaction = localMessage.getReactions().get(0);

                    if (messageReaction.getCount() > 1) {

                        // Add server to temporary server cache data.
                        serverCache.put(serverID, server);

                        // Create embed builder for command.
                        EmbedBuilder embedBuilder = EmbedUtil.getEmbedBuilder();

                        embedBuilder.setTitle("Theme Installation");
                        embedBuilder.addField("Name", theme.getName(), false);
                        embedBuilder.addField("Author", theme.getAuthor(), true);
                        embedBuilder.addField("Version", theme.getVersion(), true);
                        embedBuilder.addField("Progress", "0%", true);
                        embedBuilder.addField("Roles", theme.getRolesCompleted(), false);
                        embedBuilder.addField("Voice Channels", theme.getVoiceChannelsCompleted(), false);
                        embedBuilder.addField("Text Channels", theme.getTextChannelsCompleted(), false);

                        textChannel.sendMessage(embedBuilder.build()).queue();

                        server.getTheme().startProgress();
                    } else {
                        embedBuilder.clearFields();
                        embedBuilder.addField("Server", "Failed to confirm installation.", false);

                        textChannel.sendMessage(embedBuilder.build()).queue();
                    }
                }
            }
        };

        Timer timer = new Timer("MyTimer"); //create a new Timer

        timer.schedule(timerTask, 10000);
    }

    private Theme convertData(List<String> data, long guildID) {
        String themeInfo = getLine(data, "T:");

        Theme theme;

        if (!(themeInfo.equals("0"))) {
            theme = new Theme("", "", "");
        } else {
            theme = new Theme(SaltUtil.getRandomSalt(), "1.0.0", "None");
        }

        List<String> roleList = new ArrayList<>();

        for (int i = data.size() - 1; i-- > 0; ) {
            if (data.get(i).startsWith("R:")) {
                roleList.add(data.remove(i));
            }
        }

        List<String> textChannelList = new ArrayList<>();

        for (int i = data.size() - 1; i-- > 0; ) {
            if (data.get(i).startsWith("TC:")) {
                textChannelList.add(data.remove(i));
            }
        }

        List<String> voiceChannelList = new ArrayList<>();

        for (int i = data.size() - 1; i-- > 0; ) {
            if (data.get(i).startsWith("VC:")) {
                voiceChannelList.add(data.remove(i));
            }
        }

        theme.setGuildID(guildID);
        theme.setRoles(roleList);
        theme.setVoiceChannelsList(voiceChannelList);
        theme.setTextChannelsList(textChannelList);

        return theme;
    }

    public String getLine(List<String> data, String targetIdentifier) {
        for (String line : data) {
            if (line.startsWith(targetIdentifier)) {
                return line;
            }
        }
        return "0";
    }


    public void saveServers() {
        for (Server server : serverCache.values()) {
            bot.getLogger().info("Saved server: " + server.getID());
        }
    }

    public Server getServer(long serverID) {
        return serverCache.get(serverID);
    }
}
