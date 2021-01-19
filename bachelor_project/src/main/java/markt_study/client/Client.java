package markt_study.client;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import markt_study.AbstractModel;
import markt_study.study.Study;

@Entity(name="client")
public class Client extends AbstractModel<Long>{

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String branch;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String tel;
	
	@Column(nullable = false, name="social_reason")
	private String socialReason;
	
	@OneToMany(mappedBy="clientId", fetch = FetchType.EAGER)
	private Set<Study> studies = new HashSet<>();

	public Set<Study> getStudies() {
		return studies;
	}

	public void setStudies(Set<Study> studies) {
		this.studies = studies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSocialReason() {
		return socialReason;
	}

	public void setSocialReason(String socialReason) {
		this.socialReason = socialReason;
	}
	
}
