package in.co.hospital.mgt.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.hospital.mgt.dao.PatientSummeryDAOInt;
import in.co.hospital.mgt.dto.PatientSummeryDTO;
import in.co.hospital.mgt.exception.DuplicateRecordException;


@Service
public class PatientSummeryServiceImpl implements PatientSummeryServiceInt {

	private static Logger log = Logger.getLogger(PatientSummeryServiceImpl.class.getName());

	@Autowired
	private PatientSummeryDAOInt dao;


	@Override
	@Transactional
	public long add(PatientSummeryDTO dto) throws DuplicateRecordException {
		log.info("PatientSummeryServiceImpl Add method start");
		/* PatientSummeryDTO existDTO = dao.findByName(dto.getName()); */
		/*
		 * if (existDTO != null) throw new
		 * DuplicateRecordException("Name is Already Exist");
		 */
		long pk = dao.add(dto); 
		log.info("PatientSummeryServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(PatientSummeryDTO dto) {
		log.info("PatientSummeryServiceImpl Delete method start");
		dao.delete(dto);
		log.info("PatientSummeryServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public PatientSummeryDTO findBypk(long pk) {
		log.info("PatientSummeryServiceImpl findBypk method start");
		PatientSummeryDTO dto = dao.findBypk(pk);
		log.info("PatientSummeryServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public PatientSummeryDTO findByName(String name) {
		log.info("PatientSummeryServiceImpl findByPatientSummeryName method start");
		PatientSummeryDTO dto = dao.findByName(name);
		log.info("PatientSummeryServiceImpl findByPatientSummeryName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(PatientSummeryDTO dto) throws DuplicateRecordException {
		log.info("PatientSummeryServiceImpl update method start");
		/*
		 * PatientSummeryDTO existDTO = dao.findByName(dto.getName()); if (existDTO != null
		 * && dto.getId() != existDTO.getId()) throw new
		 * DuplicateRecordException("PatientSummery Is Already Exist");
		 */
		dao.update(dto);
		log.info("PatientSummeryServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<PatientSummeryDTO> list() {
		log.info("PatientSummeryServiceImpl list method start");
		List<PatientSummeryDTO> list = dao.list();
		log.info("PatientSummeryServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<PatientSummeryDTO> list(int pageNo, int pageSize) {
		log.info("PatientSummeryServiceImpl list method start");
		List<PatientSummeryDTO> list = dao.list(pageNo, pageSize);
		log.info("PatientSummeryServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<PatientSummeryDTO> search(PatientSummeryDTO dto) {
		log.info("PatientSummeryServiceImpl search method start");
		List<PatientSummeryDTO> list = dao.search(dto);
		log.info("PatientSummeryServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<PatientSummeryDTO> search(PatientSummeryDTO dto, int pageNo, int pageSize) {
		log.info("PatientSummeryServiceImpl search method start");
		List<PatientSummeryDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("PatientSummeryServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public Blob getFileById(long id) throws SerialException, SQLException {
		return dao.getFileById(id);
	}

}
