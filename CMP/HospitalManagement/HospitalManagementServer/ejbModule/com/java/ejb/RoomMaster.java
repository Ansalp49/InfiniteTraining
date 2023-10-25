package com.java.ejb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "room_master")
public class RoomMaster implements Serializable {

	@Id
	@Column(name = "room_no")
	private String roomNo;

	@Column(name = "room_type")
	private String roomType;

	@Column(name = "status")
	private String status;

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomType() {
		return roomType;
	}

	@Override
	public String toString() {
		return "RoomMaster [roomNo=" + roomNo + ", roomType=" + roomType + ", status=" + status + "]";
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RoomMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomMaster(String roomNo, String roomType, String status) {
		super();
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.status = status;
	}

}
