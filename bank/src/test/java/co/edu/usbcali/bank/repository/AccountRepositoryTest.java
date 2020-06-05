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

import co.edu.usbcali.bank.domain.Account;
import co.edu.usbcali.bank.domain.Client;



@SpringBootTest
@Rollback(false)
class AccountRepositoryTest {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	ClientRepository clientRepository;
	static Logger log=LoggerFactory.getLogger(DocumentTypeRepositoryTest.class);

	String accoId="4640-0341-9387-5783";
	Long clientId=71L;
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest() {
		log.info("Se ejecuto el atest");
		Optional<Account> accountOptional=accountRepository.findById(accoId);
		assertFalse(accountOptional.isPresent(),"La cuenta ya existe");
		Account account= new Account();
		account.setAccoId(accoId);
		account.setBalance(23000000.000);
		account.setEnable("S");
		account.setPassword("perro");
		account.setVersion(1L);
		Optional<Client> clientOptional=clientRepository.findById(clientId);
		assertTrue(clientOptional.isPresent(),"El cliente no existe");
		Client client=clientOptional.get();
		account.setClient(client);
		accountRepository.save(account);
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = false)
	void btest() {
		log.info("Se ejecuto el btest");
		Optional<Account> accountOptional=accountRepository.findById(accoId);
		assertTrue(accountOptional.isPresent(),"La cuenta no Existe.");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest() {
		log.info("Se ejecuto el ctest");
		Optional<Account> accountOptional=accountRepository.findById(accoId);
		assertTrue(accountOptional.isPresent(),"La cuenta  no Existe.");

		accountOptional.ifPresent(account->{
			account.setBalance(40000000.000);
			accountRepository.save(account);
		});
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void etest() {
		log.info("Se ejecuto el etest");
		Optional<Account> accountOptional=accountRepository.findById(accoId);
		assertTrue(accountOptional.isPresent(),"La cuenta  no Existe.");
		accountOptional.ifPresent(account->{
			accountRepository.delete(account);
		});
	}
	
	@BeforeEach
	void beforeEach() {
		log.info("Se ejecuto el beforeEach");
		assertNotNull(accountRepository,"El accountRepository no existe");
	}
	@AfterEach
	void afterEach() {
		log.info("Se ejecuto el afterEach");
	}


}
