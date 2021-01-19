package markt_study.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lowagie.text.DocumentException;

import markt_study.answering.AnswImpl;
import markt_study.answering.Answering;
import markt_study.configuration.DeploymentPdfService;
import markt_study.deployment.DeployImpl;
import markt_study.deployment.Deployment;
import markt_study.participation.Participation;
import markt_study.participation.ParticipationImpl;
import markt_study.questionner.QuestImpl;
import markt_study.questionner.Questionner;
import markt_study.study.Study;
import markt_study.study.StudyImpl;



@Controller
public class SupervisionController { 
	
	@Autowired
	private AnswImpl answImpl;
	
	@Autowired
	private StudyImpl studyImpl;
	
	@Autowired
	private ParticipationImpl partImpl;
	
	@Autowired
	private QuestImpl questImpl;
	
	@Autowired
	private DeployImpl deployImpl;
	
	
	@Autowired
	private DeploymentPdfService pdfService;
	
	/**
	 * home page of supervision
	 * @return name of the page
	 */
	@GetMapping("supervision/home_supervision")
	public String home_supervision() {		
			return "supervision/home_supervision";
		}
	
	/**
	 * get method to call form page to deploy answering
	 * @param model object Model
	 * @return return form page to deploy answering
	 */
	@GetMapping("supervision/answering_form_deploy")
	public String answering_form_deploy(Model model) {	
			List<Study> studies = studyImpl.findByTyp("quality");
			List<Answering> answering = answImpl.getAll();
			model.addAttribute("studies", studies);
			model.addAttribute("answering", answering);
			return "supervision/answering_form_deploy";
		}
	
	/**
	 * post method to deploy a answering
	 * @param reasons reasons of answering
	 * @param about_product about product
	 * @param interview_date date of interview
	 * @param ra
	 * @param answering answering id
	 * @param study study id
	 * @return return page to view answering
	 * @throws ParseException
	 */
	@RequestMapping(value = "/answeringDeploy/save",  method = RequestMethod.POST)
	public String saveAnsDeploy(@RequestParam(value = "reasons") String reasons,@RequestParam(value = "about_product") String about_product, @RequestParam(value = "interview_date") String interview_date, final RedirectAttributes ra, @RequestParam(value = "answering") Long answering, @RequestParam(value = "study") Long study) throws ParseException {

		   try {
		   Participation participation = new Participation();
		   participation.setAboutProduct(about_product);
		   participation.setReasons(reasons);
		   participation.setInterviewDate(new SimpleDateFormat("yyyy-MM-dd").parse(interview_date));
		   participation.setAnswering(answImpl.get(answering));
		   participation.setStudy(studyImpl.get(study));
		   
		   partImpl.save(participation);
		   
	       ra.addFlashAttribute("successFlash", "Answering Deploy has been successfully saved.");
	       
		   } catch (DataIntegrityViolationException e) {
			   ra.addFlashAttribute("errorFlash", "This Answering already participe to this study.");
			 
		   }
		   return "redirect:/supervision/answering_view?id="+answering;

	}
     	
	
/**
 * get method to show all answering
 * @param model
 * @param search lastname or firstname of answering
 * @return return page to show answering
 */
	@GetMapping("supervision/answering_view_list")
	public String answering_view_list(Model model, @RequestParam(required=false) String search) {		
		List<Answering> liste = answImpl.getAll();
		String s="";
		if(search!=null && !search.isEmpty()) {
			liste=answImpl.findByFirstNameContainingOrLastNameContainingOrId(search, search,2l);
			s=search;
		}
		System.out.println(liste); 
        model.addAttribute("list", liste);
        model.addAttribute("search", s);
		return "supervision/answering_view_list";
	}
	
	
	/**
	 * get method to show a answering
	 * @param id answering id
	 * @param model
	 * @return return page to show a answering
	 */
	@GetMapping("supervision/answering_view")
	public String answering_view(@RequestParam Optional<Long> id, Model model) {	
		if(!id.isPresent()) {
			return "redirect:/supervision/answering_view_list";
		}
		Answering data = answImpl.get(id.get());
		model.addAttribute("data", data);
		model.addAttribute("studiesSize", partImpl.findByAnswering(data).size());
		
		return "supervision/answering_view";
	}
	
/**
 * get method to call form page to deploy questioner
 * @param model
 * @return return form page to deploy a questioner
 */
	@GetMapping("supervision/questioner_form_deploy")
	public String questioner_form_deploy(Model model) {
		List<Study> studies = studyImpl.getAll();
		List<Questionner> questionner = questImpl.getAll();
		model.addAttribute("studies", studies);
		model.addAttribute("questionner", questionner);
			return "supervision/questioner_form_deploy"; 
		}
	
