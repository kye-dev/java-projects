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

package com.kyematzen.themebot.service.theme;

import com.kyematzen.themebot.Main;
import net.dv8tion.jda.api.entities.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Timer;

public class Theme implements SimpleTheme {

    private final String name;
    private final String version;
    private final String author;
    private long guildID;
    private int rolesIndex;
    private int voiceChannelsIndex;
    private int textChannelsIndex;
    private List<String> rolesList;
    private List<String> voiceChannelsList;
    private List<String> textChannelsList;
    private Timer timer;

    public Theme(String name, String version, String author) {
        this.name = name;
        this.version = version;
        this.author = author;

        this.rolesIndex = 0;
        this.voiceChannelsIndex = 0;
        this.textChannelsIndex = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public double getProgress() {
        return 0;
    }

    @Override
    public void startProgress() {
        @NotNull Guild guild = Objects.requireNonNull(Main.getInstance().getJDA().getGuildById(guildID));

        for (TextChannel textChannel : guild.getTextChannels()) {
            if (!textChannel.getName().equals("theme")) {
                textChannel.delete().queue();
            }
        }

        for (VoiceChannel voiceChannel : guild.getVoiceChannels()) {
            voiceChannel.delete().queue();
        }

        for (Category category : guild.getCategories()) {
            category.delete().queue();
        }

        for (Role role : guild.getRoles()) {
            System.out.println("Role: " + role.getName());
            if (!role.getName().equals("ThemeBot") && !role.getName().equals("@everyone")) {
                System.out.println("Role Deleted: " + role.getName());
                role.delete().queue();
            }
        }
    }

    @Override
    public void stopProgress() {
        this.timer.cancel();
    }

    public void setGuildID(long guildID) {
        this.guildID = guildID;
    }

    public void setRoles(List<String> rolesList) {
        this.rolesList = rolesList;
    }

    public void setRolesIndex(int rolesIndex) {
        this.rolesIndex = rolesIndex;
    }

    public int getRolesIndex() {
        return this.rolesIndex;
    }

    public String getRolesCompleted() {
        return (getRolesIndex() == 0 ? 0 : getRolesIndex()) + " out of " + this.rolesList.size();
    }

    public void setVoiceChannelsList(List<String> voiceChannelsList) {
        this.voiceChannelsList = voiceChannelsList;
    }

    public void setVoiceChannelsIndex(int voiceChannelsIndex) {
        this.voiceChannelsIndex = voiceChannelsIndex;
    }

    public String getVoiceChannelsCompleted() {
        return (this.voiceChannelsIndex == 0 ? 0 : this.voiceChannelsIndex) + " out of " + this.voiceChannelsList.size();
    }

    public void setTextChannelsIndex(int textChannelsIndex) {
        this.textChannelsIndex = textChannelsIndex;
    }

    public void setTextChannelsList(List<String> textChannelsList) {
        this.textChannelsList = textChannelsList;
    }

    public String getTextChannelsCompleted() {
        return (this.textChannelsIndex == 0 ? 0 : this.textChannelsIndex) + " out of " + this.textChannelsList.size();
    }

}