package in.co.hospital.mgt.form;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.util.unit.DataUnit;

import in.co.hospital.mgt.dto.BaseDTO;
import in.co.hospital.mgt.dto.PatientDTO;
import in.co.hospital.mgt.dto.UserDTO;
import in.co.hospital.mgt.util.DataUtility;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	private String firstName;
	
	@NotEmpty(message = "LastName is required")
	private String lastName;
	
	@NotEmpty(message = "Email is required")
	private String email;
	
	@NotEmpty(message = "Mobile No is required")
	private String mobileNo;
	
	@NotEmpty(message = "Gender is required")
	private String gender;
	
	@NotEmpty(message = "Address is required")
	private String address;
	
	@NotEmpty(message = "City is required")
	private String city;

	@NotEmpty(message ="Allergy is required")
	private String allergy;
	
	@NotEmpty(message ="DOB is required")
	private String dob;
	
	@Min(value = 1,message = "Doctor is required")
	private long doctorId;

	@Override
	public BaseDTO getDTO() {
		PatientDTO bean=new PatientDTO();
		bean.setId(id);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setEmailId(email);
		bean.setMobileNo(mobileNo);
		bean.setDoctorId(doctorId);
		bean.setGender(gender);
		bean.setAddress(address);
		bean.setCity(city);
		bean.setAllergy(allergy);
		bean.setDob(DataUtility.getDate(dob));
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}
	@Override
	public void populate(BaseDTO baseBean) {
		PatientDTO bean=(PatientDTO)baseBean;
		id=bean.getId();
		firstName=bean.getFirstName();
		doctorId=bean.getDoctorId();
		lastName=bean.getLastName();
		email=bean.getEmailId();
		mobileNo=bean.getMobileNo();
		gender=bean.getGender();
		address=bean.getAddress();
		allergy=bean.getAllergy();
		dob=DataUtility.getDateString(bean.getDob());
		city=bean.getCity();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}
}
