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
public class User {

    public long id;
    public boolean is_bot, can_join_groups, can_read_all_group_messages, supports_inline_queries;
    public String first_name, last_name, username, language_code;
}
