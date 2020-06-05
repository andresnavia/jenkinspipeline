package co.edu.usbcali.bank.domain.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import co.edu.usbcali.bank.domain.DocumentType;
import co.edu.usbcali.bank.dto.ResponseCorrecto;
import co.edu.usbcali.bank.dto.ResponseErrorDTO;
import co.edu.usbcali.bank.dto.DocumentTypeDTO;
import co.edu.usbcali.bank.mapper.DocumentTypeMapper;
import co.edu.usbcali.bank.service.DocumentTypeService;
@RestController
@RequestMapping("/api/documentType/")
@CrossOrigin("*")
public class DocumentTypeController {
	@Autowired
	DocumentTypeService documentTypeService;
	
	@Autowired
	DocumentTypeMapper documentTypeMapper;
	
	
	@DeleteMapping("delete/{dotyId}")
	public ResponseEntity<?> delete(@PathVariable("dotyId") Long dotyId) {

		try {
			documentTypeService.deleteById(dotyId);
			return ResponseEntity.ok().body(new ResponseCorrecto("Se elimino el Tipo de Documento con el id "+dotyId));
		} catch (Exception e) {
			return ResponseEntity.ok().body(new ResponseErrorDTO("400", e.getMessage()));
		}

	}

	@PutMapping("update")
	public ResponseEntity<?> update(@Valid @RequestBody DocumentTypeDTO documentTypeDTO) {
		try {
			DocumentType documentType = documentTypeMapper.toDocumentType(documentTypeDTO);
			documentTypeService.update(documentType);
			documentTypeDTO = documentTypeMapper.toDocumentTypeDTO(documentType);
			return ResponseEntity.ok().body(documentTypeDTO);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseErrorDTO("400", e.getMessage()));
		}
	}

	@PostMapping("save")
	public ResponseEntity<?> save(@Valid @RequestBody DocumentTypeDTO documentTypeDTO) {
		try {
			DocumentType documentType = documentTypeMapper.toDocumentType(documentTypeDTO);
			documentTypeService.save(documentType);
			documentTypeDTO = documentTypeMapper.toDocumentTypeDTO(documentType);
			return ResponseEntity.ok().body(documentTypeDTO);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseErrorDTO("400", e.getMessage()));
		}
	}

	

	@GetMapping("findAll")
	public ResponseEntity<?> findAll() {
		List<DocumentType> documentTypes = documentTypeService.findAll();
		List<DocumentTypeDTO> documentTypeDTOs = documentTypeMapper.documentTypeDTOs(documentTypes);
		return ResponseEntity.ok().body(documentTypeDTOs);
	}

	@GetMapping("findById/{dotyId}")
	public ResponseEntity<?> findById(@PathVariable("dotyId") Long dotyId) {
		Optional<DocumentType> documentTypeOptional = documentTypeService.findById(dotyId);
		if (documentTypeOptional.isPresent() == false) {
			return ResponseEntity.badRequest().body(new ResponseErrorDTO("400", "El Tipo de Documento no existe"));
		}
		DocumentType documentType = documentTypeOptional.get();
		DocumentTypeDTO documentTypeDTO = documentTypeMapper.toDocumentTypeDTO(documentType);
		return ResponseEntity.ok().body(documentTypeDTO);
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
