package in.co.hospital.mgt.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/home","/"})
public class WelcomeCtl {

	@GetMapping()
	public String display() {
		return "home";
	}
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}
	
	@GetMapping("/aboutUs")
	public String aboutUs() {
		return "aboutUs";
	}
	
	@GetMapping("/contactUs")
	public String contactUs() {
		return "contactUs";
	}
	
}
