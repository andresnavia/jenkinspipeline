package co.edu.usbcali.bank.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClientDTO {
	@NotNull
	private Long clieId;
	
	@NotNull
	private Long dotyId;
	
	@NotNull
	@NotEmpty
	@Size(min=5, max = 255)
	private String adress;
	
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Email
	private String email;
	
	@NotNull
	@NotEmpty
	@Size(max = 1)
	private String enable;
	
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String name;
	
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String phone;

	
	
	public ClientDTO() {
		super();
	}



	public ClientDTO(@NotNull Long clieId, @NotNull Long dotyId,
			@NotNull @NotEmpty @Size(min = 5, max = 255) String adress,
			@NotNull @NotEmpty @Size(max = 255) @Email String email, @NotNull @NotEmpty @Size(max = 1) String enable,
			@NotNull @NotEmpty @Size(max = 255) String name, @NotNull @NotEmpty @Size(max = 255) String phone) {
		super();
		this.clieId = clieId;
		this.dotyId = dotyId;
		this.adress = adress;
		this.email = email;
		this.enable = enable;
		this.name = name;
		this.phone = phone;
	}



	public Long getClieId() {
		return clieId;
	}



	public void setClieId(Long clieId) {
		this.clieId = clieId;
	}



	public Long getDotyId() {
		return dotyId;
	}



	public void setDotyId(Long dotyId) {
		this.dotyId = dotyId;
	}



	public String getAdress() {
		return adress;
	}



	public void setAdress(String adress) {
		this.adress = adress;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getEnable() {
		return enable;
	}



	public void setEnable(String enable) {
		this.enable = enable;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}
