/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import java.io.IOException;
import java.net.MalformedURLException;
import telegramapi.*;
import telegramapi.location.Location;
import telegramapi.mainclasses.*;
import telegramapi.responses.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author Lorenzo
 */
public class Bot implements Listener {

    /**
     * @param args the command line arguments
     */
    public Bot() {
        receiver = new UpdatesReceiver();
        receiver.addListener(this);
        receiver.start();
    }

    //UpdatesResponse updates = Functions.GetUpdatesResponse(); 
    //static MessaggiRisposti messaggiRisposti = MessaggiRisposti.GetInstance();
    UpdatesReceiver receiver;

    public static void main(String[] args) throws MalformedURLException, IOException {
        java.awt.EventQueue.invokeLater(() -> {
            new Bot();
        });
    }

    private void ScriviSuFile(Message messaggio, Location location) throws FileNotFoundException {
        File file = new File("database.csv");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.println("Impossibile creare file database.csv. " + ex.getMessage());
        }
        Scanner scanner = new Scanner(file);
        StringBuffer stringBuffer = new StringBuffer();
        String rigaDaModificare = null;
        while (scanner.hasNextLine()) {
            String riga = scanner.nextLine();
            if (riga.contains(String.valueOf(messaggio.chat.id))) {
                rigaDaModificare = riga;
            }
            stringBuffer.append(riga + System.lineSeparator());
        }
        String contents = stringBuffer.toString();
        scanner.close();
        if (rigaDaModificare != null) {
            contents = contents.replaceAll(rigaDaModificare, messaggio.chat.id + ";" + messaggio.from.username + ";" + location.lat + ";" + location.lon);
        } else {
            contents += messaggio.chat.id + ";" + messaggio.from.username + ";" + location.lat + ";" + location.lon + System.lineSeparator();
        }
        try {
            contents = contents.trim();
            FileWriter writer = new FileWriter(file);
            writer.write(contents);
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(Message message) {
        System.out.println("Message `" + message.text + "` from @" + message.from.username);
        String argument = message.text.substring(message.text.indexOf(" ")+1), comando = message.text.split(" ")[0];
        //System.out.println("comando=["+comando+"], argument=["+argument+"]");
        switch (comando) {
            case "/citta":
                if (argument == null || argument.equals("")){
                    Functions.SendMessage("Per selezionare una citta scrivi */citta <nome_citta>*", message.chat.id);
                    return;
                }
                Location location = Location.GetLocation(message.text.substring(7));
                if (location == null) {
                    Functions.SendMessage("Non ho trovato nessun risultato per la citt?? *" + message.text.substring(7) + "*", message.chat.id);
                    return;
                }
                Functions.SendMessage("La citt?? che hai scelto ??: *" + location.display_name + "*", message.chat.id);
                Functions.SendLocation(location, message.chat.id);
                try {
                    ScriviSuFile(message, location);
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
                return;
            case "/start":
                Functions.SendMessage("Ciao! Per selezionare la citt?? scrivi */citta <nome_citta>*", message.chat.id);
                return;
            default:
                Functions.SendMessage("Non riesco a rispondere a questo messaggio.", message.chat.id);
                return;
        }
    }
}
