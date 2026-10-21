package in.co.hospital.mgt.form;

import javax.validation.constraints.NotEmpty;

import in.co.hospital.mgt.dto.BaseDTO;
import in.co.hospital.mgt.dto.RoomDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomForm extends BaseForm {
	
	@NotEmpty(message ="RoomNo is required")
	private String roomNo;
	@NotEmpty(message ="Type is required")
	private String type;
	@NotEmpty(message ="Price is required")
	private String price;

	@Override
	public BaseDTO getDTO() {
		RoomDTO dto=new RoomDTO();
		dto.setId(id);
		dto.setRoomNo(roomNo);
		dto.setType(type);
		dto.setPrice(price);
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		dto.setCreatedDatetime(createdDateTime);
		dto.setModifiedDatetime(modifiedDateTime);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		RoomDTO bean=(RoomDTO)bDto;
		id=bean.getId();
		roomNo=bean.getRoomNo();
		type=bean.getType();
		price=bean.getPrice();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
		
	}

}
