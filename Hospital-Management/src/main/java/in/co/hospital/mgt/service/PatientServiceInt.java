package in.co.hospital.mgt.service;

import java.util.List;

import in.co.hospital.mgt.dto.PatientDTO;
import in.co.hospital.mgt.exception.DuplicateRecordException;


public interface PatientServiceInt {

	public long add(PatientDTO dto) throws DuplicateRecordException;

	public void delete(PatientDTO dto);

	public PatientDTO findBypk(long pk);

	public PatientDTO findByName(String name);

	public void update(PatientDTO dto) throws DuplicateRecordException;

	public List<PatientDTO> list();

	public List<PatientDTO> list(int pageNo, int pageSize);

	public List<PatientDTO> search(PatientDTO dto);

	public List<PatientDTO> search(PatientDTO dto, int pageNo, int pageSize);

}
