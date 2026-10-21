package in.co.hospital.mgt.form;


import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import in.co.hospital.mgt.dto.BaseDTO;
import in.co.hospital.mgt.dto.PatientSummeryDTO;
import in.co.hospital.mgt.util.DataUtility;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientSummeryForm extends BaseForm {
	
	
	@NotEmpty(message ="Name is required")
	private String name;
	@NotEmpty(message ="Age is required")
	private String age;
	@NotEmpty(message ="Gender is required")
	private String gender;
	@NotEmpty(message ="Diseaed is required")
	private String diseaed;
	@NotEmpty(message ="Appointment Date is required")
	private String aDate;
	
	private MultipartFile reportFile;

	@Override
	public BaseDTO getDTO() {
		PatientSummeryDTO bean=new PatientSummeryDTO();
		bean.setId(id);
		bean.setName(name);
		bean.setAge(age);
		bean.setGender(gender);
		bean.setDiseaed(diseaed);
		bean.setADate(DataUtility.getDate(aDate));
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		PatientSummeryDTO bean=(PatientSummeryDTO)bDto;
		id=bean.getId();
		name=bean.getName();
		age=bean.getAge();
		gender=bean.getGender();
		diseaed=bean.getDiseaed();
		aDate=DataUtility.getDateString(bean.getADate());
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}
