package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.bank.domain.Client;
import co.edu.usbcali.bank.domain.DocumentType;
@SpringBootTest
@Rollback(false)
class ClientServiceTest {
	final static Logger log=LoggerFactory.getLogger(ClientServiceTest.class);
	Long clieId=6060L;
	Long dotyId=1L;
	@Autowired
	ClientService clientService;
	@Autowired
	DocumentTypeService documentTypeService;
	@Autowired
	ApplicationContext applicationContext;
	
	@Test
	void context() {
		assertNotNull(applicationContext);
		ClientService cliSer=(ClientService)applicationContext.getBean(ClientServiceImpl.class);
		assertNotNull(cliSer, "El cliSer es nulo");
	}

	@Test
	void fail() {
		assertThrows(Exception.class,()->{
			Client client= new Client();
			client.setAdress("CRT");
			client.setClieId(clieId);
			client.setDocumentType(null);
			client.setEmail("1994andresnav@gmail");
			client.setEnable("SI");
			client.setName("");
			client.setPhone("");
			clientService.save(client);
		});
	}
	@Test
	@DisplayName("Save")
	void aTest(){
		Optional<Client> clientOptional=clientService.findById(clieId);
		assertFalse(clientOptional.isPresent(),"El cliente  exste");
		Client client= new Client();
		client.setAdress("calle falsa 123");
		client.setClieId(clieId);
		client.setEmail("1994andresnav@gmail");
		client.setEnable("S");
		client.setName("Camilo Ramirez");
		client.setPhone("3456721");
		Optional<DocumentType> documentTypeOptional=documentTypeService.findById(dotyId);
		assertTrue(documentTypeOptional.isPresent(),"El Document type no existe");
		client.setDocumentType(documentTypeOptional.get());
		try {
			clientService.save(client);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertNull(e,e.getMessage());
		}
	}
	@Test
	@DisplayName("findById")
	void bTest(){
		Optional<Client> clientOptional=clientService.findById(clieId);
		assertTrue(clientOptional.isPresent(),"El cliente no exste");
	}
	@Test
	@DisplayName("update")
	void cTest(){
		Optional<Client> clientOptional=clientService.findById(clieId);
		assertTrue(clientOptional.isPresent(),"El cliente no exste");
		Client client= clientOptional.get();
		client.setEnable("S");
		try {
			clientService.update(client);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertNull(e,e.getMessage());
		}
	}
	@Test
	@DisplayName("delete")
	void dTest(){
		Optional<Client> clientOptional=clientService.findById(clieId);
		assertTrue(clientOptional.isPresent(),"El cliente no exste");
		Client client= clientOptional.get();
		try {
			clientService.delete(client);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertNull(e,e.getMessage());
		}
	}
//	@BeforeAll
//	void beforeAll() {
//		assertNotNull(clientService,"El clienteservice es nulo");
//	}
//	@AfterAll
//	void afterAll(){
//		log.info("Se ejecuto el afterAll");
//	}
}
