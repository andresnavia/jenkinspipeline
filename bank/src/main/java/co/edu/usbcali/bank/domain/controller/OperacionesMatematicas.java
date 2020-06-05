package co.edu.usbcali.bank.domain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mat/")
public class OperacionesMatematicas {
	@GetMapping("sumar/{numeroUno}/{numeroDos}")
	public Resultado sumar(@PathVariable("numeroUno")Integer n1, 
						 @PathVariable("numeroDos")Integer n2) {
		return new Resultado(n1 + n2);
	}
	@GetMapping("restar/{numeroUno}/{numeroDos}")
	public Resultado restar(@PathVariable("numeroUno")Integer n1, 
						 @PathVariable("numeroDos")Integer n2) {
		return new Resultado(n1 - n2);
	}
	@GetMapping("divir/{numeroUno}/{numeroDos}")
	public ResponseEntity<?> div(@PathVariable("numeroUno")Integer n1, 
						 @PathVariable("numeroDos")Integer n2) {
		ResultadoDiv re;
		try {
//			if(n2==0) {
//				return ResponseEntity.badRequest().body("Esta realizando una divicion por zero");
//			}
			re=new ResultadoDiv(Float.valueOf(n1)/Float.valueOf(n2));
		} catch (Exception e) {

			return ResponseEntity.badRequest().body(new ErrorServOper(e.getMessage()));
		}

		return ResponseEntity.ok().body(re);
	}
}
class ErrorServOper{
	private String mess;

	public ErrorServOper(String mess) {
		super();
		this.mess = mess;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}
	
}
class ResultadoDiv{
	private Float valor;

	public ResultadoDiv(Float valor) {
		super();
		this.valor = valor;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}
}
class Resultado{
	private Integer valor;

	public Resultado(Integer valor) {
		super();
		this.valor = valor;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
}