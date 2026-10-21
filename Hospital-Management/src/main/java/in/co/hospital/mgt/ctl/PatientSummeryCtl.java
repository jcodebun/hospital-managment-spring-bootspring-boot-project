package in.co.hospital.mgt.ctl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.co.hospital.mgt.dto.PatientSummeryDTO;
import in.co.hospital.mgt.dto.UserDTO;
import in.co.hospital.mgt.exception.DuplicateRecordException;
import in.co.hospital.mgt.form.PatientSummeryForm;
import in.co.hospital.mgt.service.PatientServiceInt;
import in.co.hospital.mgt.service.PatientSummeryServiceInt;
import in.co.hospital.mgt.service.RoomServiceInt;
import in.co.hospital.mgt.service.UserServiceInt;




@Controller
public class PatientSummeryCtl extends BaseCtl {

	@Autowired
	private PatientSummeryServiceInt service;
	
	@Autowired
	private UserServiceInt userService;
	
	@Autowired
	private RoomServiceInt roomService;
	
	@Autowired
	private PatientServiceInt patientService; 
	
	
	
	@ModelAttribute
	public void preload(Model model) {
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("Male", "Male");
		map2.put("Female", "Female");
		model.addAttribute("gender", map2);
		
		UserDTO uDto=new UserDTO();
		uDto.setRoleId(3L);
		model.addAttribute("doctorList",userService.search(uDto));
		model.addAttribute("roomList",roomService.search(null));
		model.addAttribute("patientList",patientService.search(null));
		
	}

	@GetMapping("/home/login/patientSummery")
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") PatientSummeryForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			PatientSummeryDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "patientSummery";
	}

	@PostMapping("/home/login/patientSummery")
	public String submit(@RequestParam("reportFile") MultipartFile file,@Valid @ModelAttribute("form") PatientSummeryForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/patientSummery";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "patientSummery";
				}
				PatientSummeryDTO bean = (PatientSummeryDTO) populateDTO(form.getDTO(),request);
				UserDTO usDto=(UserDTO)session.getAttribute("user");
				bean.setReportFile(file.getBytes());
				bean.setDoctorId(usDto.getId());
				bean.setDoctorName(usDto.getFirstName()+" "+usDto.getLastName());
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "PatientSummery update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "PatientSummery Added Successfully!!!!");
				}
				return "patientSummery";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "patientSummery";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value = "/home/login/patientSummery/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") PatientSummeryForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/patientSummery/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/home/login/patientSummery";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					PatientSummeryDTO dto = new PatientSummeryDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		PatientSummeryDTO dto = (PatientSummeryDTO) form.getDTO();

		
		
		List<PatientSummeryDTO> list = service.search(dto, pageNo, pageSize);
		List<PatientSummeryDTO> totallist = service.search(dto);
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
		return "patientSummeryList";
	}
	
	@GetMapping("/ctl/patientSummery/getImage/{id}")
	public void getStudentPhoto(HttpServletResponse response, @PathVariable("id") long id) throws Exception {
		response.setContentType("text/pdf");
		Blob blb=service.getFileById(id);
		byte[] bytes = blb.getBytes(1, (int) blb.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}
	

}
