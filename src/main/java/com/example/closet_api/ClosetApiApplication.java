package com.example.closet_api;

// Import external dependencies (Spring)
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

// Import our storage service
import com.example.closet_api.storage.StorageProperties;
import com.example.closet_api.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@ComponentScan({"com.example.closet_api.basic", "com.example.closet_api.storage"})
public class ClosetApiApplication {

    private static ApplicationContext applicationContext;

	public static void main(String[] args) {
        applicationContext =
                new AnnotationConfigApplicationContext(ClosetApiApplication.class);

        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

		SpringApplication.run(ClosetApiApplication.class, args);
	}

    @Bean
    CommandLineRunner init(StorageService storageService) {
        /* delete and re-create storage folder at startup */
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
