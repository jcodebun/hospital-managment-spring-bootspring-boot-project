package in.co.hospital.mgt.ctl;

import java.util.Date;
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

import in.co.hospital.mgt.dto.BillDTO;
import in.co.hospital.mgt.dto.PatientDTO;
import in.co.hospital.mgt.dto.UserDTO;
import in.co.hospital.mgt.exception.DuplicateRecordException;
import in.co.hospital.mgt.form.BillForm;
import in.co.hospital.mgt.service.BillServiceInt;
import in.co.hospital.mgt.service.PatientServiceInt;
import in.co.hospital.mgt.service.RoomServiceInt;
import in.co.hospital.mgt.service.UserServiceInt;




@Controller
public class BillCtl extends BaseCtl {

	@Autowired
	private BillServiceInt service;
	
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
	
		model.addAttribute("patientList",patientService.search(null));
		
	}

	@GetMapping("/home/login/bill")
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") BillForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			BillDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "bill";
	}

	@PostMapping("/home/login/bill")
	public String submit(@Valid @ModelAttribute("form") BillForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/bill";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "bill";
				}
				BillDTO bean = (BillDTO) populateDTO(form.getDTO(),request);
				PatientDTO pDto=patientService.findBypk(bean.getPatientId());
				bean.setPatient(pDto);
				bean.setDoctorName(pDto.getDoctorName());
				UserDTO usDto=(UserDTO)session.getAttribute("user");
				bean.setUserId(usDto.getId());
				bean.setDate(new Date());
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Bill update Successfully!!!!");
				} else {
					bean.setStatus("Pendeing");
					service.add(bean);
					model.addAttribute("success", "Bill Added Successfully!!!!");
				}
				return "bill";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "bill";
		}
		return "";
	}

	@RequestMapping(value = "/home/login/bill/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") BillForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/bill/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/home/login/bill";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					BillDTO dto = new BillDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		BillDTO dto = (BillDTO) form.getDTO();

		
		List<BillDTO> list = service.search(dto, pageNo, pageSize);
		List<BillDTO> totallist = service.search(dto);
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
		return "billList";
	}
	

}
