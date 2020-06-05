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

import co.edu.usbcali.bank.domain.DocumentType;
@SpringBootTest
@Rollback(false)
class DocumentTypeRepositoryTest {
	@Autowired
	DocumentTypeRepository documentTypeRepository;
	static Logger log=LoggerFactory.getLogger(DocumentTypeRepositoryTest.class);

	public static Long domentTypeId=null;
	@Test
	@DisplayName("save")
	void atest() {
		DocumentType documentType= new DocumentType();
		documentType.setDotyId(null);
		documentType.setName("TEST");
		documentType.setEnable("S");
		documentType=documentTypeRepository.save(documentType);
		log.info("ID new document type: "+documentType.getDotyId());
		domentTypeId=documentType.getDotyId();
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = false)
	void btest() {
		log.info("Se ejecuto el btest");
		Optional<DocumentType> documentTypeOptional=documentTypeRepository.findById(domentTypeId);
		assertTrue(documentTypeOptional.isPresent(),"El documentType no Existe.");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest() {
		log.info("Se ejecuto el ctest");
		Optional<DocumentType> documentTypeOptional=documentTypeRepository.findById(domentTypeId);
		assertTrue(documentTypeOptional.isPresent(),"El documentType no Existe.");

		documentTypeOptional.ifPresent(documentType->{
			documentType.setName("Permiso Especial");
			documentTypeRepository.save(documentType);
		});
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void etest() {
		log.info("Se ejecuto el etest");
		Optional<DocumentType> documentTypeOptional=documentTypeRepository.findById(domentTypeId);
		assertTrue(documentTypeOptional.isPresent(),"El documentType no Existe.");
		documentTypeOptional.ifPresent(client->{
			documentTypeRepository.delete(client);
		});
	}
	
	@BeforeEach
	void beforeEach() {
		log.info("Se ejecuto el beforeEach");
		assertNotNull(documentTypeRepository,"El documentTypeRepository no existe");
	}
	@AfterEach
	void afterEach() {
		log.info("Se ejecuto el afterEach");
	}

}
