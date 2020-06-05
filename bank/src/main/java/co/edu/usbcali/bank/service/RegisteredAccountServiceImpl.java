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
import co.edu.usbcali.bank.domain.RegisteredAccount;
import co.edu.usbcali.bank.repository.AccountRepository;
import co.edu.usbcali.bank.repository.ClientRepository;
import co.edu.usbcali.bank.repository.RegisteredAccountRepository;
@Service
public class RegisteredAccountServiceImpl implements RegisteredAccountService {
	@Autowired
	RegisteredAccountRepository registeredAccountRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	Validator validator;
	@Override
	@Transactional(readOnly = true)
	public List<RegisteredAccount> findAll() {
		return registeredAccountRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<RegisteredAccount> findById(Long id) {
		return registeredAccountRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public RegisteredAccount save(RegisteredAccount entity) throws Exception {
		validate(entity);
		if(registeredAccountRepository.findById(entity.getReacId()).isPresent()==true) {
			throw  new Exception("La cuenta registrada con id "+entity.getReacId()+" ya existe");
			
		}
		validaClient(entity.getClient());
		validaAccount(entity.getAccount());
		return registeredAccountRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public RegisteredAccount update(RegisteredAccount entity) throws Exception {
		validate(entity);
		if(registeredAccountRepository.findById(entity.getReacId()).isPresent()==false) {
			throw  new Exception("La cuenta registrada con id "+entity.getReacId()+" ya existe");
			
		}
		validaClient(entity.getClient());
		validaAccount(entity.getAccount());
		return registeredAccountRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void delete(RegisteredAccount entity) throws Exception {
		validate(entity);
		if(entity==null) {
			throw new Exception("La cuenta registrada es nula");
		}
		Optional<RegisteredAccount> registredAccountOptional=registeredAccountRepository.findById(entity.getReacId());
		if(registredAccountOptional.isPresent()==false) {
			throw new Exception("La cuenta registrada con  id "+entity.getReacId()+" no existe");
		}
		entity=registredAccountOptional.get();
		if(entity.getAccount()!=null) {
			throw new Exception("La cuenta registrada con  id "+entity.getReacId()+" tiene cuentas asociadas");
		}
		registeredAccountRepository.delete(entity);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void deleteById(Long id) throws Exception {
		
		Optional<RegisteredAccount> registredAccountOptional=registeredAccountRepository.findById(id);
		RegisteredAccount entity=registredAccountOptional.get();
		if(registredAccountOptional.isPresent()==false) {
			throw new Exception("La cuenta registrada con  id "+entity.getReacId()+" no existe");
		}
		if(entity.getAccount()!=null) {
			throw new Exception("La cuenta registrada con  id "+entity.getReacId()+" tiene cuentas asociadas");
		}
		registeredAccountRepository.delete(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		// TODO Auto-generated method stub
		return registeredAccountRepository.count();
	}

	@Override
	public void validate(RegisteredAccount entity) throws Exception {
		if(entity==null) {
			throw new Exception("La cuenta registrada es nula");
		}
	    
        Set<ConstraintViolation<RegisteredAccount>> constraintViolations = validator.validate(entity);

        if (constraintViolations.size() > 0) {
            StringBuilder strMessage = new StringBuilder();

            for (ConstraintViolation<RegisteredAccount> constraintViolation : constraintViolations) {
                strMessage.append(constraintViolation.getPropertyPath()
                                                     .toString());
                strMessage.append(" - ");
                strMessage.append(constraintViolation.getMessage());
                strMessage.append(". \n");
            }

            throw new Exception(strMessage.toString());
        }
	}
	
	private void validaClient(Client client) throws Exception {
		if(clientRepository.findById(client.getClieId()).isPresent()==false) {
			throw new Exception("El cliente con id "+client.getClieId() +" no existe");
		}
	}
	private void validaAccount(Account account) throws Exception {
		if(accountRepository.findById(account.getAccoId()).isPresent()==false) {
			throw new Exception("La cuenta con id "+account.getAccoId() +" no existe");
		}
	}

}
