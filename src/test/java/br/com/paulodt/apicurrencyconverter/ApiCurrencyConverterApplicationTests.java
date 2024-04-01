package br.com.paulodt.apicurrencyconverter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.paulodt.apicurrencyconverter.entity.Transaction;
import br.com.paulodt.apicurrencyconverter.entity.User;

@SpringBootTest(webEnvironment =WebEnvironment.RANDOM_PORT )
class ApiCurrencyConverterApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTransactionSuccess() {
		User user = new User();
		user.setId(1l);
		var transaction = new Transaction(user, "BRL", 10l, "USD");

		webTestClient
			.post()
			.uri("/transaction")
			.bodyValue(transaction)
			.exchange()
			.expectStatus().isOk();
	}

	@Test
	void testCreateTransactionFailure() {
		User user = new User();
		user.setId(1l);
		var transaction = new Transaction(user, "", 10l, "");

		webTestClient
			.post()
			.uri("/transaction")
			.bodyValue(transaction)
			.exchange()
			.expectStatus().isBadRequest();
	}

}
