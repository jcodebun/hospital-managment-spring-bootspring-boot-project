package in.co.hospital.mgt.ctl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.co.hospital.mgt.dto.AppointmentDTO;
import in.co.hospital.mgt.dto.UserDTO;
import in.co.hospital.mgt.exception.DuplicateRecordException;
import in.co.hospital.mgt.form.AppointmentForm;
import in.co.hospital.mgt.service.AppointmentServiceInt;
import in.co.hospital.mgt.service.UserServiceInt;




@Controller
public class AppointmentCtl extends BaseCtl {

	@Autowired
	private AppointmentServiceInt service;
	
	@Autowired
	private UserServiceInt userService;
	
	@ModelAttribute
	public void preload(Model model) {
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("Male", "Male");
		map2.put("Female", "Female");
		model.addAttribute("gender", map2);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Cancel", "Cancel");
		map.put("Pending", "Pending");
		map.put("Confirmed", "Confirmed");
		model.addAttribute("status", map);
		
		UserDTO uDto=new UserDTO();
		uDto.setRoleId(3L);
		model.addAttribute("doctorList",userService.search(uDto));
		
	}

	@GetMapping("/home/login/appointment")
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") AppointmentForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			AppointmentDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "appointment";
	}

	@PostMapping("/home/login/appointment")
	public String submit(@Valid @ModelAttribute("form") AppointmentForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/appointment";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "appointment";
				}
				AppointmentDTO bean = (AppointmentDTO) populateDTO(form.getDTO(),request);
				UserDTO uDto=userService.findBypk(bean.getDoctorId());
				bean.setDoctorName(uDto.getFirstName()+" "+uDto.getLastName());
				UserDTO usDto=(UserDTO)session.getAttribute("user");
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Appointment update Successfully!!!!");
				} else {
					bean.setUserId(usDto.getId());
					bean.setStatus("Pending");
					service.add(bean);
					model.addAttribute("success", "Appointment Added Successfully!!!!");
				}
				return "appointment";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "appointment";
		}
		return "";
	}

	@RequestMapping(value = "/home/login/appointment/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") AppointmentForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/appointment/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/home/login/appointment";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					AppointmentDTO dto = new AppointmentDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		AppointmentDTO dto = (AppointmentDTO) form.getDTO();
		
		UserDTO uDto=(UserDTO)session.getAttribute("user");
		if(uDto.getRoleId()==3) {
			dto.setDoctorId(uDto.getId());
		}
		
		List<AppointmentDTO> list = service.search(dto, pageNo, pageSize);
		List<AppointmentDTO> totallist = service.search(dto);
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
		}

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return "appointmentList";
	}
	

}
