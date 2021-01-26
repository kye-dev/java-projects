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

package com.kyematzen.themebot;

import com.kyematzen.themebot.cache.ServerCache;
import com.kyematzen.themebot.commands.CoreCommand;
import com.kyematzen.themebot.service.bot.JDABot;
import net.dv8tion.jda.api.JDA;
import org.awaitility.Awaitility;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ThemeBot extends JDABot {

    private ServerCache serverCache;
    private CoreCommand coreCommand;
    private JDA jda;

    public void load() {
        // Attempt to initialize into Discord's API.
        initialize(this, "botToken");

        // Wait up to 30 seconds for connection of bot.
        Awaitility.await().atMost(30, TimeUnit.SECONDS).until(this::isConnected);

        Runtime.getRuntime().addShutdownHook(new ShutdownHook(this));
    }

    @Override
    public void onConnect(JDA jda) {
        this.jda = jda;

        getLogger().info("Successfully connected into Discord.");

        // Begin server cache import, and create core command.

        serverCache = new ServerCache(this);
        coreCommand = new CoreCommand(this);

        // Deletes temporary theme files on startup of bot that take up storage.
        int fileCount = 0;
        for (File localFile : new File("temporary-themes").listFiles()) {
            // Attempts to delete file.
            if (localFile.delete()) {
                fileCount++;
            }
        }

        getLogger().info("Deleted " + fileCount + " temporary theme files.");
    }

    public void unload() {
        this.serverCache.saveServers();
    }

    public JDA getJDA() {
        return this.jda;
    }

    public ServerCache getServerCache() {
        return this.serverCache;
    }

    public CoreCommand getCoreCommand() {
        return this.coreCommand;
    }
}