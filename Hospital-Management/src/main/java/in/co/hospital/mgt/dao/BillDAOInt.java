package in.co.hospital.mgt.dao;

import java.util.List;

import in.co.hospital.mgt.dto.BillDTO;


public interface BillDAOInt {

	public long add(BillDTO dto);
	
	public void delete(BillDTO dto);
	
	public BillDTO findBypk(long pk);
	
	public BillDTO findByName(String name);
	
	public void update(BillDTO dto);
	
	public List<BillDTO> list();
	
	public List<BillDTO>list(int pageNo,int pageSize);
	
	public List<BillDTO> search(BillDTO dto);
	
	public List<BillDTO> search(BillDTO dto,int pageNo,int pageSize);
	
	
}