	/**
	 * post method to deploy a questioner
	 * @param discipline
	 * @param editing
	 * @param mastering
	 * @param instruction
	 * @param unitPrice
	 * @param teamWork
	 * @param totalQuestion
	 * @param typeIntervention
	 * @param responsability
	 * @param observation
	 * @param ra
	 * @param questionner question id
	 * @param study study id
	 * @return return 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/questionerDeploy/save",  method = RequestMethod.POST)
	public String saveQuestDeploy(@RequestParam(value = "discipline") int discipline,
			@RequestParam(value = "editing") int editing,
			@RequestParam(value = "mastery") int mastering,
			@RequestParam(value = "instruction") int instruction,
			@RequestParam(value = "unit_price") int unitPrice,
			@RequestParam(value = "team_work") int teamWork,
			@RequestParam(value = "number_of_question") int totalQuestion,
			@RequestParam(value = "type_intervation") String typeIntervention,
			@RequestParam(value = "responsability") int responsability,
			@RequestParam(value = "observation") String observation,
			final RedirectAttributes ra,
			@RequestParam(value = "questionner") Long questionner, 
			@RequestParam(value = "study") Long study) throws ParseException {
		  

		   try {
		   Deployment deploy = new Deployment();
		   deploy.setDiscipline(discipline);
		   deploy.setEditing(editing);
		   deploy.setObservation(observation);
		   deploy.setInstruction(instruction);
		   deploy.setMastering(mastering);
		   deploy.setResponsability(responsability);
		   deploy.setTeamWork(teamWork);
		   deploy.setTotalQuestion(totalQuestion);
		   deploy.setUnitPrice(unitPrice);
		   deploy.setTypeIntervention(typeIntervention);
		   deploy.setStudy(studyImpl.get(study));
		   deploy.setQuestionner(questImpl.get(questionner));
		   
		   deployImpl.save(deploy);
		   
	       ra.addFlashAttribute("successFlash", "Questionner successful deploy");
	       
		   } catch (DataIntegrityViolationException e) {
			   ra.addFlashAttribute("errorFlash", "This Questionner already participe to this study.");
			 
		   }
		   return "redirect:/supervision/questioner_view?id="+questionner;

	}
	/**
	 * get method to show payslip of questioner
	 * @param model
	 * @param idQuestioner questioner id
	 * @param idStudy study id
	 * @return return page to show payslip of questioner
	 */
	@GetMapping("supervision/questioner_payslip")
	public String questioner_payslip(Model model,@RequestParam Optional<String> idQuestioner,@RequestParam Optional<String> idStudy) {		
		List<Deployment> liste = deployImpl.getAll();
		String quest="";
		String study="";
		if((idQuestioner.isPresent()) && (idStudy.isPresent())) {
			quest=idQuestioner.get().trim();
			study=idStudy.get().trim();
			if((quest!="")&&(study!="")) {
				List<Questionner> q =questImpl.findByFirstNameContainingOrQuestionnerIdContaining(idQuestioner.get(), idQuestioner.get());
				List<Study> s =studyImpl.findByStudyNameContainingOrStudyIdContaining(idStudy.get(),idStudy.get());
				if((s.size()>0)&&(q.size()>0)) {
					liste=deployImpl.findByStudyAndQuestionner(s.get(0), q.get(0));
				} else {
					liste.clear();
				}
			}else {
				liste.clear();
			}
		} 
	
		model.addAttribute("list", liste);
		model.addAttribute("idQuestioner", quest);
		model.addAttribute("idStudy", study);
		return "supervision/questioner_payslip";
		}
	
	/**
	 * get method to show info payslip
	 * @param model
	 * @param id questioner id
	 * @return return page of payslip info
	 */
	@GetMapping("supervision/questioner_payslipview")
	public String questioner_payslipview(Model model,@RequestParam Optional<Long> id) {		
		
		if(!id.isPresent()) {
			 return "redirect:/supervision/questioner_payslip";
		} 
		
		
		Deployment deploy = deployImpl.get(id.get());
		model.addAttribute("deploy", deploy);;
		return "supervision/questioner_payslipview";
	}
	/**
	 * get method to generate payslip as pdf file
	 * @param id
	 * @param response
	 */
	
	 @GetMapping("/supervision/deployment-pdf")
	    public void downloadPDFResource(@RequestParam("id") Long id , HttpServletResponse response) {
	        try {
	            Path file = Paths.get(pdfService.generatePdf(id).getAbsolutePath());
	            if (Files.exists(file)) {
	                response.setContentType("application/pdf");
	                response.addHeader("Content-Disposition",
	                        "attachment; filename=" + file.getFileName());
	                Files.copy(file, response.getOutputStream());
	                response.getOutputStream().flush();
	            }
	        } catch (DocumentException | IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	 
	/**
	 * get method to show a questioner
	 * @param id questioner id
	 * @param model
	 * @return return page to show questioner
	 */
	@GetMapping("supervision/questioner_view")
	public String questioner_view(@RequestParam Optional<Long> id, Model model) {	
		if(!id.isPresent()) {
			return "redirect:/supervision/questioner_view_list";
		}
		Questionner quest = questImpl.get(id.get());
		model.addAttribute("data", quest);
		model.addAttribute("studiesSize", deployImpl.findByQuestionner(quest).size());
			return "supervision/questioner_view";
		}
	
	/**
	 * get method to show all questioner
	 * @param model
	 * @param search questioner id
	 * @return return page to show questioner
	 */
	@GetMapping("supervision/questioner_view_list")
	public String questioner_view_list(Model model, @RequestParam(required=false) String search) {	
		String s="";
		List<Questionner> liste = questImpl.getAll();
		if(search!=null && !search.isEmpty()) {
			liste=questImpl.findByFirstNameContainingOrQuestionnerIdContaining(search, search);
			s=search;
		}
		model.addAttribute("list", liste);
		model.addAttribute("search", s);
			return "supervision/questioner_view_list";
		}
	
	/**
	 * get method to show study list
	 * @param model
	 * @param search study id
	 * @return return page to show list of study
	 */
	@GetMapping("supervision/study_view_list")
	public String study_view_list(Model model, @RequestParam(required=false) String search) {		
		List<Study> liste = studyImpl.getAll();
		String s="";
		if(search!=null && !search.isEmpty()) {
			liste=studyImpl.findByStudyNameContainingOrStudyIdContaining(search,search);
			s=search;
		}
		
        model.addAttribute("list", liste);
        model.addAttribute("search", s);
		return "supervision/study_view_list";
		}
}

