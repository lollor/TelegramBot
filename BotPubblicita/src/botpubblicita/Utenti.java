/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package botpubblicita;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import telegramapi.location.Location;

/**
 *
 * @author Lorenzo
 */
public class Utenti {
    private ArrayList<Utente> utenti;

    public Utenti() {
        utenti = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get("database.csv"));
            for (String line : lines) {
                String[] vs = line.split(";");
                utenti.add(new Utente(vs[1], Long.valueOf(vs[0]), Location.GetLocation(Double.valueOf(vs[2]), Double.valueOf(vs[3]))));
            }
        } catch (IOException ex) {
            Logger.getLogger(Utenti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Utente> getUtenti() {
        return utenti;
    }
}
class Utente{
    public String username;
    public long chat_id;
    public Location location;

    public Utente(String username, long chat_id, Location location) {
        this.username = username;
        this.chat_id = chat_id;
        this.location = location;
    }
}
