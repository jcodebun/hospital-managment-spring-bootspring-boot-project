package in.co.hospital.mgt.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.co.hospital.mgt.dto.PatientSummeryDTO;
import in.co.hospital.mgt.exception.DuplicateRecordException;


public interface PatientSummeryServiceInt {

	public long add(PatientSummeryDTO dto) throws DuplicateRecordException;

	public void delete(PatientSummeryDTO dto);

	public PatientSummeryDTO findBypk(long pk);

	public PatientSummeryDTO findByName(String name);

	public void update(PatientSummeryDTO dto) throws DuplicateRecordException;

	public List<PatientSummeryDTO> list();

	public List<PatientSummeryDTO> list(int pageNo, int pageSize);

	public List<PatientSummeryDTO> search(PatientSummeryDTO dto);

	public List<PatientSummeryDTO> search(PatientSummeryDTO dto, int pageNo, int pageSize);
	
	public Blob getFileById(long id) throws SerialException, SQLException;

}
