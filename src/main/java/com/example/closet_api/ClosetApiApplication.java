package com.example.closet_api;

// Import Java modules
import java.lang.System;
import java.util.HashMap;
import java.util.Date;

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

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

// Import our database & storage service
import com.example.closet_api.storage.StorageProperties;
import com.example.closet_api.storage.StorageService;
import com.example.closet_api.database.model.UserItem;
import com.example.closet_api.database.model.ClothesItem;
import com.example.closet_api.database.repository.ItemRepository;
//import com.example.database.repository.CustomItemRepository;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@ComponentScan({"com.example.closet_api.basic", "com.example.closet_api.storage"})
@EnableMongoRepositories
public class ClosetApiApplication {

    private static ApplicationContext applicationContext;

    @Autowired      // ItemRepository is autowired, allowing Spring to find it automatically.
    ItemRepository userItemRepo;

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

    @Bean
    CommandLineRunner run() {
        return (args) -> {
            this.addSampleData();
        };
    }

    public void addSampleData() {
        System.out.println("Data creation started...");

//        HashMap<String, String> tags = new HashMap<String, String>();
//        tags.put("brand", "unknown");
//        tags.put("is_favorite", "yes");

        userItemRepo.save(new UserItem("uuid-1", "Helen", "S", "hsc@mail.com"));
        userItemRepo.save(new UserItem("uuid-2", "Morgan", "B", "meb@mail.com"));
//        clothesItemRepo.save(new ClothesItem("1", "Favorite sweater", "uuid-1", tags, new Date()));
//        clothesItemRepo.save(new ClothesItem("2", "Purple fuzzy", "uuid-2", tags, new Date()));
//        clothesItemRepo.save(new ClothesItem("3", "Black top w lace sleeves", "uuid-2", tags, new Date()));

        System.out.println("Data creation complete.");

    }
}
