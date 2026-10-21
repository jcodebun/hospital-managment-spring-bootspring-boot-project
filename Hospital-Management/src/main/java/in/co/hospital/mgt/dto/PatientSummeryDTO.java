package in.co.hospital.mgt.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="PATIENT_SUMMERY")
@Getter
@Setter
public class PatientSummeryDTO extends BaseDTO {

	@Column(name="DOCTOR_ID")
	private long doctorId;
	
	@Column(name="DOCTOR_NAME",length = 225)
	private String doctorName;
	
	@Column(name="NAME",length = 255)
	private String name;
	
	@Column(name="AGE",length = 255)
	private String age;
	
	@Column(name="GENDER",length = 255)
	private String gender;
	
	@Column(name="DISEAED",length = 255)
	private String diseaed;
	
	@Column(name="REPORT_FILE",columnDefinition = "LONGBLOB")
	private byte[] reportFile;
	
	@Column(name="A_DATE")
	@Temporal(TemporalType.DATE)
	private Date aDate;
	


	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

}
