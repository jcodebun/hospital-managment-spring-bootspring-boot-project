package in.co.hospital.mgt.form;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.util.unit.DataUnit;

import in.co.hospital.mgt.dto.AppointmentDTO;
import in.co.hospital.mgt.dto.BaseDTO;
import in.co.hospital.mgt.util.DataUtility;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentForm  extends BaseForm {
	
	@NotEmpty(message = "First Name is required")
	private String firstName;
	@NotEmpty(message = "Last Name is required")
	private String lastName;
	@NotEmpty(message = "Disease is required")
	private String disease;
	@NotEmpty(message = "Time is required")
	private String time;
	@NotEmpty(message = "Appointment Date is required")
	private String appDate;
	@NotEmpty(message = "Mobile No is required")
	private String mobileNo;
	@NotEmpty(message = "Address is required")
	private String address;
	@Min(value = 1,message = "Doctor Name is required")
	private long doctorId;
	
	private String status;
	
	@Override
	public BaseDTO getDTO() {
		AppointmentDTO bean=new AppointmentDTO();
		bean.setId(id);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setDisease(disease);
		bean.setTime(time);
		bean.setAppDate(DataUtility.getDate(appDate));
		bean.setMobileNo(mobileNo);
		bean.setAddress(address);
		bean.setDoctorId(doctorId);
		bean.setStatus(status);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		AppointmentDTO bean=(AppointmentDTO)bDto;
		id=bean.getId();
		firstName=bean.getFirstName();
		lastName=bean.getLastName();
		disease=bean.getDisease();
		time=bean.getTime();
		appDate=DataUtility.getDateString(bean.getAppDate());
		mobileNo=bean.getMobileNo();
		address=bean.getAddress();
		doctorId=bean.getDoctorId();
		status=bean.getStatus();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
		
	}

}
