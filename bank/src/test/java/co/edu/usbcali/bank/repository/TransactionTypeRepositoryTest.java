package co.edu.usbcali.bank.repository;

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

import co.edu.usbcali.bank.domain.TransactionType;

@SpringBootTest
@Rollback(false)
class TransactionTypeRepositoryTest {

	@Autowired
	TransactionTypeRepository transactionTypeRepository;
	static Logger log=LoggerFactory.getLogger(TransactionTypeRepositoryTest.class);

	public static Long transactionTypeId=null;
	@Test
	@DisplayName("save")
	void atest() {
		TransactionType transactionType= new TransactionType();
		transactionType.setTrtyId(null);
		transactionType.setName("TEST");
		transactionType.setEnable("S");
		transactionType=transactionTypeRepository.save(transactionType);
		log.info("ID new document type: "+transactionType.getTrtyId());
		transactionTypeId=transactionType.getTrtyId();
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = false)
	void btest() {
		log.info("Se ejecuto el btest");
		Optional<TransactionType> transactionTypeOptional=transactionTypeRepository.findById(transactionTypeId);
		assertTrue(transactionTypeOptional.isPresent(),"El transactionType no Existe.");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest() {
		log.info("Se ejecuto el ctest");
		Optional<TransactionType> transactionTypeOptional=transactionTypeRepository.findById(transactionTypeId);
		assertTrue(transactionTypeOptional.isPresent(),"El transactionType no Existe.");

		transactionTypeOptional.ifPresent(transactionType->{
			transactionType.setName("Permiso Especial");
			transactionTypeRepository.save(transactionType);
		});
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void etest() {
		log.info("Se ejecuto el etest");
		Optional<TransactionType> transactionTypeOptional=transactionTypeRepository.findById(transactionTypeId);
		assertTrue(transactionTypeOptional.isPresent(),"El transactionType no Existe.");
		transactionTypeOptional.ifPresent(client->{
			transactionTypeRepository.delete(client);
		});
	}
	
	@BeforeEach
	void beforeEach() {
		log.info("Se ejecuto el beforeEach");
		assertNotNull(transactionTypeRepository,"El transactionTypeRepository no existe");
	}
	@AfterEach
	void afterEach() {
		log.info("Se ejecuto el afterEach");
	}


}
