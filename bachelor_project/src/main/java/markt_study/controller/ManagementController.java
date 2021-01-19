package markt_study.controller;

import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import markt_study.answering.AnswImpl;
import markt_study.answering.Answering;
import markt_study.client.ClientImpl;
import markt_study.deployment.DeployImpl;
import markt_study.deployment.Deployment;
import markt_study.participation.Participation;
import markt_study.participation.ParticipationImpl;
import markt_study.questionner.QuestImpl;
import markt_study.questionner.Questionner;
import markt_study.report.Report;
import markt_study.report.ReportImpl;
import markt_study.storage.FileData;
import markt_study.storage.FilesStorageServiceImpl;
import markt_study.storage.StorageService;
import markt_study.study.Study;
import markt_study.study.StudyImpl;

@Controller
public class ManagementController {
	
	@Autowired
    StorageService storageService;
	@Autowired
	private ClientImpl clientImpl;

	@Autowired
	private ParticipationImpl partImpl;
	
	@Autowired
	private DeployImpl deployImpl;
	

	@Autowired
	private AnswImpl answImpl;
	
	@Autowired
	private QuestImpl questionnerImpl;
	
	
	@Autowired
	private StudyImpl studyImpl;
	
	@Autowired
	private ReportImpl reportImpl;
	
	/**
	 * get method to call home page management
	 * @return the home page of management
	 */
	@GetMapping("management/home_management")
	public String home_management() {		
			return "study_management/home_management";  
		}
	
	/**
	 * get method to call form to save a new answering
	 * @return return form page to save a answering
	 */
	@GetMapping("management/answering_form_regist")
	public String answering_form_regist() {		
			return "study_management/answering_form_regist";
	}
	
	/**
	 * post method to save a new answering
	 * @param answ object of type Answering
	 * @param ra contains message
	 * @return return form page the save answering
	 */
	@RequestMapping(value = "/answering/save", method = RequestMethod.POST)
	public String save(Answering answ, final RedirectAttributes ra) {
			
		   //System.out.println(membre.getDate_inscription());
		   Answering save = answImpl.save(answ);
	       ra.addFlashAttribute("successFlash", "Answering successful saving");
	       return "redirect:/management/answering_form_regist";

	} 
     	
	
	/**
	 * get method to call page to save a new questioner
	 * @return return form page to save a questioner
	 */
	@GetMapping("management/questioner_form_regist") 
	public String questioner_form_regist() {		
			return "study_management/questioner_form_regist";
	}
	
	
	/**
	 * post method to save a new questioner
	 * @param questionner  object of type Questioner
	 * @param ra contains message
	 * @return return form page to save a questioner
	 */
	@RequestMapping(value = "/questionner/save",  method = RequestMethod.POST)
	public String save(Questionner questionner, final RedirectAttributes ra) {
		   try {  
		   Questionner save = questionnerImpl.save(questionner);
	       ra.addFlashAttribute("successFlash", "Successful Questionner save ");
	       
		   } catch (DataIntegrityViolationException e) {
			   ra.addFlashAttribute("errorFlash", "Questionner id aldready exist.");
			 
		   }
		   
		   return "redirect:/management/questioner_form_regist";

	}

	/**
	 * get method to download study report
	 * @param model object of type Model
	 * @param search study id
	 * @return return page of list to download report
	 */
	@GetMapping("management/study_report_download")
	public String study_report_download(Model model, @RequestParam(required=false) String search) {	
			List<Report> liste = reportImpl.getAll();
			
			
			String s="";
			if(search!=null && !search.isEmpty()) {
				liste=reportImpl.findByStudyIdContaining(search);
				s=search;
			}
			
			
			model.addAttribute("list", liste);
			model.addAttribute("search", s);
			return "study_management/study_report_download";
	}
	
