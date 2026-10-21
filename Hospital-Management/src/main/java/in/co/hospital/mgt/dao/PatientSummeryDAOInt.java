package in.co.hospital.mgt.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.co.hospital.mgt.dto.PatientSummeryDTO;


public interface PatientSummeryDAOInt {

	public long add(PatientSummeryDTO dto);
	
	public void delete(PatientSummeryDTO dto);
	
	public PatientSummeryDTO findBypk(long pk);
	
	public PatientSummeryDTO findByName(String name);
	
	public void update(PatientSummeryDTO dto);
	
	public List<PatientSummeryDTO> list();
	
	public List<PatientSummeryDTO>list(int pageNo,int pageSize);
	
	public List<PatientSummeryDTO> search(PatientSummeryDTO dto);
	
	public List<PatientSummeryDTO> search(PatientSummeryDTO dto,int pageNo,int pageSize);
	
	public Blob getFileById(long id) throws SerialException, SQLException;
	
	
}
