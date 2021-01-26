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

package com.kyematzen.themebot.commands;

import com.kyematzen.themebot.ThemeBot;
import com.kyematzen.themebot.service.command.Command;
import com.kyematzen.themebot.service.server.Server;
import com.kyematzen.themebot.service.utilities.AESUtil;
import com.kyematzen.themebot.service.utilities.EmbedUtil;
import com.kyematzen.themebot.service.utilities.PermUtil;
import com.kyematzen.themebot.service.utilities.TimeUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CoreCommand implements Command {

    private final ThemeBot bot;

    public CoreCommand(ThemeBot bot) {
        this.bot = bot;
    }

    @Override
    public void onCommand(User user, TextChannel textChannel, Guild guild, Member botSelf, String[] args) {
        EmbedBuilder embedBuilder = EmbedUtil.getEmbedBuilder();

        // Checks if entered information is only !theme
        if (args.length == 1) {
            embedBuilder.addField("How to import a theme?", "To import a theme, drag the theme file into discord to begin process.", false);
            embedBuilder.addField("Commands", "```" +
                    "\n!theme export - Exports server (cooldown 3h)." +
                    "\n!theme cancel - Stops installation process.```", false);

            textChannel.sendMessage(embedBuilder.build()).queue();
            return;
        }

        String argument = args[1].toLowerCase();

        switch (argument) {
            case "export":
                // Check for last export from stored file from last export and compare to current time and see if greater than 3 hours since last.
//                if (args.length == 2) {
//                    embedBuilder.addField("Cooldown", "You must wait 3 hours from last export to do another!", false);
//
//                    textChannel.sendMessage(embedBuilder.build()).queue();
//                    break;
//                }

                // Data collection.
                List<String> data = new ArrayList<>();

                List<GuildChannel> channelList = guild.getChannels();
                long estimatedTime = channelList.size() * 250L;
                long start = System.currentTimeMillis();

                embedBuilder.addField("Server", "Exporting... (est. " + TimeUtil.getFormattedTime(estimatedTime) + ")", false);

                textChannel.sendMessage(embedBuilder.build()).queue(message -> {

                    // Loop through all roles
                    for (Role role : guild.getRoles()) {
                        data.add("R:" + role.getName() + ",Color:" + role.getColorRaw() + ",Permissions:" + PermUtil.getSimplifiedPermissions(role.getPermissionsExplicit().toString()));
                    }

                    // Loop through all channels
                    for (GuildChannel localChannel : channelList) {
                        String localLine = "";
                        if (localChannel.getType() == ChannelType.VOICE) {
                            VoiceChannel localVoiceChannel = (VoiceChannel) localChannel;
                            localLine += "VC:" + localVoiceChannel.getName() +
                                    ",P-" + (localChannel.getParent() == null ? "None" : localChannel.getParent().getName()) +
                                    ",UL-" + localVoiceChannel.getUserLimit() +
                                    ",BR-" + localVoiceChannel.getBitrate();
                        } else if (localChannel.getType() == ChannelType.TEXT) {
                            TextChannel localTextChannel = (TextChannel) localChannel;
                            if (localTextChannel.getName().equals("theme")) continue;
                            localLine += "TC:" + localTextChannel.getName() +
                                    ",P-" + (localChannel.getParent() == null ? "None" : localChannel.getParent().getName()) +
                                    ",T-" + (localTextChannel.getTopic() == null ? "None" : localTextChannel.getTopic()) +
                                    ",NSFW-" + localTextChannel.isNSFW() +
                                    ",SM-" + localTextChannel.getSlowmode();
                        }

                        List<PermissionOverride> permissionOverrideList = localChannel.getRolePermissionOverrides();
                        if (!permissionOverrideList.isEmpty()) {
                            StringBuilder permissions = new StringBuilder("$PO-");
                            for (PermissionOverride permissionOverride : permissionOverrideList) {
                                permissions.append("R:").append(permissionOverride.getRole().getName()).append(",Allowed:").append(permissionOverride.getAllowed().toString()).append(",Disallowed:").append(permissionOverride.getDenied().toString());
                            }

                            localLine += permissions.toString();
                        }

                        if (!localLine.isEmpty()) {
                            data.add(PermUtil.getSimplifiedPermissions(localLine));
                        }
                    }

                    data.add("IC:" + guild.getIconUrl());

                    List<String> encrypted = AESUtil.encryptList(bot, data, "$($&*@#*$");

                    File file = new File("servers/" + guild.getId() + ".txt");
                    boolean valid = !encrypted.isEmpty();

                    embedBuilder.clearFields();

                    // Checks if the encryption worked.
                    if (valid) {
                        // Checks if we previously have had data of this server on file.
                        if (file.exists()) {
                            file.delete();
                        }

                        try {
                            Files.write(file.toPath(), encrypted);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        embedBuilder.addField("Server", "Export Complete (in " + TimeUtil.getFormattedTime(System.currentTimeMillis() - start) + ")", false);

                        textChannel.sendFile(file).queueAfter(estimatedTime, TimeUnit.MILLISECONDS);

                        // Add to file cache of last export time (LONG)
                    } else {
                        embedBuilder.addField("Server", "Export Failed (in " + TimeUtil.getFormattedTime(System.currentTimeMillis() - start) + ")", false);
                    }

                    message.editMessage(embedBuilder.build()).queueAfter(estimatedTime, TimeUnit.MILLISECONDS);
                });

                break;
            case "cancel":
                long serverID = guild.getIdLong();

                if (bot.getServerCache().hasServer(serverID)) {
                    bot.getLogger().info("Stop installation of theme for " + guild.getName() + ".");

                    Server server = bot.getServerCache().getServer(serverID);

                    embedBuilder.addField("Server", "Installation for theme " + server.getTheme().getName() + " stopped.", false);

                    server.getTheme().stopProgress();

                    textChannel.sendMessage(embedBuilder.build()).queue();
                    return;
                }

                embedBuilder.addField("Server", "You aren't importing any theme.", false);

                textChannel.sendMessage(embedBuilder.build()).queue();

                break;
        }
    }
}