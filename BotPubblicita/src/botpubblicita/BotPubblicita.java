/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import jsonlibrary.JSONLibrary;
import com.google.gson.*;
import java.awt.Image;
import java.awt.image.AbstractMultiResolutionImage;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import javax.imageio.ImageIO;

/**
 *
 * @author Lorenzo
 */
public class BotPubblicita {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        // TODO code application logic here
        UpdatesResponse updates = GetUpdatesResponse();
        MeResponse me = GetMeResponse();

        System.out.println("Ci sono " + updates.result.length + " risultati.");
        for (var arg : updates.result) {
            Attachment attachment = arg.message.GetAttachment();
            if (attachment == null || attachment.type == null) {
                try {
                    System.out.println("'" + arg.message.text + "' da " + arg.message.from.username);
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            } else if (attachment.type == Photo.class) {
                Photo photo = (Photo) attachment;
                System.out.println("C'è una foto in allegato. File_id = " + photo.file_id);
            } else if (attachment.type == Document.class) {
                Document document = (Document) attachment;
                System.out.println("C'è un documento in allegato. File_id = " + document.file_id);
            }
        }
        Update lastUpdate = updates.GetLastResult();
        if (lastUpdate == null) {
            System.out.println("Non esiste un messaggio. Non invio niente.");
            return;
        }
        //SentMessageResponse sentMessage = SendMessage(Calendar.getInstance().getTime().toString(), lastUpdate.message.chat.id);
        //System.out.println("Ho scritto a " + sentMessage.result.chat.username + " questo messaggio: " + sentMessage.result.text);
    }

    public static UpdatesResponse GetUpdatesResponse() {
        return JSONLibrary.FromJSON("https://api.telegram.org/bot5176466546:AAFohyNASLS-j3NP7rVtrzyNC0U66LK-Bl4/getUpdates", UpdatesResponse.class);
    }

    public static MeResponse GetMeResponse() {
        return JSONLibrary.FromJSON("https://api.telegram.org/bot5176466546:AAFohyNASLS-j3NP7rVtrzyNC0U66LK-Bl4/getMe", MeResponse.class);
    }

    public static SentMessageResponse SendMessage(String text, long chat_id) {
        return JSONLibrary.FromJSON("https://api.telegram.org/bot5176466546:AAFohyNASLS-j3NP7rVtrzyNC0U66LK-Bl4/sendMessage?text=" + text + "&chat_id=" + chat_id, SentMessageResponse.class);
    }
}
