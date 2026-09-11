package com.workshop.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.workshop.store.TextDecorator.TextDecorator;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);

		TextDecorator decorator = new TextDecorator(new String[] { "Hello!", "from", "Spring!" });
		System.out.println(
				decorator.decorate('*', 80));
	}

}
