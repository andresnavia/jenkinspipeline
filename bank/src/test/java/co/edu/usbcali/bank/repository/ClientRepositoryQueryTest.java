package co.edu.usbcali.bank.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.usbcali.bank.domain.Client;
@SpringBootTest
class ClientRepositoryQueryTest {
	final static Logger log=LoggerFactory.getLogger(ClientRepositoryQueryTest.class);
	@Autowired
	ClientRepository clientRepository;
	
	@Test
	void findAll() {
		List<Client> clients=clientRepository.findAll();
		clients.forEach(client->log.info(client.getName()));
	}
	@Test
	void findByEmail() {
		List<Client> clients=clientRepository.findByEmail("awaulker2@seesaa.net");
		clients.forEach(client->log.info(client.getName()));
	}
	@Test
	void findByNameLike() {
		List<Client> clients=clientRepository.findByNameLike("%wi%");
		clients.forEach(client->log.info(client.getName()));
	}

	@Test
	void findWithTwoAccounts() {
		List<Client> clients=clientRepository.findWithTwoAccounts();
		clients.forEach(client->log.info(client.getName()));
	}
}
