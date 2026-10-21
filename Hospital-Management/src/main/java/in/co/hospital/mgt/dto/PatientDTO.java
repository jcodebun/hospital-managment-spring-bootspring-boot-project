package in.co.hospital.mgt.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="PATINET")
@Getter
@Setter
public class PatientDTO extends BaseDTO {

	@Column(name="FIRST_NAME",length = 225)
	private String firstName;
	@Column(name="LAST_NAME",length = 225)
	private String lastName;
	@Column(name="DOB",length = 225)
	@Temporal(TemporalType.DATE)
	private Date dob;
	@Column(name="MOBILE_NO",length = 225)
	private String mobileNo;
	@Column(name="GENDER",length = 225)
	private String gender;

	@Column(name="ADDRESS",length = 225)
	private String address;
	@Column(name="CITY",length = 225)
	private String city;
	
	@Column(name="EMAIL_ID",length = 225)
	private String emailId;
	@Column(name="ALLERGY",length = 225)
	private String allergy;
	
	@Column(name="DOCTOR_ID")
	private long doctorId;
	@Column(name="DOCTOR_NAME",length = 225)
	private String doctorName;
	
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "patient")
	private Set<AllotRoomDTO> allots;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "patient")
	private Set<BillDTO> bills;

	
	@Override
	public String getKey() {
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		return firstName+" "+lastName;
	}

}
