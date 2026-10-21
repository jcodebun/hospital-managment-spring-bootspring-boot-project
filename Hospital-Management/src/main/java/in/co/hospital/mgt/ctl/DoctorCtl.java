package in.co.hospital.mgt.ctl;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import in.co.hospital.mgt.dto.UserDTO;
import in.co.hospital.mgt.exception.DuplicateRecordException;
import in.co.hospital.mgt.form.DoctorForm;
import in.co.hospital.mgt.service.UserServiceInt;




@Controller
public class DoctorCtl extends BaseCtl {

	@Autowired
	private UserServiceInt service;
	
	@ModelAttribute
	public void preload(Model model) {
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("Male", "Male");
		map2.put("Female", "Female");
		model.addAttribute("gender", map2);
	}

	@GetMapping("/home/login/doctor")
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") DoctorForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			UserDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "doctor";
	}

	@PostMapping("/home/login/doctor")
	public String submit(@Valid @ModelAttribute("form") DoctorForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/doctor";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "doctor";
				}
				UserDTO bean = (UserDTO) populateDTO(form.getDTO(),request);
				bean.setRoleId(3L);
				bean.setRoleName("DOCTOR");
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Doctor update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Doctor Added Successfully!!!!");
				}
				return "doctor";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "doctor";
		}
		return "";
	}

	@RequestMapping(value = "/home/login/doctor/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") DoctorForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/doctor/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/home/login/doctor";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					UserDTO dto = new UserDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		UserDTO dto = (UserDTO) form.getDTO();

		UserDTO uDto = (UserDTO) session.getAttribute("user");
		
		dto.setRoleId(3L);
		List<UserDTO> list = service.search(dto, pageNo, pageSize);
		List<UserDTO> totallist = service.search(dto);
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
		return "doctorList";
	}
	

}
