package markt_study.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import markt_study.controller.LoginController;

@SpringBootApplication
@ComponentScan(basePackageClasses=LoginController.class)
public class BachelorProjectApplication {

	

}
