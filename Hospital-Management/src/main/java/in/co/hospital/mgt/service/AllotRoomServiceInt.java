package in.co.hospital.mgt.service;

import java.util.List;

import in.co.hospital.mgt.dto.AllotRoomDTO;
import in.co.hospital.mgt.exception.DuplicateRecordException;


public interface AllotRoomServiceInt {

	public long add(AllotRoomDTO dto) throws DuplicateRecordException;

	public void delete(AllotRoomDTO dto);

	public AllotRoomDTO findBypk(long pk);

	public AllotRoomDTO findByName(String name);

	public void update(AllotRoomDTO dto) throws DuplicateRecordException;

	public List<AllotRoomDTO> list();

	public List<AllotRoomDTO> list(int pageNo, int pageSize);

	public List<AllotRoomDTO> search(AllotRoomDTO dto);

	public List<AllotRoomDTO> search(AllotRoomDTO dto, int pageNo, int pageSize);

}
