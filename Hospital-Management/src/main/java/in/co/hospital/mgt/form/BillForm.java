package in.co.hospital.mgt.form;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import in.co.hospital.mgt.dto.AllotRoomDTO;
import in.co.hospital.mgt.dto.AppointmentDTO;
import in.co.hospital.mgt.dto.BaseDTO;
import in.co.hospital.mgt.dto.BillDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillForm extends BaseForm {
	

	@Min(value = 1,message = "Patient  is required")
	private long patientId;
	@NotEmpty(message = "Amount is required")
	private String amount;
	@NotEmpty(message = "Description is required")
	private String description;

	@Override
	public BaseDTO getDTO() {
		BillDTO bean=new BillDTO();
		bean.setId(id);
		bean.setPatientId(patientId);
		bean.setAmount(amount);
		bean.setDescription(description);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		BillDTO bean=(BillDTO)bDto;
		id=bean.getId();
		amount=bean.getAmount();
		description=bean.getDescription();
		patientId=bean.getPatientId();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}
