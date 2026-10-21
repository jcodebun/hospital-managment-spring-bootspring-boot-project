package in.co.hospital.mgt.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="APPOINTMENT")
@Getter
@Setter
public class AppointmentDTO extends BaseDTO {
	
	@Column(name="FIRST_NAME",length = 225)
	private String firstName;
	@Column(name="LAST_NAME",length = 225)
	private String lastName;
	@Column(name="DOCTOR_NAME",length = 225)
	private String doctorName;
	@Column(name="DISEASE",length = 225)
	private String disease;
	@Column(name="TIME",length = 225)
	private String time;
	@Column(name="APP_DATE",length = 225)
	private Date appDate;
	@Column(name="MOBILE_NO",length = 225)
	private String mobileNo;
	@Column(name="ADDRESS",length = 225)
	private String address;
	@Column(name="DOCTOR_ID",length = 225)
	private long doctorId;
	@Column(name="USER_ID",length = 225)
	private long userId;
	@Column(name="STATUS",length = 225)
	private String status;

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

}
