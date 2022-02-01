package eu.ensup.MyResto;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class MyRestoApplication {
	public static void main(String[] args) {
		log.debug("Main");
		log.info("Test");
		log.error("Test");
		log.warn("Test");
		SpringApplication.run(MyRestoApplication.class, args);
	}

}
