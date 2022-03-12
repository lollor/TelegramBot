/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegramapi.mainclasses;



/**
 *
 * @author Lorenzo
 */
public class Message {

    public long message_id;
    public User from;
    public Chat chat;
    public long date;
    public String text;
    public Entity[] entities;
    public Photo[] photo;
    public Document document;

    public Attachment GetAttachment() {
        if (photo != null) {
            photo[photo.length-1].type = Photo.class;
            return photo[photo.length-1];
        } else if (document != null) {
            document.type = Document.class;
            return document;
        } else {
            return null;
        }
    }
}
