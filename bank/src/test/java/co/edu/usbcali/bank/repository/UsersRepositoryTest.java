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

import co.edu.usbcali.bank.domain.UserType;
import co.edu.usbcali.bank.domain.Users;


@SpringBootTest
@Rollback(false)
class UsersRepositoryTest {

Logger log=LoggerFactory.getLogger(UsersRepositoryTest.class);
	
	@Autowired
	UsersRepository usersRepository;

	@Autowired
	UserTypeRepository userTypeRepository;
	
	public static String userId="1994ANDRESNAV@GMAIL.COM";
	Long userTypeId=1L;
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest() {
		Optional<Users> userOptional=usersRepository.findById("");
		assertFalse(userOptional.isPresent(),"El usuario existe.");
		Users user= new Users();
		user.setEnable("S");
		user.setName("WILLIAM NAVIA");
		user.setUserEmail("1994ANDRESNAV@GMAIL.COM");
		Optional<UserType> userTypeOptional=userTypeRepository.findById(userTypeId);
		assertTrue(userTypeOptional.isPresent(),"El tipo de usuario no existe.");
		user.setUserType(userTypeOptional.get());
		user=usersRepository.save(user);
	}

	@Test
	@DisplayName("findById")
	@Transactional(readOnly = false)
	void btest() {
		Optional<Users> userOptional=usersRepository.findById(userId);
		assertTrue(userOptional.isPresent(),"El usuario no existe.");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest() {
		Optional<Users> userOptional=usersRepository.findById(userId);
		assertTrue(userOptional.isPresent(),"El usuario no existe.");
		userOptional.ifPresent(entity->{
			entity.setName("william andres navia giraldo");
			usersRepository.save(entity);
		});
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest() {
		Optional<Users> userOptional=usersRepository.findById(userId);
		assertTrue(userOptional.isPresent(),"El usuario no existe.");
		userOptional.ifPresent(entity->{
			usersRepository.delete(entity);
		});
	}
	
	@BeforeEach
	void beforeEach() {
		log.info("Se ejecuto el beforeEach");
		assertNotNull(usersRepository,"El usersRepository es null");
		assertNotNull(userTypeRepository,"El userTypeRepository es null");
	}
	@AfterEach
	void afterEach() {
		log.info("Se ejecuto el afterEach");
	}

}
