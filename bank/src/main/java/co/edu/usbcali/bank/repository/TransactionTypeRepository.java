package co.edu.usbcali.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.domain.TransactionType;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long>{

}
