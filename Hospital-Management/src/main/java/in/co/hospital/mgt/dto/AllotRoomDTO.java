package in.co.hospital.mgt.dto;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ALLOT")
@Getter
@Setter
public class AllotRoomDTO extends BaseDTO {
	
	@ManyToOne
	@JoinColumn(name="ROOM",nullable = false)
	private RoomDTO room;
	private long roomId;
	
	@ManyToOne
	@JoinColumn(name="DOCTOR",nullable = false)
	private UserDTO doctor;
	private long doctorId;
	
	@ManyToOne
	@JoinColumn(name="PATINET",nullable = false)
	private PatientDTO patient;
	private long patientId;
	
	

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
