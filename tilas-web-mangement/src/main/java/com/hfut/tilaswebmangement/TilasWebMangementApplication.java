package com.hfut.tilaswebmangement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
@SpringBootApplication
@ServletComponentScan
public class TilasWebMangementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TilasWebMangementApplication.class, args);
    }

}
