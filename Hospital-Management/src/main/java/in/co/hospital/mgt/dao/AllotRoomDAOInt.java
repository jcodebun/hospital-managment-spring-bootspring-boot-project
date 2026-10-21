package in.co.hospital.mgt.dao;

import java.util.List;

import in.co.hospital.mgt.dto.AllotRoomDTO;


public interface AllotRoomDAOInt {

	public long add(AllotRoomDTO dto);
	
	public void delete(AllotRoomDTO dto);
	
	public AllotRoomDTO findBypk(long pk);
	
	public AllotRoomDTO findByName(String name);
	
	public void update(AllotRoomDTO dto);
	
	public List<AllotRoomDTO> list();
	
	public List<AllotRoomDTO>list(int pageNo,int pageSize);
	
	public List<AllotRoomDTO> search(AllotRoomDTO dto);
	
	public List<AllotRoomDTO> search(AllotRoomDTO dto,int pageNo,int pageSize);
	
	
}
