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

    public static Location GetLocation(double lat, double lon) {
        try {
            Location location = new Location();
            location.display_name = "";
            location.lat = lat;
            location.lon = lon;
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

    public static double GetDistanceKilometers(Location location1, Location location2) {
        double R = 6371.0088;
        Double lat1 = location1.lat;
        Double lon1 = location1.lon;
        Double lat2 = location2.lat;
        Double lon2 = location1.lon;
        Double latDistance = Math.toRadians((lat2 - lat1));
        Double lonDistance = Math.toRadians((lon2 - lon1));
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double a = haversin(latDistance) + Math.cos(lat1) * Math.cos(lat2) * haversin(lonDistance);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
    private static double haversin(double val){
        return Math.pow(Math.sin(val / 2), 2);
    }
}
