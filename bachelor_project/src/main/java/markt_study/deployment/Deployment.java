package markt_study.deployment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

import markt_study.AbstractModel;
import markt_study.answering.Answering;
import markt_study.questionner.Questionner;
import markt_study.study.Study;

@Entity
//@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"study_id", "questionner_id"})})
public class Deployment extends AbstractModel<Long>{
	
	
 
    @ManyToOne
    @JoinColumn(name = "study_id")
    Study study;
 
    @ManyToOne
    @JoinColumn(name = "questionner_id")
    Questionner questionner;
 
    @Column(nullable = false, name="typ_intervention")
	private String typeIntervention;
    
    @Column(nullable = false, name="unit_price")
   	private int unitPrice;
  
    
    @Column(nullable = false, name="total_question")
   	private int totalQuestion;
    @Column(nullable = false)
   	private int editing;
    @Column(nullable = false)
   	private int mastering;
    @Column(nullable = false)
   	private int discipline;
    @Column(nullable = false, name="team_work")
   	private int teamWork;
    @Column(nullable = false)
   	private int instruction;
    @Column(nullable = false)
   	private int responsability;
    
    @Column(nullable = false)
   	private String observation;

	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}

	public Questionner getQuestionner() {
		return questionner;
	}

	public void setQuestionner(Questionner questionner) {
		this.questionner = questionner;
	}

	public String getTypeIntervention() {
		return typeIntervention;
	}

	public void setTypeIntervention(String typeIntervention) {
		this.typeIntervention = typeIntervention;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}



	public int getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(int totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public int getEditing() {
		return editing;
	}

	public void setEditing(int editing) {
		this.editing = editing;
	}

	public int getMastering() {
		return mastering;
	}

	public void setMastering(int mastering) {
		this.mastering = mastering;
	}

	public int getDiscipline() {
		return discipline;
	}

	public void setDiscipline(int discipline) {
		this.discipline = discipline;
	}

	public int getTeamWork() {
		return teamWork;
	}

	public void setTeamWork(int teamWork) {
		this.teamWork = teamWork;
	}

	public int getInstruction() {
		return instruction;
	}

	public void setInstruction(int instruction) {
		this.instruction = instruction;
	}

	public int getResponsability() {
		return responsability;
	}

	public void setResponsability(int responsability) {
		this.responsability = responsability;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
    
    
}
