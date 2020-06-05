package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

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
import co.edu.usbcali.bank.domain.RegisteredAccount;
@SpringBootTest
@Rollback(false)
class RegisteredAccountRepositoryTest {

	public static Long reacId=null;
	String accoId="4640-0341-9387-5782";
	Long clientId=71L;
	Logger log=LoggerFactory.getLogger(RegisteredAccountRepositoryTest.class);
	
	
	@Autowired
	RegisteredAccountRepository registeredAccountRepository;

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ClientRepository clientRepository;
	@Test
	@Transactional(readOnly = false)
	@DisplayName("Save")
	void atest() {
		Optional<RegisteredAccount> registeredAccountOptional=registeredAccountRepository.findById(0L);
		assertFalse(registeredAccountOptional.isPresent(),"La cuenta registrada existe.");
		RegisteredAccount registeredAccount= new RegisteredAccount();
		registeredAccount.setEnable("S");
		registeredAccount.setReacId(null);
		Optional<Client> clientOptional= clientRepository.findById(clientId);
		assertTrue(clientOptional.isPresent(),"El cliente no existe");
		registeredAccount.setClient(clientOptional.get());
		Optional<Account> accountOptional=accountRepository.findById(accoId);
		assertTrue(accountOptional.isPresent(),"La cuenta no existe");
		registeredAccount.setAccount(accountOptional.get());
		registeredAccount=registeredAccountRepository.save(registeredAccount);
		reacId=registeredAccount.getReacId();
		
	}

	@Test
	@Transactional(readOnly = false)
	@DisplayName("findById")
	void btest() {
		Optional<RegisteredAccount> registeredAccountOptional=registeredAccountRepository.findById(reacId);
		assertTrue(registeredAccountOptional.isPresent(),"La cuenta registrada no existe.");
	}

	@Test
	@Transactional(readOnly = false)
	@DisplayName("update")
	void ctest() {
		Optional<RegisteredAccount> registeredAccountOptional=registeredAccountRepository.findById(reacId);
		assertTrue(registeredAccountOptional.isPresent(),"La cuenta registrada no existe.");
		registeredAccountOptional.ifPresent(entity->{
			entity.setEnable("N");
			registeredAccountRepository.save(entity);
		});
	}

	@Test
	@Transactional(readOnly = false)
	@DisplayName("delete")
	void dtest() {
		Optional<RegisteredAccount> registeredAccountOptional=registeredAccountRepository.findById(reacId);
		assertTrue(registeredAccountOptional.isPresent(),"La cuenta registrada no existe.");
		registeredAccountOptional.ifPresent(entity->{
			registeredAccountRepository.delete(entity);
		});
	}

	@BeforeEach
	void beforeEach() {
		log.info("Se ejecuto el beforeEach");
		assertNotNull(registeredAccountRepository,"El accountRepository es null");
		assertNotNull(accountRepository,"El accountRepository es null");
		assertNotNull(clientRepository,"El clientRepository es null");
	}
	@AfterEach
	void afterEach() {
		log.info("Se ejecuto el afterEach");
	}
}
