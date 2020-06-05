package co.edu.usbcali.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.DocumentType;
import co.edu.usbcali.bank.repository.DocumentTypeRepository;
@Service
public class DocumentTypeServiceImp implements DocumentTypeService {

	@Autowired
	DocumentTypeRepository documentTypeRepository;
	@Autowired
	Validator validator;
	@Override
	@Transactional(readOnly = true)
	public List<DocumentType> findAll() {
		// TODO Auto-generated method stub
		return documentTypeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<DocumentType> findById(Long id) {
		// TODO Auto-generated method stub
		return documentTypeRepository.findById(id);
	}

	@Override
	public DocumentType save(DocumentType entity) throws Exception {
		validate(entity);
		if (documentTypeRepository.findById(entity.getDotyId()).isPresent() == false) {
			throw new Exception("El DocumentType con id " + entity.getDotyId() + " no existe");
		}
		return documentTypeRepository.save(entity);
	}

	@Override
	public DocumentType update(DocumentType entity) throws Exception {
		validate(entity);
		if (documentTypeRepository.findById(entity.getDotyId()).isPresent() == false) {
			throw new Exception("El DocumentType con id " + entity.getDotyId() + " no existe");
		}
		return documentTypeRepository.save(entity);
	}

	@Override
	public void delete(DocumentType entity) throws Exception {
		if (entity == null) {
			throw new Exception("El DocumentType es nulo");
		}
		Optional<DocumentType> documentTypeOptional = documentTypeRepository.findById(entity.getDotyId());
		if (documentTypeOptional.isPresent() == false) {
			throw new Exception("El DocumentType con  id " + entity.getDotyId() + " no existe");
		}
		entity = documentTypeOptional.get();

		if (entity.getClients().size() > 0) {
			throw new Exception("El DocumentType con  id " + entity.getDotyId() + " tiene clientes asociados");
		}
		documentTypeRepository.delete(entity);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		Optional<DocumentType> documentTypeOptional = documentTypeRepository.findById(id);
		if (documentTypeOptional.isPresent() == false) {
			throw new Exception("El DocumentType con  id " + id + " no existe");
		}
		DocumentType entity = documentTypeOptional.get();

		if (entity.getClients().size() > 0) {
			throw new Exception("El DocumentType con  id " + id + " tiene clientes asociados");
		}
		documentTypeRepository.delete(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return documentTypeRepository.count();
	}

	@Override
	public void validate(DocumentType entity) throws Exception {
		if (entity == null) {
			throw new Exception("El DocumentType es nulo");
		}

		Set<ConstraintViolation<DocumentType>> constraintViolations = validator.validate(entity);

		if (constraintViolations.size() > 0) {
			StringBuilder strMessage = new StringBuilder();

			for (ConstraintViolation<DocumentType> constraintViolation : constraintViolations) {
				strMessage.append(constraintViolation.getPropertyPath().toString());
				strMessage.append(" - ");
				strMessage.append(constraintViolation.getMessage());
				strMessage.append(". \n");
			}

			throw new Exception(strMessage.toString());
		}

	}

}
