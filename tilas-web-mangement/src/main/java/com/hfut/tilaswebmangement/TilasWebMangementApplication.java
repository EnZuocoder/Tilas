package com.hfut.tilaswebmangement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
@SpringBootApplication
public class TilasWebMangementApplication {
    public static void main(String[] args) {
        SpringApplication.run(TilasWebMangementApplication.class, args);
    }
}
