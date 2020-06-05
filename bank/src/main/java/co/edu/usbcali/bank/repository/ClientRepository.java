package co.edu.usbcali.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.bank.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	public List<Client>  findByEmail(String email);
	public List<Client>  findByNameLike(String name);
	@Query("FROM Client cli WHERE size(cli.accounts)>2")
	public List<Client>  findWithTwoAccounts();
}
