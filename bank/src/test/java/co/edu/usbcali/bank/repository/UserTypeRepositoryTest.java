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

import co.edu.usbcali.bank.domain.UserType;

@SpringBootTest
@Rollback(false)
class UserTypeRepositoryTest {

	@Autowired
	UserTypeRepository userTypeRepository;
	static Logger log=LoggerFactory.getLogger(UserTypeRepositoryTest.class);

	public static Long userTypeId=null;
	@Test
	@DisplayName("save")
	void atest() {
		UserType userType= new UserType();
		userType.setUstyId(null);
		userType.setName("TEST");
		userType.setEnable("S");
		userType=userTypeRepository.save(userType);
		log.info("ID new document type: "+userType.getUstyId());
		userTypeId=userType.getUstyId();
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = false)
	void btest() {
		log.info("Se ejecuto el btest");
		Optional<UserType> userTypeOptional=userTypeRepository.findById(userTypeId);
		assertTrue(userTypeOptional.isPresent(),"El userType no Existe.");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest() {
		log.info("Se ejecuto el ctest");
		Optional<UserType> userTypeOptional=userTypeRepository.findById(userTypeId);
		assertTrue(userTypeOptional.isPresent(),"El userType no Existe.");

		userTypeOptional.ifPresent(userType->{
			userType.setName("Permiso Especial");
			userTypeRepository.save(userType);
		});
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void etest() {
		log.info("Se ejecuto el etest");
		Optional<UserType> userTypeOptional=userTypeRepository.findById(userTypeId);
		assertTrue(userTypeOptional.isPresent(),"El userType no Existe.");
		userTypeOptional.ifPresent(client->{
			userTypeRepository.delete(client);
		});
	}
	
	@BeforeEach
	void beforeEach() {
		log.info("Se ejecuto el beforeEach");
		assertNotNull(userTypeRepository,"El userTypeRepository no existe");
	}
	@AfterEach
	void afterEach() {
		log.info("Se ejecuto el afterEach");
	}

}
