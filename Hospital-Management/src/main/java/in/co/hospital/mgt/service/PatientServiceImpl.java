package in.co.hospital.mgt.service;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.hospital.mgt.dao.PatientDAOInt;
import in.co.hospital.mgt.dto.PatientDTO;
import in.co.hospital.mgt.exception.DuplicateRecordException;


@Service
public class PatientServiceImpl implements PatientServiceInt {

	private static Logger log = Logger.getLogger(PatientServiceImpl.class.getName());

	@Autowired
	private PatientDAOInt dao;


	@Override
	@Transactional
	public long add(PatientDTO dto) throws DuplicateRecordException {
		log.info("PatientServiceImpl Add method start");
		/*
		 * PatientDTO existDTO = dao.findByName(dto.getName()); if (existDTO != null)
		 * throw new DuplicateRecordException("Name is Already Exist");
		 */
		long pk = dao.add(dto); 
		log.info("PatientServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(PatientDTO dto) {
		log.info("PatientServiceImpl Delete method start");
		dao.delete(dto);
		log.info("PatientServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public PatientDTO findBypk(long pk) {
		log.info("PatientServiceImpl findBypk method start");
		PatientDTO dto = dao.findBypk(pk);
		log.info("PatientServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public PatientDTO findByName(String name) {
		log.info("PatientServiceImpl findByPatientName method start");
		PatientDTO dto = dao.findByName(name);
		log.info("PatientServiceImpl findByPatientName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(PatientDTO dto) throws DuplicateRecordException {
		log.info("PatientServiceImpl update method start");
		/*
		 * PatientDTO existDTO = dao.findByName(dto.getName()); if (existDTO != null &&
		 * dto.getId() != existDTO.getId()) throw new
		 * DuplicateRecordException("Patient Is Already Exist");
		 */
		dao.update(dto);
		log.info("PatientServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<PatientDTO> list() {
		log.info("PatientServiceImpl list method start");
		List<PatientDTO> list = dao.list();
		log.info("PatientServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<PatientDTO> list(int pageNo, int pageSize) {
		log.info("PatientServiceImpl list method start");
		List<PatientDTO> list = dao.list(pageNo, pageSize);
		log.info("PatientServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<PatientDTO> search(PatientDTO dto) {
		log.info("PatientServiceImpl search method start");
		List<PatientDTO> list = dao.search(dto);
		log.info("PatientServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<PatientDTO> search(PatientDTO dto, int pageNo, int pageSize) {
		log.info("PatientServiceImpl search method start");
		List<PatientDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("PatientServiceImpl search method end");
		return list;
	}


}
