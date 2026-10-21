package in.co.hospital.mgt.dao;

import java.util.List;

import in.co.hospital.mgt.dto.AppointmentDTO;


public interface AppointmentDAOInt {

	public long add(AppointmentDTO dto);
	
	public void delete(AppointmentDTO dto);
	
	public AppointmentDTO findBypk(long pk);
	
	public AppointmentDTO findByName(String name);
	
	public void update(AppointmentDTO dto);
	
	public List<AppointmentDTO> list();
	
	public List<AppointmentDTO>list(int pageNo,int pageSize);
	
	public List<AppointmentDTO> search(AppointmentDTO dto);
	
	public List<AppointmentDTO> search(AppointmentDTO dto,int pageNo,int pageSize);
	
	
}
