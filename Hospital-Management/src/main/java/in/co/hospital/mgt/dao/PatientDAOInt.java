package in.co.hospital.mgt.dao;

import java.util.List;

import in.co.hospital.mgt.dto.PatientDTO;


public interface PatientDAOInt {

	public long add(PatientDTO dto);
	
	public void delete(PatientDTO dto);
	
	public PatientDTO findBypk(long pk);
	
	public PatientDTO findByName(String name);
	
	public void update(PatientDTO dto);
	
	public List<PatientDTO> list();
	
	public List<PatientDTO>list(int pageNo,int pageSize);
	
	public List<PatientDTO> search(PatientDTO dto);
	
	public List<PatientDTO> search(PatientDTO dto,int pageNo,int pageSize);
	
	
}
