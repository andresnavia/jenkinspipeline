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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Entity
@Table(name = "account", schema = "public")
public class Account implements java.io.Serializable {
	@NotNull
	private String accoId;
	@NotNull
	private Client client;
	@NotNull
	private Double balance;
	@NotNull
	@NotEmpty
	@Size(max = 1)
	private String enable;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String password;
	@NotNull
	private Long version;
	private List<RegisteredAccount> registeredAccounts = new ArrayList<RegisteredAccount>(0);
	private List<Transaction> transactions = new ArrayList<Transaction>(0);

	public Account() {
	}

	public Account(String accoId, Double balance, Client client, String enable, String password,
			List<RegisteredAccount> registeredAccounts, List<Transaction> transactions, Long version) {
		this.accoId = accoId;
		this.client = client;
		this.balance = balance;
		this.enable = enable;
		this.password = password;
		this.version = version;
		this.registeredAccounts = registeredAccounts;
		this.transactions = transactions;
	}

	@Id
	@Column(name = "acco_id", unique = true, nullable = false)
	public String getAccoId() {
		return this.accoId;
	}

	public void setAccoId(String accoId) {
		this.accoId = accoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clie_id")
	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Column(name = "balance", nullable = false)
	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Column(name = "enable", nullable = false)
	public String getEnable() {
		return this.enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "version", nullable = false)
	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	public List<RegisteredAccount> getRegisteredAccounts() {
		return this.registeredAccounts;
	}

	public void setRegisteredAccounts(List<RegisteredAccount> registeredAccounts) {
		this.registeredAccounts = registeredAccounts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
