package markt_study.study;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import markt_study.AbstractModel;
import markt_study.answering.Answering;
import markt_study.client.Client;
import markt_study.deployment.Deployment;
import markt_study.participation.Participation;
import markt_study.questionner.Questionner;
import markt_study.report.Report;


@Entity(name="study")
public class Study extends AbstractModel<Long>{

	@Column(nullable = false, name="study_name")
	private String studyName;
	
	@Column(nullable = false, name="start_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@Column(nullable = false, name="end_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	@Column(nullable = false)
	private String typ;
	
	@Column(nullable = false)
	private String branch;
	
	@Column(nullable = false)
	private int sample;
	
	@Column(nullable = false, name="study_id", unique=true)
	private String studyId;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client clientId;
	
	
	
	@OneToMany(mappedBy="studyId", fetch = FetchType.EAGER)
	private Set<Report> report = new HashSet<>();
	
	
	@OneToMany()
	private Set<Deployment> questionners = new HashSet<Deployment>();
	
	
	@OneToMany()
	private Set<Participation> answerings = new HashSet<Participation>();
	

	

	public Set<Report> getReport() {
		return report;
	}

	public void setReport(Set<Report> report) {
		this.report = report;
	}

	public Set<Participation> getAnswerings() {
		return answerings;
	}

	public void setAnswerings(Set<Participation> answerings) {
		this.answerings = answerings;
	}

	public Set<Deployment> getQuestionners() {
		return questionners;
	}

	public void setQuestionners(Set<Deployment> questionners) {
		this.questionners = questionners;
	}

	public Client getClientId() {
		return clientId;
	}

	public void setClientId(Client clientId) {
		this.clientId = clientId;
	}

	public String getStudyName() {
		return studyName;
	}

	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getSample() {
		return sample;
	}

	public void setSample(int sample) {
		this.sample = sample;
	}

	public String getStudyId() {
		return studyId;
	}

	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}
	
}
