package in.co.hospital.mgt.service;

import java.util.List;

import in.co.hospital.mgt.dto.BillDTO;
import in.co.hospital.mgt.exception.DuplicateRecordException;


public interface BillServiceInt {

	public long add(BillDTO dto) throws DuplicateRecordException;

	public void delete(BillDTO dto);

	public BillDTO findBypk(long pk);

	public BillDTO findByName(String name);

	public void update(BillDTO dto) throws DuplicateRecordException;

	public List<BillDTO> list();

	public List<BillDTO> list(int pageNo, int pageSize);

	public List<BillDTO> search(BillDTO dto);

	public List<BillDTO> search(BillDTO dto, int pageNo, int pageSize);

}
