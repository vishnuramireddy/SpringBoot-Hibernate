package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "controller", "service", "util" })
public class TestApp {
	private static final Logger logger = LoggerFactory.getLogger(TestApp.class);

	public static void main(String[] args) {
		SpringApplication.run(TestApp.class, args);
	}
}
