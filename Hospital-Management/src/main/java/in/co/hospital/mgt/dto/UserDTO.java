package in.co.hospital.mgt.dto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="USER")
@Setter
@Getter
public class UserDTO extends BaseDTO {

	@Column(name = "FIRST_NAME",length = 225)
	private String firstName;
	
	@Column(name = "LAST_NAME",length = 225)
	private String lastName;
	
	@Column(name = "LOGIN_ID",length = 225)
	private String login;

	@Column(name = "PASSWORD",length = 225)
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@Column(name = "DOB")
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Column(name = "MOBILE_NO",length = 225)
	private String mobileNo;
	
	@Column(name = "ROLE_ID",length = 225)
	private long roleId;
	
	@Column(name = "GENDER",length = 225)
	private String gender;
	
	@Column(name = "AGE",length = 225)
	private String age;
	
	@Column(name = "SPECIALIZATION",length = 225)
	private String spcialization;
	
	@Column(name = "ADDRESS",length = 225)
	private String address;
	
	@Column(name = "CITY",length = 225)
	private String city;
	
	@Column(name = "QUALIFICATION",length = 225)
	private String Qualification;
	
	@Column(name = "EMAIL_ID",length = 225)
	private String emailId;
	
	@Column(name = "ROLE_NAME",length = 225)
	private String roleName;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "doctor")
	private Set<AllotRoomDTO> allots;
	

	public String getKey() {
		return String.valueOf(id);
	}

	public String getValue() {

		return firstName + " " + lastName;
	}
}
