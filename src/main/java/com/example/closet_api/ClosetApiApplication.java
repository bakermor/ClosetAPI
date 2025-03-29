package com.example.closet_api;

// Import Java modules
import java.lang.System;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Import external dependencies
// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
// MongoDB imports
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.bson.BsonDocument;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

// Import our storage service
import com.example.closet_api.storage.StorageProperties;
import com.example.closet_api.storage.StorageService;
import com.example.closet_api.database.model.UserItem;
import com.example.closet_api.database.model.ClothesItem;
import com.example.closet_api.database.repository.UserItemRepository;
//import com.example.database.repository.CustomItemRepository;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@ComponentScan({"com.example.closet_api.basic", "com.example.closet_api.storage"})
@EnableMongoRepositories
public class ClosetApiApplication {

    private static ApplicationContext applicationContext;

    @Autowired      // ItemRepository is autowired, allowing Spring to find it automatically.
    UserItemRepository userItemRepo;

//    @Autowired
//    CustomItemRepository customRepo;

    @Bean
    CommandLineRunner init(StorageService storageService) {
        /* delete and re-create storage folder at startup */
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }

    public static void main(String[] args) {
        applicationContext =
                new AnnotationConfigApplicationContext(ClosetApiApplication.class);

        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

        SpringApplication.run(ClosetApiApplication.class, args);
    }
}
