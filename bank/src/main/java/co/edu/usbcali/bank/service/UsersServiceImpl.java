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

import co.edu.usbcali.bank.domain.Users;
import co.edu.usbcali.bank.repository.UserTypeRepository;
import co.edu.usbcali.bank.repository.UsersRepository;
@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	UserTypeRepository userTypeRepository;
	
	@Autowired
	Validator validator;
	@Override
	@Transactional(readOnly = true)
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return usersRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Users> findById(String id) {
		// TODO Auto-generated method stub
		return usersRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Users save(Users entity) throws Exception {
		validate(entity);
		Optional<Users> userOptional=usersRepository.findById(entity.getUserEmail());
		if(userOptional.isPresent()==true) {
			throw new Exception("El Usuario con  email "+entity.getUserEmail()+" ya existe");
		}
		return usersRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Users update(Users entity) throws Exception {
		validate(entity);
		Optional<Users> userOptional=usersRepository.findById(entity.getUserEmail());
		if(userOptional.isPresent()==false) {
			throw new Exception("El Usuario con  email "+entity.getUserEmail()+" no existe");
		}
		return usersRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void delete(Users entity) throws Exception {
		if(entity==null) {
			throw new Exception("El Usuario es nulo");
		}
		Optional<Users> usersOptional=usersRepository.findById(entity.getUserEmail());
		if(usersOptional.isPresent()==false) {
			throw new Exception("El Usuario con  email "+entity.getUserEmail()+" no existe");
		}
		entity=usersOptional.get();
		if(entity.getTransactions().size()>0) {
			throw new Exception("El usuario con  id "+entity.getUserEmail()+" tiene transacciones asociadas");
		}
		usersRepository.delete(entity);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		Optional<Users> userOptional=usersRepository.findById(id);
		if(userOptional.isPresent()==false) {
			throw new Exception("El Usuario con  email "+id+" no existe");
		}
		Users entity=userOptional.get();
		if(entity.getTransactions().size()>0) {
			throw new Exception("El usuario con  id "+entity.getUserEmail()+" tiene transacciones asociadas");
		}
		usersRepository.delete(entity);	
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		// TODO Auto-generated method stub
		return usersRepository.count();
	}

	@Override
	public void validate(Users entity) throws Exception {
		if(entity==null) {
			throw new Exception("El Usuario es nulo");
		}
	    
        Set<ConstraintViolation<Users>> constraintViolations = validator.validate(entity);

        if (constraintViolations.size() > 0) {
            StringBuilder strMessage = new StringBuilder();

            for (ConstraintViolation<Users> constraintViolation : constraintViolations) {
                strMessage.append(constraintViolation.getPropertyPath()
                                                     .toString());
                strMessage.append(" - ");
                strMessage.append(constraintViolation.getMessage());
                strMessage.append(". \n");
            }

            throw new Exception(strMessage.toString());
        }
	}

}
