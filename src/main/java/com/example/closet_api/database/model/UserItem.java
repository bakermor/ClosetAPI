package com.example.closet_api.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Users")      // Document annotation to specify the collection name that will be used by the model
public class UserItem {

    @Id // the primary key in our MongoDB document is specified using the @Id annotation
    private String user_id;

    private String first_name;
    private String family_name;
    private String email;

    public UserItem(String user_id, String first_name, String family_name, String email) {
        super();
        this.user_id = user_id;
        this.first_name = first_name;
        this.family_name = family_name;
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

