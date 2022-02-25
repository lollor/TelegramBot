/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import telegramapi.*;
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
        UpdatesResponse updates = JSONLibrary.FromJSON("https://api.telegram.org/bot5176466546:AAFohyNASLS-j3NP7rVtrzyNC0U66LK-Bl4/getUpdates", UpdatesResponse.class);
//        Me me = JSONLibrary.FromJSON("https://api.telegram.org/bot5176466546:AAFohyNASLS-j3NP7rVtrzyNC0U66LK-Bl4/getMe", Me.class);
//        String file_path = JSONLibrary.FromJSON("https://api.telegram.org/bot5176466546:AAFohyNASLS-j3NP7rVtrzyNC0U66LK-Bl4/getFile?file_id="+updates.result[updates.GetLength()-2].message.document.thumb.file_id, File.class).result.file_path;
//        URL ulr = new  URL("https://api.telegram.org/file/bot5176466546:AAFohyNASLS-j3NP7rVtrzyNC0U66LK-Bl4/"+file_path);
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ulr.openStream()));
//        PrintWriter printWriter = new PrintWriter(file_path.split("/")[1]);
//        String line;
//        char[] cbuf = new char[1000];
//        while ((line = bufferedReader.readLine()) != null) {
//            //System.out.println(line);
//            printWriter.write(line);
//        }
//        while (bufferedReader.read(cbuf) != -1) {
//            System.out.println(line);
//            printWriter.write(cbuf);
//        }
//        bufferedReader.close();
//        printWriter.close();
//        BufferedImage bufferedImage= ImageIO.read(ulr);
//        ImageIO.write(bufferedImage, "jpg", new java.io.File(file_path.split("/")[1]));
//        

        //Object documento = JSONLibrary.FromJSON("https://api.telegram.org/file/bot5176466546:AAFohyNASLS-j3NP7rVtrzyNC0U66LK-Bl4/"+file_path, Object.class);
        //System.out.println("Ci sono "+updates.result.length + " risultati.");
        for (var arg : updates.result) {
            try {
                System.out.println(arg.message.text);
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
        SentMessageResponse sentMessage = JSONLibrary.FromJSON("https://api.telegram.org/bot5176466546:AAFohyNASLS-j3NP7rVtrzyNC0U66LK-Bl4/sendMessage?text=" + Calendar.getInstance().getTime() + "&chat_id=" + updates.GetLastResult().message.chat.id, SentMessageResponse.class);
        System.out.println("Ho scritto a " + sentMessage.result.chat.username + " questo messaggio: " + sentMessage.result.text);
    }

}
