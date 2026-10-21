package in.co.hospital.mgt.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ROOM")
@Getter
@Setter
public class RoomDTO extends BaseDTO {

	@Column(name="TYPE",length = 225)
	private String type;
	@Column(name="ROOM_NO",length = 225)
	private String roomNo;
	@Column(name="PRICE",length = 225)
	private String price;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "room")
	private Set<AllotRoomDTO> allots;
	
	
	@Override
	public String getKey() {
		return String.valueOf(id);
	}
	@Override
	public String getValue() {
		return roomNo;
	}
	
}
