package co.edu.usbcali.bank.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Entity
@Table(name = "registered_account", schema = "public")
public class RegisteredAccount implements java.io.Serializable {
	private Long reacId;
	@NotNull
	private Account account;
	@NotNull
	private Client client;
	@NotNull
	@NotEmpty
	@Size(max = 1)
	private String enable;

	public RegisteredAccount() {
	}

	public RegisteredAccount(Long reacId, Account account, Client client, String enable) {
		this.reacId = reacId;
		this.account = account;
		this.client = client;
		this.enable = enable;
	}

	@Id
	@Column(name = "reac_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getReacId() {
		return this.reacId;
	}

	public void setReacId(Long reacId) {
		this.reacId = reacId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "acco_id")
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clie_id")
	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Column(name = "enable", nullable = false)
	public String getEnable() {
		return this.enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
}
