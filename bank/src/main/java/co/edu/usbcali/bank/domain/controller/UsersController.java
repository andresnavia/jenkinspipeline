package co.edu.usbcali.bank.domain.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.domain.Users;
import co.edu.usbcali.bank.dto.ResponseCorrecto;
import co.edu.usbcali.bank.dto.ResponseErrorDTO;
import co.edu.usbcali.bank.dto.UsersDTO;
import co.edu.usbcali.bank.mapper.UsersMapper;
import co.edu.usbcali.bank.service.UsersService;

@RestController
@RequestMapping("/api/users/")
public class UsersController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	UsersMapper usersMapper;
	
	
	@DeleteMapping("delete/{userEmail}")
	public ResponseEntity<?> delete(@PathVariable("userEmail") String userEmail) {

		try {
			usersService.deleteById(userEmail);
			return ResponseEntity.ok().body(new ResponseCorrecto("Se elimino el usuario con el email "+userEmail));
		} catch (Exception e) {
			return ResponseEntity.ok().body(new ResponseErrorDTO("400", e.getMessage()));
		}

	}

	@PutMapping("update")
	public ResponseEntity<?> update(@Valid @RequestBody UsersDTO usersDTO) {
		try {
			Users users = usersMapper.toUsers(usersDTO);
			usersService.update(users);
			usersDTO = usersMapper.toUsersDTO(users);
			return ResponseEntity.ok().body(usersDTO);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseErrorDTO("400", e.getMessage()));
		}
	}

	@PostMapping("save")
	public ResponseEntity<?> save(@Valid @RequestBody UsersDTO usersDTO) {
		try {
			Users users = usersMapper.toUsers(usersDTO);
			usersService.save(users);
			usersDTO = usersMapper.toUsersDTO(users);
			return ResponseEntity.ok().body(usersDTO);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseErrorDTO("400", e.getMessage()));
		}
	}

	

	@GetMapping("findAll")
	public ResponseEntity<?> findAll() {
		List<Users> userss = usersService.findAll();
		List<UsersDTO> usersDTOs = usersMapper.usersDTOs(userss);
		return ResponseEntity.ok().body(usersDTOs);
	}

	@GetMapping("findById/{userEmail}")
	public ResponseEntity<?> findById(@PathVariable("userEmail") String userEmail) {
		Optional<Users> usersOptional = usersService.findById(userEmail);
		if (usersOptional.isPresent() == false) {
			return ResponseEntity.badRequest().body(new ResponseErrorDTO("400", "El usuario no existe"));
		}
		Users users = usersOptional.get();
		UsersDTO usersDTO = usersMapper.toUsersDTO(users);
		return ResponseEntity.ok().body(usersDTO);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
		StringBuilder strMessage = new StringBuilder();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			strMessage.append(fieldName);
			strMessage.append("-");
			strMessage.append(errorMessage);
		});
		return ResponseEntity.badRequest().body(new ResponseErrorDTO("400", strMessage.toString()));
	}
	
	
}
