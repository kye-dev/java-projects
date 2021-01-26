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

package com.kyematzen.themebot.service.server;

import com.kyematzen.themebot.service.theme.Theme;

public class Server implements SimpleServer {

    private final long serverID;
    private long deletionTime;
    private Theme theme;

    public Server(long serverID) {
        this.serverID = serverID;
    }

    /**
     * This grabs the long amount of when the bot will be removed from server if
     *
     * @return deletion time of bot on server.
     */
    @Override
    public long getDeletionTime() {
        return this.deletionTime;
    }

    @Override
    public long getID() {
        return this.serverID;
    }

    @Override
    public Theme getTheme() {
        return this.theme;
    }

    public void setDeletionTime(long deletionTime) {
        this.deletionTime = deletionTime;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}