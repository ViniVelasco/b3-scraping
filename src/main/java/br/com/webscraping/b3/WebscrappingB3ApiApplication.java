package br.com.webscraping.b3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebscrappingB3ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebscrappingB3ApiApplication.class, args);
	}
	
	@Bean
	public WebDriver webDriver() {
		System.setProperty("webdriver.gecko.driver","C:\\Users\\vinic\\Documents\\workspace-spring-tool-suite-4-4.2.1.RELEASE\\webscrapping-b3-api\\src\\test\\resources\\geckodriver.exe");
	    return new FirefoxDriver();
	}

}
