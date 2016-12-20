package com.zcc.oauth2.web.changer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/formchange")
public class ChangerApplicationController {
	
	@RequestMapping("/form")
	public String changeForm(){
		
		return "form";
	}
	
	@RequestMapping("/formweb")
	public String changeFormWeb(){
		
		return "form_web";
	}
	@RequestMapping("/formapp")
	public String changFormApp(){
		
		return "form_app";
	}
}
