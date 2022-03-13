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
public class BotPubblicita implements Listener {

    /**
     * @param args the command line arguments
     */
    public BotPubblicita() {
        receiver = new UpdatesReceiver();
        receiver.addListener(this);
        receiver.start();
    }

    UpdatesResponse updates = Functions.GetUpdatesResponse();
    //static MessaggiRisposti messaggiRisposti = MessaggiRisposti.GetInstance();
    UpdatesReceiver receiver;

    public static void main(String[] args) throws MalformedURLException, IOException {
        java.awt.EventQueue.invokeLater(() -> {
            new BotPubblicita();
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
        System.out.println("Message `"+message.text+"` from @"+message.from.username);
        if (message.text.contains("/citta ")) {
            Location location = Location.GetLocation(message.text.substring(7));
            if (location == null){
                Functions.SendMessage("Non ho trovato nessun risultato per la città *" + message.text.substring(7)+"*", message.chat.id);
                return;
            }
            Functions.SendMessage("La città che hai scelto è: *" + location.display_name+"*", message.chat.id);
            Functions.SendLocation(location, message.chat.id);
            try {
                ScriviSuFile(message, location);
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (message.text.equals("/start")) {
            Functions.SendMessage("Ciao! Per selezionare la città scrivi */citta <nome_citta>*", message.chat.id);
        } else if (message.text.equals("/citta")){
            Functions.SendMessage("Per selezionare una citta scrivi */citta <nome_citta>*", message.chat.id);
        } else {
            Functions.SendMessage("Non riesco a rispondere a questo messaggio.", message.chat.id);
        }
    }
}
