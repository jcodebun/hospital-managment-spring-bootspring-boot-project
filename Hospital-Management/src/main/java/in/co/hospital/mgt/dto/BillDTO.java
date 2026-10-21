package in.co.hospital.mgt.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="BILL")
@Getter
@Setter
public class BillDTO extends BaseDTO {
	
	
	private long patientId;
	@Column(name="DOCTOR_NAME",length = 225)
	private String doctorName;
	@Column(name="AMOUNT",length = 225)
	private String amount;
	@Column(name="DESCRIPTION",length = 225)
	private String description;
	private long userId;
	@Column(name="DATE")
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name="STATUS",length = 225)
	private String status;

	@ManyToOne
	@JoinColumn(name="PATINET",nullable = false)
	private PatientDTO patient;
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
