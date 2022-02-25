/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

/**
 *
 * @author Lorenzo
 */
class UpdatesResponse {

    public boolean ok;
    public int error_code;
    public String description;
    public Update[] result;

    public Update GetLastResult() {
        return result[result.length - 1];
    }

    public int GetLength() {
        return result.length;
    }
}

class SentMessageResponse {

    public boolean ok;
    public Message result;
}

class MeResponse {

    public boolean ok;
    public int error_code;
    public String description;
    public User result;
}

class FileResponse {

    public boolean ok;
    public int error_code;
    public String description;
    public File result;
}
