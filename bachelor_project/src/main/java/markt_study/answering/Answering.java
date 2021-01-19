package markt_study.answering;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import markt_study.AbstractModel;
import markt_study.participation.Participation;
import markt_study.study.Study;


@Entity(name="answering")
public class Answering  extends AbstractModel<Long>{

	@Column(nullable = false, name="first_name")
	private String firstName;
	
	@Column(nullable = false, name="last_name")
	private String lastName;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthdate;
	
	@Column(nullable = false)
	private String tel;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String gendre;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String social_class;
	
	@Column(nullable = false)
	private String observation;
	
	@Column(nullable = false)
	private String profession;
	
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Participation> studies = new HashSet<>();
	

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Participation> getStudies() {
		return studies;
	}

	public void setStudies(Set<Participation> studies) {
		this.studies = studies;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGendre() {
		return gendre;
	}

	public void setGendre(String gendre) {
		this.gendre = gendre;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSocial_class() {
		return social_class;
	}

	public void setSocial_class(String social_class) {
		this.social_class = social_class;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
}
