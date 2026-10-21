package in.co.hospital.mgt.service;

import java.util.List;

import in.co.hospital.mgt.dto.AppointmentDTO;
import in.co.hospital.mgt.exception.DuplicateRecordException;


public interface AppointmentServiceInt {

	public long add(AppointmentDTO dto) throws DuplicateRecordException;

	public void delete(AppointmentDTO dto);

	public AppointmentDTO findBypk(long pk);

	public AppointmentDTO findByName(String name);

	public void update(AppointmentDTO dto) throws DuplicateRecordException;

	public List<AppointmentDTO> list();

	public List<AppointmentDTO> list(int pageNo, int pageSize);

	public List<AppointmentDTO> search(AppointmentDTO dto);

	public List<AppointmentDTO> search(AppointmentDTO dto, int pageNo, int pageSize);

}
