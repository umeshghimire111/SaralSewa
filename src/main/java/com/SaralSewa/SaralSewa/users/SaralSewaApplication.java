package com.SaralSewa.SaralSewa.users;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {
		"com.SaralSewa.SaralSewa.users",
		"com.SaralSewa.SaralSewa.shared"
})
@EnableJpaRepositories(basePackages = {
		"com.SaralSewa.SaralSewa.shared.repository"
})
@EntityScan(basePackages = {
		"com.SaralSewa.SaralSewa.shared.entity"
})
public class SaralSewaApplication {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "user");
		SpringApplication.run(SaralSewaApplication.class, args);
		System.out.println(" USER APP STARTED ON PORT 8082");
	}
}