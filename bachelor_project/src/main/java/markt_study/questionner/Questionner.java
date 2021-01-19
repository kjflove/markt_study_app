package markt_study.questionner;

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
import markt_study.deployment.Deployment;
import markt_study.study.Study;

@Entity(name="questionner")
public class Questionner extends AbstractModel<Long>{

	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Deployment> studies = new HashSet<>();
	
	@Column(nullable = false, name="questionner_id", unique=true)
	private String questionnerId;
	
	@Column(nullable = false, name="first_name")
	private String firstName;
	
	@Column(nullable = false, name="last_name")
	private String lastName;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(nullable = false)
	private String tel;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String area;
	
	@Column(nullable = false)
	private String level;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthdate;

	public Set<Deployment> getStudies() {
		return studies;
	}

	public void setStudies(Set<Deployment> studies) {
		this.studies = studies;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getQuestionnerId() {
		return questionnerId;
	}

	public void setQuestionnerId(String questionnerId) {
		this.questionnerId = questionnerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	
}
