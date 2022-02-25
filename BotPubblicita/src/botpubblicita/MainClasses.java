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
class Update {

    public long update_id;
    public Message message, edited_message, channel_post, edited_channel_post;
}

class Message {

    public long message_id;
    public User from;
    public Chat chat;
    public long date;
    public String text;
    public Entity[] entities;
    public Photo[] photo;
    public Document document;
}

class User {

    public long id;
    public boolean is_bot, can_join_groups, can_read_all_group_messages, supports_inline_queries;
    public String first_name, last_name, username, language_code;
}

class Chat {

    public long id;
    public String first_name, last_name, username, type;
}

class Entity {

    public long offset, length;
    public String type;
}

class Photo {

    public String file_id, file_unique_id;
    public long file_size, width, height;
}

class Document {

    public String file_name, mime_type, file_id, file_unique_id;
    public long file_size;
    public Photo thumb;
}

class File {

    public String file_id, file_unique_id, file_path;
    public long file_size;
}
