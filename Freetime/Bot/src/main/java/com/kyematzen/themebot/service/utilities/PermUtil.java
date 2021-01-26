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

package com.kyematzen.themebot.service.utilities;

public class PermUtil {

    public static String getSimplifiedPermissions(String permissions) {

        permissions = permissions.replace("CREATE_INSTANT_INVITE", "CII");
        permissions = permissions.replace("KICK_MEMBERS", "KM");
        permissions = permissions.replace("BAN_MEMBERS", "BM");
        permissions = permissions.replace("KICK_MEMBERS", "KM");
        permissions = permissions.replace("ADMINISTRATOR", "ADM");
        permissions = permissions.replace("MANAGE_CHANNEL", "MC");
        permissions = permissions.replace("MANAGE_SERVER", "MS");
        permissions = permissions.replace("MESSAGE_ADD_REACTION", "MAR");
        permissions = permissions.replace("VIEW_AUDIT_LOGS", "VAL");
        permissions = permissions.replace("PRIORITY_SPEAKER", "PS");
        permissions = permissions.replace("VIEW_GUILD_INSIGHTS", "VGI");
        permissions = permissions.replace("VIEW_CHANNEL", "VCH");
        permissions = permissions.replace("MESSAGE_READ", "MR");
        permissions = permissions.replace("MESSAGE_WRITE", "MW");
        permissions = permissions.replace("MESSAGE_TTS", "MTTS");
        permissions = permissions.replace("MESSAGE_MANAGE", "MM");
        permissions = permissions.replace("MESSAGE_EMBED_LINKS", "ME");
        permissions = permissions.replace("MESSAGE_ATTACH_FILES", "MAA");
        permissions = permissions.replace("MESSAGE_HISTORY", "MH");
        permissions = permissions.replace("MESSAGE_MENTION_EVERYONE", "MME");
        permissions = permissions.replace("MESSAGE_EXT_EMOJI", "MEE");
        permissions = permissions.replace("VOICE_STREAM", "VSTR");
        permissions = permissions.replace("VOICE_CONNECT", "VC");
        permissions = permissions.replace("VOICE_SPEAK", "VS");
        permissions = permissions.replace("VOICE_MUTE_OTHERS", "VMUO");
        permissions = permissions.replace("VOICE_DEAF_OTHERS", "VDO");
        permissions = permissions.replace("VOICE_MOVE_OTHERS", "VMO");
        permissions = permissions.replace("VOICE_USE_VAD", "VUV");
        permissions = permissions.replace("NICKNAME_CHANGE", "NC");
        permissions = permissions.replace("NICKNAME_MANAGE", "NM");
        permissions = permissions.replace("MANAGE_ROLES", "MR");
        permissions = permissions.replace("MANAGE_PERMISSIONS", "MP");
        permissions = permissions.replace("MANAGE_WEBHOOKS", "MWH");
        permissions = permissions.replace("MANAGE_EMOTES", "ME");
        permissions = permissions.replace("UNKNOWN", "UNK");

        return permissions;
    }
}