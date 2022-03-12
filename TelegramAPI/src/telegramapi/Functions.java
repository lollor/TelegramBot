/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegramapi;
import telegramapi.location.Location;
import telegramapi.responses.*;
import telegramapi.mainclasses.*;
/**
 *
 * @author Lorenzo
 */
public class Functions {
    public static UpdatesResponse GetUpdatesResponse() {
        return JSONLibrary.FromJSON("https://api.telegram.org/bot5176466546:AAFohyNASLS-j3NP7rVtrzyNC0U66LK-Bl4/getUpdates", UpdatesResponse.class, true);
    }

    public static MeResponse GetMeResponse() {
        return JSONLibrary.FromJSON("https://api.telegram.org/bot5176466546:AAFohyNASLS-j3NP7rVtrzyNC0U66LK-Bl4/getMe", MeResponse.class, true);
    }

    public static SentMessageResponse SendMessage(String text, long chat_id) {
        return JSONLibrary.FromJSON("https://api.telegram.org/bot5176466546:AAFohyNASLS-j3NP7rVtrzyNC0U66LK-Bl4/sendMessage?text=" + text + "&chat_id=" + chat_id+"&parse_mode=Markdown", SentMessageResponse.class, true);
    }
    
    public static SentMessageResponse SendLocation(Location location, long chat_id) {
        return JSONLibrary.FromJSON("https://api.telegram.org/bot5176466546:AAFohyNASLS-j3NP7rVtrzyNC0U66LK-Bl4/sendLocation?chat_id=" + chat_id + "&latitude="+location.lat + "&longitude="+location.lon, SentMessageResponse.class, true);
    }
}
