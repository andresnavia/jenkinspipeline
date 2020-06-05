package co.edu.usbcali.bank.service;

import java.util.List;

import co.edu.usbcali.bank.domain.Client;

public interface ClientService extends GenericService<Client, Long> {

	List<Client> findByEmail(String string);

}
