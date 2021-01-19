package markt_study.participation;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

import markt_study.AbstractModel;
import markt_study.answering.Answering;
import markt_study.study.Study;

@Entity
//@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"study_id", "answering_id"})})
public class Participation extends AbstractModel<Long>{
	
	
 
    @ManyToOne
    @JoinColumn(name = "study_id")
    Study study;
 
    @ManyToOne
    @JoinColumn(name = "answering_id")
    Answering answering;
 
    @Column(nullable = false)
	private String reasons;
    
    @Column(nullable = false, name="interview_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date interviewDate;
    
    @Column(nullable = false, name="about_product")
   	private String aboutProduct;

	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}

	public Answering getAnswering() {
		return answering;
	}

	public void setAnswering(Answering answering) {
		this.answering = answering;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public Date getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	public String getAboutProduct() {
		return aboutProduct;
	}

	public void setAboutProduct(String aboutProduct) {
		this.aboutProduct = aboutProduct;
	}

}
