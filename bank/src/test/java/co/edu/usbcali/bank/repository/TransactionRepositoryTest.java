package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import co.edu.usbcali.bank.domain.Transaction;
import co.edu.usbcali.bank.domain.TransactionType;
import co.edu.usbcali.bank.domain.Users;
@SpringBootTest
@Rollback(value = false)
class TransactionRepositoryTest {
	Logger log=LoggerFactory.getLogger(TransactionRepositoryTest.class);
	
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TransactionTypeRepository transactionTypeRepository;
	@Autowired
	UsersRepository usersRepository;
	
	Long trtyId=1L;
	String userId="cfaier0@cafepress.com";
	public static Long transaId;
	String accountId="4640-0341-9387-5782";
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest() {
		Optional<Transaction> transactionOptional=transactionRepository.findById(0L);
		assertFalse(transactionOptional.isPresent(),"La transaccion existe.");
		Transaction transaction= new Transaction();
		transaction.setAmount(2300000.00);
		transaction.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		transaction.setTranId(null);
		Optional<Account> accountOptional=accountRepository.findById(accountId);
		assertTrue(accountOptional.isPresent(),"La cuenta no existe");
		transaction.setAccount(accountOptional.get());
		Optional<TransactionType>transactionTypeOptional=transactionTypeRepository.findById(trtyId);
		assertTrue(transactionTypeOptional.isPresent(),"El tipo de transaccion no existe");
		transaction.setTransactionType(transactionTypeOptional.get());
		Optional<Users>usersOptional=usersRepository.findById(userId);
		assertTrue(usersOptional.isPresent(),"El usuario no existe");
		transaction.setUsers(usersOptional.get());
		
		transaction=transactionRepository.save(transaction);
		transaId=transaction.getTranId();
	}

	@Test
	@DisplayName("findById")
	@Transactional(readOnly = false)
	void btest() {
		Optional<Transaction> transactionOptional=transactionRepository.findById(transaId);
		assertTrue(transactionOptional.isPresent(),"La transaccion no existe.");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest() {
		Optional<Transaction> transactionOptional=transactionRepository.findById(transaId);
		assertTrue(transactionOptional.isPresent(),"La transaccion no existe.");
		transactionOptional.ifPresent(entity->{
			entity.setAmount(234000000.000);
			transactionRepository.save(entity);
		});
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest() {
		Optional<Transaction> transactionOptional=transactionRepository.findById(transaId);
		assertTrue(transactionOptional.isPresent(),"La transaccion no existe.");
		transactionOptional.ifPresent(entity->{
			transactionRepository.delete(entity);
		});
	}
	
	@BeforeEach
	void beforeEach() {
		log.info("Se ejecuto el beforeEach");
		assertNotNull(accountRepository,"El accountRepository es null");
		assertNotNull(transactionRepository,"El transactionTypeRepository es null");
		assertNotNull(transactionRepository,"El usersRepository es null");
		assertNotNull(transactionRepository,"El transactionRepository es null");
	}
	@AfterEach
	void afterEach() {
		log.info("Se ejecuto el afterEach");
	}


}
