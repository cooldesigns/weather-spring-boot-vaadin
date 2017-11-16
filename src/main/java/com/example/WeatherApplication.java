package com.example;

import com.google.gson.JsonObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class WeatherApplication {
	public static String[] cla;
	private static String UrlasText;

	private static Output output = null;

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}

	public String getUserInput(String input){
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		return input;
	}
}

// new String array for sending command line args to other classes

// 		cla = new String[args.length];
//
//                for (int x = 0; x < args.length; x++) {
//        cla[x] = args[x];
//        System.out.println(cla[x]);
//        }
//
//        //calls WebsiteControllerClass
//        WebsiteController wc = new WebsiteController(cla.clone());
//        wc.HttpQueryBuilder();
//        JsonObject jsonObject = wc.jParse();


