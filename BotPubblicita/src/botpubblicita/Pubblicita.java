/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package botpubblicita;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import telegramapi.location.Location;

/**
 *
 * @author Lorenzo
 */
public class Pubblicita {
    private ArrayList<OggettoPubblicita> pubblicitas;

    public Pubblicita() {
        pubblicitas = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get("pubblicita.csv"));
            for (String line : lines) {
                String[] vs = line.split(";");
                pubblicitas.add(new OggettoPubblicita(vs[0], vs[2], Double.valueOf(vs[1])));
            }
        } catch (IOException ex) {
            Logger.getLogger(Utenti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AddPubblicita(OggettoPubblicita pubblicita){
        pubblicitas.add(pubblicita);
    }
    public ArrayList<OggettoPubblicita> getPubblicitas() {
        return pubblicitas;
    }
    public OggettoPubblicita getPubblicita(int index){
        try {
            
        return pubblicitas.get(index);
        } catch (Exception e){
            return null;
        }
        
    }
    public boolean RemovePubblicita(OggettoPubblicita oggetto){
        return pubblicitas.remove(oggetto);
    }
    public String[] GetCities(){
        ArrayList<String> lista = new ArrayList<String>();
        for (OggettoPubblicita ogg : pubblicitas){
            lista.add(ogg.citta);
        }
        return lista.toArray(new String[0]);
    }
    public String toCSV(){
        String finale="";
        for (OggettoPubblicita pubblicita : pubblicitas) {
            finale+=pubblicita.citta + ";" + String.valueOf(pubblicita.raggio).trim() + ";" + pubblicita.testoPubblicita.trim() + System.lineSeparator();
        }
        return finale;
    }
}
class OggettoPubblicita{
    public String citta, testoPubblicita;
    public double raggio;
    public Location location;

    public OggettoPubblicita(String citta, String testoPubblicita, double raggio) {
        this.citta = citta;
        this.testoPubblicita = testoPubblicita;
        this.raggio = raggio;
        this.location = Location.GetLocation(citta);
    }
    
}
