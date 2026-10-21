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

import in.co.hospital.mgt.dto.AllotRoomDTO;
import in.co.hospital.mgt.dto.UserDTO;
import in.co.hospital.mgt.exception.DuplicateRecordException;
import in.co.hospital.mgt.form.AllotRoomForm;
import in.co.hospital.mgt.service.AllotRoomServiceInt;
import in.co.hospital.mgt.service.PatientServiceInt;
import in.co.hospital.mgt.service.RoomServiceInt;
import in.co.hospital.mgt.service.UserServiceInt;




@Controller
public class AllotRoomCtl extends BaseCtl {

	@Autowired
	private AllotRoomServiceInt service;
	
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

	@GetMapping("/home/login/allotRoom")
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") AllotRoomForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			AllotRoomDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "allotRoom";
	}

	@PostMapping("/home/login/allotRoom")
	public String submit(@Valid @ModelAttribute("form") AllotRoomForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/allotRoom";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "allotRoom";
				}
				AllotRoomDTO bean = (AllotRoomDTO) populateDTO(form.getDTO(),request);
				UserDTO uDto=userService.findBypk(bean.getDoctorId());
				bean.setDoctor(uDto);
				bean.setRoom(roomService.findBypk(bean.getRoomId()));
				bean.setPatient(patientService.findBypk(bean.getPatientId()));
				UserDTO usDto=(UserDTO)session.getAttribute("user");
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "AllotRoom update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "AllotRoom Added Successfully!!!!");
				}
				return "allotRoom";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "allotRoom";
		}
		return "";
	}

	@RequestMapping(value = "/home/login/allotRoom/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") AllotRoomForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/allotRoom/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/home/login/allotRoom";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					AllotRoomDTO dto = new AllotRoomDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		AllotRoomDTO dto = (AllotRoomDTO) form.getDTO();

		
		List<AllotRoomDTO> list = service.search(dto, pageNo, pageSize);
		List<AllotRoomDTO> totallist = service.search(dto);
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
		return "allotRoomList";
	}
	

}
