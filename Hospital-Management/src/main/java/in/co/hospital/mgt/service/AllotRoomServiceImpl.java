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

import in.co.hospital.mgt.dao.AllotRoomDAOInt;
import in.co.hospital.mgt.dto.AllotRoomDTO;
import in.co.hospital.mgt.exception.DuplicateRecordException;
import in.co.hospital.mgt.util.EmailBuilder;


@Service
public class AllotRoomServiceImpl implements AllotRoomServiceInt {

	private static Logger log = Logger.getLogger(AllotRoomServiceImpl.class.getName());

	@Autowired
	private AllotRoomDAOInt dao;


	@Override
	@Transactional
	public long add(AllotRoomDTO dto) throws DuplicateRecordException {
		log.info("AllotRoomServiceImpl Add method start");
		/* AllotRoomDTO existDTO = dao.findByName(dto.getName()); */
		/*
		 * if (existDTO != null) throw new
		 * DuplicateRecordException("Name is Already Exist");
		 */
		long pk = dao.add(dto); 
		log.info("AllotRoomServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(AllotRoomDTO dto) {
		log.info("AllotRoomServiceImpl Delete method start");
		dao.delete(dto);
		log.info("AllotRoomServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public AllotRoomDTO findBypk(long pk) {
		log.info("AllotRoomServiceImpl findBypk method start");
		AllotRoomDTO dto = dao.findBypk(pk);
		log.info("AllotRoomServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public AllotRoomDTO findByName(String name) {
		log.info("AllotRoomServiceImpl findByAllotRoomName method start");
		AllotRoomDTO dto = dao.findByName(name);
		log.info("AllotRoomServiceImpl findByAllotRoomName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(AllotRoomDTO dto) throws DuplicateRecordException {
		log.info("AllotRoomServiceImpl update method start");
		/*
		 * AllotRoomDTO existDTO = dao.findByName(dto.getName()); if (existDTO != null
		 * && dto.getId() != existDTO.getId()) throw new
		 * DuplicateRecordException("AllotRoom Is Already Exist");
		 */
		dao.update(dto);
		log.info("AllotRoomServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<AllotRoomDTO> list() {
		log.info("AllotRoomServiceImpl list method start");
		List<AllotRoomDTO> list = dao.list();
		log.info("AllotRoomServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<AllotRoomDTO> list(int pageNo, int pageSize) {
		log.info("AllotRoomServiceImpl list method start");
		List<AllotRoomDTO> list = dao.list(pageNo, pageSize);
		log.info("AllotRoomServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<AllotRoomDTO> search(AllotRoomDTO dto) {
		log.info("AllotRoomServiceImpl search method start");
		List<AllotRoomDTO> list = dao.search(dto);
		log.info("AllotRoomServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<AllotRoomDTO> search(AllotRoomDTO dto, int pageNo, int pageSize) {
		log.info("AllotRoomServiceImpl search method start");
		List<AllotRoomDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("AllotRoomServiceImpl search method end");
		return list;
	}


}
