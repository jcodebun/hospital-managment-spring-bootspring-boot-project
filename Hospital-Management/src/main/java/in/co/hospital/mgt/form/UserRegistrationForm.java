package in.co.hospital.mgt.form;

import javax.validation.constraints.NotEmpty;

import in.co.hospital.mgt.dto.BaseDTO;
import in.co.hospital.mgt.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	private String firstName;
	
	@NotEmpty(message = "LastName is required")
	private String lastName;
	
	@NotEmpty(message = "Email is required")
	private String email;
	
	@NotEmpty(message = "Login Id  is required")
	private String login;
	
	@NotEmpty(message = "Password is required")
	private String password;
	
	@NotEmpty(message = "Confirm Password is required")
	private String confirmPassword;
	
	@NotEmpty(message = "Mobile No is required")
	private String mobileNo;
	
	@NotEmpty(message = "Gender is required")
	private String gender;
	
	
	

	@Override
	public BaseDTO getDTO() {
		UserDTO bean=new UserDTO();
		bean.setId(id);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setEmailId(email);
		bean.setPassword(password);
		bean.setMobileNo(mobileNo);
		bean.setGender(gender);
		bean.setLogin(login);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}
	@Override
	public void populate(BaseDTO baseBean) {
		UserDTO bean=(UserDTO)baseBean;
		id=bean.getId();
		firstName=bean.getFirstName();
		lastName=bean.getLastName();
		email=bean.getEmailId();
		password=bean.getPassword();
		mobileNo=bean.getMobileNo();
		gender=bean.getGender();
		login=bean.getLogin();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}
}
