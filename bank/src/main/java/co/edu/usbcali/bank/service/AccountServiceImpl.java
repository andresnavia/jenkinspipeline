package co.edu.usbcali.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Account;
import co.edu.usbcali.bank.domain.Client;
import co.edu.usbcali.bank.repository.AccountRepository;
import co.edu.usbcali.bank.repository.ClientRepository;
@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	Validator validator;
	@Override
	@Transactional(readOnly = true)
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Account> findById(String id) {
		return accountRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Account save(Account entity) throws Exception {
		validate(entity);
		Optional<Account> accountOptional= accountRepository.findById(entity.getAccoId());
		if(accountOptional.isPresent()==true) {
			throw new Exception("La cuenta con id "+entity.getAccoId()+" ya existe");
			
		}
		validaCliente(entity.getClient());
		return accountRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Account update(Account entity) throws Exception {
		validate(entity);
		Optional<Account> accountOptional= accountRepository.findById(entity.getAccoId());
		if(accountOptional.isPresent()==false) {
			throw new Exception("La cuenta con id "+entity.getAccoId()+" no existe");
			
		}
		validaCliente(entity.getClient());
		return accountRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void delete(Account entity) throws Exception {
		if(entity==null) {
			throw new Exception("La cuenta es null");
		}
		Optional<Account> accountOptional=accountRepository.findById(entity.getAccoId());
		if(accountOptional.isPresent()==false) {
			throw new Exception("La cuenta  con  id "+entity.getAccoId()+" no existe");
		}
		entity=accountOptional.get();
		if(entity.getRegisteredAccounts().size()>0) {
			throw new Exception("La cuenta  con  id "+entity.getAccoId()+" tiene cuentas registradas");
		}
		if(entity.getTransactions().size()>0) {
			throw new Exception("La cuenta  con  id "+entity.getAccoId()+" tiene transacciones asociadas.");
		}
		accountRepository.delete(entity);

	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {

		Optional<Account> accountOptional=accountRepository.findById(id);
		if(accountOptional.isPresent()==false) {
			throw new Exception("La cuenta  con  id "+id+" no existe");
		}
		Account entity=accountOptional.get();
		if(entity.getRegisteredAccounts().size()>0) {
			throw new Exception("La cuenta  con  id "+entity.getAccoId()+" tiene cuentas registradas");
		}
		if(entity.getTransactions().size()>0) {
			throw new Exception("La cuenta  con  id "+entity.getAccoId()+" tiene transacciones asociadas.");
		}
		accountRepository.delete(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return accountRepository.count();
	}

	@Override
	public void validate(Account account) throws Exception {
		if(account==null) {
			throw new Exception("La cuenta es null");
		}
	    
        Set<ConstraintViolation<Account>> constraintViolations = validator.validate(account);

        if (constraintViolations.size() > 0) {
            StringBuilder strMessage = new StringBuilder();

            for (ConstraintViolation<Account> constraintViolation : constraintViolations) {
                strMessage.append(constraintViolation.getPropertyPath()
                                                     .toString());
                strMessage.append(" - ");
                strMessage.append(constraintViolation.getMessage());
                strMessage.append(". \n");
            }

            throw new Exception(strMessage.toString());
        }
	}
	
	private void validaCliente(Client client) throws Exception {
		Optional<Client> clientOptional=clientRepository.findById(client.getClieId());
		if(clientOptional.isPresent()==false) {
			throw new Exception("El cliente "+client.getClieId()+" que intenta asociar a esta cuenta no existe");
		}
	}

}
