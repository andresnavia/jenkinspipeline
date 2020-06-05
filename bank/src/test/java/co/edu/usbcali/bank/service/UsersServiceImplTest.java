package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.bank.domain.UserType;
import co.edu.usbcali.bank.domain.Users;
@SpringBootTest
@Rollback(false)
class UsersServiceImplTest {

	final static Logger log=LoggerFactory.getLogger(UsersServiceImplTest.class);
	String email="1994ANDRESNAV@GMAIL.COM";
	Long ustyId=1L;
	@Autowired
	UsersService usersService;
	@Autowired
	UserTypeService userTypeService;
	
	@Test
	void fail() {
		assertThrows(Exception.class,()->{
			Users users= new Users();
			users.setEnable("Se");
			users.setName("wILLIAM"); 
			users.setUserEmail(email);
			users.setUserType(null);
			usersService.save(users);
		});
	}
	@Test
	@DisplayName("Save")
	void aTest(){
		Optional<Users> usersOptional=usersService.findById(email);
		assertFalse(usersOptional.isPresent(),"El users  exste");
		Users users= new Users();
		users.setEnable("S");
		users.setName("wILLIAM"); 
		users.setUserEmail(email);
		Optional<UserType> documentTypeOptional=userTypeService.findById(ustyId);
		assertTrue(documentTypeOptional.isPresent(),"El USER type no existe");
		users.setUserType(null);
		users.setUserType(documentTypeOptional.get());
		try {
			usersService.save(users);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertNull(e,e.getMessage());
		}
	}
	@Test
	@DisplayName("findById")
	void bTest(){
		Optional<Users> usersOptional=usersService.findById(email);
		assertTrue(usersOptional.isPresent(),"El userse no exste");
	}
	@Test
	@DisplayName("update")
	void cTest(){
		Optional<Users> usersOptional=usersService.findById(email);
		assertTrue(usersOptional.isPresent(),"El userse no exste");
		Users users= usersOptional.get();
		users.setName("William Navia");
		try {
			usersService.update(users);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertNull(e,e.getMessage());
		}
	}
	@Test
	@DisplayName("delete")
	void dTest(){
		Optional<Users> usersOptional=usersService.findById(email);
		assertTrue(usersOptional.isPresent(),"El userse no exste");
		Users users= usersOptional.get();
		try {
			usersService.delete(users);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertNull(e,e.getMessage());
		}
	}

}
