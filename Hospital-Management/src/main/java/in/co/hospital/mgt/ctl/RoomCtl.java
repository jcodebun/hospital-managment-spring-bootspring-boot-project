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

import in.co.hospital.mgt.dto.RoomDTO;
import in.co.hospital.mgt.exception.DuplicateRecordException;
import in.co.hospital.mgt.form.RoomForm;
import in.co.hospital.mgt.service.RoomServiceInt;




@Controller
public class RoomCtl extends BaseCtl {

	@Autowired
	private RoomServiceInt service;
	
	@ModelAttribute
	public void preload(Model model) {
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("Genral", "Genral");
		map2.put("Private", "Private");
		map2.put("ICU", "ICU");
		model.addAttribute("roomType", map2);
	}

	@GetMapping("/home/login/room")
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") RoomForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			RoomDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "room";
	}

	@PostMapping("/home/login/room")
	public String submit(@Valid @ModelAttribute("form") RoomForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/room";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "room";
				}
				RoomDTO bean = (RoomDTO) populateDTO(form.getDTO(),request);
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Room update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Room Added Successfully!!!!");
				}
				return "room";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "room";
		}
		return "";
	}

	@RequestMapping(value = "/home/login/room/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") RoomForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/room/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/home/login/room";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					RoomDTO dto = new RoomDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		RoomDTO dto = (RoomDTO) form.getDTO();

		RoomDTO uDto = (RoomDTO) session.getAttribute("Room");
		
		List<RoomDTO> list = service.search(dto, pageNo, pageSize);
		List<RoomDTO> totallist = service.search(dto);
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
		return "roomList";
	}
	

}
