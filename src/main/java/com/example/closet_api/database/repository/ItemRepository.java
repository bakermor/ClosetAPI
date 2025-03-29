package com.example.closet_api.database.repository;

import java.util.List;

import com.example.closet_api.database.model.UserItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ItemRepository extends MongoRepository<UserItem, String> {

    @Query("{user_id:'?0'}")
    UserItem findUserItemById(String user_id);

    @Query(value="{first_name:'?0'}", fields="{'user_id' : 1, 'first_name' : 1, 'family_name' : 1}")
    List<UserItem> findUserByFirstName(String first_name);

    public long count();
}

