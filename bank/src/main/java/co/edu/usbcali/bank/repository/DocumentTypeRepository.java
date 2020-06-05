package co.edu.usbcali.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.domain.DocumentType;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {

}
