package markt_study.controller;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.lowagie.text.DocumentException;

import markt_study.answering.AnswImpl;
import markt_study.answering.Answering;
import markt_study.client.Client;
import markt_study.client.ClientImpl;
import markt_study.configuration.PdfService;
import markt_study.deployment.DeployImpl;
import markt_study.deployment.Deployment;
import markt_study.login.Role;
import markt_study.login.RoleImpl;
import markt_study.login.User;
import markt_study.login.UserImpl;
import markt_study.participation.Participation;
import markt_study.participation.ParticipationImpl;
import markt_study.questionner.QuestImpl;
import markt_study.questionner.Questionner;
import markt_study.report.Report;
import markt_study.report.ReportImpl;
import markt_study.storage.FileData;
import markt_study.storage.StorageService;
import markt_study.study.Study;
import markt_study.study.StudyImpl;

@Controller
public class DirectionController {
	@Autowired
	private AnswImpl answImpl;
	
	@Autowired
	private StudyImpl studyImpl;
	
	@Autowired
	private ClientImpl clientImpl;

	@Autowired
	private ParticipationImpl partImpl;
	
	@Autowired
	private DeployImpl deployImpl;
	
	@Autowired
	private UserImpl userImpl;
	

	@Autowired
	private RoleImpl roleImpl;
	
	@Autowired
	private  PdfService pdfService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ReportImpl reportImpl;
	
	@Autowired
    StorageService storageService;
	
	@Autowired
	private QuestImpl questImpl;
	
	
	/**
	 * Get method for the home page of admin
	 * @return name of the html page
	 */
	@GetMapping("all/home_admin")  
	public String home_admin() {		
			return "all/home_admin";
		}
	
	/**
	 * get method for the home page of direction
	 * @return name of the html page
	 */
	@GetMapping("direction/home_direction")  
	public String home_direction() {		
			return "direction/home_direction";
		}
	
	/**
	 * get method for the form to regist a client
	 * @return  name of the html page
	 */
	@GetMapping("direction/client_form_regist")
	public String client_form_regist() {
		
			return "direction/client_form_regist";
		}
	
	/**
	 * post method to save a new user
	 * @param roles role of user
	 * @param username name of user
	 * @param password password of user
	 * @param ra variable to contain the confirm message
	 * @return return name of save page of user
	 */
	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public String saveUser(Long roles, String username, String password, final RedirectAttributes ra) {
		   try {
			   User u =new User();
			   Role r = roleImpl.get(roles);
			   Set<Role> rol =new HashSet<>();
			   rol.add(r);
			   
			   u.setUsername(username);
			   u.setPassword(passwordEncoder.encode(password)); 
			   u.setEnabled(true);
			   u.setRoles(rol);
			   //System.out.print(roles);
		   User save = userImpl.save(u);
	       ra.addFlashAttribute("successFlash", "User Created successful");
	       return "redirect:/all/user_view_list";
		   } catch (DataIntegrityViolationException e) {
			   ra.addFlashAttribute("errorFlash", "User id aldready exist.");
			 return "redirect:/all/user_view_list";
		   }

	}
     
	/**
	 * post method to save a new client
	 * @param client object of type client
	 * @param ra confirm message
	 * @return return name of html page to save client
	 */
	@RequestMapping(value = "/client/save", method = RequestMethod.POST)
	public String saveClient(Client client, final RedirectAttributes ra) {
		   try {
		   Client save = clientImpl.save(client);
	       ra.addFlashAttribute("successFlash", "Client successfully registered.");
	       return "redirect:/direction/client_view_list";
		   } catch (DataIntegrityViolationException e) {
			   ra.addFlashAttribute("errorFlash", "Client aldready exist.");
			 return "redirect:/direction/client_form_regist";
		   }

	}
     	
	
	/**
	 * get method to show all user
	 * @return preview page of user
	 */
	@GetMapping("all/user_view_list")
	public String user_view_list(Model model, @RequestParam(required=false) String search) {		
		List<User> liste = userImpl.getAll();
		String s="";
		/*if(search!=null && !search.isEmpty()) {
			liste=userImpl.findByNameContaining(search);
			s=search;
		}*/
		System.out.println(liste.size()); 
        model.addAttribute("list", liste);		
        model.addAttribute("search", s);	
		
		return "all/user_view_list";
    }
	
