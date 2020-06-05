package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Client;
import co.edu.usbcali.bank.domain.DocumentType;
@SpringBootTest
@Rollback(false)
class ClientRepositoryTest {
	final static Logger log=LoggerFactory.getLogger(ClientRepositoryTest.class); 
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	DocumentTypeRepository documentTypeRepository;
	Long clientId=4040L;
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest() {
		log.info("Se ejecuto el atest");
		Optional<Client> clientOptional=clientRepository.findById(clientId);
		assertFalse(clientOptional.isPresent(),"El client ya existe");
		Client client= new Client();
		client.setClieId(clientId);
		client.setAdress("Avenida siempre viva 123");
		client.setEmail("hjsimsomp@gmail.com");
		client.setEnable("S");
		client.setName("Homero J Simpson");
		client.setPhone("+57 318 525 4451");
		Optional<DocumentType> documenTypeOptional=documentTypeRepository.findById(1L);
		assertTrue(documenTypeOptional.isPresent(),"El DocumentType no existe");
		DocumentType documentType=documenTypeOptional.get();
		client.setDocumentType(documentType);
		clientRepository.save(client);
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = false)
	void btest() {
		log.info("Se ejecuto el btest");
		Optional<Client> clientOptional=clientRepository.findById(clientId);
		assertTrue(clientOptional.isPresent(),"El cliente no Existe.");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest() {
		log.info("Se ejecuto el ctest");
		Optional<Client> clientOptional=clientRepository.findById(clientId);
		assertTrue(clientOptional.isPresent(),"El cliente no Existe.");

		clientOptional.ifPresent(client->{
			client.setName("Carlos Giraldo");
			clientRepository.save(client);
		});
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void etest() {
		log.info("Se ejecuto el etest");
		Optional<Client> clientOptional=clientRepository.findById(clientId);
		assertTrue(clientOptional.isPresent(),"El cliente no Existe.");
		clientOptional.ifPresent(client->{
			clientRepository.delete(client);
		});
	}
	
	@BeforeEach
	void beforeEach() {
		log.info("Se ejecuto el beforeEach");
		assertNotNull(clientRepository,"El clientRepository no existe");
		assertNotNull(documentTypeRepository,"El documentTypeRepository no existe");
	}
	@AfterEach
	void afterEach() {
		log.info("Se ejecuto el afterEach");
	}
}
