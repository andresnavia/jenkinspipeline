package co.edu.usbcali.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.domain.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction, Long>{

}
