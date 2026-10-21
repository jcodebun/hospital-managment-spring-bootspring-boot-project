package in.co.hospital.mgt.service;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.hospital.mgt.dao.AppointmentDAOInt;
import in.co.hospital.mgt.dto.AppointmentDTO;
import in.co.hospital.mgt.exception.DuplicateRecordException;
import in.co.hospital.mgt.util.EmailBuilder;


@Service
public class AppointmentServiceImpl implements AppointmentServiceInt {

	private static Logger log = Logger.getLogger(AppointmentServiceImpl.class.getName());

	@Autowired
	private AppointmentDAOInt dao;


	@Override
	@Transactional
	public long add(AppointmentDTO dto) throws DuplicateRecordException {
		log.info("AppointmentServiceImpl Add method start");
		/* AppointmentDTO existDTO = dao.findByName(dto.getName()); */
		/*
		 * if (existDTO != null) throw new
		 * DuplicateRecordException("Name is Already Exist");
		 */
		long pk = dao.add(dto); 
		log.info("AppointmentServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(AppointmentDTO dto) {
		log.info("AppointmentServiceImpl Delete method start");
		dao.delete(dto);
		log.info("AppointmentServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public AppointmentDTO findBypk(long pk) {
		log.info("AppointmentServiceImpl findBypk method start");
		AppointmentDTO dto = dao.findBypk(pk);
		log.info("AppointmentServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public AppointmentDTO findByName(String name) {
		log.info("AppointmentServiceImpl findByAppointmentName method start");
		AppointmentDTO dto = dao.findByName(name);
		log.info("AppointmentServiceImpl findByAppointmentName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(AppointmentDTO dto) throws DuplicateRecordException {
		log.info("AppointmentServiceImpl update method start");
		/*
		 * AppointmentDTO existDTO = dao.findByName(dto.getName()); if (existDTO != null
		 * && dto.getId() != existDTO.getId()) throw new
		 * DuplicateRecordException("Appointment Is Already Exist");
		 */
		dao.update(dto);
		log.info("AppointmentServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<AppointmentDTO> list() {
		log.info("AppointmentServiceImpl list method start");
		List<AppointmentDTO> list = dao.list();
		log.info("AppointmentServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<AppointmentDTO> list(int pageNo, int pageSize) {
		log.info("AppointmentServiceImpl list method start");
		List<AppointmentDTO> list = dao.list(pageNo, pageSize);
		log.info("AppointmentServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<AppointmentDTO> search(AppointmentDTO dto) {
		log.info("AppointmentServiceImpl search method start");
		List<AppointmentDTO> list = dao.search(dto);
		log.info("AppointmentServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<AppointmentDTO> search(AppointmentDTO dto, int pageNo, int pageSize) {
		log.info("AppointmentServiceImpl search method start");
		List<AppointmentDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("AppointmentServiceImpl search method end");
		return list;
	}


}
