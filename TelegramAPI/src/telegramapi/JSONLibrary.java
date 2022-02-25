/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegramapi;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo
 */
public class JSONLibrary {

    public static String ToJSON(Object objectToSerialize) {
        Gson g = new Gson();
        return g.toJson(objectToSerialize);
    }

    public static <T> T FromJSON(String url, Class<T> classe) {
        try {
            URL urlObject = new URL(url);
            Reader reader = new BufferedReader(new InputStreamReader(urlObject.openStream()));
            Gson g = new Gson();
            return g.fromJson(reader, classe);
        } catch (MalformedURLException ex) {
            Logger.getLogger(JSONLibrary.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(JSONLibrary.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
