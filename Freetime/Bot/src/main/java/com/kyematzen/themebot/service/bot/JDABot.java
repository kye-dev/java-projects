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

package com.kyematzen.themebot.service.bot;

import com.kyematzen.themebot.ThemeBot;

import com.kyematzen.themebot.listener.GuildJoinListener;
import com.kyematzen.themebot.listener.GuildLeaveListener;
import com.kyematzen.themebot.listener.GuildMessageReceivedListener;
import com.kyematzen.themebot.service.listener.ReadyListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.slf4j.Logger;

import javax.security.auth.login.LoginException;

public abstract class JDABot implements SimpleBot {

    public static final Logger logger = JDALogger.getLog(ThemeBot.class);
    private boolean connected;
    private boolean initialized;

    /**
     * Registers bot into API using JDABuilder.
     *
     * @param bot instance of core class.
     * @param token to register into Discord's Bot API.
     */
    @Override
    public void initialize(ThemeBot bot, String token) {

        try {
            JDABuilder jdaBuilder = JDABuilder.createDefault(token)
                    .enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .addEventListeners(new ReadyListener(this))
                    .setActivity(Activity.playing("themes | !theme"))
                    .addEventListeners(new GuildJoinListener(bot), new GuildLeaveListener(bot), new GuildMessageReceivedListener(bot));
            jdaBuilder.build();
        } catch (LoginException e) {
            logger.error("Unable to log into Discord, the following error occurred:", e);
        }

        this.initialized = true;
    }

    /**
     * Checks if the bot is connected to API.
     * @return boolean if API is connected.
     */
    public boolean isConnected() {
        return this.connected;
    }

    /**
     * Updates variable for bot connection to API.
     * @param connected of whether or not API is connected.
     */
    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    /**
     * Sends information whether or not the bot was initialized.
     * @return boolean if bot is running.
     */
    @Override
    public boolean isInitialized() {
        return this.initialized;
    }

    /**
     * Allows the ability to grab logger for bot.
     * @return local instance of Logger that was initialized.
     */
    public Logger getLogger() {
        return logger;
    }
}
