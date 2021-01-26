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

package com.kyematzen.themebot.service.listener;

import com.kyematzen.themebot.service.bot.SimpleBot;
import com.kyematzen.themebot.service.utilities.EmbedUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import org.awaitility.Awaitility;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class ReadyListener extends ListenerAdapter {
    private final SimpleBot bot;
    private boolean triggered;

    public ReadyListener(SimpleBot bot) {
        this.bot = bot;
    }

    @Override
    public void onReady(@NotNull ReadyEvent readyEvent) {
        if (!this.triggered) {
            this.triggered = true;
            // Set bot to be enabled.
            this.bot.setConnected(true);

            // Wait for initialization of bot for a maximum of five minutes.
            Awaitility.await().atMost(5, TimeUnit.MINUTES).until(this.bot::isInitialized);

            // Call bot interface method to start bot.
            this.bot.onConnect(readyEvent.getJDA());

            // Create embed builder for command.
            EmbedBuilder embedBuilder = EmbedUtil.getEmbedBuilder();

            embedBuilder.addField("How to import a theme?", "To import a theme, drag the theme file into discord to begin process.", false);
            embedBuilder.addField("Commands", "```" +
                    "\n!theme export - Exports server (cooldown 3h)." +
                    "\n!theme cancel - Stops installation process.```", false);

            // Check if channel exists.
            for (Guild guild : readyEvent.getJDA().getGuilds()) {
                List<TextChannel> channelList = guild.getTextChannelsByName("theme", false);

                // Check if there is a channel named theme yet.
                if (!channelList.isEmpty()) {
                    channelList.get(0).delete().queue();
                }

                // Grab roles from server that have administrator permissions.
                Optional<Role> adminRoles = guild.getRoles().stream().filter(role -> role.hasPermission(Permission.ADMINISTRATOR)).findAny();
                // Check if there are any roles with administrator.
                if (adminRoles.isPresent()) {
                    // Grab and create text channel.
                    ChannelAction<TextChannel> textChannel = guild.createTextChannel("theme");

                    // If there is at least one role with permission, allow it to see channel.
                    adminRoles.ifPresent(role -> textChannel.addPermissionOverride(role, null, EnumSet.of(Permission.VIEW_CHANNEL)));

                    // Create channel and send embed message of help information.
                    textChannel.complete().sendMessage(embedBuilder.build()).queue();
                }
            }
        }
    }
}