	/**
	 * get method to show the form to regist au user
	 * @return return form page to regist a user
	 */
	@GetMapping("all/user_form_regist")
	public String user_form_regist() {		
		
		return "all/user_form_regist";
    }
	
	
	/**
	 * get method to show a client
	 * @return name of html page to view a client
	 */
	@GetMapping("direction/client_view_list")
	public String client_view_list(Model model, @RequestParam(required=false) String search) {		
		List<Client> liste = clientImpl.getAll();
		String s="";
		if(search!=null && !search.isEmpty()) {
			liste=clientImpl.findByNameContaining(search);
			s=search;
		}
		System.out.println(liste); 
        model.addAttribute("list", liste);		
        model.addAttribute("search", s);	
		
		return "direction/client_view_list";
    }
	
	
	/**
	 * get method to show one client
	 * @return return page to to show client
	 */
	@GetMapping("direction/client_view")
	public String client_view(@RequestParam Optional<Long> id, Model model) {	
		
		if(!id.isPresent()) {
			 return "redirect:/direction/client_view_list"; 
		 }
		Client data = clientImpl.get(id.get());
		model.addAttribute("row", data);
		model.addAttribute("studiesSize", data.getStudies().size());
		
	    return "direction/client_view";
    }
	
	
	/**
	 * get method to call regist form a study
	 * @param model object of Model type
	 * @return return form page to save a study
	 */
	@GetMapping("direction/study_form_regist")
	public String study_form_regist(Model model) {	
		List<Client> liste = clientImpl.getAll();
		 model.addAttribute("clients", liste);
		return "direction/study_form_regist";
		}
	
	/**
	 * post method to save a new study
	 * @param study object of type Study
	 * @param ra  variable to  contains confirm message 
	 * @param client client id
	 * @return return form page to save a study
	 */
	@RequestMapping(value = "/study/save",  method = RequestMethod.POST)
	public String save(Study study, final RedirectAttributes ra, @RequestParam(value = "client") Long client) {
		   try {
		   study.setClientId(clientImpl.get(client));  
		   Study save = studyImpl.save(study);
	       ra.addFlashAttribute("successFlash", "Study successfully regitered.");
	       return "redirect:/direction/study_view_list";
		   } catch (DataIntegrityViolationException e) {
			   ra.addFlashAttribute("errorFlash", "Study id aldready exist.");
			 return "redirect:/direction/study_form_regist";
		   }

	} 

	/**
	 * get mapping to show one study
	 * @param model Model object
	 * @param search study id as string
	 * @return return page to show a study
	 */
	@GetMapping("direction/study_view_list")
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
		return "direction/study_view_list";
	
	}
	
	/**
	 * get method to show  study with questioner and answering
	 * @param id study id
	 * @param model object Model
	 * @return return page to view study with all questioner and answering
	 */
	@GetMapping("direction/study_view")
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
			return "direction/study_view";
		}
	
	/**
	 * get mapping to generate pdf file of study view 
	 * @param id 
	 * @param response  http response
	 */
	 @GetMapping("/study/download-pdf")
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
	  * get mapping to study report
	  * @param model 
	  * @param search study id
	  * @return return page to download study report
	  */
	 @GetMapping("direction/study_report_download")
		public String study_report_download(Model model, @RequestParam(required=false) String search) {	
				List<Report> liste = reportImpl.getAll();
				
				
				String s="";
				if(search!=null && !search.isEmpty()) {
					liste=reportImpl.findByStudyIdContaining(search);
					s=search;
				}
				
				
				model.addAttribute("list", liste);
				model.addAttribute("search", s);
				return "direction/study_report_download";
		}
	 
	 /**
	  * get method to save a file
	  * @param filename name of file
	  * @return response
	  */
	 @GetMapping("/download_/{filename:.+}")
	 @ResponseBody
	    public ResponseEntity<Resource>downloadFile(@PathVariable String filename) {

	        Resource resource = storageService.loadAsResource(filename);

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION,
	                        "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	 }
	
	 /**
	  * get method to upload report
	  * @param model
	  * @param search study id
	  * @return return page to upload report
	  */
	@GetMapping("direction/study_report_upload")
	public String study_report_upload(Model model, @RequestParam(required=false) String search) {	
		List<Study> liste = studyImpl.getAll();
		//Study data = new Study();
		
		model.addAttribute("liste", liste);
		return "direction/study_report_upload";
	}
	
	/**
	 * post method to save report
	 * @param typ type of report
	 * @param responsible name of responsible
	 * @param name name of report
	 * @param ra
	 * @param file file to upload
	 * @param study study id
	 * @return return return page to download report
	 */
	@RequestMapping(value = "/reportupload/save_",  method = RequestMethod.POST,
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
		   
		   return "redirect:/direction/study_report_download";

	}
	
	/**
	 * get method the show questioner
	 * @param model
	 * @param search lastname or firstname of questioner
	 * @return return page to show questioner
	 */
	@GetMapping("direction/questioner_view_list")
	public String questioner_view_list(Model model, @RequestParam(required=false) String search) {	
		String s="";
		List<Questionner> liste = questImpl.getAll();
		if(search!=null && !search.isEmpty()) {
			liste=questImpl.findByFirstNameContainingOrQuestionnerIdContaining(search, search);
			s=search;
		}
		model.addAttribute("list", liste);
		model.addAttribute("search", s);

			return "direction/questioner_view_list";
		}

	

	/**
	 * get method show answering
	 * @param model
	 * @param search
	 * @return return page to show answering
	 */
	@GetMapping("direction/answering_view_list")
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
		return "direction/answering_view_list";
	}
}
