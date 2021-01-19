package de.htwberlin.bachelor_project.main;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import de.htwberlin.bachelor_project.controller.LoginController;
import de.htwberlin.bachelor_project.storage.StorageService;

//@SpringBootApplication
@ComponentScan(basePackageClasses=LoginController.class)
public class BachelorProjectApplication  {
	 
	
	// @Resource
	// StorageService storageService;
	
	public static void main(String[] args) {
		//SpringApplication.run(BachelorProjectApplication.class, args);
	}
	
	/*@Override
	public void run(String...   arg) throws Exception {
	    storageService.deleteAll();
	    storageService.init();
	}*/

}