	/**
	 * get method to save a file
	 * @param filename name of file
	 * @return return a responseEntity
	 */
	 @GetMapping("/download/{filename:.+}")
	 @ResponseBody
	    public ResponseEntity<Resource>downloadFile(@PathVariable String filename) {

	        Resource resource = storageService.loadAsResource(filename);

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION,
	                        "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	 }
	
	 /**
	  * get method to call page to upload report
	  * @param model object of type Model
	  * @param search study id
	  * @return return the form page to upload report
	  */
	@GetMapping("management/study_report_upload")
	public String study_report_upload(Model model, @RequestParam(required=false) String search) {	
		List<Study> liste = studyImpl.getAll();

		model.addAttribute("liste", liste);
		return "study_management/study_report_upload";
	}
	
	/**
	 * post method to upload a study report
	 * @param typ type of study
	 * @param responsible name of responsible
	 * @param name  report name
	 * @param ra contains file
	 * @param file file to upload
	 * @param study study id
	 * @return return page to download stuy report
	 */
	@RequestMapping(value = "/reportupload/save",  method = RequestMethod.POST,
			headers = ("content-type=multipart/*"))
	public String reportupload(@RequestParam(value = "report_type") String typ,
			@RequestParam(value = "responsible") String responsible,
			@RequestParam(value = "report_name") String name,
			final RedirectAttributes ra, 
			@RequestParam("joint_file") MultipartFile file,
			@RequestParam(value = "study") Long study) {

		   try {
		  
		   
		   FileData fd = new FileData();
		   Long filename=  ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
		   String newfilename= filename+"."+fd.getFileExtension(file.getOriginalFilename());
		   storageService.store(file, newfilename); 
		   
		   Report report = new Report(); 
		   report.setName(name);
		   report.setTyp(typ);
		   report.setResponsible(responsible);
		   report.setFile(newfilename);
		   
		   Study s = studyImpl.get(study);
		   report.setStudyId(s);
		   
		   reportImpl.save(report);
		   
		  
		   
	       ra.addFlashAttribute("successFlash", "Upload Report successful.");
	       
		   } catch (Exception e) {
			   ra.addFlashAttribute("errorFlash", e.getMessage());
			 System.out.println(e);
		   }
		   
		   return "redirect:/management/study_report_download";

	}
	
/**
 * get method to show all questioner
 * @param model object Model
 * @param search questioner id
 * @return return page to view all questioner
 */
	@GetMapping("management/questioner_view_list")
	public String questioner_view_list(Model model, @RequestParam(required=false) String search) {	
		String s="";
		List<Questionner> liste = questionnerImpl.getAll();
		if(search!=null && !search.isEmpty()) {
			liste=questionnerImpl.findByFirstNameContainingOrQuestionnerIdContaining(search, search);
			s=search;
		}
		model.addAttribute("list", liste);
		model.addAttribute("search", s);

			return "study_management/questioner_view_list";
		}

	

	/**
	 * get method to show the answering
	 * @param model object Model
	 * @param search firstname or lastname of answering
	 * @return return page to show answering
	 */
	@GetMapping("management/answering_view_list")
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
		return "study_management/answering_view_list";
	}
	
	/**
	 * get method to show study
	 * @param model object Model
	 * @param search study id
	 * @return return page to view all study
	 */
	@GetMapping("management/study_view_list")
	public String study_view_list(Model model, @RequestParam(required=false) String search) {		
		List<Study> liste = studyImpl.getAll();
		String s="";
		if(search!=null && !search.isEmpty()) {
			liste=studyImpl.findByStudyNameContainingOrStudyIdContaining(search,search);
			s=search;
		}
		//System.out.println(liste); 
        model.addAttribute("list", liste);
        model.addAttribute("search", s);
		return "study_management/study_view_list";
		}
	
	/**
	 * get method to view study
	 * @param id study id
	 * @param model object Model
	 * @return return page to show study
	 */
	@GetMapping("management/study_view")
	public String study_view(@RequestParam("id") Optional<Long> id, Model model) {	
		
		 if(!id.isPresent()) {
			 return "redirect:/direction/study_view_list"; 
		 }
		Study data = studyImpl.get(id.get());
		List<Participation> parti = partImpl.findByStudy(data);
		List<Deployment> deploy = deployImpl.findByStudy(data);
		model.addAttribute("data", data);
		model.addAttribute("participation", parti);
		model.addAttribute("deploy", deploy);
			return "study_management/study_view";
		}

}
