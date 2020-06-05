package co.edu.usbcali.bank.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Entity
@Table(name = "client", schema = "public")
public class Client implements java.io.Serializable {
	@NotNull
	private Long clieId;
	@NotNull
	private DocumentType documentType;
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
	private List<Account> accounts = new ArrayList<Account>(0);
	private List<RegisteredAccount> registeredAccounts = new ArrayList<RegisteredAccount>(0);

	public Client() {
	}

	public Client(Long clieId, List<Account> accounts, String adress, DocumentType documentType, String email,
			String enable, String name, String phone, List<RegisteredAccount> registeredAccounts) {
		this.clieId = clieId;
		this.documentType = documentType;
		this.adress = adress;
		this.email = email;
		this.enable = enable;
		this.name = name;
		this.phone = phone;
		this.accounts = accounts;
		this.registeredAccounts = registeredAccounts;
	}

	@Id
	@Column(name = "clie_id", unique = true, nullable = false)
	public Long getClieId() {
		return this.clieId;
	}

	public void setClieId(Long clieId) {
		this.clieId = clieId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doty_id")
	public DocumentType getDocumentType() {
		return this.documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	@Column(name = "adress", nullable = false)
	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "enable", nullable = false)
	public String getEnable() {
		return this.enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "phone", nullable = false)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	public List<RegisteredAccount> getRegisteredAccounts() {
		return this.registeredAccounts;
	}

	public void setRegisteredAccounts(List<RegisteredAccount> registeredAccounts) {
		this.registeredAccounts = registeredAccounts;
	}
}
