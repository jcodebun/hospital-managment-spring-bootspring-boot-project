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

import in.co.hospital.mgt.dao.RoomDAOInt;
import in.co.hospital.mgt.dto.RoomDTO;
import in.co.hospital.mgt.exception.DuplicateRecordException;
import in.co.hospital.mgt.util.EmailBuilder;


@Service
public class RoomServiceImpl implements RoomServiceInt {

	private static Logger log = Logger.getLogger(RoomServiceImpl.class.getName());

	@Autowired
	private RoomDAOInt dao;


	@Override
	@Transactional
	public long add(RoomDTO dto) throws DuplicateRecordException {
		log.info("RoomServiceImpl Add method start");
		RoomDTO existDTO = dao.findByRoomNo(dto.getRoomNo());
		if (existDTO != null)
			throw new DuplicateRecordException("Room Is Already Exist");
		long pk = dao.add(dto);
		log.info("RoomServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(RoomDTO dto) {
		log.info("RoomServiceImpl Delete method start");
		dao.delete(dto);
		log.info("RoomServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public RoomDTO findBypk(long pk) {
		log.info("RoomServiceImpl findBypk method start");
		RoomDTO dto = dao.findBypk(pk);
		log.info("RoomServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public RoomDTO findByRoomNo(String roomNo) {
		log.info("RoomServiceImpl findByRoomName method start");
		RoomDTO dto = dao.findByRoomNo(roomNo);
		log.info("RoomServiceImpl findByRoomName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(RoomDTO dto) throws DuplicateRecordException {
		log.info("RoomServiceImpl update method start");
		RoomDTO existDTO = dao.findByRoomNo(dto.getRoomNo());
		if (existDTO != null && dto.getId() != existDTO.getId())
			throw new DuplicateRecordException("Room Is Already Exist");
		dao.update(dto);
		log.info("RoomServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<RoomDTO> list() {
		log.info("RoomServiceImpl list method start");
		List<RoomDTO> list = dao.list();
		log.info("RoomServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<RoomDTO> list(int pageNo, int pageSize) {
		log.info("RoomServiceImpl list method start");
		List<RoomDTO> list = dao.list(pageNo, pageSize);
		log.info("RoomServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<RoomDTO> search(RoomDTO dto) {
		log.info("RoomServiceImpl search method start");
		List<RoomDTO> list = dao.search(dto);
		log.info("RoomServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<RoomDTO> search(RoomDTO dto, int pageNo, int pageSize) {
		log.info("RoomServiceImpl search method start");
		List<RoomDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("RoomServiceImpl search method end");
		return list;
	}


}
