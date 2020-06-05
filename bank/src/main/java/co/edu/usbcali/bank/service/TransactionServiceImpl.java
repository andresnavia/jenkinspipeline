package co.edu.usbcali.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Account;
import co.edu.usbcali.bank.domain.Transaction;
import co.edu.usbcali.bank.domain.TransactionType;
import co.edu.usbcali.bank.domain.Users;
import co.edu.usbcali.bank.repository.AccountRepository;
import co.edu.usbcali.bank.repository.TransactionRepository;
import co.edu.usbcali.bank.repository.TransactionTypeRepository;
import co.edu.usbcali.bank.repository.UsersRepository;

public class TransactionServiceImpl implements TransactionService {
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	TransactionTypeRepository transactionTypeRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	Validator validator;

	@Override
	@Transactional(readOnly = true)
	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Transaction> findById(Long id) {
		return transactionRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Transaction save(Transaction entity) throws Exception {
		validate(entity);
		if (transactionRepository.findById(entity.getTranId()).isPresent() == true) {
			throw new Exception("La transaccion con id " + entity.getTranId() + " ya existe");
		}
		validaUsers(entity.getUsers());
		validaAccount(entity.getAccount());
		validaTransactionType(entity.getTransactionType());
		return transactionRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Transaction update(Transaction entity) throws Exception {
		validate(entity);
		if (transactionRepository.findById(entity.getTranId()).isPresent() == false) {
			throw new Exception("La transaccion con id " + entity.getTranId() + " no existe");
		}
		validaUsers(entity.getUsers());
		validaAccount(entity.getAccount());
		validaTransactionType(entity.getTransactionType());
		return transactionRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Transaction entity) throws Exception {
		validate(entity);
		if (entity == null) {
			throw new Exception("La transaccion es nula");
		}
		Optional<Transaction> transactionOptional = transactionRepository.findById(entity.getTranId());
		if (transactionOptional.isPresent() == false) {
			throw new Exception("La transaccion con  id " + entity.getTranId() + " no existe");
		}
		entity = transactionOptional.get();
		if (entity.getAccount() != null) {
			throw new Exception("La transaccion con  id " + entity.getTranId() + " tiene cuentas asociadas");
		}
		transactionRepository.delete(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Long id) throws Exception {

		Optional<Transaction> transactionOptional = transactionRepository.findById(id);
		Transaction entity = transactionOptional.get();
		if (transactionOptional.isPresent() == false) {
			throw new Exception("La transaccion con  id " + id + " no existe");
		}
		if (entity.getAccount() != null) {
			throw new Exception("La transaccion con  id " + id + " tiene cuentas asociadas");
		}
		transactionRepository.delete(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		// TODO Auto-generated method stub
		return transactionRepository.count();
	}

	@Override
	public void validate(Transaction entity) throws Exception {
		if (entity == null) {
			throw new Exception("La transaccion es nula");
		}

		Set<ConstraintViolation<Transaction>> constraintViolations = validator.validate(entity);

		if (constraintViolations.size() > 0) {
			StringBuilder strMessage = new StringBuilder();

			for (ConstraintViolation<Transaction> constraintViolation : constraintViolations) {
				strMessage.append(constraintViolation.getPropertyPath().toString());
				strMessage.append(" - ");
				strMessage.append(constraintViolation.getMessage());
				strMessage.append(". \n");
			}

			throw new Exception(strMessage.toString());
		}
	}

	private void validaUsers(Users users) throws Exception {
		if(usersRepository.findById(users.getUserEmail()).isPresent()==false) {
			throw new Exception("el usuario "+users.getUserEmail()+"  que intenta asociar con esta transaccion no existe");
		}
	}

	private void validaAccount(Account account) throws Exception {
		if(accountRepository.findById(account.getAccoId()).isPresent()==false) {
			throw new Exception("La account "+account.getAccoId()+"  que intenta asociar con esta transaccion no existe");
		}

	}

	private void validaTransactionType(TransactionType transactionType) throws Exception {
		if(transactionTypeRepository.findById(transactionType.getTrtyId()).isPresent()==false) {
			throw new Exception("el  tipo de transaccion "+transactionType.getTrtyId()+" que intenta asociar con esta transaccion no existe");
		}

	}

}
