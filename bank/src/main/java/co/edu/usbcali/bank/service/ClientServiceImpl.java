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

import co.edu.usbcali.bank.domain.Client;
import co.edu.usbcali.bank.domain.DocumentType;
import co.edu.usbcali.bank.repository.ClientRepository;
import co.edu.usbcali.bank.repository.DocumentTypeRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;
	@Autowired
	DocumentTypeRepository documentTypeRepository;
	@Autowired
	Validator validator;

	@Transactional(readOnly = true)
	public List<Client> findByEmail(String email) {
		// TODO Auto-generated method stub
		return clientRepository.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return clientRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Client> findById(Long id) {
		// TODO Auto-generated method stub
		return clientRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Client save(Client entity) throws Exception {
		validate(entity);
		if (clientRepository.findById(entity.getClieId()).isPresent() == true) {
			throw new Exception("El cliente con id " + entity.getClieId() + " ya existe");
		}
		validaDocumentType(entity.getDocumentType());
		return clientRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Client update(Client entity) throws Exception {
		validate(entity);
		if (clientRepository.findById(entity.getClieId()).isPresent() == false) {
			throw new Exception("El cliente con id " + entity.getClieId() + " no existe");
		}
		validaDocumentType(entity.getDocumentType());
		return clientRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Client entity) throws Exception {
		if (entity == null) {
			throw new Exception("El cliente es nulo");
		}
		Optional<Client> clientOptional = clientRepository.findById(entity.getClieId());
		if (clientOptional.isPresent() == false) {
			throw new Exception("El cliente con  id " + entity.getClieId() + " no existe");
		}
		entity = clientOptional.get();
		if (entity.getAccounts().size() > 0) {
			throw new Exception("El cliente con  id " + entity.getClieId() + " tiene cuentas asociadas");
		}
		if (entity.getRegisteredAccounts().size() > 0) {
			throw new Exception("El cliente con  id " + entity.getClieId() + " tiene cuentas registradas");
		}
		clientRepository.delete(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Long id) throws Exception {
		Optional<Client> clientOptional = clientRepository.findById(id);
		if (clientOptional.isPresent() == false) {
			throw new Exception("El cliente con  id " + id + " no existe");
		}
		Client entity = clientOptional.get();

		if (entity.getAccounts().size() > 0) {
			throw new Exception("El cliente con  id " + entity.getClieId() + " tiene cuentas asociadas");
		}
		if (entity.getRegisteredAccounts().size() > 0) {
			throw new Exception("El cliente con  id " + entity.getClieId() + " tiene cuentas registradas");
		}
		clientRepository.delete(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return clientRepository.count();
	}

	@Override
	public void validate(Client client) throws Exception {

		if (client == null) {
			throw new Exception("El client es nulo");
		}

		Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);

		if (constraintViolations.size() > 0) {
			StringBuilder strMessage = new StringBuilder();

			for (ConstraintViolation<Client> constraintViolation : constraintViolations) {
				strMessage.append(constraintViolation.getPropertyPath().toString());
				strMessage.append(" - ");
				strMessage.append(constraintViolation.getMessage());
				strMessage.append(". \n");
			}

			throw new Exception(strMessage.toString());
		}

	}

	private void validaDocumentType(DocumentType documentType) throws Exception {
		Optional<DocumentType> documentTypeOptional=documentTypeRepository.findById(documentType.getDotyId());
		if(documentTypeOptional.isPresent()==false) {
			throw new Exception("El documentType "+documentType.getDotyId()+" que intenta asociar a este cliente no existe");
		}
	}

}
