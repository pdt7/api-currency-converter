package br.com.paulodt.apicurrencyconverter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiCurrencyConverterApplication {

	private static Logger log = LoggerFactory.getLogger(ApiCurrencyConverterApplication.class);

	public static void main(String[] args) {
		log.info("Start app");
		SpringApplication.run(ApiCurrencyConverterApplication.class, args);
		log.info("End app");
	}

}
