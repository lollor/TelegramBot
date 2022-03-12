/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegramapi.location;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Lorenzo
 */
public class Location {

    public double lon, lat;
    public String display_name;

    public Location() {
        lon = 0;
        lat = 0;
        display_name = "";
    }

    public static Location GetLocation(String city) {
        try {
            Element element = null;
            element = GetElement("https://nominatim.openstreetmap.org/search?q=" + city + "&format=xml&addressdetails=1");
            Node place = element.getElementsByTagName("place").item(0);
            Location location = new Location();
            location.display_name = place.getAttributes().getNamedItem("display_name").getNodeValue();
            location.lat = Double.parseDouble(place.getAttributes().getNamedItem("lat").getNodeValue());
            location.lon = Double.parseDouble(place.getAttributes().getNamedItem("lon").getNodeValue());
            return location;
        } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
        }
    }

    private static Element GetElement(String url) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            var document = builder.parse(url);
            return document.getDocumentElement();
        } catch (SAXException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ParserConfigurationException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
