package com.app.malikcode.creatorstore;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreatorStoreApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(dotenvEntry -> System.setProperty(
                dotenvEntry.getKey(), dotenvEntry.getValue()
        ));
        SpringApplication.run(CreatorStoreApplication.class, args);

        System.out.println("Bang Bang");
    }

}
