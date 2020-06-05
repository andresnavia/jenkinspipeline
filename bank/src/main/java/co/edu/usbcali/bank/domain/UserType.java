package co.edu.usbcali.bank.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "user_type", schema = "public")
public class UserType implements java.io.Serializable {
	private Long ustyId;
	@NotNull
	@NotEmpty
	@Size(max = 1)
	private String enable;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String name;
	private List<Users> userses = new ArrayList<Users>(0);

	public UserType() {
	}

	public UserType(Long ustyId, String enable, String name, List<Users> userses) {
		this.ustyId = ustyId;
		this.enable = enable;
		this.name = name;
		this.userses = userses;
	}

	@Id
	@Column(name = "usty_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getUstyId() {
		return this.ustyId;
	}

	public void setUstyId(Long ustyId) {
		this.ustyId = ustyId;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userType")
	public List<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(List<Users> userses) {
		this.userses = userses;
	}
}
