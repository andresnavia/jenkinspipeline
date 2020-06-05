package co.edu.usbcali.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.domain.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
