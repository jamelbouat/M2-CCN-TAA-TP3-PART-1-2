package fr.istic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.istic.service.client.IRun;

@SpringBootApplication
@RestController
public class TaaTp3Application implements CommandLineRunner {
	
	@Autowired(required = true)
	private IRun irun;

	public void run(String ...args) {
		this.irun.run();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TaaTp3Application.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
}