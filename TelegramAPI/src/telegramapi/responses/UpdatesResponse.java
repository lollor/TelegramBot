/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegramapi.responses;
import telegramapi.mainclasses.*;
/**
 *
 * @author Lorenzo
 */
public class UpdatesResponse {

    public boolean ok;
    public int error_code;
    public String description;
    public Update[] result;

    public Update GetLastResult() {
        try {
            return result[result.length - 1];
        } catch (Exception e) {
            return null;
        }
    }

    public int GetLength() {
        return result.length;
    }
}
