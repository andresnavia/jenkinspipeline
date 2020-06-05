package co.edu.usbcali.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.domain.RegisteredAccount;

public interface RegisteredAccountRepository extends JpaRepository<RegisteredAccount, Long> {

}
