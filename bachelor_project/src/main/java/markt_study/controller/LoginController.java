package markt_study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	/**
	 * get method to call login page with "/"
	 * @return return the index page
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String index(){
		return "index";
	}
	
	/**
	 * get method to call login page with "login"
	 * @return
	 */
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(){
		return "index";
	}
	
	
}
