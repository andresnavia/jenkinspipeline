package co.edu.usbcali.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.domain.Users;

public interface UsersRepository extends JpaRepository<Users, String>{

}
