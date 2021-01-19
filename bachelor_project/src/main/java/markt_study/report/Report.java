package markt_study.report;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import markt_study.AbstractModel;
import markt_study.study.Study;


@Entity
public class Report extends AbstractModel<Long>{
	
	@Column(nullable = false)
	private String typ;
	
	@Column(nullable = false)
	private String responsible;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String file;
	
	@ManyToOne
	@JoinColumn(nullable = true, name = "study_id")
	private Study studyId;

	public Study getStudyId() {
		return studyId;
	}

	public void setStudyId(Study studyId) {
		this.studyId = studyId;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	

}
