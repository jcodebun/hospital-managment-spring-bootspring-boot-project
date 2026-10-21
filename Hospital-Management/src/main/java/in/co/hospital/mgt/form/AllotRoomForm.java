package in.co.hospital.mgt.form;

import javax.validation.constraints.Min;

import in.co.hospital.mgt.dto.AllotRoomDTO;
import in.co.hospital.mgt.dto.AppointmentDTO;
import in.co.hospital.mgt.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllotRoomForm extends BaseForm {
	
	@Min(value = 1,message = "Room is required")
	private long roomId;
	@Min(value = 1,message = "Doctor is required")
	private long doctorId;
	@Min(value = 1,message = "Patient  is required")
	private long patientId;

	@Override
	public BaseDTO getDTO() {
		AllotRoomDTO bean=new AllotRoomDTO();
		bean.setId(id);
		bean.setRoomId(roomId);
		bean.setDoctorId(doctorId);
		bean.setPatientId(patientId);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		AllotRoomDTO bean=(AllotRoomDTO)bDto;
		id=bean.getId();
		roomId=bean.getRoomId();
		doctorId=bean.getDoctorId();
		patientId=bean.getPatientId();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}
