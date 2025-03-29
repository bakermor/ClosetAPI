package com.example.closet_api.database.model;

import java.util.Date;
import java.util.HashMap;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Clothes")
public class ClothesItem {

    @Id
    private String item_id;

    private String item_name;
    private String owner_user_id;
    private HashMap item_tags;
    private Date last_worn_dt;

    public ClothesItem(String item_id, String item_name, String owner_user_id, HashMap item_tags, Date last_worn_dt) {
        super();
        this.item_id = item_id;
        this.item_name = item_name;
        this.owner_user_id = owner_user_id;
        this.item_tags = item_tags;
        this.last_worn_dt = last_worn_dt;
    }

    public String getItem_id() {
        return item_id;
    }

    public String getOwner_user_id() {
        return owner_user_id;
    }

    public void setOwner_user_id(String owner_user_id) {
        this.owner_user_id = owner_user_id;
    }

    public HashMap getItem_tags() {
        return item_tags;
    }

    public void setItem_tags(HashMap item_tags) {
        this.item_tags = item_tags;
    }

    public Date getLast_worn_dt() {
        return last_worn_dt;
    }

    public void setLast_worn_dt(Date last_worn_dt) {
        this.last_worn_dt = last_worn_dt;
    }
}

