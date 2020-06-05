package co.edu.usbcali.bank.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Entity
@Table(name = "transaction", schema = "public")
public class Transaction implements java.io.Serializable {
	private Long tranId;
	@NotNull
	private Account account;
	@NotNull
	private TransactionType transactionType;
	@NotNull
	private Users users;
	@NotNull
	private Double amount;
	@NotNull
	private Date date;

	public Transaction() {
	}

	public Transaction(Long tranId, Account account, Double amount, Date date, TransactionType transactionType,
			Users users) {
		this.tranId = tranId;
		this.account = account;
		this.transactionType = transactionType;
		this.users = users;
		this.amount = amount;
		this.date = date;
	}

	@Id
	@Column(name = "tran_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getTranId() {
		return this.tranId;
	}

	public void setTranId(Long tranId) {
		this.tranId = tranId;
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
	@JoinColumn(name = "trty_id")
	public TransactionType getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_email")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "amount", nullable = false)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "date", nullable = false)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
